package dev.codejar.repository;

import dev.codejar.model.entity.enums.ERole;
import dev.codejar.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
