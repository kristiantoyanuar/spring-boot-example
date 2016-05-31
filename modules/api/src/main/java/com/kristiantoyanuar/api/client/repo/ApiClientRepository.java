package com.kristiantoyanuar.api.client.repo;

import com.kristiantoyanuar.api.client.model.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kristianto Yanuar on 5/31/2016.
 */
public interface ApiClientRepository extends JpaRepository<ApiClient, String> {

    ApiClient findByClientId(String clientId);

}
