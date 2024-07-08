package com.example.cryptoapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crypto_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";

    public static DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        return new HikariDataSource(config);
    }
}
