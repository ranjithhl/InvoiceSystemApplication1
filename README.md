# Invoice System Application

This is a simple Invoice Management System built using Spring Boot. The microservice allows for the creation of invoices, making payments on invoices, and processing overdue invoices. The system also handles partial payments and adds late fees to overdue invoices.

## Features

- **Create an Invoice**: Create an invoice with an amount and due date.
- **View All Invoices**: Retrieve a list of all invoices with their status (pending, paid, or void).
- **Make Payments**: Pay for an invoice partially or fully.
- **Process Overdue Invoices**: Apply late fees to overdue invoices and void unpaid invoices.

## Technologies

- **Java 17**
- **Spring Boot**
- **Postman (for testing the APIs)**
- **JUnit 5** (for testing)

## Setup and Running

### Prerequisites

Ensure that you have the following installed:

- Java 17

### Build and Run Locally

1. **Clone the repository**:

   ```bash
   git clone https://github.com/ranjithhl/InvoiceSystemApplication.git
   cd InvoiceSystemApplication
