package com.kristiantoyanuar.api.config;

import com.kristiantoyanuar.api.client.Resources;
import com.kristiantoyanuar.api.client.service.ApiClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by Kristianto Yanuar on 5/31/2016.
 */
@Configuration
public class OAuth2ServerConfig {

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        ApiClientDetailsService apiClientDetailsService;


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .withClientDetails(apiClientDetailsService);
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .requestMatchers()
                    .antMatchers("/", "/api/*")
                    .and()
                    .authorizeRequests()
                    .anyRequest().access("#oauth2.hasScope('read')");
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(Resources.USER_RESOURCE.name());
        }
    }

}
