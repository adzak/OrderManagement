package se.azakir.ordermanagement.model.calculator;

import se.azakir.ordermanagement.model.article.Cycle;
import se.azakir.ordermanagement.model.order.Order;
import se.azakir.ordermanagement.model.order.OrderRow;

import java.util.Optional;

public class OrderGiftChecker {

    private Order order;

    public OrderGiftChecker(Order order) {
        this.order = order;
    }

    /**
     * Checks for reedemable gift articles in an order
     *
     * @return Optional orderrow if order is has reedemable gift article
     */
    public Optional<OrderRow> checkForReedemableGiftArticle() {
        double totalAmount = this.order.getTotalAmount();
        Optional<OrderRow> optionalOrderRow;
        if (totalAmount > 10000) {
            OrderRow cycleRow = new OrderRow(new Cycle(), 1);
            optionalOrderRow = Optional.of(cycleRow);

            return optionalOrderRow;
        }
        optionalOrderRow = Optional.empty();

        return optionalOrderRow;
    }

}
