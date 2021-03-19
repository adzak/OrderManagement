package se.azakir.ordermanagement.model.article;

import java.util.HashMap;
import java.util.Optional;


public abstract class Article {

    /**
     * Gets the name of the article
     *
     * @return name of the article
     */
    public abstract String getName();

    /**
     * Gets the standard price of the article
     *
     * @return standard price of article
     */
    public abstract double getPrice();

    /**
     * Gets the available discounts for the article
     *
     * @return the discounts for the article. Empty if not any available.
     */
    public abstract Optional<HashMap<Integer, Double>> availableDiscounts();
}
