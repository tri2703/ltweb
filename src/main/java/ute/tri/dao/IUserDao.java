package ute.tri.dao;

import java.util.List;

import ute.tri.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	UserModel findById(int id);
	void insert(UserModel user);
	UserModel findByUserName(String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean update(UserModel user);
}