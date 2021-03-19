package se.azakir.ordermanagement.model.order;

import se.azakir.ordermanagement.model.customer.Customer;

public class OrderSearchResult {

    private Customer customer;
    private Order order;

    public OrderSearchResult(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order getOrder() {
        return order;
    }
}
