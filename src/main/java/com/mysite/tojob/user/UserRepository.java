package com.mysite.tojob.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SiteUser 관련 레파지토리(DAO)
 *
 * @author joonoh
 * @since 2023.02.22
 * @version 0.1
 *
 * == 개정이력(Modification Information) ==
 *
 * 수정일           수정자         수정내용
 * -----------     ---------     -------------------------
 * 2023.02.22      신준오          최초 생성
 *
 * Copyright (C) by SN All right reserved.
 */

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
}