package com.kristiantoyanuar.api.client.service;

import com.kristiantoyanuar.api.client.repo.ApiClientRepository;
import com.kristiantoyanuar.api.client.model.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * Created by Kristianto Yanuar on 5/31/2016.
 */
@Service
public class ApiClientDetailsService implements ClientDetailsService {

    @Autowired
    private ApiClientRepository apiClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String apiClientId) throws ClientRegistrationException {
        ApiClient apiClient = apiClientRepository.findByClientId(apiClientId);
        if(apiClient == null) {
            throw new ClientRegistrationException("Client is not registered");
        } else
            return apiClient;
    }
}
