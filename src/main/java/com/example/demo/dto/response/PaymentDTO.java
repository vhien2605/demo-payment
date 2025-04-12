package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Builder
public class PaymentDTO implements Serializable {
    public String code;
    public String message;
    public String paymentUrl;
}
