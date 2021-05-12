import java.awt.Color;

import javax.swing.*;

public class AppUI extends JPanel{
	private static int defaultHeight = 25;
	private static int windowWidth = AppWindow.windowWidth;
	private static int windowHeight = AppWindow.windowHeight;
	private static int marginLeft = 20;
	private static int marginRight = windowWidth-60;
	private static Color bgColor = new Color(50, 50, 50);
	private static Color fgColor = new Color(250, 250, 250);
	JLabel firstStep = createText("1. Choose directory with your file mess.", marginLeft, 10, 250, 20);
	JButton browse = createNewButton("Browse Directory", windowWidth-175, 40, 135, defaultHeight);
	JLabel secondStep = createText("2. Sort your mess!", marginLeft, 100, marginRight, defaultHeight);
	JButton sortButton = createNewButton("Sort my mess", marginLeft, 130, marginRight, defaultHeight*2);
	JTextField directoryName = createInput(1, false, marginLeft, 40, windowWidth - 195, defaultHeight);
	JCheckBox checkSort = createCheckBox("Put files in folders by last modified year", marginLeft, 70, marginRight, defaultHeight);
	
	AppUI(){
		new JPanel();
		setBounds(0, 0, windowWidth, windowHeight);
		setBackground(bgColor);
		setLayout(null);
		add(browse);
		add(directoryName);
		add(checkSort);
		add(firstStep);
		add(secondStep);
		add(sortButton);
		new SetDirectory(browse, directoryName);
		new SortFiles(checkSort, sortButton);
	}
	
	//Method to create button for UI.
	private static JButton createNewButton(String title, int x, int y, int width, int height) {
		JButton button = new JButton(title);
		button.setBounds(x, y, width, height);
		return button;
	}
	
	//Method to create text field for UI.
	private static JTextField createInput(int lineCount, boolean editable, int x, int y, int width, int height) {
		JTextField input = new JTextField(lineCount);
		input.setEditable(editable);
		input.setBounds(x, y, width, height);
		return input;
	}
	
	//Method to create text for UI.
	private static JLabel createText(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		label.setForeground(fgColor);
		return label;
	}
	
	//Method to make check box.
	private static JCheckBox createCheckBox(String text, int x, int y, int width, int height) {
		JCheckBox checkBox = new JCheckBox(text);
		checkBox.setBounds(x, y, width, height);
		checkBox.setBackground(bgColor);
		checkBox.setForeground(fgColor);
		return checkBox;
	}
	
}
