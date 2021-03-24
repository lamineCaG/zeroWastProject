package com.projet.react.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findUserById(Long id);

    Optional<Users> findByEmail(String email);

    @Modifying
    @Query(value = "update users u set u.enable = true where u.email = ?1",
            nativeQuery = true)
    int enableUser(String email);
}
