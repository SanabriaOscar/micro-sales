package com.micro.products.utils.response;

import com.micro.products.model.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCustom<T> {
    private int code;
    private String title;
    private String description;
    private String messageButton;
    private T data;

    public ResponseCustom(String number, List<Product> products) {
    }
}

