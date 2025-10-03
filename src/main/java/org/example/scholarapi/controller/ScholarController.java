package org.example.scholarapi.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.scholarapi.model.Article;
import org.example.scholarapi.model.Researcher;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ScholarController {

    private static final String API_KEY = "bbc6a2e1285bb450911c04b06f90c6c4803c1af9b70778adb8033785af1f688e"; // Asegúrate de que tu API Key esté aquí

    public Researcher getAuthorData(String authorName) throws IOException, InterruptedException {
        // --- INICIO DE LA CORRECCIÓN FINAL ---
        // Usamos el motor "google_scholar" y el parámetro de búsqueda "q"
        String url = "https://serpapi.com/search.json?engine=google_scholar&q=" + authorName.replace(" ", "+") + "&api_key=" + API_KEY;
        // --- FIN DE LA CORRECCIÓN FINAL ---

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                }

                String jsonResponse = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

                // Los resultados ahora vienen en la lista "organic_results"
                if (jsonObject.has("organic_results")) {
                    JsonArray results = jsonObject.getAsJsonArray("organic_results");

                    // Definimos el tipo de una Lista de Artículos para Gson
                    Type articleListType = new TypeToken<List<Article>>() {}.getType();
                    List<Article> articles = gson.fromJson(results, articleListType);

                    Researcher researcher = new Researcher();
                    researcher.setArticles(articles);
                    return researcher;
                }

                throw new IOException("No 'organic_results' found for the given author name.");
            }
        }
    }
}