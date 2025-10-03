# Google Scholar API Integration Project

**Author:** Eduardo Gutiérrez
**Status:** All Sprints Complete

![Java Version](https://img.shields.io/badge/java-17-blue)
![Built with](https://img.shields.io/badge/built%20with-Maven-orange)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

This repository contains the full source code for the "Server and Database Commands" challenge. The project is a Java application that automates the process of fetching researcher data from the Google Scholar API (via SerpApi) and preparing it for database integration.

---

### 1. Project Purpose
The main objective of this project is to develop a system in Java that automates the collection of information about researchers and their publications. It uses the SerpApi service to extract data from Google Scholar, which is then integrated into a database, eliminating the need for manual processes.

### 2. Key Functionalities
* **API Connection:** Performs GET requests to the SerpApi to retrieve author data from Google Scholar.
* **Data Processing:** Parses the JSON response from the API and maps it to Java objects using the MVC pattern.
* **Database Integration:** (Sprint 3) Inserts the processed information into a MySQL database for storage and querying.

### 3. Project Relevance
This project solves a critical problem in academic environments: the slow and tedious task of gathering and updating information on scientific production. By automating this process, the institution can significantly reduce manual time and effort, minimize human errors, and generate updated reports more quickly and reliably.

---

### 🛠️ Technologies Used

| Component         | Technology         | Purpose                                                      |
| :---------------- | :----------------- | :----------------------------------------------------------- |
| **Core Logic** | Java 17            | The main programming language for the application's functionality. |
| **API Client** | Apache HttpClient  | For making HTTP GET requests to the external API.            |
| **JSON Parsing** | Google Gson        | For parsing the JSON response from the API into Java objects. |
| **Dependencies** | Maven              | Manages the project's external libraries and automates the build process. |
| **Version Control**| GitHub             | Used for source code management and project history.         |

---

### 🏗️ Project Architecture (MVC)

The application is architected using the **Model-View-Controller (MVC)** pattern to ensure a clean separation of concerns.

* **Model (`/model`):** Represents the data. The `Researcher`, `Article`, and `PublicationInfo` classes are Plain Old Java Objects (POJOs) that mirror the structure of the JSON data.
* **View (`/view`):** Responsible for the presentation. The `ScholarView` class takes the Model objects and displays their data in a human-readable format on the console.
* **Controller (`/controller`):** The brain of the application. The `ScholarController` class handles all the logic for making API requests, parsing the response, and populating the Model objects.

---

### 🧪 Testing & Verification Strategy

The application was tested by performing author searches and verifying the results. The strategy focused on functionality and robustness.

| Test Case | Steps to Reproduce | Expected Outcome | Result |
| :--- | :--- | :--- | :--- |
| **1. Functional "Happy Path" Test** | 1. Run the `Main.java` class with a valid API key and author name. | The console displays the formatted list of articles for the specified author. | ✅ Pass |
| **2. Error Handling (Auth Failure)**| 1. Run the `Main.java` class with an invalid API key. | The console displays a clear `HTTP error code : 401` message. The application does not crash. | ✅ Pass |
| **3. Error Handling (Bad Request)**| 1. Run the `Main.java` class with an invalid API parameter. | The console displays a clear `HTTP error code : 400` message. The application does not crash. | ✅ Pass |

---

## Project Estimation & Budgeting
A cost estimation was performed to simulate a real-world project budget. The estimation is based on a total of **64 hours** of work, distributed across five project phases.

| Phase                      | Estimated Hours | Cost (@ $350/hr) | Notes                                      |
| :------------------------- | :-------------- | :--------------- | :----------------------------------------- |
| 1. Planning & Analysis     | 16              | $5,600           | API Research, Backlog, Roadmap.            |
| 2. Design / Architecture   | 4               | $1,400           | MVC pattern and database schema design.    |
| 3. Development / Construction | 32              | $11,200          | Java programming (API & Database).         |
| 4. Testing & Validation    | 4               | $1,400           | Functionality and error handling tests.    |
| 5. Delivery & Documentation| 8               | $2,800           | Creation of the final video and presentation. |
| **Subtotal:** | **64** | **$22,400** |                                            |
| **Profit Margin (30%):** |                 | **$6,720** |                                            |
| **Total Estimated Cost:** |                 | **$29,120** |
### 🚀 How to Run the Project

1.  **Clone the repository.**
2.  **Open as a Maven project** in your IDE (e.g., IntelliJ IDEA). Maven will automatically download the required dependencies.
3.  **Add your API Key:** Open `src/main/java/org/example/scholarapi/controller/ScholarController.java` and replace the placeholder `"TU_API_KEY"` with your actual SerpApi key.
4.  **Run the application:** Execute the `main` method in the `Main.java` class. The results for the hardcoded author search ("Geoffrey Hinton") will be printed to the console.