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

/**
 * The Controller in the MVC pattern.
 * This class handles all the logic for interacting with the SerpApi
 * to fetch Google Scholar data.
 */
public class ScholarController {

    // IMPORTANT: Replace this with your actual SerpApi key.
    private static final String API_KEY = "bbc6a2e1285bb450911c04b06f90c6c4803c1af9b70778adb8033785af1f688e";

    /**
     * Fetches author data from the Google Scholar API for a given author name.
     * @param authorName The name of the author to search for.
     * @return A Researcher object containing the author's found articles.
     * @throws IOException If there is a network error or the API returns an error.
     * @throws InterruptedException If the connection is interrupted.
     */
    public Researcher getAuthorData(String authorName) throws IOException, InterruptedException {
        // Step 1: Build the full URL for the API request.
        // We use the "google_scholar" engine and the "q" parameter for the query.
        // Spaces in the author's name are replaced with '+' for the URL.
        String url = "https://serpapi.com/search.json?engine=google_scholar&q=" + authorName.replace(" ", "+") + "&api_key=" + API_KEY;

        // Use a try-with-resources block to automatically close the HttpClient.
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Step 2: Create an HTTP GET request object with the URL.
            HttpGet request = new HttpGet(url);

            // Step 3: Execute the request and get the response.
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Step 4: Check if the request was successful (HTTP status code 200).
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                }

                // Step 5: Convert the response body into a plain String.
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Step 6: Use the Gson library to parse the JSON string.
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

                // Step 7: The search results are inside a JSON array called "organic_results".
                // We check if this key exists before trying to access it.
                if (jsonObject.has("organic_results")) {
                    JsonArray results = jsonObject.getAsJsonArray("organic_results");

                    // Step 8: To parse a list of complex objects (like our Articles),
                    // we need to tell Gson what type of list it is.
                    Type articleListType = new TypeToken<List<Article>>() {}.getType();
                    List<Article> articles = gson.fromJson(results, articleListType);

                    // Step 9: Create a new Researcher object and populate it with the list of articles.
                    Researcher researcher = new Researcher();
                    researcher.setArticles(articles);
                    return researcher;
                }

                // If "organic_results" was not found, throw an error.
                throw new IOException("No 'organic_results' found for the given author name.");
            }
        }
    }
}