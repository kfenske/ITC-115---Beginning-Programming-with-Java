package com.kfenske.Assignment5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	private String path;
	
	public ReadFile(String path)
	{
		this.path = path;
	}
	
	public String getText() throws IOException
	{
		String content = "";
		FileInputStream fstream = new FileInputStream(path);
		DataInputStream fileIn = new DataInputStream(fstream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(fileIn));
		
		String lineIn;
		while ((lineIn = buff.readLine()) != null)
		{
			content += (lineIn + "\n");
		}
		return content;
	}
}
