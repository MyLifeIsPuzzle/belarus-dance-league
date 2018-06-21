package com.artsiomtretinnikov.config;

import com.artsiomtretinnikov.aspect.RequestAspect;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import com.artsiomtretinnikov.repository.RequestRepository;
import com.artsiomtretinnikov.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.artsiomtretinnikov")
@Import(PersistenceConfig.class)
public class ServiceConfiguration {

    private final RequestRepository requestRepository;
    private final DanceGroupRepository danceGroupRepository;

    @Autowired
    public ServiceConfiguration(RequestRepository requestRepository, DanceGroupRepository danceGroupRepository) {
        this.requestRepository = requestRepository;
        this.danceGroupRepository = danceGroupRepository;
    }

    @Bean
    public RequestService requestService() {
        return new RequestService(requestRepository, danceGroupRepository);
    }

    @Bean
    public RequestAspect requestAspect() {
        return new RequestAspect();
    }
}
