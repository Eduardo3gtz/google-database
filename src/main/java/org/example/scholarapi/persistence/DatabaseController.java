package org.example.scholarapi.persistence;

import org.example.scholarapi.model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles all database operations, such as connecting and inserting data.
 * This class follows the Data Access Object (DAO) pattern.
 */
public class DatabaseController {
    // --- IMPORTANT: CONFIGURE YOUR DATABASE DETAILS HERE ---
    // The database connection URL, specifying the driver (jdbc:mysql), host (localhost), port (3306), and database name (scholar_db).
    private static final String DB_URL = "jdbc:mysql://localhost:3306/scholar_db";
    // The database username.
    private static final String USER = "root";
    // The database password.
    private static final String PASS = "Encomienda.171"; // <-- YOUR MYSQL PASSWORD

    // This object will hold the active database connection.
    private Connection conn = null;

    /**
     * Establishes a connection to the MySQL database.
     * @throws SQLException if a database access error occurs.
     */
    public void connect() throws SQLException {
        try {
            // Attempt to create a connection using the URL, user, and password.
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection to database established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            throw e; // Re-throw the exception to let the caller handle it.
        }
    }

    /**
     * Inserts a list of articles into the 'articles' table using a batch operation for efficiency.
     * @param articles The list of Article objects to insert.
     */
    public void insertArticles(List<Article> articles) {
        // Safety check: Do nothing if the list is empty or the connection is not established.
        if (articles == null || articles.isEmpty() || conn == null) {
            System.out.println("No articles to insert or database not connected.");
            return;
        }

        // The SQL INSERT statement with '?' placeholders for a PreparedStatement.
        // This prevents SQL injection attacks.
        String sql = "INSERT INTO articles(title, authors, publication_date, link, cited_by) VALUES(?, ?, ?, ?, ?)";

        // Use a try-with-resources block to ensure the PreparedStatement is always closed.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Loop through each article in the list.
            for (Article article : articles) {
                // Set the value for each '?' placeholder.
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getAuthors());
                // For simplicity, we get the summary from the nested object.
                pstmt.setString(3, article.getPublication_info() != null ? article.getPublication_info().toString() : null);
                pstmt.setString(4, article.getLink());

                // --- Special handling for 'cited_by' ---
                int citedBy = 0; // Default to 0 if the field is missing or invalid.
                if (article.getCited_by() != null && !article.getCited_by().isEmpty()) {
                    try {
                        // The API returns a string like "Cited by 1234", so we extract only the numbers.
                        String numberOnly = article.getCited_by().replaceAll("[^0-9]", "");
                        if (!numberOnly.isEmpty()) {
                            citedBy = Integer.parseInt(numberOnly);
                        }
                    } catch (NumberFormatException e) {
                        // If parsing fails, 'citedBy' remains 0. We ignore the error and continue.
                    }
                }
                pstmt.setInt(5, citedBy);

                // Add the current set of parameters to the batch.
                // This prepares the query but doesn't execute it yet.
                pstmt.addBatch();
            }

            // Execute all the batched SQL statements at once. This is much faster than running one by one.
            pstmt.executeBatch();
            System.out.println(articles.size() + " articles inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting articles: " + e.getMessage());
        }
    }

    /**
     * Closes the database connection if it is open.
     */
    public void disconnect() {
        // Check if the connection object is not null before trying to close it.
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection to database closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}