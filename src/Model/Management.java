package Model;

public class Management {

	private Thread threadOrganizer;
	private RunnableOrganizer runnableOrganizer;
	private long time;
	
	public void organize(String[] datos) {
		time = 0;
		runnableOrganizer = new RunnableOrganizer(this);
		threadOrganizer = new Thread(runnableOrganizer);
		System.out.println("hilo::" + threadOrganizer.getState());
		System.out.println("\\N " + threadOrganizer.isAlive());
		runnableOrganizer.resetProgress();
		runnableOrganizer.setData(datos);
		threadOrganizer.start();
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
	  	return threadOrganizer.isAlive();
	}
  
	public String[] getProgress() {
	  	return runnableOrganizer.getProgress();
	}
}
