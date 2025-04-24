package com.example.api.client.controller;

import com.example.api.test.ApiTest;
import com.example.api.test.RestControllerTestBase;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 顧客検索APIのテスト。
 * 
 * @author sample
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DBRider
@ApiTest
class ClientSearchControllerTest extends RestControllerTestBase {

    public static final String ENDPOINT = "/clients";
    private static final String BASE_PATH = "com/example/api/client/controller/ClientSearchControllerTest/";

    /**
     * 不正な値の場合、精査エラー。
     * 
     */
    @Test
    @DataSet(BASE_PATH + "testInvalidParameters/testInvalidParameters.yml")
    void testInvalidParameters() {
        URI uri = UriComponentsBuilder.fromPath(ENDPOINT)
                .queryParam("clientName", "invalid parameter")
                .queryParam("industryCode", "04")
                .build().encode().toUri();
        RequestEntity<Void> request = RequestEntity.get(uri).build();

        ResponseEntity<String> response = http.exchange(request, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(forJson(response.getBody()))
                .isStrictlyEqualToJson(read("testInvalidParameters/expected.json"));
    }

    /**
     * 顧客名を条件に検索する。
     * 
     */
    @Test
    @DataSet(BASE_PATH + "testSearchClientByClientName/testSearchClientByClientName.yml")
    void testSearchClientByClientName() {
        URI uri = UriComponentsBuilder.fromPath(ENDPOINT)
                .queryParam("clientName", "テスト会社３")
                .build().encode().toUri();
        RequestEntity<Void> request = RequestEntity.get(uri).build();

        ResponseEntity<String> response = http.exchange(request, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(forJson(response.getBody()))
                .isStrictlyEqualToJson(read("testSearchClientByClientName/expected.json"));
    }
}
