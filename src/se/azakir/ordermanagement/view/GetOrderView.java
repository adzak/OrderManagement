package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;
import se.azakir.ordermanagement.model.order.OrderSearchResult;

import java.util.Optional;
import java.util.Scanner;

public class GetOrderView {

    private Scanner scanner;

    public GetOrderView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the get order view. Users fetches order in this view.
     */
    public void getOrder() {
        while (true) {
            System.out.println("Ange ordernummer:");
            int inputOrderNumber = scanner.nextInt();
            scanner.nextLine();
            Optional<OrderSearchResult> order = Controller.getInstance().getOrder(inputOrderNumber);

            if (order.isPresent()) {
                System.out.println(order.get().getOrder());
                System.out.println("Ange alternativ?");
                System.out.println("1. Uppdatera order");
                System.out.println("2. Sök efter annan order");
                System.out.println("3. Gå tillbaka");

                int selectedOption = scanner.nextInt();
                scanner.nextLine();
                if (selectedOption == 1) {
                    EditOrderView editOrderView = new EditOrderView();
                    editOrderView.EditOrder(order.get());
                    return;
                }
                if (selectedOption == 2) {
                    continue;
                }
                if (selectedOption == 3) {
                    return;
                }
            } else {
                System.out.println("Ingen order hittad. Vill du försöka igen?");
                System.out.println("1. Ja");
                System.out.println("2. Nej");
                int continueSearching = scanner.nextInt();
                scanner.nextLine();
                if (continueSearching == 1) {
                    continue;
                }
                if (continueSearching == 2) {
                    break;
                }
            }
        }
    }
}
