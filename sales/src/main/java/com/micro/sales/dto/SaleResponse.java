package com.micro.sales.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponse {
    private Long id;
    private String productsDescription;
    private Double total;
    private LocalDateTime createdAt;
}
