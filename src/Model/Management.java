package Model;

import java.io.File;
import java.util.ArrayList;

public class Management {
	private File directory;
	private Thread threadTestFileGenerator;
	private RunnableTestFileGenerator runnableTestFileGenerator;
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
  
	public void generateFiles(String[] datos) {
		start = System.currentTimeMillis();
		threadsList = new ArrayList<>();
		totalFiles = Integer.parseInt(datos[1]);
		directory = new File(datos[0]+"/Test Files");
		int threads = (Integer.parseInt(datos[1])/BLOCK);
		if((Integer.parseInt(datos[1])%BLOCK)!=0) {
			threads++;
		}
		System.out.println(totalFiles+"-->"+BLOCK+"-->"+threads);
		for (int i = 0; i < threads; i++) {
			runnableTestFileGenerator = new RunnableTestFileGenerator(this);
			runnableTestFileGenerator.setDirectory(directory);
			threadTestFileGenerator = new Thread(runnableTestFileGenerator);
			threadsList.add(threadTestFileGenerator);
			if(i==(Integer.parseInt(datos[1])/BLOCK)) {
//				totalFiles+=(Integer.parseInt(datos[1])%BLOCK);
				System.out.println("Bloque final");
				System.out.println("PRODUCCION==>"+(Integer.parseInt(datos[1])%BLOCK));
				runnableTestFileGenerator.setLimit(Integer.parseInt(datos[1])%BLOCK);//168 
				//en este caso el bloque no se encuentra completo por lo que se generaran la cantidad de archivos faltantes para completar el total
				//este bloque sera siempre el ultimo en generarse 
				//este bloque se generara si (Integer.parseInt(datos[1])%BLOCK) != 0 es decir la cantidad de bloques no es exacto
			}else {
//				totalFiles+=BLOCK;
				System.out.println("Bloque");
				System.out.println("PRODUCCION==>"+(BLOCK));
				runnableTestFileGenerator.setLimit(BLOCK);//500
				//este bloque sera cualqueira menos el ultimo
			}
			try {
				threadTestFileGenerator.start();
			} catch (Exception e) {}
		}
		
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
		System.out.println(time);
		data += (time / (1000 * 60 * 60)) % 24  +"H ";//horas gastadas
		data += (time / (1000 * 60)) % 60  +"M ";//minutos gastados
		data += (time / 1000) % 60  +"S ";//segundos gastados
		data += (time-((time / 1000) % 60)*1000)  +"mS ";//mili segundos gastados
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
