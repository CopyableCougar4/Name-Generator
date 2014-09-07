package game.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NameGenerator {
	
	private static Random random = new Random();
	
	private static ArrayList<String> firstnames = new ArrayList<String>();
	private static ArrayList<String> lastnames = new ArrayList<String>();
	
	private static ArrayList<String> readFileToList(File file){
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			for(String line = reader.readLine(); line != null; line = reader.readLine()){
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static void preload() throws IOException{
		firstnames.addAll(readFileToList(new File("male-firstnames.txt")));
		firstnames.addAll(readFileToList(new File("female-firstnames.txt")));
		lastnames.addAll(readFileToList(new File("lastnames.txt")));
	}
	
	public static String generateFirstName(){
		int index = random.nextInt(firstnames.size());
		return firstnames.get(index);
	}
	
	public static String generateLastName(){
		int index = random.nextInt(lastnames.size());
		return lastnames.get(index);
	}
	
	public static String[] generateName(){
		return new String[]{ generateFirstName(), generateLastName() };
	}
	
	public static void debug(){
		if(firstnames.isEmpty() || lastnames.isEmpty()){
			System.out.println("no names loaded");
			return;
		}
		for(int index = 0; index < 200; index++){
			String[] name = generateName();
			System.out.println(name[0] + " " + name[1]);
		}
	}

}
