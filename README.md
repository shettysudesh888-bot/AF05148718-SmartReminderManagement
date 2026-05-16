# Smart Reminder Manager (Java + JDBC + MySQL)

## 📌 Project Overview

Smart Reminder Manager is a console-based Java application developed using JDBC and MySQL.
The project helps users efficiently manage reminders by allowing them to add, view, search, update, and delete reminders.

The application demonstrates:

* CRUD Operations
* JDBC Connectivity
* MySQL Database Integration
* Object-Oriented Programming Concepts
* Modular Java Project Structure

---

## 🚀 Features

* Add Reminder
* View All Reminders
* Search Reminder
* Update Reminder
* Delete Reminder
* Mark Reminder as Completed
* Filter Reminders by Status
* Reminder Statistics

---

## 🛠️ Technologies Used

| Technology  | Purpose                      |
| ----------- | ---------------------------- |
| Java        | Core application development |
| JDBC        | Database connectivity        |
| MySQL       | Database storage             |
| Eclipse IDE | Development environment      |

---

## 🗂️ Project Structure

```text id="j0rjgx"
com.jdbc.main
   └── MainApp.java

com.jdbc.dao
   └── ReminderDAO.java

com.jdbc.model
   └── Reminder.java

com.jdbc.util
   └── DBConnection.java
```

---

## 🗄️ Database Design

### Database Name

```sql id="8w4d26"
reminder_db
```

### Table Name

```sql id="dldnzh"
reminders
```

### Table Columns

| Column Name | Data Type |
| ----------- | --------- |
| id          | INT       |
| title       | VARCHAR   |
| description | TEXT      |
| remind_date | DATE      |
| status      | VARCHAR   |
| priority    | VARCHAR   |
| category    | VARCHAR   |

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash id="4bhxhi"
git clone <repository-link>
```

### 2. Import Project

* Open Eclipse IDE
* Import existing Java project

### 3. Configure MySQL

Create database:

```sql id="jlwmfj"
CREATE DATABASE reminder_db;
```

### 4. Create Table

Run SQL table creation script.

### 5. Add MySQL Connector

* Download MySQL Connector/J
* Add JAR to project Build Path

### 6. Run Application

Run:

```text id="jlwmup"
MainApp.java
```

---

## 📷 Sample Console Output

```text id="lrzks9"
=== SMART REMINDER MANAGER ===

1. Add Reminder
2. View Reminders
3. Search Reminder
4. Update Reminder
5. Delete Reminder
6. Mark as Completed
7. Filter by Status
8. View Statistics
0. Exit
```

---

## 📚 Concepts Used

* JDBC API
* PreparedStatement
* ResultSet
* CRUD Operations
* Exception Handling
* OOP Concepts
* Modular Architecture

---

## 🔮 Future Enhancements

* GUI using JavaFX or Swing
* Email Notifications
* Authentication System
* Mobile Integration
* Cloud Database Support

---

## 👨‍💻 Developer

**Sudesh S Shetty**
ID: AF05148718
School of Computer Application
Dayananda Sagar University

---

## 📄 License

This project is developed for academic and learning purposes.

Linkedin:https://www.linkedin.com/in/sudesh-shetty-061838319
