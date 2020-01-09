package rf.db;

import java.sql.*;
import rf.gui.*;

public class DataBaseLogics {
	
	private Connection con;
	private Statement stmt;
	
	private long stuId,stuPhn;
	private String stuName,stuDept,stuCity,stuEmail,stuFname,stuAdd;
	public String query;
	
	
	public void setAttributes(long stuId, String stuName, long stuPhn, String stuDept,String stuCity, String stuEmail, String stuFname, String stuAdd) {
		this.stuId = stuId;
		this.stuPhn = stuPhn;
		this.stuName = stuName;
		this.stuDept = stuDept;
		this.stuCity = stuCity;
		this.stuEmail = stuEmail;
		this.stuFname = stuFname;
		this.stuAdd = stuAdd;
	}
	
	public DataBaseLogics()
	{
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","manoj","Manojdvs_7");  
			  
			//step3 create the statement object  
			stmt=con.createStatement();     
			  
			}catch(Exception e){ System.out.println(e);}
	}
	
	public void insertQuery()
	{
		query = "Insert into RegistrationDetails values("
				+ stuId + "," 
				+ "'"+ stuName +"'" + ","
				+ stuPhn + ","
				+ "'" + stuDept + "'" + ","
				+ "'" + stuCity + "'" + ","
				+ "'" + stuEmail + "'" + ","
				+ "'" + stuFname+ "'" + ","
				+ "'" + stuAdd + "'" +")";
		try {
			stmt.executeQuery(query);
		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			System.out.print("Constraint");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			new ErrorDialog("Student Already Exists");
		}
	}
	public void deleteQuery(int id)
	{
		query="Delete from RegistrationDetails where stuId="+id;
		try {
			stmt.executeQuery(query);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			new ErrorDialog("ID Not Found");
		}
	}
	public void closeConnection() throws SQLException
	{
		con.close(); 
	}
}
