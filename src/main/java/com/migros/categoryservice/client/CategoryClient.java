package com.migros.categoryservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service", url = "localhost:8080")
public interface CategoryClient {
    public boolean verifyCategoryCode(String categoryCode);
}
