package renameFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Rename {
	File folder = new File(".");				//get the current path
	File[] files = folder.listFiles();			//get a list of all the files
	
	public void renameFiles() {
		for (File file : files) {
			String fileName = file.getName();	//get the name of every file
			if(fileName.endsWith(".docx") || fileName.endsWith(".doc")) {	//if the file is a .docx
				Path filePath = file.toPath();		//get the path of the file
				BasicFileAttributes attr;
				try {
					attr = Files.readAttributes(filePath, BasicFileAttributes.class);	//create the attributes
					FileTime date = attr.creationTime();								//get the creation time
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");			//get a date format we want
					String dateCreated = df.format(date.toMillis());					//create a string with the date
					
					File fileNew = new File(dateCreated + "_" + fileName);				//create the new file name
					
					
					boolean success = file.renameTo(fileNew);							//rename the file
					
					if(success) {
						System.out.println("Files renamed successfully");
					}
					else {
						System.out.println("PROBLEM");
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "There are no .docx files");
					e.printStackTrace();
				}
			}
		}
	}
	

}
