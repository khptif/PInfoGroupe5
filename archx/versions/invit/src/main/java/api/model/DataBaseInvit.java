package api.model;

import java.util.*;
import java.util.Properties;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/

public class DataBaseInvit{
	
	private String url;
	private String username;
	private String password;

	private String JDBC_DRIVER= "org.postgresql.Driver"; //"com.mysql.cj.jdbc.Driver"

	public DataBaseInvit(String path){
		Properties props =  new DataBaseInvitProperties().readProperties(path);

		this.url=props.getProperty("db.url");
		this.username=props.getProperty("db.user");
		this.password=props.getProperty("db.passwd");

		/*this.url="jdbc:mysql://129.194.10.130:3306/tinderfilmBD";
		this.username = "groupe5";
		this.password = "12345";*/

	}//end constructor


	public String try_connect(){
		String connected= "try to make connection!";

		Connection conn = null;

		try{
			Class.forName(JDBC_DRIVER); 
			/*Connection*/ conn = DriverManager.getConnection(this.url, this.username, this.password);
			    if (conn != null) {
				System.out.println("\n"+"Connection established!");
				connected = "Connection established!";

			    } else {
				System.out.println("Failed to make connection!");
				connected = "Failed to make connection!";
			    }

		} catch (SQLException e) {
			 System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			   e.printStackTrace();

		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}  
		return connected;

    }//end connect


	public void INSERT_Invit(String ID, String film, int totalscore ){
		String query = "INSERT INTO Invits(ID, film, score, totalscore) VALUES(?, ?, 0,?)";

		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);

			pst.setString(1, ID);
			pst.setString(2, film); //film
			pst.setInt(3, totalscore); //totalscore

            		pst.executeUpdate();			 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}        


    }//end INSERT_Invit

	public ArrayList<String> SELECT_Invit(String ID, String film){
		String query ="SELECT *  FROM Invits WHERE ID="+"'"+ID+"'"+" AND film="+"'"+film+"'";

		ArrayList<String> params = new ArrayList();

		Connection conn = null;

		try{ 
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				//System.out.print(rs+"\n");
            			
				//System.out.print(rs.getString(1)+" * "+rs.getString(2)+" * "+rs.getString(3)+" * "+rs.getString(4)+" * ");

				params.add(rs.getString(1)); //ID
				params.add(rs.getString(2)); //film
				params.add(rs.getString(3)); //score
				params.add(rs.getString(4)); //totalscore
            		}
						 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}        
		
		return params;

    }//end SELECT_Invit


	public ArrayList<ArrayList<String>> SELECT_Invit(String ID){
		String query ="SELECT *  FROM Invits WHERE ID="+"'"+ID+"'";

		ArrayList<ArrayList<String>> params = new ArrayList();

		Connection conn = null;

		try{ 
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				//System.out.print(rs+"\n");
            			
				//System.out.print(rs.getString(1)+" * "+rs.getString(2)+" * "+rs.getString(3)+" * "+rs.getString(4)+" * ");

				ArrayList<String> param = new ArrayList();

				param.add(rs.getString(1)); //ID
				param.add(rs.getString(2)); //film
				param.add(rs.getString(3)); //score
				param.add(rs.getString(4)); //totalscore

				params.add(param);
            		}
						 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}        
		
		return params;

    }//end SELECT_Invit


	public void DELETE_Invit(String ID){
		String query = "DELETE FROM Invits WHERE ID=?";

		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);

			pst.setString(1, ID);
            		pst.executeUpdate();			 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}        


    }//end DELETE_Invit


	public boolean EXIST_Invit(String ID){
		boolean exist = false;

		String query ="SELECT ID  FROM Invits WHERE ID="+"'"+ID+"'";

		Connection conn = null;

		try{  
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if (!rs.next() ){
    				//System.out.println("no data");
			}else{
				exist=true;
				//System.out.println("data exist");
			}
						 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}

		return exist;     


    }//end EXIST_Invit


	public void UPDATE_Invit(String ID, String film, int score_updt){

		String query = "UPDATE Invits SET score = ?, totalscore = ? WHERE ID="+"'"+ID+"'"+" AND film="+"'"+film+"'";

		ArrayList<String> params = this.SELECT_Invit(ID, film);
		
		//System.out.println(params.get(0)+" "+params.get(5));

		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			PreparedStatement pst = conn.prepareStatement(query);


			pst.setInt(1, Integer.valueOf(params.get(2))+score_updt); //score
			pst.setInt(2, Integer.valueOf(params.get(3))-1); //totalscore

            		pst.executeUpdate();			 

		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s"+"\n", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch (Exception e) {
			 e.printStackTrace();
			}
		}        


    }//end INSERT_Invit


}//end class
