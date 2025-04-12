package com.example.demo.services;


import com.example.demo.config.VNPayConfig;
import com.example.demo.dto.response.PaymentUrl;
import com.example.demo.exceptions.AppException;
import com.example.demo.utils.VNPayUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final VNPayConfig vnPayConfig;

    public PaymentUrl createVnPayPayment(String amountString, String bankCode, HttpServletRequest request) {
        long amount = Integer.parseInt(amountString) * 100L;
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);

        // build hash key from secret key and hashData
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String url = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentUrl.builder()
                .url(url)
                .build();
    }
}
