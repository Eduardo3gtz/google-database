package org.example.scholarapi.model;

/**
 * Represents a single published article from Google Scholar.
 * This version correctly handles the nested publication_info object.
 */
public class Article {
    private String title;
    private String link;
    private PublicationInfo publication_info; // CAMBIO: Ahora es un objeto, no un String
    private int year;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", publication_info='" + publication_info + '\'' +
                '}';
    }
}