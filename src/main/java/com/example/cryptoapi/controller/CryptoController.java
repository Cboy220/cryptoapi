package com.example.cryptoapi.controller;

import com.example.cryptoapi.entity.CryptoPrice;
import com.example.cryptoapi.service.CryptoPriceService;
import com.example.cryptoapi.serviceimpl.CryptoPriceServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Path("/crypto")
@Tag(name = "Crypto API", description = "API for Crypto Prices")
public class CryptoController {
    private static final String COINGECKO_API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
    private final CryptoPriceService cryptoPriceService;

    public CryptoController() {
        this.cryptoPriceService = new CryptoPriceServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Bitcoin price from CoinGecko and save to database")
    public Response getBitcoinPrice() throws IOException {
        String jsonResponse = getExternalApiData(COINGECKO_API_URL);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonResponse);
        double price = jsonNode.get("bitcoin").get("usd").asDouble();
        CryptoPrice cryptoPrice = new CryptoPrice("bitcoin", price);

        try {
            cryptoPriceService.saveCryptoPrice(cryptoPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok(jsonResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Save Crypto Price to database")
    public Response saveCryptoPrice(CryptoPrice cryptoPrice) {
        try {
            cryptoPriceService.saveCryptoPrice(cryptoPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }

    private String getExternalApiData(String apiUrl) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            return responseBuilder.toString();
        }
    }
}
