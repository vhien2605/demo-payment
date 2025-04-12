package com.example.demo.controllers;


import com.example.demo.dto.response.ResponseObject;
import com.example.demo.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ResponseObject<?> pay(@RequestParam(value = "amount") String amount
            , @RequestParam(value = "bankCode") String bankCode
            , HttpServletRequest request) {
        return ResponseObject.builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(paymentService.createVnPayPayment(amount, bankCode, request))
                .build();
    }

    @GetMapping("/vn-pay-callback")
    public ResponseObject<?> payCallbackHandler(@RequestParam(value = "vnp_ResponseCode") String status) {
        if (status.equals("00")) {
            return ResponseObject.builder()
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .data(null)
                    .build();
        } else {
            return ResponseObject.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("failed")
                    .data(null)
                    .build();
        }
    }
}
