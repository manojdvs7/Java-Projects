package rf.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import rf.db.*;

public class RegistrationFormGUI extends Frame{

	private JLabel lblId,lblName,lblPhn,lblDept,lblCt,lblEmail,lblFname,lblAdd;
	private JTextField tfId,tfName,tfPhn,tfDept,tfCt,tfEmail,tfFname,tfAdd;
	private JPanel panel1,panel2;
	private JButton btnSubmit,btnModify,btnDelete;
	private DataBaseLogics dbLogics;
	private long stuId,stuPhn;
	private String stuName,stuDept,stuCity,stuEmail,stuFname,stuAdd;		/*variables used for creating GUI*/

	public RegistrationFormGUI() /* default constructor used for initializing all the variables */
	{
		initObjects();
		initComponents();
		addComponents();
		addListeners();
	}
	
	private void initObjects() /*initialize all the objects of all user defined classes*/
	{
		dbLogics = new DataBaseLogics();
	}
	
	private void initLabels()  /* initialize label objects in UI*/
	{
		lblId = new JLabel("Student ID");
		lblName= new JLabel("Student Name");
		lblPhn= new JLabel("Phone Number");
		lblDept= new JLabel("Department");
		lblCt= new JLabel("City");
		lblEmail= new JLabel("Email Address");
		lblFname= new JLabel("Father Name");
		lblAdd= new JLabel("Address");
	}
	private void initTextFields() /* initialize text fields objects in UI*/
	{
		
		tfId=new JTextField();
		tfName=new JTextField();
		tfPhn=new JTextField();
		tfDept=new JTextField();
		tfCt=new JTextField();
		tfEmail=new JTextField();
		tfFname=new JTextField();
		tfAdd=new JTextField();
	}
	private void initButtons() /* initialize button objects in UI*/
	{
		btnSubmit=new JButton("Submit");
		btnModify=new JButton("Modify");
		btnDelete=new JButton("Delete");
	}
	private void initComponents() /* initialize panels*/
	{
		panel1=new JPanel();
		panel2=new JPanel();
		initLabels();
		initTextFields();
		initButtons();
	}
	private void addToPanel1() /* add labels and text fields to panel 1*/
	{
		panel1.setLayout(new GridLayout(8,8));
		panel1.add(lblId);
		panel1.add(tfId);
		
		panel1.add(lblName);
		panel1.add(tfName);
		
		panel1.add(lblPhn);
		panel1.add(tfPhn);
		
		panel1.add(lblDept);
		panel1.add(tfDept);
		
		panel1.add(lblCt);
		panel1.add(tfCt);
		
		panel1.add(lblEmail);
		panel1.add(tfEmail);
		
		panel1.add(lblFname);
		panel1.add(tfFname);
		
		panel1.add(lblAdd);
		panel1.add(tfAdd);
	}
	private void addToPanel2() /* add buttons to panel 2*/
	{
		panel2.setLayout(new FlowLayout());
		panel2.add(btnSubmit);
		panel2.add(btnModify);
		panel2.add(btnDelete);
	}
	private void addComponents() /* add panels to the frame*/
	{
		setTitle("Registration Form");
		setLayout(new GridLayout(2,0));
		setSize(500,500);
		addToPanel1();
		addToPanel2();
		add(panel1);
		add(panel2);
		setVisible(true);
	}
	
	private void addListeners() /* add listeners to buttons */
	{
		this.addWindowListener(new WindowClosingAdapter());
		btnSubmit.addActionListener(new submitBtnActionListener());
		btnDelete.addActionListener(new deleteBtnActionListener());
		btnModify.addActionListener(new updateBtnActionListener());
	}
	
	private class WindowClosingAdapter extends WindowAdapter{ /* window adapter for closing the GUI*/
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	
	private class submitBtnActionListener implements ActionListener /* Listerner for submit button*/
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			try 
			{
				stuId=Integer.parseInt(tfId.getText());
				stuName=tfName.getText();
				stuPhn=Integer.parseInt(tfPhn.getText());
				stuDept=tfDept.getText();
				stuCity=tfCt.getText();
				stuEmail=tfEmail.getText();
				stuFname=tfFname.getText();
				stuAdd=tfAdd.getText();
				
				if(stuName.trim().isEmpty() || stuDept.trim().isEmpty() || stuCity.trim().isEmpty() /*checks if all the details are filled*/
				|| stuEmail.trim().isEmpty() || stuFname.trim().isEmpty() || stuAdd.trim().isEmpty() )
				{
					throw new Exception();
				}
				
				dbLogics.setAttributes(stuId, stuName, stuPhn, stuDept, stuCity, stuEmail, stuFname, stuAdd);
				dbLogics.insertQuery();
			}
			catch(Exception e1)
			{
				new ErrorDialog("Check all fields"); /* Warning for not filling all the details*/
			}
			

		}
		
	}
	private class deleteBtnActionListener implements ActionListener /*Listener for delete button*/
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			new DeleteDialog(); /* creates a delete window */
		}
		
	}
	
	private class updateBtnActionListener implements ActionListener /* Listener for update*/
	{
		@Override
		public void actionPerformed(ActionEvent e) { /* creates a update window */
			new UpdateGUI();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RegistrationFormGUI(); /* creates Registration window*/
	}

}
