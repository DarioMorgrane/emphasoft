package dariomorgrane.emphasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final HttpHeaders headers = new HttpHeaders();

    @BeforeAll
    static void setUpHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void postRequestShouldMatchExpectedResult() throws JsonProcessingException {
        for (int count = 1; count <= 5; count++) {
            RequestJson requestJson = new RequestJson(1, count * 100, "RUB", "RUB");
            ResponseJson responseJson = new ResponseJson(count, count * 100);

            String requestBody = objectMapper.writeValueAsString(requestJson);
            String expectedResponseBody = objectMapper.writeValueAsString(responseJson);

            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
            String responseBody = restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
            Assertions.assertEquals(expectedResponseBody, responseBody);
        }
    }

}
