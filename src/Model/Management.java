package Model;


public class Management {
	private Thread threadTestFileGenerator;
	private RunnableTestFileGenerator runnableTestFileGenerator;
	private long time;
  
	public void generateFiles(String[] datos) {
		runnableTestFileGenerator = new RunnableTestFileGenerator(this);
		threadTestFileGenerator = new Thread(runnableTestFileGenerator);
		System.out.println("hilo::" + threadTestFileGenerator.getState());
		System.out.println("\\N " + threadTestFileGenerator.isAlive());
		runnableTestFileGenerator.setTotalFilesGenerated(0);
		runnableTestFileGenerator.setLimit(Integer.parseInt(datos[1]));
		runnableTestFileGenerator.setDirectory(datos[0]);
		threadTestFileGenerator.start();
	}
	
	public void addTime(long time) {
		this.time += time;
	}
	
	public String getTime() {
//		https://www.geeksforgeeks.org/find-the-duration-of-difference-between-two-dates-in-java/#:~:text=Find%20the%20time%20difference%20between,the%20difference%20between%20two%20dates.
		String data = "";
		System.out.println(time);
		data += (time / (1000 * 60 * 60)) % 24  +"H ";//horas gastadas
		data += (time / (1000 * 60)) % 60  +"M ";//minutos gastados
		data += (time / 1000) % 60  +"S ";//segundos gastados
		data += (time-((time / 1000) % 60)*1000)  +"mS ";//segundos gastados
        return data;
	}
  
	public boolean getStatus() {
		return threadTestFileGenerator.isAlive();
	}
  
	public String[] getProgress() {
	  	return runnableTestFileGenerator.getProgress();
	}
}
