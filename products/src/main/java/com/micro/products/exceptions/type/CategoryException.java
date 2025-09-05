package com.micro.products.exceptions.type;

import com.micro.products.utils.response.ResponseCustom;
import lombok.Getter;

@Getter
public class CategoryException extends RuntimeException {

    private final ResponseCustom<Object> response;

    public CategoryException(ResponseCustom<Object> response) {
        super(response.getDescription());
        this.response = response;
    }

}
