package se.azakir.ordermanagement.model.customer;

import se.azakir.ordermanagement.model.order.Order;

import java.util.ArrayList;

public class Customer {

    private String name;
    private int type;
    private ArrayList<Order> orders;

    public Customer(String name, int type) {
        this.name = name;
        this.type = type;
        this.orders = new ArrayList<Order>();
    }

    /**
     * Sets the customer to the appropriate type
     *
     * @param type type of the customer
     */
    private void setCustomerType(int type) {
        switch (type) {
            case 1:
                this.type = CustomerTypes.PRIVATE;
                break;
            case 2:
                this.type = CustomerTypes.SMALL_COMPANY;
                break;
            case 3:
                this.type = CustomerTypes.BIG_COMPANY;
                break;
        }
    }

    /**
     * Adds an order to a customer
     *
     * @param order order to be added
     */
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.type;
    }
}
