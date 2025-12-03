# 🎭 Performance Hall Event Management System (PHEM)

![Java](https://img.shields.io/badge/Language-Java-orange) ![GUI](https://img.shields.io/badge/Interface-Swing%20%2F%20AWT-blue) ![Status](https://img.shields.io/badge/Status-Completed-success)

## 📖 Overview
The **Performance Hall Event Management System** is a comprehensive Java desktop application designed to streamline the operations of a performance venue. It provides distinct interfaces for **Administrators** to manage event stock and **Customers** to browse, book, and pay for tickets securely.

The system utilizes file-based persistence (txt) for stock and user data handling.

---

## 🚀 Key Features

### 👤 Administrator Module
* **Inventory Oversight:** View all scheduled events with full attribute details.
* **Smart Sorting:** Events are automatically sorted by ticket price (ascending) for easier management.
* **Stock Management:** Capability to add new performance events directly to the system stock.

### 🛒 Customer Module
* **Event Browsing:** View available shows with "customer-facing" details (hiding sensitive internal fees).
* **Shopping Basket:**
    * ➕ Add tickets to a personal basket.
    * 👀 View current basket contents.
    * ❌ Cancel session and empty basket instantly.
* **Advanced Search & Filter:**
    * **Fast Look-up:** Search by specific Event ID.
    * **Language Filter:** Filter performances based on language.

### 💳 Payment & Transactions
A robust mock payment gateway supporting multiple methods:
* **PayPal Integration:** Validates email format and processes payment.
* **Credit Card Processing:** Validates 6-digit card numbers and 3-digit security codes.
* **Automated Receipts:** Generates detailed on-screen receipts including transaction amount, date, method, and billing address.
* **Stock Updates:** Automatically deducts sold tickets from inventory upon successful payment.

---

## 🛠️ Technology Stack
* **Language:** Java (SE)
* **GUI Framework:** Swing 
* **Layout Manager:** MigLayout
* **Data Persistence:** File I/O (Text files)

---

## ⚙️ Installation & Usage

 **Run the Application**
    Locate the `phem.jar` file and run the following command in your terminal:
    ```bash
    java -jar phem.jar
    ```

---

## 📂 Project Structure
The system is built on an Object-Oriented architecture. Below is a high-level view of the class relationships:

*(Note: You can upload your ClassDiagram.jpg to the repo and link it here like this: `![Class Diagram](ClassDiagram.jpg)`)*

* **AdminFrame:** Handles administrative UI and logic.
* **CustomerFrame:** Handles booking UI and logic.
* **Stock:** Manages the list of `Event` objects.
* **Basket:** Manages temporary ticket selection for the user.

---

## 🛡️ Error Handling
The application includes robust error handling to ensure stability, preventing crashes during invalid user input or file read/write operations.
