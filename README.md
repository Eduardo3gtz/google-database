# Google Scholar API Integration Project

**Author:** Eduardo Gutiérrez  
**Status:** All Sprints Complete  
**Version:** 1.0.0

![Java Version](https://img.shields.io/badge/java-17-blue)
![Built with](https://img.shields.io/badge/built%20with-Maven-orange)
![License](https://img.shields.io/badge/license-MIT-lightgrey)
![Status](https://img.shields.io/badge/status-active-success)

This repository contains the full source code for the "Server and Database Commands" challenge. The project is a Java application that automates the process of fetching researcher data from the Google Scholar API (via SerpApi) and integrating it into a database.

---

## 📋 Table of Contents

- [Project Description](#-project-description)
- [Key Features](#-key-features)
- [Technologies Used](#️-technologies-used)
- [Project Architecture](#️-project-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Usage](#-usage)
- [Testing & Verification](#-testing--verification)
- [Database Configuration](#️-database-configuration)
- [Project Roadmap](#-project-roadmap)
- [Contributing](#-contributing)

---

## 🎯 Project Description

### Purpose
The main objective of this project is to develop a Java system that automates the collection of information about researchers and their publications. It uses the SerpApi service to extract data from Google Scholar, which is then integrated into a MySQL database, eliminating the need for manual processes.

### Problem It Solves
This project addresses a critical issue in academic environments: the slow and tedious task of gathering and updating information on scientific production. By automating this process, institutions can:
- ✅ Significantly reduce manual time and effort
- ✅ Minimize human errors
- ✅ Generate updated reports quickly and reliably
- ✅ Maintain a historical record of publications

---

## ✨ Key Features

### Sprint 1: API Connection
- 🔌 Robust connection with SerpApi for Google Scholar
- 📡 HTTP GET request handling
- 🔐 Secure authentication with API Key
- ⚠️ HTTP error handling (401, 400, 404, etc.)

### Sprint 2: Data Processing
- 📊 JSON response parsing
- 🏗️ Mapping to Java objects using MVC pattern
- 💾 Data models: `Researcher`, `Article`, `PublicationInfo`
- 🖥️ Formatted console visualization

### Sprint 3: Database Integration
- 🗄️ MySQL connection
- 💽 Persistence of researcher and article data
- 🔄 Complete CRUD operations
- 🔍 Optimized queries

---

## 🛠️ Technologies Used

| Component | Technology | Version | Purpose |
|:----------|:-----------|:--------|:--------|
| **Core Language** | Java | 17 | Main programming language |
| **API Client** | Apache HttpClient | 5.x | HTTP requests to external API |
| **JSON Parsing** | Google Gson | 2.10.x | JSON ↔ Java objects conversion |
| **Dependency Management** | Maven | 3.8+ | Library and build management |
| **Database** | MySQL | 8.0+ | Persistent storage |
| **JDBC Driver** | MySQL Connector/J | 8.x | Java-MySQL connection |
| **Version Control** | Git/GitHub | - | Source code management |

---

## 🏗️ Project Architecture

The application is architected using the **MVC (Model-View-Controller)** pattern to ensure a clean separation of concerns.

```
┌─────────────────────────────────────────────┐
│              Main (Entry Point)              │
└────────────────┬────────────────────────────┘
                 │
                 ▼
        ┌────────────────┐
        │   Controller   │ ◄─── Orchestrates logic
        │                │
        │ ScholarController
        └───┬────────┬───┘
            │        │
            ▼        ▼
    ┌───────────┐ ┌─────────┐
    │   Model   │ │  View   │
    │           │ │         │
    │ Researcher│ │ Scholar │
    │  Article  │ │  View   │
    │Publication│ │         │
    └─────┬─────┘ └─────────┘
          │
          ▼
    ┌──────────────┐
    │ Persistence  │
    │              │
    │ Database     │
    │ Controller   │
    └──────────────┘
```

### Components:

#### 📦 Model (`/model`)
Represents domain data:
- `Researcher.java`: Researcher information
- `Article.java`: Publication data
- `PublicationInfo.java`: Publication metadata

#### 👁️ View (`/view`)
Presentation layer:
- `ScholarView.java`: Renders data to console
- `Main.java`: Application entry point

#### 🎮 Controller (`/controller`)
Business logic:
- `ScholarController.java`: Handles API requests and processing

#### 💾 Persistence (`/persistence`)
Data layer:
- `DatabaseController.java`: CRUD operations with MySQL

---

## 📁 Project Structure

```
google-scholar-api/
├── .idea/                      # IntelliJ IDEA configuration
├── .mvn/                       # Maven wrapper
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/scholarapi/
│   │   │       ├── controller/
│   │   │       │   └── ScholarController.java
│   │   │       ├── model/
│   │   │       │   ├── Article.java
│   │   │       │   ├── PublicationInfo.java
│   │   │       │   └── Researcher.java
│   │   │       ├── persistence/
│   │   │       │   └── DatabaseController.java
│   │   │       └── view/
│   │   │           ├── Main.java
│   │   │           └── ScholarView.java
│   │   └── resources/
│   └── test/                   # Unit tests (future)
├── target/                     # Compiled files
├── .gitignore
├── pom.xml                     # Maven configuration
└── README.md
```

---

## 📋 Prerequisites

Before running this project, make sure you have installed:

- ☕ **Java JDK 17** or higher
  ```bash
  java -version
  ```
- 🔧 **Maven 3.8+**
  ```bash
  mvn -version
  ```
- 🗄️ **MySQL Server 8.0+**
- 🔑 **SerpApi Key** (Get a free one at [serpapi.com](https://serpapi.com))
- 💻 **Recommended IDE:** IntelliJ IDEA, Eclipse, or VS Code

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/google-scholar-api.git
cd google-scholar-api
```

### 2️⃣ Configure Maven
The project already includes a configured `pom.xml`. Maven will automatically download dependencies:
```bash
mvn clean install
```

### 3️⃣ Configure API Key
Open `src/main/java/org/example/scholarapi/controller/ScholarController.java` and replace:
```java
private static final String API_KEY = "YOUR_API_KEY_HERE";
```

### 4️⃣ Configure Database
Edit `src/main/java/org/example/scholarapi/persistence/DatabaseController.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/scholar_db";
private static final String DB_USER = "your_username";
private static final String DB_PASSWORD = "your_password";
```

### 5️⃣ Create Database Schema
Execute the following SQL script:
```sql
CREATE DATABASE IF NOT EXISTS scholar_db;
USE scholar_db;

CREATE TABLE researchers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    affiliation VARCHAR(255),
    citations INT,
    h_index INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE articles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    researcher_id INT,
    title VARCHAR(500) NOT NULL,
    year INT,
    citations INT,
    FOREIGN KEY (researcher_id) REFERENCES researchers(id)
);
```

---

## 💻 Usage

### Basic Execution
```bash
# From command line
mvn exec:java -Dexec.mainClass="org.example.scholarapi.view.Main"

# Or run Main.java from your IDE
```

### Sample Output
```
===========================================
Researcher: Geoffrey Hinton
Affiliation: University of Toronto
Citations: 500,000+
h-index: 180
===========================================

Articles:
-----------
1. Deep Learning
   Year: 2015 | Citations: 50,000
   
2. ImageNet Classification with Deep CNNs
   Year: 2012 | Citations: 100,000

...
```

### Modify Search
In `Main.java`, change the author to search:
```java
controller.searchAuthor("Researcher Name");
```

---

## 🧪 Testing & Verification

### Implemented Test Suite

| Test Case | Steps | Expected Result | Status |
|:----------|:------|:----------------|:-------|
| **Functional Test** | Run with valid API key | Displays article list | ✅ Pass |
| **Error 401** | Run with invalid API key | Authentication error message | ✅ Pass |
| **Error 400** | Invalid parameters | Bad request message | ✅ Pass |
| **DB Connection** | Insert data | Data persisted correctly | ✅ Pass |

### Run Tests
```bash
mvn test
```

---

## 🗄️ Database Configuration

### ER Diagram
```
┌─────────────────┐         ┌─────────────────┐
│   Researchers   │         │    Articles     │
├─────────────────┤         ├─────────────────┤
│ id (PK)         │────┐    │ id (PK)         │
│ name            │    └───►│ researcher_id(FK)│
│ affiliation     │         │ title           │
│ citations       │         │ year            │
│ h_index         │         │ citations       │
│ created_at      │         └─────────────────┘
└─────────────────┘
```

### Environment Variables (Optional)
For better security, you can use environment variables:
```bash
export SERPAPI_KEY="your_api_key"
export DB_USER="your_username"
export DB_PASSWORD="your_password"
```

---

## 🗺️ Project Roadmap

### ✅ Completed
- [x] Sprint 1: API Connection
- [x] Sprint 2: Data Processing (MVC)
- [x] Sprint 3: MySQL Integration

### 🔜 Upcoming Features
- [ ] Graphical Interface (JavaFX/Swing)
- [ ] REST API for queries
- [ ] Unit tests with JUnit
- [ ] PDF/Excel export
- [ ] Caching system
- [ ] Multi-criteria search
- [ ] Metrics dashboard

---

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Style Guide
- Follow Java Code Conventions
- Comment complex code
- Write tests for new features
- Update documentation

---



## 📧 Contact

**Eduardo Gutiérrez**

- 📧 Email: your-email@example.com
- 🐙 GitHub: [eduardo3gtz](https://github.com/Eduardo3gtz)




