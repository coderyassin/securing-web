package org.yascode.securingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.securingweb.model.Subscriber;


public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Subscriber findByEmail(String email);
}
