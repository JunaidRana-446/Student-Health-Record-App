# 🩺 Student Health Record Management System

[![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)](https://www.java.com/)  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A Java Swing desktop application to manage student health records using file serialization.

---

## 📖 Project Description

The **Student Health Record Management System** is a Java-based GUI application designed for schools or colleges to store and manage student medical information.  
Users can add, view, and search student health records, which are stored persistently using serialization.

---

## 🌟 Features

- Add student health records (ID, name, age, gender, blood group, medical conditions)  
- View all saved student records  
- Search records by Student ID  
- Data persistence using `records.dat` file  
- Clean, user-friendly GUI built with Java Swing  

---

## 🧰 Technologies Used

| Technology | Purpose |
|------------|---------|
| Java       | Core programming language |
| Swing      | GUI design |
| Serialization | Save and load objects to file |
| OOP        | Data modeling |
| File I/O   | Data persistence |

---

## 🗂 Project Structure

StudentHealthRecordApp/
src/

StudentHealthRecord.java – Model class for student health records

HealthRecordApp.java – Main GUI and application logic

records.dat – Auto-generated data file (stores serialized records)

---

## 🧩 How It Works

1. **Adding a Record**: Fill in the form → click *Add Record* → saved to `records.dat`.  
2. **Viewing Records**: Click *View All* → loads all records into a list.  
3. **Searching Records**: Click *Search by ID* → enter ID → displays detailed information.  
4. **Data Persistence**: Records remain saved even after closing the application.

---

## 💻 Installation

### Requirements

- Java JDK 8 or above  
- IDE (IntelliJ, Eclipse, NetBeans) or Command Line  

### Using an IDE

1. Create a new Java project.  
2. Add `StudentHealthRecord.java` and `HealthRecordApp.java` to the `src` folder.  
3. Run `HealthRecordApp.java`.

### Using Command Line

```bash
# Compile
javac StudentHealthRecord.java HealthRecordApp.java

# Run
java HealthRecordApp

