package rf.gui;
import java.awt.*;
import java.awt.event.*;

public class ErrorDialog extends Dialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button btnOK;
	private Label errorLbl;
	private static Frame frameDlg;
	private String errorMsg;
	public ErrorDialog(String errorMsg)
	{
		super(frameDlg,"Warnings",true);
		this.errorMsg=errorMsg;
		init();
		addComponents();
		addActionListeners();
	}
	public void init()
	{
		frameDlg = new Frame();
		btnOK = new Button("OK");
		errorLbl = new Label(errorMsg); 
	}
	public void addComponents()
	{
		frameDlg.setSize(300,200);
		frameDlg.setLayout(new FlowLayout());
		frameDlg.add(errorLbl);
		frameDlg.add(btnOK);
		frameDlg.setVisible(true);
	}
	public void addActionListeners()
	{
		frameDlg.addWindowListener(new WindowClosingAdapter());
		btnOK.addActionListener(new btnOkActionListener());
		
	}
	private class WindowClosingAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			frameDlg.dispose();
		}
	}
	private class btnOkActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			frameDlg.dispose();
		}
		
	}
}
