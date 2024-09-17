package ute.tri.services;

import ute.tri.models.UserModel;

public interface IUserService {
	
	UserModel login(String username, String password);

	UserModel FindByUserName(String username);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean register(String username, String password, String email, String fullname, String phone);
}
