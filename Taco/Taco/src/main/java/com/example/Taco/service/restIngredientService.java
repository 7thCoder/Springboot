package com.example.Taco.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.example.Taco.security.securityConfig;
import com.example.Taco.taco.Ingredient;

public class restIngredientService {
    private RestTemplate restTemplate;

    public restIngredientService(String accessToken){
        this.restTemplate = new RestTemplate();
        if (accessToken != null){
            this.restTemplate
            .getInterceptors()
            .add(getBearerTokenInterceptor(accessToken));
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken){
        ClientHttpRequestInterceptor interceptor =
            new ClientHttpRequestInterceptor() {
                @Override
                public ClientHttpResponse intercept(
                    HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException{
                        request.getHeaders().add("Authorization", "Bearer" + accessToken);
                        return execution.execute(request, bytes);
                    }
            };
        return interceptor;
    }

    //@Override
    public Iterable<Ingredient> findAll(){
        return Arrays.asList(restTemplate.getForObject(
            "http://localhost:8080/api/ingredients",
            Ingredient[].class));
    }

    public Ingredient addIngredient(Ingredient ingredient){
        return restTemplate.postForObject(
            "http://localhost:8080/api/ingredients",
            ingredient,Ingredient.class
        );
    }

    @Bean
    @RequestScope
    public restIngredientService ingredientService(
        OAuth2AuthorizedClientService clientService){
            Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

            String accessToken = null;
            if(authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)){
                OAuth2AuthenticationToken oauthToken =
                    (OAuth2AuthenticationToken) authentication;
                String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
                if(clientRegistrationId.equals("taco-admin-client")){
                    OAuth2AuthorizedClient client =
                        clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
                        accessToken = client.getAccessToken().getTokenValue();
                }
            }
        return new restIngredientService(accessToken);
    }
}
