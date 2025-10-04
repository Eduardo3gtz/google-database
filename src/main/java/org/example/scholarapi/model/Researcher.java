package org.example.scholarapi.model;

import java.util.List;

/**
 * Represents a Researcher, acting as a container for the list of articles
 * retrieved from a Google Scholar search. In this simplified model for Sprint 2,
 * the researcher is defined by their collection of articles.
 */
public class Researcher {

    // A list of Article objects found for the researcher during the API search.
    private List<Article> articles;

    /**
     * Gets the list of articles associated with this researcher.
     * @return A List of Article objects.
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Sets the list of articles for this researcher. This method is used to populate
     * the object with the data retrieved and parsed from the API response.
     * @param articles A List of Article objects.
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}