package org.demo.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.*;

public class ResourceDemo {
	public static void main(String[] args) throws IOException {
		String filePath="spring-demo/src/main/java/org/demo/resource/test.txt";
		FileSystemResource fileSystemResource = new FileSystemResource(filePath);
		File file = fileSystemResource.getFile();
		System.out.println(file.length());
		OutputStream outputStream = fileSystemResource.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.write("hello world");
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}
