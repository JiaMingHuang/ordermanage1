package com.yc.ordermanage.user.dao;

import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserVO, Long> {

	@Query(value = "SELECT T.* FROM T_USER T WHERE USERID = :userid", nativeQuery = true)
	public List<UserVO> getUserVOByUserid(@Param("userid") String userid);

	@Modifying
	@Override
	public void deleteById(Long id);

	@Modifying
	@Override
	public UserVO save(UserVO userVO);

	@Query(value = "SELECT T.* FROM T_USER T WHERE ACCOUNTTYPE = :accounttype", nativeQuery = true)
	public List<UserVO> getFactory(@Param("accounttype") Integer accounttype);

	@Query(value = "SELECT T.* FROM T_USER T WHERE ACCOUNTTYPE = :accounttype", nativeQuery = true)
	public List<UserVO> queryByType(@Param("accounttype") Integer accounttype);
}
