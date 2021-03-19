package se.azakir.ordermanagement.model.order;

import java.util.ArrayList;

public class Order {

    private int orderNumber;
    private ArrayList<OrderRow> orderRows;
    private double totalAmount;
    private double totalDiscountAmount;


    public Order() {
        this.orderRows = new ArrayList<OrderRow>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void addOrderRow(OrderRow orderRow) {
        this.orderRows.add(orderRow);
    }

    public ArrayList<OrderRow> getOrderRows() {
        return orderRows;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Ordernummer: " + this.orderNumber + "\n");
        String columns = String.format("%-20s%-20s%-20s%-20s%-20s\n", "Artikel", "Antal", "Pris per artikel", "Total rabatt rad", "Summa rad");
        stringBuilder.append(columns);

        int i = 1;
        for (OrderRow orderRow : this.orderRows) {
            stringBuilder.append(i + "." + orderRow.toString() + "\n");
            i++;
        }
        String totalColumns = String.format("%-20s%-20s\n", "Total rabatt", "Total summa");
        String totalColumnsValue = String.format("%-20s%-20s\n", this.totalDiscountAmount, this.totalAmount);
        stringBuilder.append(totalColumns);
        stringBuilder.append(totalColumnsValue);

        return stringBuilder.toString();

    }

}
