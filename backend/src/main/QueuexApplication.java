package com.queuex.api;
import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication; import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication @EnableScheduling public class QueuexApplication { public static void main(String[] args){SpringApplication.run(QueuexApplication.class,args);} }
