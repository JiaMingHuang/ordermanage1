package com.yc.ordermanage.demo.dao;

import com.yc.ordermanage.demo.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DemoRepository extends JpaRepository<UserVO, Long> {

	@Query(value = "SELECT T.* FROM T_USER T WHERE USERID = ?1", nativeQuery = true)
	public UserVO getBySQL(String id);
}
