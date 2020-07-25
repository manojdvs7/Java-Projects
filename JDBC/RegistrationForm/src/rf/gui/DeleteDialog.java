package rf.gui;
import java.awt.*;
import java.awt.event.*;

import rf.db.DataBaseLogics;

public class DeleteDialog extends Dialog {

	private static final long serialVersionUID = 1L;
	private Button btnDelete;
	private Label lblDelete;
	private TextField tfDelete;
	private static Frame frameDlg;
	private DataBaseLogics dbLogics; /*variables for delete dialog*/
	
	public DeleteDialog() /*Constructor for initializing all variables,listeners and adding components to frame*/
	{
		super(frameDlg,"Warnings",true); /*As this class extends dialog*/
		init();
		addComponents();
		addActionListeners();
	}
	public void init() /*initializing objects and components*/
	{
		dbLogics = new DataBaseLogics();
		frameDlg = new Frame();
		btnDelete = new Button("Delete");
		lblDelete = new Label("ID");
		tfDelete= new TextField();
	}
	public void addComponents() /* add components to frame*/
	{
		frameDlg.setTitle("Delete");
		frameDlg.setSize(300,200);
		frameDlg.setLayout(new FlowLayout());
		frameDlg.add(lblDelete);
		frameDlg.add(tfDelete);
		frameDlg.add(btnDelete);
		frameDlg.setVisible(true);
	}
	public void addActionListeners()/* add listeners to components*/
	{
		frameDlg.addWindowListener(new WindowClosingAdapter());
		btnDelete.addActionListener(new btnDeleteActionListener());
		
	}
	private class WindowClosingAdapter extends WindowAdapter{ /*Window Listener */
		public void windowClosing(WindowEvent e) {
			frameDlg.dispose();
		}
	}
	private class btnDeleteActionListener implements ActionListener/*delete button listener*/
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(tfDelete.getText());
			dbLogics.deleteQuery(id);
		}
		
	}
}
