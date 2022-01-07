import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;  

public class Database {
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:movies.db");
			System.out.println("CONNECTED");
		} catch (Exception e) {
			System.out.println("ERROR:  "+e);
		}
		return con;
	}
	
	
	public static Connection connectToNewDatabase(String db) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+db);
			System.out.println("CONNECTED");
		} catch (Exception e) {
			System.out.println("ERROR:  "+e);
		}
		return con;
	}
	
	  public static void createNewDatabase(String fileName) {  
		   
	        String url = "jdbc:sqlite:" + fileName;  
	   
	        try {  
	            Connection conn = DriverManager.getConnection(url);  
	            if (conn != null) {  
	                DatabaseMetaData meta = conn.getMetaData();  
	                System.out.println("The driver name is " + meta.getDriverName());  
	                System.out.println("A new database has been created.");  
	            }  
	   
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	
	   
	   
	    public static void createNewTable() {  
	        // SQLite connection string  
	        String url = "jdbc:sqlite:movies.db";  
	          
	        // SQL statement for creating a new table  
	        String sql = "CREATE TABLE IF NOT EXISTS MoviesName (\n"  
	                + " id integer PRIMARY KEY,\n"  
	                + " movie text NOT NULL,\n"  
	                + " actor text NOT NULL,\n"
	                + " actress text NOT NULL,\n" 
	                + " director text NOT NULL,\n" 
	                + " releasedate text NOT NULL\n" 
	                + ");";  
	          
	        try{  
	            Connection conn = DriverManager.getConnection(url);  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql); 
	            System.out.println("TABLE CREATED");
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    } 
	    

	    public static void insert(String movie, String actor,String actress,String director,String date ) { 
	    	
	    	
	           
	        String sql = "INSERT INTO MoviesName( movie, actor, actress, director, releasedate) VALUES(?,?,?,?,?)";  
	   
	        try{  
	             Connection conn = Database.connect();  
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, movie);  
	            pstmt.setString(2, actor);
	            pstmt.setString(3, actress);
	            pstmt.setString(4, director);
	            pstmt.setString(5, date);
	            pstmt.executeUpdate();  
	            System.out.println("DATA INSERYED");
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }
	    
	    
	    public static void readAllData() {
	    	Connection conn=null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	String sql = "SELECT * FROM MoviesName";
	    	
	    	try {
		    	 conn= Database.connect();
	    		pstmt =conn.prepareStatement(sql);
	    		rs= pstmt.executeQuery();
	    		
	    		while(rs.next()) {
	    			String movie= rs.getString("movie");
	    			String actor = rs.getString("actor");
	    			String actress = rs.getString("actress");
	    			String director = rs.getString("director");
	    			String date = rs.getString("releasedate");
	    			

	    			System.out.println("movie       : "+ movie);
	    			System.out.println("actor       : "+ actor);
	    			System.out.println("actress     : "+ actress);
	    			System.out.println("director    : "+ director);
	    			System.out.println("releasedate : "+ date);
                              
	    		}
	    	}
	    	
	    		catch(SQLException e) {
	    			System.out.println(e.getMessage());
	    			
	    		}
	    		finally {
	    			try {
	    				rs.close();
	    				pstmt.close();
	    				conn.close();
	    			}catch(SQLException e) {
	    				System.out.println(e.getMessage());
	    			}
	    		
	    		
	    	}
	    }
	    
	    public static void readSpecificRow() {
	    	Connection conn =null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	String sql = "SELECT movie,actor FROM MoviesName WHERE actor=?";
	    	
	    	try {
		    	 conn= Database.connect();
	    		pstmt =conn.prepareStatement(sql);
	    		pstmt.setString(1,"Tom Hardy");
	    		rs= pstmt.executeQuery();
	    		
	    		while(rs.next()) {
	    			String movie= rs.getString("movie");
	    			String actor = rs.getString("actor");
	    			
	    			
	    			System.out.println("movie  : "+ movie);
	    			System.out.println("actor  : "+ actor);
	    		}
	    	}
	    	
	    		catch(SQLException e) {
	    			System.out.println(e.getMessage());
	    			
	    		}
	    		finally {
	    			try {
	    				rs.close();
	    				pstmt.close();
	    				conn.close();
	    			}catch(SQLException e) {
	    				System.out.println(e.getMessage());
	    			}
	    		

	    	
	    }


}}
