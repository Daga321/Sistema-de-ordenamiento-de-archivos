package modelo;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;



public class HiloOrdenamiento extends Thread{
	
	private File carpetaOrigen;
	File carpetaDestino;
	private SimpleDateFormat sdfMes;
	private SimpleDateFormat sdfAnio;
	private String mes;
	private String aAnio;
	private BasicFileAttributes atributos;
	private FileTime fechaCreacion;
	private FileTime fechaModificacion;
	private File carpeta;
	int totalArchivos;
	int archivosPasados;
	private String[] datos;

	public void run() {
		super.run();
  
		try {
			Thread.sleep(700);
			sdfMes = new SimpleDateFormat("MMMMMMMMMM");
			sdfAnio = new SimpleDateFormat("yyyy");
			carpeta = null;
			ordenar(datos);
		} catch (InterruptedException interruptedException) {}
}

	public boolean getEstado() {
		return isAlive();
	}

	public void resetearProgreso() {
		totalArchivos = 0;
		archivosPasados = 0;
	}

	public void setDatos(String[] datos) {
		this.datos = datos;
	}

	private File[] archivos(File carpeta) {
		return carpeta.listFiles();
	}

	private File[] archivosFiltro(File carpeta, final String extencion) {
		FileFilter filtro = new FileFilter(){
			public boolean accept(File archivo){
	    	  	if (archivo.isDirectory() || archivo.getName().contains(extencion)) {
	    	  		return true;
	    	  	}
		  		return false;
	  		}
		};
		return carpeta.listFiles(filtro);
	}

	private File[] ordenarArchivos(File[] archivos) {
		for (int i = 1; i < archivos.length; i++) {
			for (int j = 0; j < archivos.length - 1; j++) {
					if (archivos[j].isFile()) {
						File aux = archivos[j];
						archivos[j] = archivos[j + 1];
						archivos[j + 1] = aux;
					} 
			} 
		} 
		return archivos;
	}
	
	public void ordenar(String[] datos) {
		carpetaOrigen = new File(datos[0]);
		carpetaDestino = new File(datos[1]);
	  
		File[] archivos = null;
		if (datos[6].equals("false")) {
			archivos = archivos(carpetaOrigen);
		} else {
			archivos = archivosFiltro(carpetaOrigen, datos[7]);
		} 
		archivos = ordenarArchivos(archivos);
	
		totalArchivos += archivos.length;
		if (archivos.length != 0) {
			for (int i = 0; i < archivos.length; i++) {
				try {
					if (archivos[i].isDirectory()) {
						String[] info = {
								archivos[i].toPath().toString(), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7]
						};
						totalArchivos--;
						Thread.sleep(150);
						ordenar(info);
					} else {
						atributos = Files.readAttributes(archivos[i].toPath(), BasicFileAttributes.class, new java.nio.file.LinkOption[0]);
						fechaCreacion = atributos.creationTime();
						fechaModificacion = atributos.lastModifiedTime();
						System.out.println(archivos[i].getName());
						System.out.println("Fecha creacion::" + fechaCreacion);
						System.out.println("Fecha modificacion::" + fechaModificacion);
						if (datos[2].equals("true") || datos[3].equals("true")) {
							if ((new Date(fechaCreacion.toMillis())).before(new Date(fechaModificacion.toMillis()))) {
								aAnio = sdfAnio.format(new Date(fechaCreacion.toMillis()));
								System.out.println("toma año cracion");
							} else {
								aAnio = sdfAnio.format(new Date(fechaModificacion.toMillis()));
								System.out.println("toma año modificacion");
							} 
	         
							if (datos[3].equals("true")) {
								carpeta = new File(String.valueOf(datos[1]) + "/" + aAnio);
							} else {
								if ((new Date(fechaCreacion.toMillis())).before(new Date(fechaModificacion.toMillis()))) {
									mes = sdfMes.format(new Date(fechaCreacion.toMillis()));
									System.out.println("toma mes cracion");
								} else {
									mes = sdfMes.format(new Date(fechaModificacion.toMillis()));
									System.out.println("toma mes modificacion");
								} 
								carpeta = new File(String.valueOf(datos[1]) + "/" + aAnio + "/" + mes);
							}
						} else if (datos[4].equals("true")) {
							carpeta = new File(String.valueOf(datos[1]) + "/" + datos[5]);
						} 

						if (!carpeta.exists()) {
							carpeta.mkdirs();
						}
						carpeta = new File(carpeta.toPath() + "/" + archivos[i].getName());
						archivos[i].renameTo(carpeta);
						archivosPasados++;
						Thread.sleep(250);
					} 
				} catch (Exception exception) {}
	    } 
	  }
	}

	public String[] obtenerProgreso() {
		String[] datos = {
				""+archivosPasados, ""+totalArchivos
	    };
		return datos;
	}
}
