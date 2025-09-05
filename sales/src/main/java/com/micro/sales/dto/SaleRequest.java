package com.micro.sales.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleRequest {
    private List<Long> productIds;
}

