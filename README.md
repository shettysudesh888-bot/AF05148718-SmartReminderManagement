📌 Smart Reminder Manager (Java + JDBC)
📖 Project Description

Smart Reminder Manager is a console-based application developed using Java and JDBC that allows users to manage their daily reminders efficiently.
The system supports full CRUD operations along with status tracking for reminders.

🚀 Features
➕ Add new reminders
📋 View all reminders
❌ Delete reminders
✅ Mark reminders as completed
🔄 Real-time database interaction using JDBC


🛠️ Technologies Used
Java
JDBC (Java Database Connectivity)
MySQL Database
Eclipse IDE


🗄️ Database Structure
CREATE DATABASE reminder_db;

USE reminder_db;

CREATE TABLE reminders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(255),
    remind_date DATE,
    status VARCHAR(20)
);


▶️ How to Run
Install JDK and Eclipse
Setup MySQL database
Add MySQL Connector JAR to project
Update database credentials in DBConnection.java
Run MainApp.java


💡 Sample Output
1. Add Reminder
2. View Reminders
3. Delete Reminder
4. Mark Completed
5. Exit


📌 Example Use Case
Manage study schedule
Track meetings and deadlines
Maintain daily tasks


🎯 Learning Outcomes
Understanding of JDBC connectivity
Implementation of CRUD operations
Working with SQL queries in Java
Structured project design using packages


👨‍💻 Author
Sudesh S Shetty

⭐ Future Enhancements
Search reminders
Filter by date
GUI version (Swing/JavaFX)
Notification system

Linkedin:https://www.linkedin.com/in/sudesh-shetty-061838319
