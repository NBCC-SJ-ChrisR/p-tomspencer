
# Thomas Spencer Pizza Project API Guide

## Overview

The  API allows users to interact with a pizza ordering system programmatically.
It provides RESTful endpoints for managing customers, employees, orders, pizzas, and associated data such as crusts, sizes, and toppings.

### Key Features:
- **Customer Management**: Create, update, and authenticate customers.
- **Employee Management**: Manage employee login and retrieve employee details.
- **Order Management**: Create, view, and delete orders.
- **Pizza Customization**: Build pizzas with crusts, sizes, and toppings.
- **Lookup Options**: Retrieve available crusts, sizes, and toppings.

### Authentication
This API does not currently use authentication tokens or sessions. However, employee and customer login mechanisms are supported for validating credentials.

---

## Error Handling

The API uses standard HTTP status codes to indicate the outcome of API requests. Below is a list of possible error responses:

### Common Error Codes

| Status Code | Meaning                          | Example Scenario                                                               |
|-------------|----------------------------------|--------------------------------------------------------------------------------|
| 400 Bad Request | The request is malformed or missing required data. | Submitting an invalid password during customer creation.                       |
| 401 Unauthorized | Authentication failed due to invalid credentials. | Incorrect email/password during login.                                         |
| 404 Not Found    | The requested resource was not found.             | Trying to retrieve a customer or pizza that does not exist.                    |
| 409 Conflict     | A resource conflict occurred (e.g., duplicate entry). | Attempting to create a customer with an existing email orempty password field. |
| 500 Internal Server Error | An unexpected error occurred on the server. | Any unhandled exception in the backend logic.                                  |

### Example Error Responses

#### **400 Bad Request**
- **Scenario**: Submitting a short password during customer creation.
- **Response**:
    ```json
    {
      "message": "Password must be at least 6 characters long."
    }
    ```

#### **401 Unauthorized**
- **Scenario**: Incorrect email or password during login.
- **Response**:
    ```json
    {
      "message": "Invalid email or password."
    }
    ```

#### **404 Not Found**
- **Scenario**: Retrieving a customer by an ID that doesn’t exist.
- **Response**:
    ```json
    {
      "message": "Customer with ID 999 not found."
    }
    ```

#### **409 Conflict**
- **Scenario**: Creating a customer with an already registered email.
- **Response**:
    ```json
    {
      "message": "Email already exists."
    }
    ```

#### **500 Internal Server Error**
- **Scenario**: Unhandled server-side errors during order creation.
- **Response**:
    ```json
    {
      "message": "An unexpected error occurred while processing your request."
    }
    ```

---
## Endpoint Overview

### Customer Endpoints
- `GET /customers`: Retrieve all customers.
- `GET /customers/{id}`: Retrieve a specific customer by ID.
- `POST /customers`: Create a new customer.
- `PUT /customers/{id}`: Update an existing customer.
- `POST /customers/login`: Authenticate a customer.

### Employee Endpoints
- `GET /employees`: Retrieve all employees.
- `GET /employees/{id}`: Retrieve a specific employee by ID.
- `POST /employees/login`: Authenticate an employee.

### Order Endpoints
- `GET /orders`: Retrieve all orders.
- `POST /orders`: Create a new order.
- `DELETE /orders/{id}`: Delete a specific order.

### Pizza Endpoints
- `GET /pizzas`: Retrieve all pizzas.
- `POST /pizzas`: Create a new pizza.
- `DELETE /pizzas/{id}`: Delete a specific pizza.

### Pizza Topping Map Endpoints
- `GET /pizzaToppingMap`: Retrieve all pizzas.
- `POST /pizzaToppingMap`: Create a new pizza topping map.
- `DELETE /pizzaToppingMap/{id}`: Delete a specific pizza topping map.

### Crust, Size, and Topping Endpoints
- `GET /pizzaCrusts`: Retrieve all available crusts.
- `GET /pizzaSizes`: Retrieve all available sizes.
- `GET /pizzaToppings`: Retrieve all available toppings.

---

## Detailed Endpoint Documentation

### Customer Endpoints

#### `GET /customers`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "customerId": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "1234567890",
        "houseNumber": 101,
        "street": "Main St",
        "province": "NB",
        "postalCode": "E2L 4Z6"
      }
    ]
    ```

#### `GET /customers/{id}`
- **Method**: `GET`
- **Request**:
    - **Path Parameters**:
        - `id` (integer): The ID of the customer.
- **Response**:
    ```json
    {
      "customerId": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "phoneNumber": "1234567890",
      "houseNumber": 101,
      "street": "Main St",
      "province": "NB",
      "postalCode": "E2L 4Z6"
    }
    ```

#### `POST /customers`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "firstName": "John",
          "lastName": "Doe",
          "email": "john.doe@example.com",
          "phoneNumber": "1234567890",
          "houseNumber": 101,
          "street": "Main St",
          "province": "NB",
          "postalCode": "E2L 4Z6",
          "password": "securepassword"
        }
        ```
- **Response**:
    ```json
    {
      "customerId": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "phoneNumber": "1234567890",
      "houseNumber": 101,
      "street": "Main St",
      "province": "NB",
      "postalCode": "E2L 4Z6"
    }
    ```

#### `PUT /customers/{id}`
- **Method**: `PUT`
- **Request**:
    - **Path Parameters**:
        - `id` (integer): The ID of the customer.
    - **Body**:
        ```json
        {
          "firstName": "Jane",
          "lastName": "Doe",
          "email": "jane.doe@example.com",
          "phoneNumber": "0987654321",
          "houseNumber": 102,
          "street": "Elm St",
          "province": "NB",
          "postalCode": "E3L 5R7"
        }
        ```
- **Response**:
    ```json
    {
      "customerId": 1,
      "firstName": "Jane",
      "lastName": "Doe",
      "email": "jane.doe@example.com",
      "phoneNumber": "0987654321",
      "houseNumber": 102,
      "street": "Elm St",
      "province": "NB",
      "postalCode": "E3L 5R7"
    }
    ```

#### `POST /customers/login`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "email": "john.doe@example.com",
          "password": "securepassword"
        }
        ```
- **Response**:
    ```json
    {
      "customerId": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "phoneNumber": "1234567890",
      "houseNumber": 101,
      "street": "Main St",
      "province": "NB",
      "postalCode": "E2L 4Z6"
    }
    ```

---

## Employee Endpoints

#### `GET /employees`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "employeeId": 1,
        "username": "admin",
        "firstName": "John",
        "lastName": "Doe",
        "role": "Manager"
      }
    ]
    ```

#### `POST /employees/login`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "username": "admin",
          "password": "securepassword"
        }
        ```
- **Response**:
    ```json
    {
      "employeeId": 1,
      "username": "admin",
      "firstName": "John",
      "lastName": "Doe",
      "role": "Manager"
    }
    ```

---

## Order Endpoints

#### `GET /orders`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "orderId": 101,
        "customer": {
          "customerId": 1,
          "firstName": "John",
          "lastName": "Doe"
        },
        "subTotal": 25.0,
        "hst": 3.75,
        "orderTotal": 28.75,
        "delivery": true,
        "orderStatus": "PENDING",
        "deliveryDate": "2024-12-06T14:30:00",
        "orderPlacedDate": "2024-12-06T12:00:00"
      }
    ]
    ```

#### `POST /orders`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "customer": {
            "customerId": 1
          },
          "subTotal": 25.0,
          "hst": 3.75,
          "orderTotal": 28.75,
          "delivery": true,
          "deliveryDate": "2024-12-06T14:30:00",
          "orderPlacedDate": "2024-12-06T12:00:00"
        }
        ```
- **Response**:
    ```json
    {
      "orderId": 101,
      "customer": {
        "customerId": 1,
        "firstName": "John",
        "lastName": "Doe"
      },
      "subTotal": 25.0,
      "hst": 3.75,
      "orderTotal": 28.75,
      "delivery": true,
      "orderStatus": "PENDING",
      "deliveryDate": "2024-12-06T14:30:00",
      "orderPlacedDate": "2024-12-06T12:00:00"
    }
    ```

#### `DELETE /orders/{id}`
- **Method**: `DELETE`
- **Request**:
    - **Path Parameters**:
        - `id` (integer): The ID of the order.
- **Response**:
    ```
    204 No Content
    ```

---

## Pizza Endpoints

#### `GET /pizzas`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "pizzaId": 201,
        "orderId": 101,
        "pizzaSize": {
          "pizzaSizeId": 1,
          "name": "Large",
          "price": 15.0
        },
        "pizzaCrust": {
          "pizzaCrustId": 2,
          "name": "Thin",
          "price": 2.0
        },
        "quantity": 2,
        "priceEach": 17.0,
        "totalPrice": 34.0
      }
    ]
    ```

#### `POST /pizzas`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "order": {
            "orderId": 101
          },
          "pizzaSize": {
            "pizzaSizeId": 1
          },
          "pizzaCrust": {
            "pizzaCrustId": 2
          },
          "quantity": 2,
          "priceEach": 17.0,
          "totalPrice": 34.0
        }
        ```
- **Response**:
    ```json
    {
      "pizzaId": 201,
      "orderId": 101,
      "pizzaSize": {
        "pizzaSizeId": 1,
        "name": "Large",
        "price": 15.0
      },
      "pizzaCrust": {
        "pizzaCrustId": 2,
        "name": "Thin",
        "price": 2.0
      },
      "quantity": 2,
      "priceEach": 17.0,
      "totalPrice": 34.0
    }
    ```

#### `DELETE /pizzas/{id}`
- **Method**: `DELETE`
- **Request**:
    - **Path Parameters**:
        - `id` (integer): The ID of the pizza.
- **Response**:
    ```
    204 No Content
    ```

---

## Pizza Topping Map Endpoints

#### `GET /pizzaToppingMap`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "pizzaToppingMapId": 1,
        "pizza": {
          "pizzaId": 201,
          "orderId": 101,
          "quantity": 2
        },
        "pizzaTopping": {
          "pizzaToppingId": 1,
          "name": "Pepperoni",
          "price": 1.5
        }
      },
      {
        "pizzaToppingMapId": 2,
        "pizza": {
          "pizzaId": 201,
          "orderId": 101,
          "quantity": 2
        },
        "pizzaTopping": {
          "pizzaToppingId": 2,
          "name": "Mushrooms",
          "price": 1.0
        }
      }
    ]
    ```

#### `POST /pizzaToppingMap`
- **Method**: `POST`
- **Request**:
    - **Body**:
        ```json
        {
          "pizza": {
            "pizzaId": 201
          },
          "pizzaTopping": {
            "pizzaToppingId": 1
          }
        }
        ```
- **Response**:
    ```json
    {
      "pizzaToppingMapId": 3,
      "pizza": {
        "pizzaId": 201,
        "orderId": 101,
        "quantity": 2
      },
      "pizzaTopping": {
        "pizzaToppingId": 1,
        "name": "Pepperoni",
        "price": 1.5
      }
    }
    ```

#### `DELETE /pizzaToppingMap/{id}`
- **Method**: `DELETE`
- **Request**:
    - **Path Parameters**:
        - `id` (integer): The ID of the pizza topping map entry.
- **Response**:
    ```
    204 No Content
    ```

## Crust, Size, and Topping Endpoints

#### `GET /pizzaCrusts`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "pizzaCrustId": 1,
        "name": "Thin",
        "price": 2.0
      },
      {
        "pizzaCrustId": 2,
        "name": "Thick",
        "price": 3.0
      }
    ]
    ```

#### `GET /pizzaSizes`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "pizzaSizeId": 1,
        "name": "Small",
        "price": 8.0
      },
      {
        "pizzaSizeId": 2,
        "name": "Large",
        "price": 15.0
      }
    ]
    ```

#### `GET /pizzaToppings`
- **Method**: `GET`
- **Request**: None
- **Response**:
    ```json
    [
      {
        "pizzaToppingId": 1,
        "name": "Pepperoni",
        "price": 1.5
      },
      {
        "pizzaToppingId": 2,
        "name": "Mushrooms",
        "price": 1.0
      }
    ]
    ```
