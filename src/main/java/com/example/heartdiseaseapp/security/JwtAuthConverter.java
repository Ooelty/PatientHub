package com.example.heartdiseaseapp.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


import org.springframework.core.convert.converter.Converter;

public class JwtAuthConverter implements Converter<Jwt,AbstractAuthenticationToken>{

private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities=Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt,authorities,jwt.getClaim("preferred_username"));//methode qui cherche dans le scope et les considere comme authorites et aussi on va chercher les roles dans le claim realm_access et on les considere comme authorities aussi
    }   
    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt){ //methode pour extraire les roles du claim realm_access
       Map<String,Object> realmAccess;
       Collection<String> roles;
       if(jwt.getClaim("realm_access")==null){ // va au jwt pour check le realm_access si on retourne une liste vide
        return Set.of();//le realm_access cest dans keycloak si on el change il se peut quand trouve un autre 
       }
       //si le realm_access existe on va extraire les roles et les convertir en authorities
       realmAccess=jwt.getClaim("realm_access");
       roles=(Collection<String>) realmAccess.get("roles");
   //hasAuthority//
       return roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet()); //pour chaque role trouver dans la liste des roles 
   //pour HasRole on doit ajouter le prefix ROLE_ devant chaque role pour que spring security puisse les reconnaitre comme des roles et pas des authorities 
    }
   

    


}  


