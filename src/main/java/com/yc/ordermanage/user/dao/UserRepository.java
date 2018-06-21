package com.yc.ordermanage.user.dao;

import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserVO, Long> {

	@Query(value = "SELECT T.* FROM T_USER T WHERE USERID = :userid", nativeQuery = true)
	public UserVO getUserVOByUserid(@Param("userid") String userid);


}
