package com.ebl.TalentBoard.repository;

import com.ebl.TalentBoard.model.User;
import org.hibernate.internal.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Page<User> findByFirstNameContaining(String name, Pageable pageable);


}
