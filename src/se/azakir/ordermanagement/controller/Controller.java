package se.azakir.ordermanagement.controller;

import se.azakir.ordermanagement.model.article.ArticleManager;
import se.azakir.ordermanagement.dataacessobject.OrderManagementDAO;
import se.azakir.ordermanagement.model.article.Article;
import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.order.Order;
import se.azakir.ordermanagement.model.order.OrderNumberCalculator;
import se.azakir.ordermanagement.model.order.OrderSearchResult;
import se.azakir.ordermanagement.view.CreateCustomerView;
import se.azakir.ordermanagement.view.CreateOrderView;
import se.azakir.ordermanagement.view.GetCustomerView;
import se.azakir.ordermanagement.view.GetOrderView;

import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    private static Controller instance;
    private OrderManagementDAO orderManagementDAO;
    private static final int CREATE_CUSTOMER = 1;
    private static final int GET_CUSTOMER = 2;
    private static final int CREATE_ORDER = 3;
    private static final int GET_ORDER = 4;


    private Controller() {
        this.orderManagementDAO = OrderManagementDAO.getInstance();
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();

        return instance;
    }

    /**
     * Starts the appropriate view given user selection
     *
     * @param option operation which user requests
     */
    public void select(int option) {
        switch (option) {
            case CREATE_CUSTOMER:
                CreateCustomerView createCustomerView = new CreateCustomerView();
                createCustomerView.createCustomer();
                break;
            case GET_CUSTOMER:
                GetCustomerView getCustomerView = new GetCustomerView();
                getCustomerView.getCustomer();
                break;
            case CREATE_ORDER:
                CreateOrderView createOrderView = new CreateOrderView();
                createOrderView.createOrder();
                break;
            case GET_ORDER:
                GetOrderView getOrderView = new GetOrderView();
                getOrderView.getOrder();
                break;
        }
    }

    /**
     * Creates a customer
     *
     * @param name name of the customer
     * @param type type of customer
     */
    public void createCustomer(String name, int type) {
        this.orderManagementDAO.createCustomer(name, type);
    }

    /**
     * Gets all available articles for purchase
     *
     * @return available for purchase
     */
    public ArrayList<Article> getArticles() {
        ArticleManager articleManager = new ArticleManager();

        return articleManager.getArticles();
    }

    /**
     * Searches for customer
     *
     * @param name name of the customer
     * @return Optional which contains customer if exists
     */
    public Optional<Customer> searchForCustomer(String name) {
        return this.orderManagementDAO.getCustomer(name);
    }

    /**
     * Gets order and customer for specific ordernumber
     *
     * @param orderNumber the ordernumber for the order
     * @return Optional which contains order and customer if found
     */
    public Optional<OrderSearchResult> getOrder(int orderNumber) {
        return OrderManagementDAO.getInstance().getOrder(orderNumber);
    }

    /**
     * Creates an order object
     *
     * @return order object
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Creates a ordernumber
     *
     * @return an ordernumber
     */
    public int createOrderNumber() {
        return OrderNumberCalculator.getInstance().getOrderNumber();
    }

    /**
     * Removes order from customer
     * @param order order to remove
     * @param customer customer where order will be removed
     */
    public void removeOrder(Order order, Customer customer) {
        this.orderManagementDAO.removeOrder(order, customer);
    }
}
