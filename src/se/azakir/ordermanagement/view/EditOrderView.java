package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;
import se.azakir.ordermanagement.model.calculator.OrderCalculator;
import se.azakir.ordermanagement.model.order.OrderRow;
import se.azakir.ordermanagement.model.order.OrderSearchResult;

import java.util.ArrayList;
import java.util.Scanner;

public class EditOrderView {

    private Scanner scanner;

    public EditOrderView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the edit order view. User edits an order from this view.
     *
     * @param orderSearchResult contains the order and the customer
     */
    public void EditOrder(OrderSearchResult orderSearchResult) {
        System.out.println(orderSearchResult.getOrder());
        System.out.println("Välj ett alternativ från följande:");
        System.out.println("1. Välj orderrad att uppdatera");
        System.out.println("2. Ta bort order");

        int selectedOption = this.scanner.nextInt();
        this.scanner.nextLine();

        if (selectedOption == 1) {
            while (true) {
                System.out.println("Ange en orderrad att uppdatera:");
                int rowToUpdate = this.scanner.nextInt();
                this.scanner.nextLine();
                ArrayList<OrderRow> orderRows = orderSearchResult.getOrder().getOrderRows();
                OrderRow orderRow = orderRows.get(rowToUpdate - 1);
                System.out.println(orderRow);

                System.out.println("Ange alternativ:");
                System.out.println("1. Editera antal artiklar");
                System.out.println("2. Ta bort rad");
                int alternativeToRow = this.scanner.nextInt();
                this.scanner.nextLine();

                if (alternativeToRow == 1) {
                    System.out.println("Ange antal artiklar:");
                    int amountOfArticles = this.scanner.nextInt();
                    this.scanner.nextLine();
                    orderRow.setAmount(amountOfArticles);
                } else if (alternativeToRow == 2) {
                    if (orderRows.size() == 1) {
                        System.out.println("Du tar bort den enda raden i orden. Detta kommer ta bort hela ordern. Vill du göra detta?");
                        System.out.println("1. Ja");
                        System.out.println("2. Nej");
                        int removeLastRow = this.scanner.nextInt();
                        if (removeLastRow == 1) {
                            orderRows.remove(rowToUpdate - 1);
                            Controller.getInstance().removeOrder(orderSearchResult.getOrder(), orderSearchResult.getCustomer());
                            System.out.println("Order borttagen");
                            return;
                        }

                        if (removeLastRow == 2) {
                            continue;
                        }
                    }
                    orderRows.remove(rowToUpdate - 1);
                    System.out.println("Rad borttagen");
                }

                OrderCalculator orderCalculator = new OrderCalculator(orderSearchResult.getOrder(), orderSearchResult.getCustomer());
                orderCalculator.summarizeOrder();
                System.out.println(orderSearchResult.getOrder());

                System.out.println("Fortsätt uppdatera?");
                System.out.println("1. Ja");
                System.out.println("2. Spara order");

                int continueOrQuit = this.scanner.nextInt();
                this.scanner.nextLine();

                if (continueOrQuit == 1) {
                    continue;
                }

                if (continueOrQuit == 2) {
                    return;
                }
            }
        } else if (selectedOption == 2) {
            Controller.getInstance().removeOrder(orderSearchResult.getOrder(), orderSearchResult.getCustomer());
            System.out.println("Order borttagen");
        }

    }

}
