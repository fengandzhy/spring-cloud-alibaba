package org.frank.order.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    private final TestRestTemplate template;

    @Autowired
    public OrderControllerTest(TestRestTemplate template) {
        this.template = template;
    }   
    
    @Test // 注意这里的@Test一定要用Junit5的
    public void getOrderByIdTest() throws InterruptedException {
        String url = "/api/v1/order/find/30";
        for(int i=0;i<100;i++){
            Thread.sleep(50);
            int finalI = i;
            new Thread(() -> {
                try {
                    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                    ResponseEntity<String> response = template.postForEntity(url, params, String.class);                   
                    assertEquals(200, response.getStatusCodeValue());                    
                    System.out.println("Request " + finalI + " Response body: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Request " + finalI + " failed: " + e.getMessage());
                }
            }).start();
        }
    }
}
