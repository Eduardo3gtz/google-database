package org.example.scholarapi.model;

import java.util.List;

public class Researcher {
    // En esta versión, un "Researcher" es simplemente un contenedor para sus artículos
    private List<Article> articles;

    // Getter y Setter
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}