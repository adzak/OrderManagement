package se.azakir.ordermanagement.model.article;
import java.util.ArrayList;

public class ArticleManager {

    private ArrayList<Article> articles;

    public ArticleManager() {
        this.articles = new ArrayList<Article>();
        articles.add(new Paper());
        articles.add(new Pen());
        articles.add(new Notebook());
        articles.add(new Eraser());
    }

    /**
     * Gets all available articles
     *
     * @return all available articles for purchase
     */
    public ArrayList<Article> getArticles() {
        return this.articles;
    }
}
