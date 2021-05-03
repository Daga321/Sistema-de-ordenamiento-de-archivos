package vista;

public interface Acciones {
  public static final String CONTINUAR = "continuar";
  
  public static final String CHECKBOXOPCIONES = "checkboxopciones";
  
  public static final String CHECKBOXOPCIONESAVANZADAS = "checkboxopcionesavanzadas";
  
  public static final String CHECKBOXCARPETAPERSONALIZADA = "checkboxcarpetapersonalizada";
  
  public static final String CHECKBOXFILTRO = "checkboxfiltro";
  
  public static final String FILECHOOSERORIGEN = "filechooserorigen";
  
  public static final String FILECHOOSERDESTINO = "filechooserdestino";
  
  public static final String UBICACION = "ubicacion";
  
  public static final String OPCIONESAVANZADAS = "opcionesavanzadas";
  
  public static final String PROGRESSBAR = "progressbar";
  
  void activar(String paramString);
  
  void desactivar(String paramString);
  
  String[] capturar(String paramString);
  
  void mostrar(String paramString, String[] paramArrayOfString);
  
  void mensaje(String paramString);
}


/* Location:              C:\Users\Daga\Desktop\SOA\!\vista\Acciones.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */