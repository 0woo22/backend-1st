package com.github.supercodinpj1.respository;

import com.github.supercodinpj1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //db 연동을 처리하는 DAO 클래스
public interface UserRepository extends JpaRepository<User, Long> {
    //email을 통해 사용자 정보를 가져옴
    Optional<User> findByEmail(String email);
}
