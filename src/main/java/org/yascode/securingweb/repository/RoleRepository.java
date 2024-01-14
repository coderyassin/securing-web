package org.yascode.securingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.securingweb.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
