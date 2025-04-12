package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter
@Setter
@Builder
public class PaymentUrl implements Serializable {
    private String url;
}
