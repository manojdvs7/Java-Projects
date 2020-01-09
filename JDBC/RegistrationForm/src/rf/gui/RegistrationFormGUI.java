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
	private String stuName,stuDept,stuCity,stuEmail,stuFname,stuAdd;
	
	public RegistrationFormGUI()
	{
		initObjects();
		initComponents();
		addComponents();
		addListeners();
	}
	
	private void initObjects()
	{
		dbLogics = new DataBaseLogics();
	}
	
	private void initLabels()
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
	private void initTextFields()
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
	private void initButtons()
	{
		btnSubmit=new JButton("Submit");
		btnModify=new JButton("Modify");
		btnDelete=new JButton("Delete");
	}
	private void initComponents()
	{
		panel1=new JPanel();
		panel2=new JPanel();
		initLabels();
		initTextFields();
		initButtons();
	}
	private void addToPanel1()
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
	private void addToPanel2()
	{
		panel2.setLayout(new FlowLayout());
		panel2.add(btnSubmit);
		panel2.add(btnModify);
		panel2.add(btnDelete);
	}
	private void addComponents()
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
	
	private void addListeners()
	{
		this.addWindowListener(new WindowClosingAdapter());
		btnSubmit.addActionListener(new submitBtnActionListener());
		btnDelete.addActionListener(new deleteBtnActionListener());
		btnModify.addActionListener(new updateBtnActionListener());
	}
	
	private class WindowClosingAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	
	private class submitBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			stuId=Integer.parseInt(tfId.getText());
			stuName=tfName.getText();
			stuPhn=Integer.parseInt(tfPhn.getText());
			stuDept=tfDept.getText();
			stuCity=tfCt.getText();
			stuEmail=tfEmail.getText();
			stuFname=tfFname.getText();
			stuAdd=tfAdd.getText();
			
			dbLogics.setAttributes(stuId, stuName, stuPhn, stuDept, stuCity, stuEmail, stuFname, stuAdd);
			dbLogics.insertQuery();
		}
		
	}
	private class deleteBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			new DeleteDialog();
		}
		
	}
	
	private class updateBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			new UpdateGUI();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RegistrationFormGUI();
	}

}
