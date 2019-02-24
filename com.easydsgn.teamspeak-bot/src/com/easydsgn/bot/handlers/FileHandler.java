package com.easydsgn.bot.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FileHandler {
	
	public static Properties prop = new Properties();
	public static InputStream input = null;
	public static BufferedReader br = null;
	public static BufferedWriter writer = null;
	
	public static String text;
	public static String[] textArray;
	
	public static String str_group_eins;
	public static String str_group_zwei;
	public static String str_period;
	
	public static int group_eins;
	public static int group_zwei;
	public static long period;
	
	public static String user;
	public static String pass;
	public static String nick;
	public static String ip;
	public static String hashMapInput;
	public static String[] hashMapInputArray;
	public static int id;
	

	
	public FileHandler() {
		handleIpAndPass();
	}
	
	public static void handleFile() {
		Path path = Paths.get(".");
		Path filePath = path.toAbsolutePath().getParent().getParent();
		
		try {
			br = new BufferedReader(new FileReader(filePath.toString() + "\\config.txt"));
			
			text = br.readLine();
			textArray = text.split(";");
			
			group_eins = Integer.parseInt(textArray[0]);
			group_zwei = Integer.parseInt(textArray[1]);
			period = Integer.parseInt(textArray[2]);
			
			System.out.println(group_eins + " | " + group_zwei + " | " + period);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void handleIpAndPass() {
		Path path = Paths.get(".");
		Path filePath = path.toAbsolutePath().getParent().getParent();
		
		try {
			br = new BufferedReader(new FileReader(filePath.toString() + "\\config.txt"));
			
			text = br.readLine();
			textArray = text.split(";");
			
			user = textArray[3];
			pass = textArray[4];
			nick = textArray[5];
			ip = textArray[6];
			id = Integer.parseInt(textArray[7]);
			
			System.out.println(ip + " | " + pass + " | " + id);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void save() {
		try {
			writer = new BufferedWriter(new FileWriter("userlist.txt"));
			writer.write("USER;" + TimeHandler.hm);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void load() {
		if(TimeHandler.hm.isEmpty()) {
			try {
				br = new BufferedReader(new FileReader("userlist.txt"));
				hashMapInput = br.readLine();
				hashMapInputArray = hashMapInput.split(";");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
