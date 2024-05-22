package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;

public class createDbTable {
	Connection connection;
	private final String url = "jdbc:postgresql://localhost:5432/student";
	private  final String userName = "postgres";
	private  final String password = "123";
	private  final String tableName="student";

	void connectDb() {
		try {
			connection= DriverManager.getConnection(url, userName, password);
			System.out.println("Connection success!!");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	void createTable() {
		try {
			Statement s= connection.createStatement();
			String table= "CREATE TABLE IF NOT EXISTS "+tableName+"(roll SERIAL PRIMARY KEY, name VARCHAR(50), gender varchar(255), fav_food varchar(255))";
			s.execute(table);
			System.out.println("Table "+tableName+" is created successfully!!");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void insertTable(String name,String gender,String favoriteFoods) {
		try {
			Statement s= connection.createStatement();
			String insertSQL =String.format("INSERT INTO %s(name,gender,fav_food)VALUES('%s','%s','%s')",tableName,name,gender,favoriteFoods);
			int rowsAffected= s.executeUpdate(insertSQL);
			if(rowsAffected>0)
				System.out.println("Successfully inserted!!");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void displayStudentList( JTextArea textArea) {
        try{

//            StringBuilder sb = new StringBuilder();
        	Statement s= connection.createStatement();
        	String selectSql= "SELECT * FROM "+ tableName;
        	
        	ResultSet rs= s.executeQuery(selectSql);
        	StringBuilder exp= new StringBuilder();
            while (rs.next()) {
            	int rollno=rs.getInt("roll");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String favFood = rs.getString("fav_food");
                
               exp.append("\n\nRoll: "+rollno+ "\nName: "+name+"\nGender "+gender+"\nFavFood: "+favFood);
               System.out.println("\n\nRoll: "+rollno+ "\nName: "+name+"\nGender "+gender+"\nFavFood: "+favFood);
                textArea.setText(exp.toString());
            }

           
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
}






//--------------------------------------------------------------------------------
//CREATE TYPE gender_enum AS ENUM ('Male', 'Female');
//CREATE TYPE favFood_enum AS ENUM ('Pizza', 'Burger','Momo');
//-- Create a table using the enum type
//CREATE TABLE students (
//  id SERIAL PRIMARY KEY,
//  name VARCHAR(50),
//  gender gender_enum,
//	fav_food favFood_enum
//);