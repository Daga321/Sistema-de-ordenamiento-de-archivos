package View;

public interface Actions {
  public static final String CONTINUE = "continue";
  
  public static final String CHECKBOXOPTIONS = "checkboxopcions";
  
  public static final String CHECKBOXADVANCEDOPTIONS = "checkboxadvancedoptions";
  
  public static final String CHECKBOXCUSTOMIZEDDIRECTORY = "checkboxcustomizeddirectory";
  
  public static final String CHECKBOXFILTER = "checkboxfilter";
  
  public static final String ORIGINFILECHOOSER = "originFileChooser";
  
  public static final String DESTINYFILECHOOSER = "destinyFileChooser";
  
  public static final String PATH = "path";
  
  public static final String ADVANCEDOPTIONS = "advancedOptions";
  
  public static final String PROGRESSBAR = "progressbar";
  
  void activate(String paramString);
  
  void desactivate(String paramString);
  
  String[] capture(String paramString);
  
  void show(String paramString, String[] paramArrayOfString);
  
  void message(String paramString);
}