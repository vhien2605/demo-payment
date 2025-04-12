package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class ResponseObject  <T> implements Serializable {
    private int code;
    private String message;
    private T data;
}
