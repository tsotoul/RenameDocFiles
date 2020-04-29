package renameFiles;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Demo {

	public static void main(String[] args) throws IOException {


		
		
		
		
		File folder = new File(".");
		File[] files = folder.listFiles();
		
		for (File file : files) {
			
			String fileName = file.getName();
			if(fileName.endsWith(".docx")) {
				Path filePath = file.toPath();
				BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
				
				FileTime date = attr.creationTime();
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String dateCreated = df.format(date.toMillis());
				
				System.out.println(dateCreated);
				File fileNew = new File(dateCreated + "_" + fileName);
				System.out.println(fileNew.getName());
				
				boolean success = file.renameTo(fileNew);
				
				if(success) {
					System.out.println("all good");
				}
				else {
					System.out.println("PROBLEM");
				}
				
				//System.out.println(fileName);
				//System.out.println(dateCreated);
			}
			
		}
		
		/*
		File file = new File("test.docx");
		File file2 = new File("test2.docx");
		File file3 = new File("test3.docx");
		
		Path filePath = file.toPath();
		Path filePath3 = file3.toPath();
		
		BasicFileAttributes attr = Files.readAttributes(filePath3, BasicFileAttributes.class);
		
		
		
		Files.newDirectoryStream(Paths.get("."),
		        path -> path.toString().endsWith(".docx"))
		        .forEach(System.out::println);
		
		long creationDate = attr.creationTime().toMillis();
		
		//System.out.println(attr.creationTime());
		
		
		if(file2.exists()) {
			throw new java.io.IOException("file exists");
		}
		boolean success = file.renameTo(file2);
		
		if(success) {
			System.out.println("all good");
		}
		else {
			System.out.println("PROBLEM");
		}
		*/
	}

}
