package com.etiya.ReCapProject.core.utilities.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.core.constants.FilePathConfiguration;

public class FileHelper {
	
	public File createFile(MultipartFile file) throws IOException {

		String imagePath = UUID.randomUUID().toString();

		File myFile = new File(FilePathConfiguration.CarImagesPath + imagePath + "."
				+ file.getContentType().substring(file.getContentType().indexOf("/") + 1));
		myFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(myFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();

		return myFile;
	}
}
