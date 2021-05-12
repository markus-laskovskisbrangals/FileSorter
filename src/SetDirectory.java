import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SetDirectory implements ActionListener{
	
	private JButton browse;
	private JTextField directoryName;
	static String filePath = "";
	
	SetDirectory(JButton browse, JTextField directoryName){
		this.browse = browse;
		this.directoryName = directoryName;
		
		browse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filePath = getDirectory();
				directoryName.setText(filePath);
				SortFiles.filePath = filePath;
			}
			
		});
	}
	
	//Gives user a window to choose directory and shows it on screen.
	private static String getDirectory() {
		JFileChooser browser = new JFileChooser();
		String path = "";
		browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = browser.showOpenDialog(Main.appWindow);
		if(option == JFileChooser.APPROVE_OPTION) {
			path = browser.getSelectedFile().getAbsolutePath();
		}
		return path;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
