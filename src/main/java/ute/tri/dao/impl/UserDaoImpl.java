package ute.tri.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ute.tri.configs.DBConnectSQLServer;
import ute.tri.dao.IUserDao;
import ute.tri.models.UserModel;

public class UserDaoImpl extends DBConnectSQLServer implements IUserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * from users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("image"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"),
						rs.getInt("roleid"), rs.getDate("createDate")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImage(rs.getString("image"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(username, password,image , fullname,email , phone,roleid,createDate) VALUES (?,?,?,?,?,?,?,?)";
				try {
				conn = new DBConnectSQLServer().getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getImage());
				ps.setString(4, user.getFullname());
				ps.setString(5, user.getEmail());
				ps.setString(6,user.getPhone());
				ps.setInt(7,user.getRoleid());
				ps.setDate(8, user.getCreateDate());
				ps.executeUpdate();
				} catch (Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();

		// Test findAll method
		List<UserModel> list = userDao.findAll();
		for (UserModel user : list) {
			System.out.println(user);
		}

		// Test findByUserName method
		String testUsername = "tiendv"; // Replace with an actual username for testing
		UserModel userByUsername = userDao.findByUserName(testUsername);
		if (userByUsername != null) {
			System.out.println("User found by username:");
			System.out.println(userByUsername);
		} else {
			System.out.println("User not found with username: " + testUsername);
		}

		// Test findById method
		int testId = 1; // Replace with an actual ID for testing
		UserModel userById = userDao.findById(testId);
		if (userById != null) {
			System.out.println("User found by ID:");
			System.out.println(userById);
		} else {
			System.out.println("User not found with ID: " + testId);
		}
		 // Test insert method
	    UserModel newUser1 = new UserModel();
	    newUser1.setEmail("newuser@example.com");
	    newUser1.setUsername("newuser1");
	    newUser1.setFullname("New User1");
	    newUser1.setPassword("password1231");
	    newUser1.setImage("default.png");
	    newUser1.setRoleid(2); 
	    newUser1.setPhone("12345678901");
	    newUser1.setCreateDate(new java.sql.Date(System.currentTimeMillis())); // Sets current date

	    userDao.insert(newUser1);

	    // Verify insertion by finding the user by username
	    UserModel insertedUser = userDao.findByUserName("newuser1");
	    if (insertedUser != null) {
	        System.out.println("User successfully inserted:");
	        System.out.println(insertedUser);
	    } else {
	        System.out.println("Failed to insert user.");
	    }
	    String emailToCheck = "newuser@example.com";
        String usernameToCheck = "newuser";
        String phoneToCheck = "11111111";

        boolean emailExists = userDao.checkExistEmail(emailToCheck);
        boolean usernameExists = userDao.checkExistUsername(usernameToCheck);
        boolean phoneExists = userDao.checkExistPhone(phoneToCheck);

        System.out.println("Email exists: " + emailExists);
        System.out.println("Username exists: " + usernameExists);
        System.out.println("Phone exists: " + phoneExists);
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImage(rs.getString("image"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where email = ?";
		try {
		conn = new DBConnectSQLServer().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where username = ?";
		try {
		conn = new DBConnectSQLServer().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from users where phone = ?";
		try {
		conn = new DBConnectSQLServer().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, phone);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean update(UserModel user) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
}
