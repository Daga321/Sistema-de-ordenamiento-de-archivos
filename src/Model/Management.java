package Model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class Management {

	private Thread threadOrganizer;
	private RunnableOrganizer runnableOrganizer;
	private long time;
	private ArrayList<Thread> threadsList;
	private int totalFiles;
	private int processedFiles;
	
	private long start;
	private long end;
	
	private final int BLOCK = 500; 
	
	public Management() {
		threadsList = new ArrayList<>();
	}
	
	
	public void organize(String[] data) {
		threadsList = new ArrayList<>();
		time = 0;
		totalFiles = 0;
		processedFiles = 0;
		start = System.currentTimeMillis();
		organizeSort(data);
	}

	public void organizeSort(String[] data) {
		File directory = new File(data[0]);
		File[] files;
		
		if (data[6].equals("false")) {
			files = directory.listFiles();
		} else {
			files = filterFiles(directory, data[7]);
		} 
		int threads = ((files.length)/BLOCK);
		if(((files.length)%BLOCK)!=0) {
			threads++;
		}
		System.out.println(directory.getPath()+"-->"+files.length+"-->"+threads);
		for(int i = 0; i< threads; i++) {
			
			runnableOrganizer = new RunnableOrganizer(this);
			threadOrganizer = new Thread(runnableOrganizer);
			threadsList.add(threadOrganizer);
//			System.out.println("I::"+i+"-----"+((files.length)/BLOCK));
			if(i==((files.length)/BLOCK)) {
				totalFiles+=((files.length)%BLOCK);
				System.out.println("Bloque final");
				System.out.println("MIN::"+(BLOCK*i)+"   MAX::"+files.length);
				runnableOrganizer.setData(files, data, (BLOCK*i), files.length);//[2500 a 2580) 
				//en este caso el bloque no se encuentra completo por lo que el tope maximo es la cantidad maxima de archivos
				//este bloque sera siempre el ultimo en generarse 
				//este bloque se generara si ((files.length)%BLOCK) != 0 es decir la cantidad de bloques no es exacto
			}else {
				totalFiles+=BLOCK;
				System.out.println("Bloque");
				System.out.println("MIN::"+(BLOCK*i)+"   MAX::"+(BLOCK*(i+1)));
				runnableOrganizer.setData(files, data, (BLOCK*i), (BLOCK*(i+1)));//[0 a 500) desde cero hasta 499
				//no se agrega -1 dado que el for va hasta (i<500)=499
				//este bloque sera cualqueira menos el ultimo
			}
			
			try {
				threadOrganizer.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//no doy star aqui ps varios hilos se adelantan y empiezan a mover archivos lo cual deberia de ser bueno pero NO
			//resulta que en data envio es una direccion y cada hilo crea su propia instancia de este archivo por lo que los 
			//ultimos hilos tienen una instancia con menos archivos que las otras pero aun asi deben de procesar el bloque compreto
		}
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
	
	public void totalFilesSubtract(int value) {
		totalFiles-=value;
	}
	
	public void processedFilesAdd(int value) {
		processedFiles+=value;
	}
	
	public String getTime() {
		end = System.currentTimeMillis();
		time = (end-start);
//		https://www.geeksforgeeks.org/find-the-duration-of-difference-between-two-dates-in-java/#:~:text=Find%20the%20time%20difference%20between,the%20difference%20between%20two%20dates.
		String data = "";
//		System.out.println(time);
		data += (time / (1000 * 60 * 60)) % 24  +"H ";//horas gastadas
		data += (time / (1000 * 60)) % 60  +"M ";//minutos gastados
		data += (time / 1000) % 60  +"S ";//segundos gastados
		data += (time-((time / 1000) % 60)*1000)  +"mS ";//segundos gastados
        return data;
	}
  
	public boolean getStatus() {
		boolean status = false;
		for (int i = 0; i < threadsList.size(); i++) {
			if(threadsList.get(i).isAlive()) {
				status = true;
			}
		}
	  	return status;
	}
  
	public String[] getProgress() {
		String[] data = {
				""+processedFiles, ""+totalFiles
	    };
		return data;
	}
}
