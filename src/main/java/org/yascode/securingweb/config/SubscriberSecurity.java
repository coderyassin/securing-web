package org.yascode.securingweb.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yascode.securingweb.model.Subscriber;
import org.yascode.securingweb.util.RoleAuthorityMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriberSecurity implements UserDetails {

    private Subscriber subscriber;

    public SubscriberSecurity(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = subscriber.getRoles().stream()
                .map(role ->
                        RoleAuthorityMapper.getAuthority(role.getName())
                                .stream().map(authority -> new SimpleGrantedAuthority(authority))
                                .collect(Collectors.toList())

                )
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return subscriber.getPassword();
    }

    @Override
    public String getUsername() {
        return subscriber.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
