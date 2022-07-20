package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Long countById(Integer id);

    Optional<User> findByEmail(String email);

    public User findByResetPassword(String token);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    @Modifying
    @Query("UPDATE User a " +
            "SET a.locked = True WHERE a.email = ?1")
    int lockedUser(String email);


}
