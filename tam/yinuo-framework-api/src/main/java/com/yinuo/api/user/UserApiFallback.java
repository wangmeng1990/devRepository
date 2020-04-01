package com.yinuo.api.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.github.pagehelper.Page;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.common.common.HttpResult;

public class UserApiFallback implements UserApi {

	@Override
	public User queryUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResult register(@Valid User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> authList(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findUser(int page, int size, CriteriaUser criteriaUser) {
		return null;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int update(Long id, Integer state) {
		return 0;
	}

	@Override
	public int resetPassword(Long id) {
		return 0;
	}

	@Override
	public Page<Roles> findAllRoles(int page, int size) {
		return null;
	}

	@Override
	public User getUserById(Long id) {
		return null;
	}

	@Override
	public int updateUset(User user) {
		return 0;
	}

	@Override
	public int updatePassword(Long id, String password, String newpassword) {
		return 0;
	}

	@Override
	public User getUserByName(String username) {
		return null;
	}

	@Override
	public List<Roles> findRoles() {
		return null;
	}

	@Override
	public HttpResult addRoles(Roles roles) {
		return null;
	}

	@Override
	public Roles getRolesByName(String name) {
		return null;
	}

	@Override
	public Roles getRolesById(Long id) {
		return null;
	}


}
