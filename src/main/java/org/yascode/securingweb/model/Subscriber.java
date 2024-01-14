package org.yascode.securingweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriber")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email; //  username

    private String password; // password

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subscriber_roles",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


}
