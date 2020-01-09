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
	private DataBaseLogics dbLogics;
	
	public DeleteDialog()
	{
		super(frameDlg,"Warnings",true);
		init();
		addComponents();
		addActionListeners();
	}
	public void init()
	{
		dbLogics = new DataBaseLogics();
		frameDlg = new Frame();
		btnDelete = new Button("Delete");
		lblDelete = new Label("ID");
		tfDelete= new TextField();
	}
	public void addComponents()
	{
		frameDlg.setTitle("Delete");
		frameDlg.setSize(300,200);
		frameDlg.setLayout(new FlowLayout());
		frameDlg.add(lblDelete);
		frameDlg.add(tfDelete);
		frameDlg.add(btnDelete);
		frameDlg.setVisible(true);
	}
	public void addActionListeners()
	{
		frameDlg.addWindowListener(new WindowClosingAdapter());
		btnDelete.addActionListener(new btnDeleteActionListener());
		
	}
	private class WindowClosingAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			frameDlg.dispose();
		}
	}
	private class btnDeleteActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(tfDelete.getText());
			dbLogics.deleteQuery(id);
		}
		
	}
}
