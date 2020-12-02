package dariomorgrane.emphasoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dariomorgrane.emphasoft.dto.RequestJson;
import dariomorgrane.emphasoft.dto.ResponseJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeControllerTest implements ApplicationContextAware {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @BeforeAll
    static void setUpHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void postRequestShouldMatchExpectedResult() throws JsonProcessingException, SQLException {
        for (int count = 1; count <= 5; count++) {
            RequestJson requestJson = new RequestJson(1, count * 100, "RUB", "RUB");
            ResponseJson responseJson = new ResponseJson(count, count * 100);

            String requestBody = objectMapper.writeValueAsString(requestJson);
            String expectedResponseBody = objectMapper.writeValueAsString(responseJson);

            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
            String responseBody = restTemplate.postForObject("http://localhost:" + port + "/exchange", request, String.class);
            Assertions.assertEquals(expectedResponseBody, responseBody);
        }
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        jdbcTemplate.execute("DELETE FROM exchange_operations;");
        jdbcTemplate.execute("DELETE FROM users;");
        DataSourceUtils.releaseConnection(jdbcTemplate.getDataSource().getConnection(), jdbcTemplate.getDataSource());
    }


}
