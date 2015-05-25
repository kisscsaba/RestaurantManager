package RestaurantManager.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import RestaurantManager.Unit.Item;

public class SystemDAO {
	
	public final String jdbcDriver = "com.mysql.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/";
	public final String name= "table_manager";
	
	private final String user = "root";
	private final String password = "123456789";
	
	public SystemDAO() {}

	public void initDatabase(){
	    String createDatabase = "CREATE DATABASE IF NOT EXISTS table_manager";
	
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(url, user , password);			
	        try (Statement statement = connection.createStatement()) {
	            statement.executeUpdate(createDatabase);
	        }
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        if( connection != null ){
	            try{
	                connection.close();
	            }
	            catch(SQLException e){
	                    e.printStackTrace();
	                    System.exit(1);
	            }
	        }
	    }
	}
	  
	public void initTable(){
	    StringBuilder sb = new StringBuilder();
	    sb.append("CREATE TABLE IF NOT EXISTS item("
	    			+ "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
	                + "name VARCHAR(255),"
	                + "type VARCHAR(255),"
	                + "number_of_item INT UNSIGNED,"
	                + "item_price INT UNSIGNED,"
	                + "PRIMARY KEY (id)"
	            + ");");
	
	
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(url + name, user , password);			
	        try (Statement statement = connection.createStatement()) {
	            statement.executeUpdate(sb.toString());
	        }
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        if( connection != null ){
	            try{
	                connection.close();
	            }
	            catch(SQLException e){
	                    e.printStackTrace();
	                    System.exit(1);
	            }
	        }
	    }
	}
	
	public String[] getItemTypes(){
		String sql = "SELECT distinct type FROM item";
		ArrayList<String> al = new ArrayList<String>();
		al.add("Termék típus");
	    try {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);			

			ResultSet rs = pstm.executeQuery();
			while(rs.next())
				al.add(rs.getString("type"));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}



		return al.toArray(new String[al.size()]);
	}
	
	
	public HashMap<Integer, String> getItemNames(String type){
		String sql = "SELECT id,name FROM item WHERE type = ?";
		HashMap<Integer,String> hm = new HashMap<>();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, type);			

			ResultSet rs = pstm.executeQuery();
			while(rs.next())
				hm.put(rs.getInt("id"),rs.getString("name"));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return hm;
	}
	
	public ArrayList<Item> getItemsByItemType(String type){
		ArrayList<Item> items = new ArrayList<>();
		String sql = "SELECT id, name, number_of_item, item_price FROM item WHERE type = ?";
	    try {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, type);			

			ResultSet rs = pstm.executeQuery();
			while(rs.next())
				items.add(new Item(rs.getInt("id"), rs.getString("name"), type, rs.getInt("number_of_item"), rs.getInt("item_price")));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return items;
	}
	
	public Item getItem(int ID){
		String sql = "SELECT id,name,type,number_of_item,item_price FROM item WHERE id = ?";
		Item item = null;
	    try {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, ID);			

			ResultSet rs = pstm.executeQuery();
			if(rs.next())
				item = new Item(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("number_of_item"), rs.getInt("item_price"));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return item;
	}
	
	public void deleteItem(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String sql = "DELETE FROM item WHERE id = ?";

	    Class.forName("com.mysql.jdbc.Driver").newInstance();

	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} 



	}
	
	public void updateItem(Item item) throws SQLException{
		String sql = "UPDATE item SET name = ?, type = ?, number_of_item = ?, item_price = ? WHERE id = ?;";
	    try {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    }catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, item.getName());			
			pstm.setString(2, item.getType());	
			pstm.setInt(3, item.getNumberOfItem());
			pstm.setInt(4, item.getPrice());
			pstm.setInt(5, item.getID());
			pstm.executeUpdate();


		} 
		
	}
	
	public void insertItem(Item item) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String sql = "INSERT INTO item (name,type,number_of_item,item_price) VALUES (?,?,?,?)";

	    Class.forName("com.mysql.jdbc.Driver").newInstance();

		try(Connection connection = DriverManager.getConnection(url + name, user , password)){
		
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, item.getName());			
			pstm.setString(2, item.getType());	
			pstm.setInt(3, item.getNumberOfItem());
			pstm.setInt(4, item.getPrice());
			pstm.executeUpdate();

		} 
		
	}

	
	public boolean isAvaibleItem(String name, int number){
	    return true;
	}

}
