package dariomorgrane.emphasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.dto.RatingJson;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class StatsControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpHeaders headers = new HttpHeaders();
    private static String expectedResponseBodyForUsers;
    private static String expectedResponseBodyForExchangeOperations;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

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
        expectedResponseBodyForUsers = MAPPER.writeValueAsString(users);
    }

    @BeforeAll
    static void composeExpectedResponseBodyForRatingStats() throws JsonProcessingException {
        List<RatingJson> rating = new ArrayList<>();
        rating.add(new RatingJson(8, "RUB", "EUR"));
        rating.add(new RatingJson(3, "EUR", "RUB"));
        rating.add(new RatingJson(2, "USD", "EUR"));
        expectedResponseBodyForExchangeOperations = MAPPER.writeValueAsString(rating);
    }

    @Test
    @Order(1)
    void setPreviouslyDataForUsersLimit() throws JsonProcessingException {
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
            doPostRequestToExchange(requestBody);
        }


    }

    @Test
    @Order(2)
    void setPreviouslyDataForRating() throws JsonProcessingException {

        for (int count = 1; count < 4; count++) {
            RequestJson requestJson = new RequestJson(20, 100, "EUR", "RUB");
            String requestBody = MAPPER.writeValueAsString(requestJson);
            doPostRequestToExchange(requestBody);
        }

        for (int count = 1; count < 3; count++) {
            RequestJson requestJson = new RequestJson(30, 100, "USD", "EUR");
            String requestBody = MAPPER.writeValueAsString(requestJson);
            doPostRequestToExchange(requestBody);
        }

    }

    private void doPostRequestToExchange(String requestBody) {
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
    }

    @Test
    @Order(3)
    void requestToStatsSingleShouldReturnUsersFrom4To7() {
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats?type=single&limit=10000", String.class);
        Assertions.assertEquals(expectedResponseBodyForUsers, responseBody);
    }

    @Test
    @Order(4)
    void requestToStatsCommonShouldReturnUsersFrom4To7() {
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats?type=common&limit=100000", String.class);
        Assertions.assertEquals(expectedResponseBodyForUsers, responseBody);
    }

    @Test
    @Order(5)
    void requestToStatsRatingShouldReturnExpectedRating() {
        String responseBody = restTemplate.getForObject("http://localhost:" + port + "/stats?type=rating", String.class);
        Assertions.assertEquals(expectedResponseBodyForExchangeOperations, responseBody);
    }

}
