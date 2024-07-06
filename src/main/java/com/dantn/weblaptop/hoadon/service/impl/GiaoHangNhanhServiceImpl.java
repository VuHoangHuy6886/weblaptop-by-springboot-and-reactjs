package com.dantn.weblaptop.hoadon.service.impl;

import com.dantn.weblaptop.hoadon.service.GiaoHangThanhService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class GiaoHangNhanhServiceImpl implements GiaoHangThanhService {

     String GHN_BASE_URL = "https://online-gateway.ghn.vn/shiip/public-api/";
//    @Value("${api.ghn.token}")
    String TOKEN="0292ba75-34b6-11ef-89ca-1aad91406dac";
//    @Value("${api.ghn.shop-id}")
     String SHOP_ID="5160967 - 0397962849";
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String createOrder(Map<String, Object> orderData) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(orderData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GHN_BASE_URL + "v2/shipping-order/create"))
                .header("Token", TOKEN)
                .header("Content-Type", "application/json")
                .header("ShopId",SHOP_ID)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
