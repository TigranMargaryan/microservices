package com.notes.noteservice.config.security;

import com.notes.noteservice.config.Response;
import com.notes.noteservice.context.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginUser {

    private static final Logger LOG = LoggerFactory.getLogger(LoginUser.class);

    public static final String AUTHORIZATION_SUCCESSFUL = "authorization successful";

    @Value("${resource.token.uri}")
    private String uri;

    @Value("${resource.client.id}")
    private String clientId;

    @Value("${resource.client.secret}")
    private String clientSecret;

    @Value("${resource.grant.type}")
    private String grantType;

    @Autowired
    private UserDetailService detailService;

    @PostMapping
    public Response loginUser(@RequestParam String email, @RequestParam String password) {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(uri);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setGrantType(grantType);
        resource.setUsername(email);
        resource.setPassword(password);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
        detailService.setEmail(email);
        detailService.setPassword(password);
        detailService.setScope(template.getAccessToken().toString());

        LOG.debug("authorization successful for user: " + email);
        return new Response(AUTHORIZATION_SUCCESSFUL);
    }
}
