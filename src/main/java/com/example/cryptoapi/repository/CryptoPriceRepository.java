package com.example.cryptoapi.repository;

import com.example.cryptoapi.config.DatabaseConfig;
import com.example.cryptoapi.entity.CryptoPrice;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CryptoPriceRepository {
    private final DataSource dataSource;

    public CryptoPriceRepository() {
        this.dataSource = DatabaseConfig.getDataSource();
    }

    public void save(CryptoPrice cryptoPrice) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO crypto_prices (name, price) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cryptoPrice.getName());
                statement.setDouble(2, cryptoPrice.getPrice());
                statement.executeUpdate();
            }
        }
    }
}
