package se.azakir.ordermanagement.model.calculator;

import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.customer.CustomerTypes;
import se.azakir.ordermanagement.model.order.Order;
import se.azakir.ordermanagement.model.order.OrderRow;

import java.util.*;

public class OrderCalculator {

    private Order order;
    private Customer customer;

    private static final int PRICE_PER_ARTICLE = 0;
    private static final int DISCOUNT_ROW_AMOUNT = 1;
    private static final int SUMMARY_ROW_AMOUNT = 2;

    public OrderCalculator(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }
    /**
     * Summarizes and calculates the cost for the order
     */
    public void summarizeOrder() {
        double totalAmountOfDiscount = 0;
        double totalAmount = 0;

        for (OrderRow orderRow : this.order.getOrderRows()) {
            List<Double> calculatedOrderRow = calculateOrderRow(orderRow);
            double rowDiscountAmount = calculatedOrderRow.get(DISCOUNT_ROW_AMOUNT);
            double summaryRowAmount = calculatedOrderRow.get(SUMMARY_ROW_AMOUNT);

            totalAmountOfDiscount += rowDiscountAmount;
            totalAmount += summaryRowAmount;
            orderRow.setPricePerArticle(calculatedOrderRow.get(PRICE_PER_ARTICLE));
            orderRow.setDiscountRowAmount(rowDiscountAmount);
            orderRow.setSummaryRowAmount(summaryRowAmount);
        }

        order.setTotalAmount(totalAmount);
        order.setTotalDiscountAmount(totalAmountOfDiscount);

        cullCurrentGiftArticles(); //Remove gift articles if editing order

        Optional<OrderRow> optionalOrderRow = this.checkForGiftArticle();
        if (optionalOrderRow.isPresent()) {
            order.addOrderRow(optionalOrderRow.get());
        }

    }

    /**
     * Calculates total discount and total cost for an orderrow
     *
     * @param orderRow orderrow to be calculated
     * @return list which contains the price per article, total discount and total cost for the orderrow
     */
    private List<Double> calculateOrderRow(OrderRow orderRow) {
        double pricePerArticle = orderRow.getArticle().getPrice();
        double amountOfArticle = orderRow.getAmount();

        double totalSummary = pricePerArticle * amountOfArticle;
        double currentSummary = totalSummary;

        Optional<HashMap<Integer, Double>> availableDiscountsOptional = orderRow.getArticle().availableDiscounts();

        if (availableDiscountsOptional.isPresent()) {
            HashMap<Integer, Double> availableDiscount = availableDiscountsOptional.get();

            int customerType = this.customer.getType();
            if (customerType == CustomerTypes.PRIVATE && availableDiscount.containsKey(CustomerTypes.PRIVATE)) {
                Double multiplier = availableDiscount.get(CustomerTypes.PRIVATE);
                currentSummary *= multiplier;
            }

            if (customerType > 1 && availableDiscount.containsKey(CustomerTypes.COMPANY)) {
                Double multiplier = availableDiscount.get(CustomerTypes.COMPANY);
                currentSummary *= multiplier;
            }

            if (customerType == CustomerTypes.SMALL_COMPANY && availableDiscount.containsKey(CustomerTypes.SMALL_COMPANY)) {
                Double multiplier = availableDiscount.get(CustomerTypes.SMALL_COMPANY);
                currentSummary *= multiplier;
            }

            if (customerType == CustomerTypes.BIG_COMPANY && availableDiscount.containsKey(CustomerTypes.BIG_COMPANY)) {
                Double multiplier = availableDiscount.get(CustomerTypes.BIG_COMPANY);
                currentSummary *= multiplier;
            }

        }

        return Arrays.asList(currentSummary / amountOfArticle, totalSummary - currentSummary, currentSummary);

    }

    /**
     * Checks for reedemable gift articles
     *
     * @return Optional orderrow with reedemable gift article
     */
    private Optional<OrderRow> checkForGiftArticle() {
       OrderGiftChecker orderGiftChecker = new OrderGiftChecker(order);
       return orderGiftChecker.checkForReedemableGiftArticle();
    }

    /**
     * Remove gift articles that already exist in order
     */
    private void cullCurrentGiftArticles(){
       ArrayList<OrderRow> orderRows =  this.order.getOrderRows();
       for(int i = 0; i < orderRows.size(); i++){
         OrderRow orderRow =  orderRows.get(i);
         if(orderRow.getArticle().getPrice() == 0){
             orderRows.remove(i);
         }
       }
    }
}
