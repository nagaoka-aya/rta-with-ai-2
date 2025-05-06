package com.example.web.client.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.web.common.exception.BusinessException;
import com.example.web.common.exception.SystemException;

/**
 * 顧客管理システムAPIとの連携を行うクライアントクラス
 */
@Component
public class ClientApiClient {

    /** APIのベースURL */
    @Value("${api.client.base-url}")
    private String baseUrl;

    /** RestTemplate */
    private final RestTemplate restTemplate;

    /**
     * コンストラクタ
     * 
     * @param restTemplate RestTemplate
     */
    public ClientApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 顧客IDから顧客名を取得する
     * 
     * @param clientId 顧客ID
     * @return 顧客名
     */
    public String getClientName(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            return null;
        }

        try {
            String url = baseUrl + "/clients/{clientId}";
            ResponseEntity<ClientResponse> response = restTemplate.getForEntity(
                    url,
                    ClientResponse.class,
                    clientId);

            if (response.getBody() == null) {
                throw new BusinessException("errors.client.notfound", new Object[] { clientId });
            }

            return response.getBody().getClientName();
        } catch (HttpClientErrorException.NotFound e) {
            // 404エラーの場合は業務例外
            throw new BusinessException("errors.client.notfound", new Object[] { clientId });
        } catch (Exception e) {
            // その他のエラーはシステム例外
            throw new SystemException("errors.client.systemerror", e, new Object[] { clientId });
        }
    }

    /**
     * 顧客APIレスポンスクラス
     */
    private static class ClientResponse {
        private String clientId;
        private String clientName;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }
    }
}
