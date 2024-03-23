package fun.debaucherydungeon.http;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpClient {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public HttpClient() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String get(String url) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }
}