package com.study.board.user.domain.repository;

import com.study.board.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 유효성 검사 - 중복 체크
     *
     * @param email 회원 이메일
     * @return
     */
    static boolean existsByEmail(String email) {
        return false;
    }
}
