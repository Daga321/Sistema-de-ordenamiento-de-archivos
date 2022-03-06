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
	
	private File file;
	private SimpleDateFormat sdfMonth;
	private SimpleDateFormat sdfYear;
	private String mes;
	private String year;
	private BasicFileAttributes attributes;
	private FileTime creationDate;
	private FileTime modificationDate;
	private File directory;
	
	private String[] data;
	private int min;
	private int max;
	
	public RunnableOrganizer(Management management) {
		this.management = management;
	}
	
	public void run() {
		try {
			Thread.sleep(700);
			sdfMonth = new SimpleDateFormat("MMMMMMMMMM");
			sdfYear = new SimpleDateFormat("yyyy");
			directory = null;
			organize();
		} catch (Exception e) {}
	}

	public void setData(String[] data, int min, int max) {
		this.data = data;
		this.min = min;
		this.max = max;
	}

	public void organize() {
		
//		System.out.println(files.length+"<-F");
		
		file = management.getFile(data);
		
		if (file != null) {
			while(!management.getOrganize()) {}//esperando luz verde para iniciar
			for (int i = min; i < max; i++) {
//				System.out.println("==>"+file.getName()+"<==");
				try {
					if (file.isDirectory()) {
						String[] info = {
								file.toPath().toString(), data[1], data[2], data[3], data[4], data[5], data[6], data[7]
						};
						management.totalFilesSubtract(1);
//						Thread.sleep(150);
						management.organizeSort(info);
					} else {
						attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class, new java.nio.file.LinkOption[0]);
						creationDate = attributes.creationTime();
						modificationDate = attributes.lastModifiedTime();
//						System.out.println(file.getName()+"  Fecha creacion::" + creationDate+"  Fecha modificacion::" + modificationDate);
						if (data[2].equals("true") || data[3].equals("true")) {
							if ((new Date(creationDate.toMillis())).before(new Date(modificationDate.toMillis()))) {
								year = sdfYear.format(new Date(creationDate.toMillis()));
//								System.out.println("toma año cracion");
							} else {
								year = sdfYear.format(new Date(modificationDate.toMillis()));
//								System.out.println("toma año modificacion");
							} 
	         
							if (data[3].equals("true")) {
								directory = new File(String.valueOf(data[1]) + "/" + year);
							} else {
								if ((new Date(creationDate.toMillis())).before(new Date(modificationDate.toMillis()))) {
									mes = sdfMonth.format(new Date(creationDate.toMillis()));
//									System.out.println("toma mes cracion");
								} else {
									mes = sdfMonth.format(new Date(modificationDate.toMillis()));
//									System.out.println("toma mes modificacion");
								} 
								directory = new File(String.valueOf(data[1]) + "/" + year + "/" + mes);
							}
						} else if (data[4].equals("true")) {
							directory = new File(String.valueOf(data[1]) + "/" + data[5]);
						} 

						if (!directory.exists()) {
							directory.mkdirs();
						}
						directory = new File(directory.toPath() + "/" + file.getName());
						if(directory.exists()) {
							//el archivo ya fue transferido desde otra carpeta a la actual siendo esta su destino por ende no amerita ser contada como parte del total de archivos existentes ps desde la carpeta anterior conto
							management.totalFilesSubtract(1);
						}else {
							file.renameTo(directory);
							management.processedFilesAdd(1);
						}
						
//						Thread.sleep(250);
					} 
				} catch (Exception exception) {
					exception.printStackTrace();
				}
	    } 
	  }
	}

	
}
