package View;

public interface Actions {
  public static final String CONTINUAR = "continuar";
  
  public static final String FILECHOOSERORIGEN = "filechooser";
  
  public static final String PROGRESSBAR = "progressbar";
  
  void activar(String paramString);
  
  void desactivar(String paramString);
  
  String[] capturar(String paramString);
  
  void mostrar(String paramString, String[] paramArrayOfString);
  
  void mensaje(String paramString);
}