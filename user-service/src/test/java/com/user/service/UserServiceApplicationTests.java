package com.user.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@SpringBootTest
class UserServiceApplicationTests {

    public static final String ACCESS_TOKEN_URI = "http://localhost:8080/login";
    public static final String CLIENT_ID = "tlam14";
    public static final String CLIENT_SECRET = "tlam14";
    public static final String GRANT_TYPE = "password";
    public static final String USERNAME = "margaryan@gmail.com";
    public static final String PASSWORD = "tlam142536";

    @Test
    void userLoginOauth2Test() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(ACCESS_TOKEN_URI);
        resource.setClientId(CLIENT_ID);
        resource.setClientSecret(CLIENT_SECRET);
        resource.setGrantType(GRANT_TYPE);
        resource.setUsername(USERNAME);
        resource.setPassword(PASSWORD);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource);

        assertThat(template.getAccessToken() != null).isTrue();
        assertThat(template.getResource().getClientId().equals(CLIENT_ID)).isTrue();
        assertThat(template.getResource().getClientSecret().equals(CLIENT_SECRET)).isTrue();
        assertThat(template.getResource().getGrantType().equals(GRANT_TYPE)).isTrue();
        assertThat(template.getResource().getAccessTokenUri().equals(ACCESS_TOKEN_URI)).isTrue();
    }
}
