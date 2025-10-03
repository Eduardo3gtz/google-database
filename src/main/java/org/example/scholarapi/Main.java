package org.example.scholarapi;

import org.example.scholarapi.controller.ScholarController;
import org.example.scholarapi.model.Researcher;
import org.example.scholarapi.view.ScholarView;

/**
 * Main class to run the application.
 * It integrates the Model, View, and Controller to perform a search and display the results.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Create instances of our MVC components
        ScholarController controller = new ScholarController();
        ScholarView view = new ScholarView();

        // 2. Define the ID of the author we want to search for (e.g., Geoffrey Hinton, AI pioneer)
        String authorIdToSearch = "Geoffrey Hinton";
        System.out.println("Searching for author with ID: " + authorIdToSearch);

        try {
            // 3. Use the Controller to get the author's data
            Researcher researcher = controller.getAuthorData(authorIdToSearch);

            // 4. Use the View to display the retrieved data
            view.displayResearcher(researcher);

        } catch (Exception e) {
            // 5. If anything goes wrong, use the View to display the error
            view.displayError("An error occurred: " + e.getMessage());
        }
    }
}