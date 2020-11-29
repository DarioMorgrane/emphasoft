package dariomorgrane.emphasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.dto.RequestJson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExchangeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    static void setUpHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    void postRequestShouldCorrectResponse() throws JsonProcessingException {
        RequestJson requestJson = new RequestJson();
        requestJson.setAmountInOriginalCurrency(100);
        requestJson.setOriginalCurrency("USD");
        requestJson.setTargetCurrency("UAH");
        requestJson.setUserId(1);
        String expectedResult = "{\"requestId\":1,\"amountInTargetCurrency\":100}";
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestJson);
        HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        String responseBody = restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
        Assertions.assertEquals(expectedResult, responseBody);
    }

}
