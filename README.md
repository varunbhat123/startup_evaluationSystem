Startup Evaluation System

A Java-based Startup Evaluation System that allows founders to register, submit startup ideas, and receive automated evaluation scores. Administrators can view submitted startups, evaluate them, and view startup rankings.

Features

* User registration and login
* Founder dashboard
* Startup submission
* Automated startup evaluation
* Innovation score
* Market potential score
* Technical feasibility score
* Financial potential score
* Overall startup score
* Admin dashboard
* View all submitted startups
* Evaluate startups
* Startup ranking system
* MySQL database integration using JDBC

⸻

🛠️ Technologies Used

Technology	Purpose
Java	Application development
JDBC	Java-MySQL database connectivity
MySQL	Database management
MySQL Workbench	Database creation and management
Eclipse IDE	Java development
Git & GitHub	Version control

⸻

 Requirements

Before running this project, install:

1. Java JDK

* JDK 21 or later
* Verify installation:

java -version

2. Eclipse IDE

Use Eclipse IDE with Java development support.

3. MySQL Server

Install MySQL Server and make sure the MySQL service is running.

4. MySQL Workbench

MySQL Workbench can be used to create and manage the project database.

5. MySQL Connector/J

The project requires MySQL Connector/J for JDBC connectivity.

Add the MySQL Connector/J .jar file to the Eclipse project’s build path.

⸻

🗄️ Database Setup

Create a MySQL database named:

CREATE DATABASE startup_evaluation;

Select the database:

USE startup_evaluation;

Users Table

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'FOUNDER'
);

Startups Table

CREATE TABLE startups (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    startup_name VARCHAR(200) NOT NULL,
    domain VARCHAR(100),
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

Evaluations Table

CREATE TABLE evaluations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    startup_id INT,
    innovation_score DOUBLE,
    market_score DOUBLE,
    technical_score DOUBLE,
    financial_score DOUBLE,
    total_score DOUBLE,
    FOREIGN KEY (startup_id) REFERENCES startups(id)
);

⸻

 Database Configuration

Open:

src/startup/DBConnection.java

Update the MySQL username and password according to your local MySQL installation.

Example:

String url = "jdbc:mysql://localhost:3306/startup_evaluation";
String username = "root";
String password = "YOUR_MYSQL_PASSWORD";

Do not upload your actual MySQL password to GitHub.

⸻

 How to Run

Step 1: Clone the repository

git clone https://github.com/varunbhat123/startup_evaluationSystem.git

Step 2: Open in Eclipse

1. Open Eclipse.
2. Select File → Import.
3. Import the existing Java project.
4. Select the cloned StartupEvaluationSystem folder.

Step 3: Configure MySQL

Make sure:

* MySQL Server is running.
* The startup_evaluation database exists.
* Required tables have been created.
* Database credentials in DBConnection.java are correct.

Step 4: Configure JDBC

Add the MySQL Connector/J .jar file to the project’s build path.

In Eclipse:

Right Click Project
→ Build Path
→ Configure Build Path
→ Libraries
→ Classpath
→ Add External JARs

Select the MySQL Connector/J .jar file.

Step 5: Run the application

Run:

src/startup/Main.java

as a Java Application.

⸻

📁 Project Structure

StartupEvaluationSystem/
│
├── src/
│   ├── module-info.java
│   │
│   └── startup/
│       ├── AIEvaluator.java
│       ├── AdminMenu.java
│       ├── DBConnection.java
│       ├── EvaluationDAO.java
│       ├── FounderMenu.java
│       ├── Main.java
│       ├── StartupDAO.java
│       └── UserDAO.java
│
├── .gitignore
└── README.md

⸻

 Automated Evaluation

The system automatically evaluates startup descriptions using the AIEvaluator class.

The evaluation considers:

Innovation

Evaluates innovation-related technologies and concepts such as:

* Artificial Intelligence
* Machine Learning
* Blockchain
* IoT

Market Potential

Considers domains such as:

* Healthcare
* Agriculture
* Education
* FinTech

Technical Feasibility

Considers technologies such as:

* Cloud
* AI
* IoT

Financial Potential

The system generates a financial evaluation based on the startup information.

Overall Score

The individual evaluation scores are combined to generate an overall startup score.

⸻

 User Roles

Founder

A founder can:

1. Register
2. Login
3. Submit a startup
4. View submitted startups
5. Logout

Admin

An administrator can:

1. Login
2. View all startups
3. Evaluate startups
4. View startup rankings
5. Logout

⸻

 Startup Ranking

Startups can be ranked according to their overall evaluation score.

Higher-scoring startups appear higher in the ranking.

Startup → Evaluation → Total Score → Ranking

⸻

 Security Note

For demonstration purposes, database credentials may be stored in the Java configuration.

For a production application, credentials should be stored using environment variables or a secure configuration system.

Never commit real passwords, API keys, or other secrets to GitHub.

⸻

Testing

The system can be tested using the following scenarios:

Test Case	Expected Result
User Registration	User account created
User Login	User successfully logged in
Startup Submission	Startup stored in database
Startup Evaluation	Evaluation scores generated
View Startups	Submitted startups displayed
Admin Evaluation	Startup evaluated successfully
Rankings	Startups displayed according to score
Invalid Login	Login rejected

⸻

 Future Improvements

Possible future enhancements include:

* Machine Learning-based startup evaluation
* Real AI/LLM integration
* Startup valuation prediction
* Graphical user interface
* Web-based frontend
* REST API
* User authentication improvements
* Secure password hashing
* Startup analytics dashboard
* PDF evaluation reports
* Investor recommendation system

⸻

 Author

Varun Bhat
prajin jain
sunil g
shripati dev

GitHub:

https://github.com/varunbhat123

