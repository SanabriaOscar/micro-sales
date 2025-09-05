package com.micro.sales.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service", url = "${products.service.url}", dismiss404 = true)
public interface ProductClient {

    @GetMapping("/api/products")
    ResponseEntity<Object> getAllProducts();

    @GetMapping("/api/products/{id}")
    ResponseEntity<Object> getProductById(@PathVariable("id") Long id);
}
