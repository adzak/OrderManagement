package se.azakir.ordermanagement.model.article;

import se.azakir.ordermanagement.model.customer.CustomerTypes;

import java.util.HashMap;
import java.util.Optional;

public class Pen extends Article {

    private static final String NAME = "Penna";
    private final static double PRICE = 20.0;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }

    @Override
    public Optional<HashMap<Integer, Double>> availableDiscounts() {
        HashMap<Integer, Double> availableDiscounts = new HashMap<Integer, Double>();
        availableDiscounts.put(CustomerTypes.COMPANY, 0.9);
        availableDiscounts.put(CustomerTypes.BIG_COMPANY, 0.8);

        return Optional.of(availableDiscounts);
    }

}
