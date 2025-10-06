package org.example.scholarapi.model;

/**
 * Represents the nested "publication_info" object found in the SerpApi Google Scholar response.
 * This class is a simple data model to hold the summary text provided by the API.
 */
public class PublicationInfo {

    // A single string that typically contains a summary of the authors, publication venue, and year.
    // Example: "Y LeCun, Y Bengio, G Hinton - nature, 2015 - nature.com"
    private String summary;

    /**
     * Overridden to return the summary string directly.
     * This allows for clean and easy printing of the article's publication information
     * when the Article's toString() method is called.
     *
     * @return The summary string of the publication.
     */
    @Override
    public String toString() {
        return summary;
    }
}