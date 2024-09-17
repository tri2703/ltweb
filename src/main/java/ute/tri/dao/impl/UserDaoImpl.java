package ute.tri.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;

import ute.tri.configs.DBConnectSQLServer;
import ute.tri.dao.IUserDao;
import ute.tri.models.UserModel;

public class UserDaoImpl extends DBConnectSQLServer implements IUserDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<>(); // Create a List to hold the data

        try {
            conn = super.getConnection(); // Connect to the database
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) { // Move to the next row
                list.add(
                        new UserModel(
                        ) // Add to the list
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
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
		user.setImage(rs.getString("images"));
		user.setRoleid(Integer.parseInt(rs.getString("roleid")));
		user.setPhone(rs.getString("phone"));
		user.setCreateDate(rs.getDate("createdDate"));
		return user; }
		} catch (Exception e) {e.printStackTrace(); }
		return null;
	}

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users (id, username, email, password, images, fullname) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = super.getConnection(); // Connect to the database
            ps = conn.prepareStatement(sql); // Prepare the SQL statement

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            //ps.setString(5, user.getImages());
            ps.setString(6, user.getFullname());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
/*
  	  public static void main(String[] args) {
  	        UserDaoImpl userDao = new UserDaoImpl(); // Correct class instantiation

  	        List<UserModel> list = userDao.findAll(); // Correct method call

  	        for (UserModel user : list) {
  	            System.out.println(user);
  	        }
  	    }
  	    
	@Override
	public List<UserModel> findAl1() {
		String sql = "SELECT * FROM users";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
		conn = new DBConnectSQLServer().getConnection();
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new UserModel(
					 rs.getInt("id"),
                     rs.getString("username"),
                     rs.getString("password"),
                     rs.getString("email"),
                     rs.getString("fullname"),
                     rs.getString("images"),
                     rs.getInt("roleid"),
                     rs.getString("phone"),
                     rs.getDate("createdDate")));
			return list;
		}
		} catch (Exception e) {e.printStackTrace(); }
		return null;
	}
*/
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
				//user.setImages(rs.getString("image"));
				
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				//user.setCreatedDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	     // Main method to test the connection
	     public static void main(final String[] args) {

	             IUserDao userDao = new UserDaoImpl();         
	             // Print the connection object (it will print connection details if successful)
	             String testUsername = "tripn"; // Replace with an actual username for testing
	     	    UserModel userByUsername = userDao.findByUserName(testUsername);
	     	    if (userByUsername != null) {
	     	        System.out.println("User found by username:");
	     	        System.out.println(userByUsername);
	     	    } else {
	     	        System.out.println("User not found with username: " + testUsername);
	     	    }
}

		@Override
		public List<UserModel> findAl1() {
			// TODO Auto-generated method stub
			return null;
		}

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
		public boolean checkExistPhone(String phone) {
			// TODO Auto-generated method stub
			return false;
		}
}

