package org.yascode.securingweb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yascode.securingweb.config.SubscriberSecurity;
import org.yascode.securingweb.model.Subscriber;
import org.yascode.securingweb.repository.SubscriberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SubscriberRepository subscriberRepository;

    public CustomUserDetailsService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Subscriber subscribers = subscriberRepository.findByEmail(username);
        if(subscribers == null){
            throw new UsernameNotFoundException("Invalid Username");
        }
        return new SubscriberSecurity(subscribers);
    }
}
