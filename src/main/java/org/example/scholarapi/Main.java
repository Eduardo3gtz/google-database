package org.example.scholarapi;

import org.example.scholarapi.controller.ScholarController;
import org.example.scholarapi.model.Researcher;
import org.example.scholarapi.persistence.DatabaseController;
import org.example.scholarapi.view.ScholarView;

/**
 * Main class to run the application.
 * Integrates the full workflow: API call -> Data Processing -> Database Storage.
 */
public class Main {

    /**
     * The main entry point for the application. It orchestrates the entire process.
     * @param args Command line arguments (not used in this project).
     */
    public static void main(String[] args) {
        // 1. Create instances of all our components.
        ScholarController apiController = new ScholarController();
        DatabaseController dbController = new DatabaseController();
        ScholarView view = new ScholarView();

        // 2. Define the author to search for.
        String authorToSearch = "Geoffrey Hinton";
        System.out.println("Searching for author: " + authorToSearch);

        try {
            // --- PROCESS START ---

            // 3. Use the Controller to fetch the data from the API.
            Researcher researcher = apiController.getAuthorData(authorToSearch);

            // 4. Use the View to display the retrieved data on the console.
            view.displayResearcher(researcher);

            // 5. Connect to the database.
            dbController.connect();

            // 6. Insert the articles into the database, after a null check.
            if (researcher != null && researcher.getArticles() != null) {
                dbController.insertArticles(researcher.getArticles());
            }

        } catch (Exception e) {
            // 7. If any exception occurs during the process, use the View to display the error.
            view.displayError("An error occurred during the process: " + e.getMessage());
        } finally {
            // 8. CRUCIAL: No matter what happens (success or error),
            // always ensure the database connection is closed to prevent resource leaks.
            dbController.disconnect();
        }
    }
}