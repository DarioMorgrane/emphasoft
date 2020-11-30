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

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setUpHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void requestToStatsSingleShouldReturnUsersFrom4To7() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        for (long count = 1, amount = 770; count <= 8; count++, amount *= 10) {
            RequestJson requestJson = new RequestJson(count, amount, "RUB", "EUR");
            if (count == 8) {
                requestJson.setUserId(7);
            }
            String requestBody = objectMapper.writeValueAsString(requestJson);
            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
            restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
        }
        for (int count = 4; count <= 7; count++) {
            User user = new User();
            user.setId(count);
            users.add(user);
        }
        String expectedResponseBody = objectMapper.writeValueAsString(users);
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats/users?type=single&limit=10000", String.class);
        Assertions.assertEquals(expectedResponseBody, responseBody);
    }

}
