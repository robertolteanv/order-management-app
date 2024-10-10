# Order Management Application

## Overview
This is an Order Management Application developed in **Java** with a **MySQL** database and **Swing** for the graphical user interface. The application allows users to manage clients, products, and orders through a user-friendly interface.

## Features
- **Client Management**: Add, update, delete, and view clients.
- **Product Management**: Add, update, delete, and view products.
- **Order Placement**: Create orders by selecting clients and products, with stock validation.
  
## Technologies Used
- **Java**: Core language used for business logic.
- **Swing**: Used for building the GUI.
- **MySQL**: Database management system for storing clients, products, and orders.
- **JDBC**: For database connectivity.

## Project Structure
- `presentation`: Java Swing components and UI controllers.
- `businesslogic`: Core business logic and validations.
- `dataaccess`: Database operations (CRUD).
- `model`: Data structures (clients, products).
- `connection`: Database connection management.

## How to Run
1. Set up the **MySQL** database using the provided schema.
2. Configure database connection parameters in the `ConnectionFactory` class.
3. Run the project in your favorite IDE (e.g., Eclipse, IntelliJ IDEA).
4. Interact with the UI to manage clients, products, and orders.

