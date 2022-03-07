package View;

public interface Actions {
	
	public static final String CONTINUE = "continue";
  
	public static final String ORIGINFILECHOOSER = "originFileChooser";
	
	public static final String PATH = "path";
  
	public static final String PROGRESSBAR = "progressbar";
  
	void activate(String paramString);
  
	void desactivate(String paramString);
  
	String[] capture(String paramString);
  
	void show(String paramString, String[] paramArrayOfString);
  
	void message(String paramString);
}