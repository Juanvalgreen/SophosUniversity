package com.example.gateWay.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;


/*
    @Autowired
    private JWTUtil;
*/


    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {

        return (((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey("Authorization")){

                    throw new RuntimeException("Falta token de autorización");
                }
            }


            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if(authHeader !=null) {



                try {

                    HttpHeaders headers = new HttpHeaders();
                    System.out.println(headers);
                    headers.set("Authorization", authHeader);
                    //headers.setBearerAuth(authHeader);

                    System.out.println(authHeader);
                    System.out.println(headers);

                    HttpEntity<?> httpEntity = new HttpEntity<>(headers);

                    // Realizar la solicitud con los encabezados personalizados
                    ResponseEntity<Boolean> response = restTemplate.exchange(

                            "http://localhost:9008/api/v1/user/validate",
                            HttpMethod.GET,
                            httpEntity,
                            boolean.class

                    );

                } catch (Exception exception) {

                    throw new RuntimeException("Token de authorizacion invalido");

                }
            }
            return chain.filter(exchange);
        }));


    }











    public static class Config{

    }
}
