package com.micro.products.exceptions.model;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionGeneral {
    private String code;
    private  String message;
}

