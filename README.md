# Hostel Management System

**A smart solution for managing university housing.**

This project was built for the **Object-Oriented Programming (OOP)** course. It is a complete system that connects **Students** looking for accommodation with **Admins** who manage the hostel.

##  Project Overview

Managing a hostel manually is hard. This system makes it easy. It handles the entire flow—from a student creating an account to getting a room key.

The system is designed with a specific logic:
* **Total Capacity:** The hostel has **5 Floors**.
* **Room Layout:** Each floor has exactly **5 Rooms**.
* **Privacy:** There is **1 Student per room** (Single occupancy).

##  Key Features

###  For Students
* **Easy Registration:** Students can create their own accounts easily.
* **Room Application:** Apply for a room with just a click. The request goes directly to the Admin.
* **Feedback System:** Students can share **complaints** or **feedback** about the hostel facilities.

### 🛡 For Admins
* **Admin Login:** Selected Admins on the code can access to the Admin Portal. (Admin **Username: Arpon**, **Password: Arpon**)
* **Application Management:** View pending requests and **Approve** or **Reject** them based on availability.
* **Room Allocation:** The system checks if a room is empty before assigning it.
* **Student Control:** Admins have full power to **add** or **remove** students from the hostel database.

##  How It Works (The Logic)

1.  **Sign Up:** A user signs up as a Student.
2.  **Apply:** The student applies for a room.
3.  **Verification:** The Admin sees the request.
4.  **Approval:**
    * If the Admin approves **AND** a room is free (out of the 25 total rooms), the student gets the room.
    * If the hostel is full, the request remains pending or is rejected.

##  Tech Stack

* **Language:** Java (Core)
* **Concepts Used:** OOP Principles (Encapsulation, Inheritance, Polymorphism), File Handling (if you used it), Exception Handling.

##  Contributors

* **[Arpon Sarker]** - *Lead Developer / Full Project Implementation*

---
*Created as part of the University Coursework. This project demonstrates strong logic building and OOP understanding.*