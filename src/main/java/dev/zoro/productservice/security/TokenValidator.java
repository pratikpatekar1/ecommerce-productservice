package dev.zoro.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {

    private RestTemplateBuilder restTemplateBuilder;

    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "http://localhost:8081/auth/validate";
        Boolean tokenValid = restTemplate.postForObject(url, token, Boolean.class);
        if(!tokenValid)return Optional.empty();
        //TODO: use JwtObject
        JwtObject jwtObject = new JwtObject();
        return Optional.of(jwtObject);
    }

}
