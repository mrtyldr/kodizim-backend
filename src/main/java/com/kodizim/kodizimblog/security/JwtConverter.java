package com.kodizim.kodizimblog.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final String defaultAuthority;
    private final JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();

    public JwtConverter(String defaultAuthority) {
        this.defaultAuthority = defaultAuthority;
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
        var permissions = jwt.getClaimAsStringList("permissions");
        if (permissions == null || permissions.isEmpty())
            return Set.of(new SimpleGrantedAuthority(defaultAuthority));
        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(converter.convert(source)
                                .stream(),
                        extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }
}
