package com.mangjakseon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // BaseEntity 자동 기입
public class MangjakseonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangjakseonApplication.class, args);
    }

}
