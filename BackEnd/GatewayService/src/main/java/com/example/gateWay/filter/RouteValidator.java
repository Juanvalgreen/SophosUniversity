package com.example.gateWay.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.util.function.Predicate;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/user/login",
            "/user",
            "/user/validate"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
