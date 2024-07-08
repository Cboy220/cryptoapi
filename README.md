# Crypto API

This project is a comprehensive implementation of a Crypto API using Java, Jetty, and Jersey. It fetches Bitcoin prices from the CoinGecko API and stores them in a MySQL database. The project utilizes Swagger UI for API documentation, making it easy to test and explore the API endpoints.

## Features

- **Fetch Bitcoin Prices**: Retrieves current Bitcoin prices from the CoinGecko API.
- **Store Prices in MySQL**: Saves fetched prices into a MySQL database.
- **Swagger UI**: Provides an interactive interface for exploring and testing the API.
- **Layered Architecture**: Follows best practices with a structured approach using controllers, services, and repositories.

## Technologies Used

- **Java**: Core programming language.
- **Jetty**: Server to run the application.
- **Jersey**: RESTful web services framework.
- **MySQL**: Database to store crypto prices.
- **Swagger UI**: Tool for API documentation and testing.

## Project Structure

```
cryptoapi
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── example
        │           └── cryptoapi
        │               ├── Main.java
        │               ├── config
        │               │   ├── SwaggerConfig.java
        │               ├── controller
        │               │   └── CryptoController.java
        │               ├── entity
        │               │   └── CryptoPrice.java
        │               ├── repository
        │               │   └── CryptoPriceRepository.java
        │               ├── service
        │               │   └── CryptoPriceService.java
        │               └── serviceimpl
        │                   └── CryptoPriceServiceImpl.java
        └── resources
            ├── META-INF
            │   └── resources
            │       └── webjars
            │           └── swagger-ui
            │               ├── swagger-ui.css
            │               ├── swagger-ui.js
            │               ├── index.html
            │               └── diğer swagger-ui dosyaları...
            └── api
                └── openapi.json
```

## Getting Started

### Prerequisites

- Java 11
- Maven
- MySQL

### Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/YOUR_GITHUB_USERNAME/cryptoapi.git
    cd cryptoapi
    ```

2. **Set up MySQL database**:
    ```sql
    CREATE DATABASE crypto_db;
    CREATE USER 'crypto_user'@'localhost' IDENTIFIED BY 'password';
    GRANT ALL PRIVILEGES ON crypto_db.* TO 'crypto_user'@'localhost';
    FLUSH PRIVILEGES;
    ```

3. **Configure database connection** in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/crypto_db
    spring.datasource.username=crypto_user
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    ```

4. **Build and run the project**:
    ```bash
    mvn clean install
    mvn exec:java -Dexec.mainClass="com.example.cryptoapi.Main"
    ```

5. **Access Swagger UI**:
    Open your browser and go to `http://localhost:8080/swagger-ui/index.html` to explore and test the API.

## API Endpoints

### Fetch Bitcoin Price

- **URL**: `/api/crypto`
- **Method**: `GET`
- **Description**: Retrieves the current Bitcoin price from the CoinGecko API and stores it in the database.

### Save Crypto Price

- **URL**: `/api/crypto`
- **Method**: `POST`
- **Description**: Saves a crypto price to the database.
- **Request Body**:
    ```json
    {
      "name": "bitcoin",
      "price": 45000.0
    }
    ```

## Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/fooBar`).
3. Commit your changes (`git commit -m 'Add some fooBar'`).
4. Push to the branch (`git push origin feature/fooBar`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [CoinGecko API](https://www.coingecko.com/en/api) for providing cryptocurrency data.
- [Swagger](https://swagger.io/) for API documentation and testing tools.
- [Jetty](https://www.eclipse.org/jetty/) for the web server.
- [Jersey](https://eclipse-ee4j.github.io/jersey/) for the RESTful web services framework.
