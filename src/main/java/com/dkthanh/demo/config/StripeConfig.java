package com.dkthanh.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    @Value("${stripe.publickey}")
    private String publicKey;
    @Value("${stripe.secretkey}")
    private String secretKey;
}
