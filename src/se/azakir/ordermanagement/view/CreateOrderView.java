package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;
import se.azakir.ordermanagement.model.article.Article;
import se.azakir.ordermanagement.model.calculator.OrderCalculator;
import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.order.Order;
import se.azakir.ordermanagement.model.order.OrderRow;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CreateOrderView {

    private Scanner scanner;

    public CreateOrderView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the create order view. User creates an order from this view.
     */
    public void createOrder() {
        Controller controller = Controller.getInstance();
        ArrayList<Article> articles = controller.getArticles();
        Order order = controller.createOrder();
        while (true) {
            System.out.println("Välj artikel:");
            int humanPosition = 1;
            for (int i = 0; i < articles.size(); i++) {
                Article article = articles.get(i);
                String articleString = String.format("%-10s%-10s", article.getName(), "[" + article.getPrice() + "]");
                System.out.println(humanPosition + "." + articleString);
                humanPosition++;
            }

            int selectedArticle = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ange antal önskade artiklar:");
            int selectedAmountOfArticles = scanner.nextInt();
            scanner.nextLine();

            boolean appendedToExistingRow = false;
            for(OrderRow orderRow : order.getOrderRows()){
                if(orderRow.getArticle().getName().equals(articles.get(selectedArticle - 1).getName())){
                    int newAmount = orderRow.getAmount() + selectedAmountOfArticles;
                    orderRow.setAmount(newAmount);
                    appendedToExistingRow = true;
                }
            }

            if(!appendedToExistingRow){
                OrderRow orderRow = new OrderRow(articles.get(selectedArticle - 1), selectedAmountOfArticles);
                order.addOrderRow(orderRow);
            }

            System.out.println("Lägg till ny rad?");
            System.out.println("1. Ja");
            System.out.println("2. Nej");

            int selectedForwardOption = scanner.nextInt();
            scanner.nextLine();

            if (selectedForwardOption == 1) {
                continue;
            } else if (selectedForwardOption == 2) {
                while (true) {
                    System.out.println("Ange kund för denna order:");
                    String searchCustomer = scanner.nextLine();
                    Optional<Customer> searchCustomerOptional = controller.searchForCustomer(searchCustomer);
                    if (searchCustomerOptional.isPresent()) {
                        Customer customer = searchCustomerOptional.get();
                        OrderCalculator orderCalculator = new OrderCalculator(order, customer);
                        orderCalculator.summarizeOrder();
                        order.setOrderNumber(controller.createOrderNumber());
                        System.out.println(order);
                        System.out.println("Beställ order?");
                        System.out.println("1. Ja");
                        System.out.println("2. Avbryt order");
                        int finalizeOrder = this.scanner.nextInt();
                        this.scanner.nextLine();
                        if (finalizeOrder == 1) {
                            customer.addOrder(order);
                            System.out.println("Order beställd!");
                            return;
                        } else if (finalizeOrder == 2) {
                            return;
                        }
                    } else {
                        System.out.println("Kunde ej hitta kund. Vill du försöka igen?");
                        System.out.println("1. Ja");
                        System.out.println("2. Nej (Avbryt order)");
                        int tryAgain = this.scanner.nextInt();
                        this.scanner.nextLine();
                        if (tryAgain == 1) {
                            continue;
                        } else if (tryAgain == 2) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
