package co.com.ias.certification.backend.controllers;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class TestController {

    /**
     * Ejemplo de como obtener las autoridades de un usuario por el token
     * @param authentication Object Authentication de Spring Security, casteado como KeycloakAuthenticationToken
     * @return Colección de autoridades del usuario.
     */
    @GetMapping("/me")
    public Collection<GrantedAuthority> me(Authentication authentication) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        return authenticationToken.getAuthorities();
    }
}
