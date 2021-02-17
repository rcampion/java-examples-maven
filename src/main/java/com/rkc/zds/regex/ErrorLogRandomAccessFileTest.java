package com.rkc.zds.regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorLogRandomAccessFileTest {
	private String filePath = "C:\\_\\data\\files\\error.log";
	private long fileLength = 0;
	
	public static void main(String[] args) {
		ErrorLogRandomAccessFileTest app = new ErrorLogRandomAccessFileTest();
		
		app.readFile();
		
	}

	private void readFile() {
		File file = new File(filePath);
		RandomAccessFile readWriteFile = null;
		
		try {
			readWriteFile = new RandomAccessFile(file,"rw");
			readWriteFile.seek(0);
			String line = null;
			String regex = "^((?<datetime>\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}))" 
					+ " \\[([a-zA-Z]+)\\] "
					+ "([0-9#]+): " 
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\*\\-:\\\\]+)), " 
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\-:\\\\]+)), "
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\-:\\\\]+)), " 
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\-:\\\\]+)),"
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\-:\\\\]+))"
					+ "?([, ]+)"
					+ "?(([/_\\.\"\\(\\)0-9a-zA-Z \\[\\]=<>@&=\\?\\-:\\\\]+))";
			while((line = readWriteFile.readLine())!= null) {
				System.out.println(line);
				Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
				Matcher matcher = pattern.matcher(line);
				if(matcher.matches()) {
					String recieveTime = matcher.group(1);
					System.out.println("Recieve Time: "+recieveTime);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(readWriteFile !=null) {
				try {
					readWriteFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}	
	}

}
