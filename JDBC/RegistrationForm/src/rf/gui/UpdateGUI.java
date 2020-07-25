package rf.gui;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import rf.db.*;

public class UpdateGUI extends Frame{
	
	private JLabel lblId;
	private JTextField tfId;
	private JLabel lblSelect;
	private JComboBox cbSelect;
	private JLabel lblValue;
	private JTextField tfValue;
	private JButton btnModify;
	private JPanel pnlOther;
	private JPanel pnlButton;
	private DataBaseLogics dbLogics;
	
	public UpdateGUI()
	{
		initComponents();
		addComponents();
		addListeners();
	}
	
	private void initPanels()
	{
		pnlOther = new JPanel();
		pnlButton = new JPanel();
	}
	private void initObjects()
	{
		dbLogics = new DataBaseLogics();
	}
	
	private void initComponents()
	{
		initObjects();
		String attributes[] = {"Name","Phone number","Department","City","Email","Father Name","Address" };
		lblId = new JLabel("ID");
		tfId = new JTextField();
		lblSelect = new JLabel("Select Attribute");
		cbSelect = new JComboBox(attributes);
		lblValue = new JLabel("Enter Data");
		tfValue = new JTextField();
		btnModify = new JButton("Modify");
		initPanels();
	}
	
	private void addToFrame()
	{
		setSize(300,200);
		setTitle("Modify");
		setLayout(new GridLayout(2,0));
		add(pnlOther, new GridBagConstraints());
		add(pnlButton,new GridBagConstraints());
		setVisible(true);
	}
	private void addComponents()
	{
		pnlOther.setLayout(new GridLayout(3,3));
		pnlOther.add(lblId);
		pnlOther.add(tfId);
		pnlOther.add(lblSelect);
		pnlOther.add(cbSelect);
		pnlOther.add(lblValue);
		pnlOther.add(tfValue);
		
		pnlButton.setLayout(new GridBagLayout());
		pnlButton.add(btnModify, new GridBagConstraints());
		addToFrame();
		
	}
	
	private void addListeners()
	{
		this.addWindowListener(new WindowClosingAdapter());
		btnModify.addActionListener(new modifyBtnActionListener());
	}
	
	private class WindowClosingAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			try {
				dbLogics.closeConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		}
	}
	
	private class modifyBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = Integer.parseInt(tfId.getText());
			String value=tfValue.getText();
			int index=cbSelect.getSelectedIndex();
			try {
				dbLogics.updateQuery(id,value, index);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
