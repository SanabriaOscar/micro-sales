package com.micro.sales.service;
import com.micro.sales.dto.SaleRequest;
import com.micro.sales.dto.SaleResponse;
import com.micro.sales.utils.response.ResponseCustom;

import java.util.List;

public interface ISaleService {
    ResponseCustom<SaleResponse> createSale(SaleRequest request);
    ResponseCustom<List<SaleResponse>> getAllSales();
    ResponseCustom<SaleResponse> updateSale(Long id, SaleRequest request);
}

