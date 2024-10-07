package ute.tri.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ute.tri.configs.DBConnectSQLServer;
import ute.tri.dao.ICategoryDao;
import ute.tri.models.CategoryModel;
import ute.tri.models.UserModel;

public class CategoryDaoImpl implements ICategoryDao{
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	@Override
	public List<CategoryModel> findAll() {
		String sql="Select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn=new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql="Select * from categories where categoryid=?";
		
		try {
			conn=new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			conn.close();
			ps.close();
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CategoryModel category) {
		// TODO Auto-generated method stub
String sql="INSERT INTO categories(categoryname,images,status) VALUES(?,?,?)";
		
		try {
			conn=new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CategoryModel category) {
		// TODO Auto-generated method stub
String sql="UPDATE categories SET categoryname=?,images=?,status=? where categoryid=?";
		
		try {
			conn=new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());		
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM categories WHERE categoryid =?";
				
				try {
					conn=new DBConnectSQLServer().getConnection();
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);	
					ps.executeUpdate();
					conn.close();
					ps.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		String sql="Select * from categories where categoryname like ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn=new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();

        // Find all categories
        System.out.println("Find all categories:");
        List<CategoryModel> categories = categoryDao.findAll();
        for (CategoryModel category : categories) {
			System.out.println(category);
		}

        // Find category by ID
        System.out.println("\nFind category by ID (1):");
        CategoryModel categoryById = categoryDao.findById(1);
        if (categoryById != null) {
            System.out.println(categoryById);
        }

        // Insert a new category
        System.out.println("\nInsert a new category:");
        CategoryModel newCategory = new CategoryModel();
        newCategory.setCategoryname("New Category");
        newCategory.setImages("image_url");
        newCategory.setStatus(1);
        categoryDao.insert(newCategory);
        System.out.println("New category inserted.");

        // Update a category
        System.out.println("\nUpdate category with ID (1):");
        CategoryModel updatedCategory = categoryDao.findById(1);
        if (updatedCategory != null) {
            updatedCategory.setCategoryname("Updated Category");
            updatedCategory.setImages("updated_image_url");
            updatedCategory.setStatus(2);
            categoryDao.update(updatedCategory);
            System.out.println("Category updated.");
        }

        // Delete a category by ID
        System.out.println("\nDelete category by ID (2):");
        categoryDao.delete(2);
        System.out.println("Category with ID 2 deleted.");

        // Search categories by name keyword
        System.out.println("\nSearch categories by name ('Category'):");
        List<CategoryModel> searchResults = categoryDao.findName("Category");
        if (searchResults != null) {
            for (CategoryModel category : searchResults) {
                System.out.println(category);
            }
        }
    }
}
