package com.thientdk.be_auth.repositories;

import com.thientdk.be_auth.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

    @Query("""
       SELECT u FROM UserEntity u
       WHERE (:keySearch IS NULL OR
              LOWER(u.username) LIKE LOWER(CONCAT('%', :keySearch, '%')) OR
              LOWER(u.email) LIKE LOWER(CONCAT('%', :keySearch, '%'))
              )
       """)
    Page<UserEntity> searchUsers(@Param("keySearch") String keySearch, Pageable pageable);

}
