package rf.db;

import java.sql.*;
import rf.gui.*;

public class DataBaseLogics {
	
	private Connection con;
	private Statement stmt;
	
	private long stuId,stuPhn;
	private String stuName,stuDept,stuCity,stuEmail,stuFname,stuAdd;
	public String query;
																		/*Objects for student info and executing sql query */
	
	public void setAttributes(long stuId, String stuName, long stuPhn, String stuDept,
			String stuCity, String stuEmail, String stuFname, String stuAdd) { /* function to get info */
		this.stuId = stuId;
		this.stuPhn = stuPhn;
		this.stuName = stuName;
		this.stuDept = stuDept;
		this.stuCity = stuCity;
		this.stuEmail = stuEmail;
		this.stuFname = stuFname;
		this.stuAdd = stuAdd;
	}
	
	public DataBaseLogics() /*constructor to establish and create conection*/
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
	
	public void insertQuery()/*insert info to db*/
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
			new ErrorDialog("Student Already Exists");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			new ErrorDialog("Student Already Exists");
		}
	}
	public void deleteQuery(int id) /*delete info to db*/
	{
		query="Delete from RegistrationDetails where id="+id;
		int rs = 0;
		try
		{
			rs =stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		if(rs==0)
			new ErrorDialog("ID not found");
	}
	
	public void updateQuery(int id,String value,int index) throws SQLException /*update info to db*/
	{
		String[] attributes= {"name","phone","dept","city","email","fname","address"};
		query = "update RegistrationDetails set "+attributes[index]+"='"+value+"'where id="+id ;
		int rs = 0;
		try
		{
			rs =stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		if(rs==0)
			new ErrorDialog("ID not found");
	}
	
	public void closeConnection() throws SQLException /*Close connection to db*/
	{
		con.close(); 
	}
}
