package Execution;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Runner {

	private int year;
	private int month;
	private int day;
	
	private final int SIZE = 5000;
	private File directory;
	
	private int extentionIndex;
	private final String[] extention = {".txt", ".bat", ".jar", ".docx", ".xlsx", ".pptx", ".exe", ".rar", ".pdf"};
	
	private SimpleDateFormat sdfDate;
	
	public static void main(String[] args) {
		
		new Runner().generateFiles();
		
		
	}

	public void generateFiles() {
		sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		directory = new File("./Test Files");
		if (directory.exists()) {
			
			for(int i=0; i<directory.listFiles().length; i++) {
				System.out.println(directory.listFiles()[i].getName()+" **HA SIDO ELIMINADO**");
				directory.listFiles()[i].delete();
			}
			directory.delete();
		}
		directory.mkdirs();
		File newFile;
		Date newDate;
		while(directory.listFiles().length<SIZE) {
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
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
