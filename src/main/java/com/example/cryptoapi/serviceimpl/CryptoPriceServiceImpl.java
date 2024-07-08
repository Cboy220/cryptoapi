package com.example.cryptoapi.serviceimpl;

import com.example.cryptoapi.entity.CryptoPrice;
import com.example.cryptoapi.repository.CryptoPriceRepository;
import com.example.cryptoapi.service.CryptoPriceService;

public class CryptoPriceServiceImpl implements CryptoPriceService {
    private final CryptoPriceRepository cryptoPriceRepository;

    public CryptoPriceServiceImpl() {
        this.cryptoPriceRepository = new CryptoPriceRepository();
    }

    @Override
    public void saveCryptoPrice(CryptoPrice cryptoPrice) throws Exception {
        cryptoPriceRepository.save(cryptoPrice);
    }
}
