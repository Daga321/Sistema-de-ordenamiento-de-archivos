package Model;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunnableTestFileGenerator implements Runnable{
	
	private Management management;
	
	private int totalFilesGenerated;
	private int limit;

	private int year;
	private int month;
	private int day;
	
	private File directory;
	
	private int extentionIndex;
	private final String[] extention = {".txt", ".bat", ".jar", ".docx", ".xlsx", ".pptx", ".exe", ".rar", ".pdf"};
	
	private SimpleDateFormat sdfDate;
	
	private Long start;
	private Long end;
	
	public RunnableTestFileGenerator(Management management) {
		this.management = management;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(700);
			start = System.currentTimeMillis();
			totalFilesGenerated = 0;
			generateFiles();
			end = System.currentTimeMillis();
			management.addTime(end-start);
		} catch (Exception e) {}
	}
	
	public void generateFiles() {
		sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		File newFile;
		Date newDate;
		while(directory.listFiles().length<limit) {
			year = ((int)(Math.random()*(52+1)+70));//(M+1-m)+m
			//el año 0= 1900 pero no se pueden sobree escribir fecha de modificacion antes de 1970 tons +70 y +52 para la fecha actual
			month = ((int)(Math.random()*(11+1)));//(M+1-m)+m
			day = ((int)(Math.random()*(31+1)));//(M+1-m)+m
			extentionIndex = ((int)(Math.random()*(extention.length)));//(M+1-m)+m
			
			try {
				newDate = new Date(year, month, day);
				System.out.println(directory.getPath()+"/"+sdfDate.format(newDate)+extention[extentionIndex]);
				newFile = new File(directory.getPath()+"/"+sdfDate.format(newDate)+extention[extentionIndex]);
				if (!newFile.exists()) {
					newFile.createNewFile();
					newFile.setLastModified(newDate.getTime());
					totalFilesGenerated++;
//					Thread.sleep(250);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public String[] getProgress() {
		String[] datos = {
				""+totalFilesGenerated, ""+limit
	    };
		return datos;
	}
	
	public int getTotalFilesGenerated() {
		return totalFilesGenerated;
	}

	public void setTotalFilesGenerated(int totalFilesGenerated) {
		this.totalFilesGenerated = totalFilesGenerated;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setDirectory(String path) {
		this.directory = new File(path+"/Test Files");
	}

	
}
