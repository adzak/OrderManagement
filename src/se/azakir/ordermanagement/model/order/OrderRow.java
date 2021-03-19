package se.azakir.ordermanagement.model.order;

import se.azakir.ordermanagement.model.article.Article;

import static java.lang.String.format;

public class OrderRow {

    private Article article;
    private int amount;
    private double pricePerArticle;
    private double discountRowAmount;
    private double summaryRowAmount;

    public OrderRow(Article article, int amount) {
        this.article = article;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Article getArticle() {
        return article;
    }

    public double getPricePerArticle() {
        return pricePerArticle;
    }

    public void setPricePerArticle(double pricePerArticle) {
        this.pricePerArticle = pricePerArticle;
    }

    public void setDiscountRowAmount(double discountRowAmount) {
        this.discountRowAmount = discountRowAmount;
    }

    public void setSummaryRowAmount(double summaryRowAmount) {
        this.summaryRowAmount = summaryRowAmount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        String formattedString = format("%-20s%-20s%-20s%-20s%-20s", this.getArticle().getName(), this.getAmount(), this.pricePerArticle, this.discountRowAmount, this.summaryRowAmount);

        return formattedString;
    }

}
