package com.rkc.zds.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	private String filePath = "C:\\_\\data\\files\\access.log";
	private long fileLength = 0;
	
	public static void main(String[] args) {
		RandomAccessFileTest app = new RandomAccessFileTest();
		
		app.readFile();
		
	}

	private void readFile() {
		File file = new File(filePath);
		RandomAccessFile readWriteFile = null;
		
		try {
			readWriteFile = new RandomAccessFile(file,"rw");
			readWriteFile.seek(0);
			String line = null;
			while((line = readWriteFile.readLine())!= null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
