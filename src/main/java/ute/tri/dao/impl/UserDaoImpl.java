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
        } finally {
            closeResources();
        }
        return null;
    }

    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            conn = new DBConnectSQLServer().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapUserModel(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return null;
    }

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users(username, email, password, image, fullname, phone, roleid, createDate) VALUES (?,?,?,?,?,?,?,?)";
        try {
            conn = new DBConnectSQLServer().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getImage());
            ps.setString(5, user.getFullname());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getRoleid());
            ps.setDate(8, user.getCreateDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            conn = new DBConnectSQLServer().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return mapUserModel(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
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
        //return checkExistField("email", email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return checkExistField("username", username);
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
        //return checkExistField("phone", phone);
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
        } finally {
            closeResources();
        }
        return false;
    }

    // Helper methods
    private UserModel mapUserModel(ResultSet rs) throws Exception {
        UserModel user = new UserModel();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setFullname(rs.getString("fullname"));
        user.setPassword(rs.getString("password"));
        user.setImage(rs.getString("image"));
        user.setRoleid(rs.getInt("roleid"));
        user.setPhone(rs.getString("phone"));
        user.setCreateDate(rs.getDate("createDate"));
        return user;
    }

    private boolean checkExistField(String fieldName, String value) {
        String sql = "SELECT * FROM users WHERE " + fieldName + " = ?";
        try {
            conn = new DBConnectSQLServer().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return false;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        // Test insert method
        UserModel newUser = new UserModel();
        newUser.setEmail("newuser@example.com");
        newUser.setUsername("newuser");
        newUser.setFullname("New User");
        newUser.setPassword("123456");
        newUser.setImage("default.png");
        newUser.setRoleid(3); // Assume 3 is a valid role id
        newUser.setPhone("0123456789");
        newUser.setCreateDate(new java.sql.Date(System.currentTimeMillis())); // Sets current date
        userDao.insert(newUser);

        // Verify insertion by finding the user by username
        UserModel insertedUser = userDao.findByUserName("newuser");
        if (insertedUser != null) {
            System.out.println("User successfully inserted:");
            System.out.println(insertedUser);
        } else {
            System.out.println("Failed to insert user.");
        }

        // Test findAll method
        List<UserModel> list = userDao.findAll();
        System.out.println("List of all users:");
        for (UserModel user : list) {
            System.out.println(user);
        }

        // Test findById method
        int testId = insertedUser.getId(); // Use the id of the inserted user
        UserModel userById = userDao.findById(testId);
        if (userById != null) {
            System.out.println("User found by ID:");
            System.out.println(userById);
        } else {
            System.out.println("User not found with ID: " + testId);
        }
    }
}
