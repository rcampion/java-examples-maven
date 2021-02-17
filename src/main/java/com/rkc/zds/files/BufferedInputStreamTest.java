package com.rkc.zds.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamTest {

	public static void main(String[] args) {

		File file = new File("C:\\_\\data\\files\\access.log");
		FileInputStream fis = null;
		BufferedInputStream br = null;

		try {
			fis = new FileInputStream(file);
			br = new BufferedInputStream(fis);
			int data = br.read();
			while (data != -1) {
				System.out.print((char)data);
				data = br.read();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
