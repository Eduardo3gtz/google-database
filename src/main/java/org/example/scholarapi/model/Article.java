package org.example.scholarapi.model;

/**
 * Represents a single article found in a Google Scholar search result.
 * This class acts as a data model (a POJO - Plain Old Java Object) to hold the information
 * parsed from the API's JSON response. The field names are designed to match the JSON keys.
 */
public class Article {

    // The title of the article.
    private String title;

    // A direct link to the article's source (e.g., a PDF or webpage).
    private String link;

    // A nested object containing summary information like authors and publication venue.
    private PublicationInfo publication_info;

    // The authors of the article.
    private String authors;

    // A string indicating how many times the article has been cited (e.g., "Cited by 1234").
    private String cited_by;

    // --- GETTERS ---
    // The following methods are "getters," which provide safe, read-only access
    // to the private fields of the object.

    /**
     * Gets the title of the article.
     * @return The article's title as a String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the direct link to the article's source.
     * @return The URL of the article as a String.
     */
    public String getLink() {
        return link;
    }

    /**
     * Gets the nested publication information object.
     * @return A PublicationInfo object.
     */
    public PublicationInfo getPublication_info() {
        return publication_info;
    }

    /**
     * Gets the authors of the article.
     * @return The authors' names as a String.
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Gets the citation count information.
     * @return A String describing the number of citations (e.g., "Cited by 1234").
     */
    public String getCited_by() {
        return cited_by;
    }

    /**
     * Provides a simple string representation of the Article object, useful for debugging and logging.
     * @return A formatted string with key details of the article.
     */
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", publication_info='" + publication_info + '\'' +
                '}';
    }
}