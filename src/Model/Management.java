package Model;


public class Management {
	private Thread threadTestFileGenerator;
	private RunnableTestFileGenerator runnableTestFileGenerator;
  
	public void generateFiles(String[] datos) {
		runnableTestFileGenerator = new RunnableTestFileGenerator();
		threadTestFileGenerator = new Thread(runnableTestFileGenerator);
		System.out.println("hilo::" + threadTestFileGenerator.getState());
		System.out.println("\\N " + threadTestFileGenerator.isAlive());
		runnableTestFileGenerator.setTotalFilesGenerated(0);
		runnableTestFileGenerator.setLimit(Integer.parseInt(datos[1]));
		runnableTestFileGenerator.setDirectory(datos[0]);
		threadTestFileGenerator.start();
	}
  
	public boolean getStatus() {
		return threadTestFileGenerator.isAlive();
	}
  
	public String[] obtenerProgreso() {
	  	return runnableTestFileGenerator.getProgress();
	}
}
