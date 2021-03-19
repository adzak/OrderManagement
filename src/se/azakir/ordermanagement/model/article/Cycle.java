package se.azakir.ordermanagement.model.article;

import java.util.HashMap;
import java.util.Optional;

public class Cycle extends Article {

    private static final String NAME = "Cykel";
    private static final double PRICE = 0.0;

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
        return Optional.empty();
    }
}
