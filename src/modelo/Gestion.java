package modelo;


public class Gestion {
	private HiloOrdenamiento hiloOrdenamiento;
  
	public void ordenar(String[] datos) {
		hiloOrdenamiento = new HiloOrdenamiento();
		System.out.println("hilo::" + hiloOrdenamiento.getState());
		System.out.println("\\N " + hiloOrdenamiento.isAlive());
		hiloOrdenamiento.resetearProgreso();
		hiloOrdenamiento.start();
		hiloOrdenamiento.setDatos(datos);
	}

  
	public boolean getEstado() {
	  	return hiloOrdenamiento.getEstado();
	}
  
	public String[] obtenerProgreso() {
	  	return hiloOrdenamiento.obtenerProgreso();
	}
}
