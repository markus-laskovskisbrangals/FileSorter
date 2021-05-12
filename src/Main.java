import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {
	
	static AppWindow appWindow;
	static String configLocationPath = System.getProperty("user.dir") + "\\config.conf";
	static String encoding = "UTF-8";
	static String seperator = ": ";
	static HashMap<String, String[]> fileCategories = new HashMap<String, String[]>();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		initApp();
	}
	
	//Initiate app window. If config is not fount error pops up.
	private static void initApp() {
		try {
			File config = new File(configLocationPath);
			Scanner reader = new Scanner(config, encoding);
			reader.nextLine();
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] parts = line.split(seperator);
				if(parts.length < 2) continue;
				new FileType(parts);
			}
			reader.close();
			appWindow = new AppWindow();
		} catch (FileNotFoundException e) {
			printErrorMessage("Config file is missing. Put it in app's directory and try again.", "Missing config file");
			return;
		}
	}
	
	//Creates popup windows for specific purposes.
	static void printErrorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	static void printInfoMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
