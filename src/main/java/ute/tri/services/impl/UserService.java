package ute.tri.services.impl;

import ute.tri.models.UserModel;
import ute.tri.services.IUserService;
import ute.tri.dao.impl.UserDaoImpl;

public class UserService implements IUserService{
	
	UserDaoImpl userDao = new UserDaoImpl();


	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}
/*
	@Override
	public void saveUser(UserModel user) {
	    // Bạn có thể thêm mã hóa mật khẩu tại đây nếu cần
	    userDao.save(user);
	}
	*/

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new UserModel(username, password, null, fullname, email, phone, 5, date));
		return true;
	}

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}