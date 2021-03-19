package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;
import se.azakir.ordermanagement.model.customer.Customer;
import se.azakir.ordermanagement.model.order.Order;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class GetCustomerView {

    private Scanner scanner;

    public GetCustomerView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the get customer view. Users gets a customer by this view.
     */
    public void getCustomer() {
        while (true) {
            System.out.println("Ange namn på kunden:");
            String searchCustomer = this.scanner.nextLine();
            Optional<Customer> optionalCustomer = Controller.getInstance().searchForCustomer(searchCustomer);

            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                System.out.println(customer.getName());

                System.out.println("Ange alternativ:");
                System.out.println("1. Visa ordrar för kund");
                System.out.println("2. Gå tillbaka");
                int selectedOption = this.scanner.nextInt();
                this.scanner.nextLine();

                if (selectedOption == 1) {
                    ArrayList<Order> orders = customer.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("Inga ordrar tillgängliga.");
                        return;
                    }
                    for (Order order : customer.getOrders()) {
                        System.out.println(order);
                    }
                    return;
                }
                if (selectedOption == 2) {
                    return;
                }
            } else {
                System.out.println("Kunde ej hitta kund. Vill du försöka igen?");
                System.out.println("1. Ja");
                System.out.println("2. Nej (Gå tillbaka)");
                int tryAgain = this.scanner.nextInt();
                this.scanner.nextLine();
                if (tryAgain == 1) {
                    continue;
                } else if (tryAgain == 2) {
                    return;
                }
            }
        }

    }

}
