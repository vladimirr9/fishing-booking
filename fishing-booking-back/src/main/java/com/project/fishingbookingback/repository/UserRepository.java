package com.project.fishingbookingback.repository;

import com.project.fishingbookingback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select b from User b where b.email=:email")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout",value="0")})
    public User findOneByEmail(String email);
}
