package com.micro.sales.utils.response;

import lombok.*;


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
}

