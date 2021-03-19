package se.azakir.ordermanagement.model.order;

import org.junit.Test;
import se.azakir.ordermanagement.controller.Controller;
import se.azakir.ordermanagement.dataacessobject.OrderManagementDAO;;
import se.azakir.ordermanagement.model.article.Eraser;
import se.azakir.ordermanagement.model.article.Notebook;
import se.azakir.ordermanagement.model.article.Paper;
import se.azakir.ordermanagement.model.article.Pen;
import se.azakir.ordermanagement.model.calculator.OrderCalculator;
import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.customer.CustomerTypes;


import static org.junit.Assert.assertEquals;

public class TestCreateOrder {

    @Test
    public void testBigCompanyOrder() {
        OrderManagementDAO.getInstance().createCustomer("Scania", CustomerTypes.BIG_COMPANY);
        Controller controller = Controller.getInstance();
        Customer customer = OrderManagementDAO.getInstance().getCustomer("Scania").get();
        Order order = controller.createOrder();
        OrderRow orderPen = new OrderRow(new Pen(), 12);
        OrderRow orderNoteBook = new OrderRow(new Notebook(), 5);
        OrderRow orderPaper = new OrderRow(new Paper(), 1000);
        order.addOrderRow(orderPen);
        order.addOrderRow(orderNoteBook);
        order.addOrderRow(orderPaper);
        OrderCalculator orderCalculator = new OrderCalculator(order, customer);
        orderCalculator.summarizeOrder();
        order.setOrderNumber(controller.createOrderNumber());

        assertEquals(14.4, orderPen.getPricePerArticle(), 0);
        assertEquals(12, orderPen.getAmount());

        assertEquals(22.5, orderNoteBook.getPricePerArticle(), 0);
        assertEquals(5, orderNoteBook.getAmount());

        assertEquals(21.6, orderPaper.getPricePerArticle(), 0);
        assertEquals(1000, orderPaper.getAmount());

        OrderRow orderRow = order.getOrderRows().get(3);

        assertEquals(0, orderRow.getPricePerArticle(), 0.0);
        assertEquals("Cykel", orderRow.getArticle().getName());

        assertEquals(21885.3, order.getTotalAmount(), 0.0);
        assertEquals(8479.7, order.getTotalDiscountAmount(), 0.0);
    }

    @Test
    public void testSmallCompanyOrder() {
        OrderManagementDAO.getInstance().createCustomer("LinasCafe", CustomerTypes.SMALL_COMPANY);
        Controller controller = Controller.getInstance();
        Customer customer = OrderManagementDAO.getInstance().getCustomer("LinasCafe").get();
        Order order = controller.createOrder();
        OrderRow orderPen = new OrderRow(new Pen(), 5);
        order.addOrderRow(orderPen);
        OrderCalculator orderCalculator = new OrderCalculator(order, customer);
        orderCalculator.summarizeOrder();
        order.setOrderNumber(controller.createOrderNumber());

        assertEquals(18.0, orderPen.getPricePerArticle(), 0);
        assertEquals(5, orderPen.getAmount());

        assertEquals(90.0, order.getTotalAmount(), 0.0);
        assertEquals(10.0, order.getTotalDiscountAmount(), 0.0);
    }

    @Test
    public void testPrivateOrder() {
        OrderManagementDAO.getInstance().createCustomer("NisseNilsson", 0);
        Controller controller = Controller.getInstance();
        Customer customer = OrderManagementDAO.getInstance().getCustomer("NisseNilsson").get();
        Order order = controller.createOrder();

        OrderRow orderPen = new OrderRow(new Pen(), 50);
        order.addOrderRow(orderPen);

        OrderRow orderEraser = new OrderRow(new Eraser(), 40);
        order.addOrderRow(orderEraser);

        OrderRow orderPaper = new OrderRow(new Paper(), 20);
        order.addOrderRow(orderPaper);

        OrderRow orderNoteBook = new OrderRow(new Notebook(), 10);
        order.addOrderRow(orderNoteBook);

        OrderCalculator orderCalculator = new OrderCalculator(order, customer);
        orderCalculator.summarizeOrder();
        order.setOrderNumber(controller.createOrderNumber());

        assertEquals(20.0, orderPen.getPricePerArticle(), 0);
        assertEquals(50, orderPen.getAmount());

        assertEquals(15.0, orderEraser.getPricePerArticle(), 0);
        assertEquals(40, orderEraser.getAmount());

        assertEquals(30.0, orderPaper.getPricePerArticle(), 0);
        assertEquals(20, orderPaper.getAmount());

        assertEquals(25.0, orderNoteBook.getPricePerArticle(), 0);
        assertEquals(10, orderNoteBook.getAmount());

        assertEquals(2450.0, order.getTotalAmount(), 0.0);
        assertEquals(0.0, order.getTotalDiscountAmount(), 0.0);
    }


}