package dariomorgrane.emphasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatsControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    private static String expectedResponseBody;

    @BeforeAll
    static void setUpHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeAll
    static void composeExpectedResponseBodyForUserStats() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        for (int count = 4; count <= 7; count++) {
            User user = new User();
            user.setId(count);
            users.add(user);
        }
        expectedResponseBody = MAPPER.writeValueAsString(users);
    }

    @Test
    @Order(1)
    void setPreviouslyData() throws JsonProcessingException {
        for (long count = 1; count <= 8; count++) {
            double amount;
            if (count < 4) {
                amount = 1;
            } else {
                amount = 100000000;
            }
            RequestJson requestJson = new RequestJson(count, amount, "RUB", "EUR");
            if (count == 8) {
                requestJson.setUserId(7);
            }
            String requestBody = MAPPER.writeValueAsString(requestJson);
            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
            restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
        }
    }

    @Test
    @Order(2)
    void requestToStatsSingleShouldReturnUsersFrom4To7() {
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats?type=single&limit=10000", String.class);
        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

    @Test
    @Order(3)
    void requestToStatsCommonShouldReturnUsersFrom4To7() {
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats?type=common&limit=100000", String.class);
        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

}
