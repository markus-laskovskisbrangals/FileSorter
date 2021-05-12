import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class SortFiles implements ActionListener{
	
	private static JCheckBox checkBox;
	private static JButton sortButton;
	private static String dateFormat = "yyyy";
	private static String remainingFileDir = "Other Files";
	static String filePath = "";
	private static String protectedConfig = System.getProperty("user.dir") + "\\protectedPath.conf";
	
	SortFiles(JCheckBox check, JButton sortButton){
		
		this.checkBox = check;
		this.sortButton = sortButton;
		
		sortButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int fileCount = countFiles(filePath);
				if(filePath.equals("")) {
					Main.printErrorMessage("You need to specify the path to sort your files.", "Path not specified");
				}else if(getAccess(filePath)) {
					sortFiles(filePath);
					Main.printInfoMessage(fileCount + " files has been sorted successfully.", "Success");
				}
			}
			
		});
		
	}
	
	//Checks if directory is safe to sort.
	private static boolean getAccess(String filePath) {
		try {
			File config = new File(protectedConfig);
			Scanner reader = new Scanner(config, Main.encoding);
			reader.nextLine();
			while(reader.hasNextLine()) {
				String path = reader.nextLine();
				if(filePath.equals(path)) {
					Main.printErrorMessage("The path you are trying to sort is forbiden by safety reasons.", "Forbiden Path");
					return false;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			Main.printErrorMessage("Config file for protected path list was not found, files will not be sorted.", "Config file was not found");
			return false;
		}
		return true;
	}
	
	//Counts files in directory.
	private static int countFiles(String path) {
		int files = 0;
		try {
			File fileList = new File(path);
			for(File file : fileList.listFiles()) {
				if(!file.isDirectory()) {
					files++;
				}
			}
		} catch (Exception e) {
			files = 0;
		}
		return files;
	}
	
	//Main method for file sorting.
	private static void sortFiles(String filePath) {
		File fileDirectory = new File(filePath);
		for(File file : fileDirectory.listFiles()) {
			detectFileType(file);
		}
		sortRemainingFiles(fileDirectory);
	}
	
	//Detects file type according to it's extension and information from config file.
	private static void detectFileType(File file) {
		for(String type : Main.fileCategories.keySet()) {
			for(String ext : Main.fileCategories.get(type)) {
				if(file.getName().endsWith(ext)) {
					moveFileToDir(file, type);
				}
			}
		}
	}
	
	//Creates new folders for each file type and moves files to the new directories.
	private static void moveFileToDir(File file, String dirName) {
		File dir = new File(file.getParent(), dirName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		file.renameTo(new File(dir, file.getName()));
		if(checkBox.isSelected()) {
			sortByYears(file, dir);
		}
	}
	
	//Puts files in sub directories according to their last modify year if checkbox was previously checked.
	private static void sortByYears(File file, File dir) {
		File files = new File(dir.getAbsolutePath(), file.getName());
		String modifyYear = getModifyYear(files);
		File dirYear = new File(dir.getAbsolutePath(), modifyYear);
		if(!dirYear.exists()) {
			dirYear.mkdirs();
		}
		files.renameTo(new File(dirYear, files.getName()));
	}
	
	//Gets year for each file when it was modified last time.
	private static String getModifyYear(File file) {
		long modifyDate = file.lastModified();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String year = format.format(modifyDate);
		return year;
	}
	
	//Puts all remaining files into directory which type was not specified in config file.
	private static void sortRemainingFiles(File file) {
		for(File remainingFiles : file.listFiles()) {
			if(!remainingFiles.isDirectory()) {
				moveFileToDir(remainingFiles, remainingFileDir);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
