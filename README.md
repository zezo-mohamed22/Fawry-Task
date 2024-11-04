# FawriTask E-commerce System

## Project Overview

This project is an interactive e-commerce system developed as part of the **Fawry Quantum Internship Challenge**. The application enables customers to add products to a cart, proceed to checkout, and handle shipping for items that require it. The system manages both expired and non-expired products, calculates shipping fees, and updates customer balance after each purchase.

## Class Diagram

![Class Diagram](https://github.com/user-attachments/assets/8cc16b56-5af0-4666-ac56-f8f1fc6b7dc2)

The class diagram above illustrates the structure of the e-commerce system and the relationships between the various classes:

- **Product**: The base class for all products.
- **ExpiredProduct**: Represents products with an expiration date.
- **NonExpiredProduct**: Represents products that do not expire.
- **Shippable**: Interface for products that require shipping.
- **ShippableExpiredProduct**: Expired products that are also shippable.
- **ShippableNonExpiredProduct**: Non-expired products that are shippable.
- **Cart**: Manages products added by the customer.
- **Customer**: Represents a customer with a balance and a cart.
- **ShippingService**: Handles the shipping of items implementing the `Shippable` interface.
- **Main**: Contains the entry point to run the application interactively.

## Test Case Example

![Test Case](https://github.com/user-attachments/assets/3b91a3b7-c818-4f84-ac27-f145f2e3c526)

### Description of the Test Case Output

In this example:

1. The user adds 2 units of Cheese, 1 unit of Biscuits, and 1 unit of TV to the cart.
2. During checkout, the system displays:
   - Each item with its quantity and total cost.
   - Subtotal for all items in the cart.
   - Shipping fees (if any).
   - Total amount charged.
   - Updated customer balance after checkout.
3. A **Shipment Notice** lists each shippable item with quantity, weight (in grams), and total package weight.

## Features

- **Interactive Console Application**: Users can interact with the application through a console-based menu.
- **Product Management**: Supports both expired and non-expired products.
- **Shipping Management**: Identifies shippable items and calculates shipping costs.
- **Error Handling**: Alerts users to expired products, out-of-stock items, and insufficient funds.
- **Checkout and Balance Updates**: Provides a detailed checkout receipt and updates the customer's balance.

## Running the Project

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **Git** (optional): If you plan to clone this project from a repository.

