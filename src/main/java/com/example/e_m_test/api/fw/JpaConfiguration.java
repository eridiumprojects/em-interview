package com.example.e_m_test.api.fw;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.example.e_m_test.api.domain")
public class JpaConfiguration {
}