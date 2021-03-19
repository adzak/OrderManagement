package se.azakir.ordermanagement.view;

import se.azakir.ordermanagement.controller.Controller;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Välkommen till orderhanteraren. Välj ett val från följande:\n");
        while (true) {
            System.out.println("1. Skapa kund");
            System.out.println("2. Hämta kund");
            System.out.println("3. Skapa order");
            System.out.println("4. Hämta order");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            Controller controller = Controller.getInstance();
            controller.select(option);
        }
    }
}
