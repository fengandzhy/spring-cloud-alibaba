package org.frank.order.controllers;

import org.junit.Test;
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

    @Autowired
    private TestRestTemplate template;
    
    @Test
    public void inventoryTest() throws InterruptedException {
        String url = "http://localhost:8000/api/v1/order/find/30";
        for(int i=0;i<100;i++){
            TimeUnit.SECONDS.sleep(1);
            int finalI = i;
            new Thread(() -> {
                try {
                    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                    ResponseEntity<String> response = template.postForEntity(url, params, String.class);
                    // Check if the response status code is 200 (OK)
                    assertEquals(200, response.getStatusCodeValue());
                    System.out.println("Request " + finalI + " succeeded with status code " + response.getStatusCodeValue());
                } catch (Exception e) {
                    System.err.println("Request " + finalI + " failed: " + e.getMessage());
                }
            }).start();
        }
    }
}
