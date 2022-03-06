package Model;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RunnableOrganizer implements Runnable{
	
	private Management management;
	
	private File originDirectory;
	private SimpleDateFormat sdfMonth;
	private SimpleDateFormat sdfYear;
	private String mes;
	private String year;
	private BasicFileAttributes attributes;
	private FileTime creationDate;
	private FileTime modificationDate;
	private File directory;
	int totalFiles;
	int processedFiles;
	private String[] data;
	private Long start;
	private Long end;
	
	public RunnableOrganizer(Management management) {
		this.management = management;
	}
	
	public void run() {
		try {
			Thread.sleep(700);
			start = System.currentTimeMillis();
			sdfMonth = new SimpleDateFormat("MMMMMMMMMM");
			sdfYear = new SimpleDateFormat("yyyy");
			directory = null;
			organize(data);
			end = System.currentTimeMillis();
			management.addTime(end-start);
		} catch (Exception e) {}
}

	public void resetProgress() {
		totalFiles = 0;
		processedFiles = 0;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	private File[] files(File carpeta) {
		return carpeta.listFiles();
	}

	private File[] filterFiles(File carpeta, final String extencion) {
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

	public void organize(String[] data) {
		originDirectory = new File(data[0]);
//		destinyDirectory = new File(data[1]);
	  
		File[] files = null;
		if (data[6].equals("false")) {
			files = files(originDirectory);
		} else {
			files = filterFiles(originDirectory, data[7]);
		} 
		
		totalFiles += files.length;
		if (files.length != 0) {
			for (int i = 0; i < files.length; i++) {
				System.out.println("==>"+files[i].getName()+"<==");
				try {
					if (files[i].isDirectory()) {
						String[] info = {
								files[i].toPath().toString(), data[1], data[2], data[3], data[4], data[5], data[6], data[7]
						};
						totalFiles--;
//						Thread.sleep(150);
						organize(info);
					} else {
						attributes = Files.readAttributes(files[i].toPath(), BasicFileAttributes.class, new java.nio.file.LinkOption[0]);
						creationDate = attributes.creationTime();
						modificationDate = attributes.lastModifiedTime();
						System.out.println(files[i].getName()+"  Fecha creacion::" + creationDate+"  Fecha modificacion::" + modificationDate);
						if (data[2].equals("true") || data[3].equals("true")) {
							if ((new Date(creationDate.toMillis())).before(new Date(modificationDate.toMillis()))) {
								year = sdfYear.format(new Date(creationDate.toMillis()));
								System.out.println("toma año cracion");
							} else {
								year = sdfYear.format(new Date(modificationDate.toMillis()));
								System.out.println("toma año modificacion");
							} 
	         
							if (data[3].equals("true")) {
								directory = new File(String.valueOf(data[1]) + "/" + year);
							} else {
								if ((new Date(creationDate.toMillis())).before(new Date(modificationDate.toMillis()))) {
									mes = sdfMonth.format(new Date(creationDate.toMillis()));
									System.out.println("toma mes cracion");
								} else {
									mes = sdfMonth.format(new Date(modificationDate.toMillis()));
									System.out.println("toma mes modificacion");
								} 
								directory = new File(String.valueOf(data[1]) + "/" + year + "/" + mes);
							}
						} else if (data[4].equals("true")) {
							directory = new File(String.valueOf(data[1]) + "/" + data[5]);
						} 

						if (!directory.exists()) {
							directory.mkdirs();
						}
						directory = new File(directory.toPath() + "/" + files[i].getName());
						if(directory.exists()) {
							//el archivo ya fue transferido desde otra carpeta a la actual siendo esta su destino por ende no amerita ser contada como parte del total de archivos existentes ps desde la carpeta anterior conto
							totalFiles--;
						}else {
							files[i].renameTo(directory);
							processedFiles++;
						}
						
//						Thread.sleep(250);
					} 
				} catch (Exception exception) {}
	    } 
	  }
	}

	public String[] getProgress() {
		String[] data = {
				""+processedFiles, ""+totalFiles
	    };
		return data;
	}
}
