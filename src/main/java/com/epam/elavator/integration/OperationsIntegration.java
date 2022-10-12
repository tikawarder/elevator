package com.epam.elavator.integration;

import com.epam.elavator.domain.report.ReportMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OperationsIntegration {
    private static final String BASE_URL = "http://localhost:8081/operations/movements";

    @Autowired
    RestTemplate restTemplate;

    public List<ReportMovement> postOperations (List<ReportMovement> movements){
        return restTemplate.postForObject(BASE_URL, movements, List.class);
    }
}
