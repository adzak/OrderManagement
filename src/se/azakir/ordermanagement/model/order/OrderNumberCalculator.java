package se.azakir.ordermanagement.model.order;

import se.azakir.ordermanagement.dataacessobject.OrderManagementDAO;
import se.azakir.ordermanagement.model.customer.Customer;

import java.util.ArrayList;

public class OrderNumberCalculator {

    private int currentOrderNumber;
    private static OrderNumberCalculator instance;

    private OrderNumberCalculator() {
        this.currentOrderNumber = 1;
    }

    public static OrderNumberCalculator getInstance() {
        if (instance == null)
            instance = new OrderNumberCalculator();

        return instance;
    }

    /**
     * Generates an ordernumber
     *
     * @return an ordernumber
     */
    public int getOrderNumber() {
        return currentOrderNumber++;
    }

}
