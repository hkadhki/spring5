package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevapp = new GenericContainer<>("devapp").withExposedPorts(8080);

    private static final GenericContainer<?> myProdapp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myDevapp.start();
        myProdapp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + myDevapp.getMappedPort(8080), String.class);
        System.out.println(forEntityDev.getBody());
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + myProdapp.getMappedPort(8081), String.class);
        System.out.println(forEntityProd.getBody());
        Assertions.assertEquals(forEntityDev.getBody(),"Current profile is dev");
        Assertions.assertEquals(forEntityProd.getBody(),"Current profile is production");
    }

}
