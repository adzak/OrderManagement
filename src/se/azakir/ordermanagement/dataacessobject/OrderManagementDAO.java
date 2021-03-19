package se.azakir.ordermanagement.dataacessobject;

import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.order.Order;
import se.azakir.ordermanagement.model.order.OrderSearchResult;

import java.util.ArrayList;
import java.util.Optional;

public class OrderManagementDAO {

    private static OrderManagementDAO instance;
    private ArrayList<Customer> customers;

    private OrderManagementDAO() {
        customers = new ArrayList<Customer>();
    }

    public static OrderManagementDAO getInstance() {
        if (instance == null)
            instance = new OrderManagementDAO();

        return instance;
    }

    /**
     * Creates a customer
     *
     * @param name name of the customer
     * @param type type of the customer
     */
    public void createCustomer(String name, int type) {
        customers.add(new Customer(name, type));
    }

    /**
     * Fetches customer
     *
     * @param name name of customer
     * @return Optional which contains costumer if found
     */
    public Optional<Customer> getCustomer(String name) {
        Optional<Customer> optionalCustomer;
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                optionalCustomer = Optional.of(customer);

                return optionalCustomer;
            }
        }
        optionalCustomer = Optional.empty();

        return optionalCustomer;
    }

    /**
     * Gets order and customer for specific ordernumber
     *
     * @param orderNumber the ordernumber for the order
     * @return Optional which contains order and customer if found
     */
    public Optional<OrderSearchResult> getOrder(int orderNumber) {
        Optional<OrderSearchResult> optionalOrder;
        for (Customer customer : this.customers) {
            ArrayList<Order> orders = customer.getOrders();
            for (Order order : orders) {
                if (order.getOrderNumber() == orderNumber) {
                    optionalOrder = Optional.of(new OrderSearchResult(customer, order));
                    return optionalOrder;
                }
            }

        }
        optionalOrder = Optional.empty();

        return optionalOrder;
    }


    public void removeOrder(Order order, Customer customer) {
        customer.getOrders().remove(order);
    }
}
