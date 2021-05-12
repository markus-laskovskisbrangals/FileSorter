import javax.swing.JFrame;

public class AppWindow extends JFrame{
	static String title = "File Sorter v1.0.0";
	static int windowWidth = 480;
	static int windowHeight = 240;
	
	AppWindow(){
		new JFrame();
		setTitle(title);
		setSize(windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(new AppUI());
		setVisible(true);
	}
}
