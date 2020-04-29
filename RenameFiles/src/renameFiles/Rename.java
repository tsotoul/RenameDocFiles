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
	File folder = new File(".");
	File[] files = folder.listFiles();
	
	public void renameFiles() {
		for (File file : files) {
			String fileName = file.getName();
			if(fileName.endsWith(".docx")) {
				Path filePath = file.toPath();
				BasicFileAttributes attr;
				try {
					attr = Files.readAttributes(filePath, BasicFileAttributes.class);
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
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "There are no .docx files");
					e.printStackTrace();
				}
			}
		}
	}
	

}
