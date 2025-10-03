package org.example.scholarapi.view;

import org.example.scholarapi.model.Article;
import org.example.scholarapi.model.Researcher;

public class ScholarView {

    public void displayResearcher(Researcher researcher) {
        if (researcher == null || researcher.getArticles() == null || researcher.getArticles().isEmpty()) {
            System.out.println("No articles found to display.");
            return;
        }

        System.out.println("\n--- Articles Found ---");
        for (Article article : researcher.getArticles()) {
            System.out.println(article.toString());
        }
        System.out.println("----------------------");
    }

    public void displayError(String errorMessage) {
        System.err.println("\n--- ERROR ---");
        System.err.println(errorMessage);
        System.err.println("---------------");
    }
}