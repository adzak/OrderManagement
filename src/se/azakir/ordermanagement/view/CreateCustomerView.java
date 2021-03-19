package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;

import java.util.Scanner;

public class CreateCustomerView {

    private Scanner scanner;

    public CreateCustomerView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the create customer view.
     */
    public void createCustomer() {
        System.out.println("Ange namn på kunden:");
        String name = scanner.nextLine();
        System.out.println("Ange typ av kund:");
        System.out.println("1. Privat");
        System.out.println("2. Litet företag");
        System.out.println("3. Stort företag");
        int type = scanner.nextInt();
        scanner.nextLine();

        Controller.getInstance().createCustomer(name, type);
        System.out.println("Kund har skapats.\n");

    }

}
