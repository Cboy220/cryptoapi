package com.example.cryptoapi.service;

import com.example.cryptoapi.entity.CryptoPrice;

public interface CryptoPriceService {
    void saveCryptoPrice(CryptoPrice cryptoPrice) throws Exception;
}
