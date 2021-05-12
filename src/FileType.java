
public class FileType {
	String type;
	String[] extensions;
	
	FileType(String[] parts){
		this.type = parts[0];
		this.extensions = getExtensions(parts[1]);
		Main.fileCategories.put(this.type, this.extensions);
	}
	
	static String[] getExtensions(String part) {
		String[] extensionList = part.split(" ");
		for(String ext : extensionList) {
			ext.trim();
		}
		return extensionList;
	}
}
