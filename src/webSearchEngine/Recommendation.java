package webSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recommendation {

	static ArrayList<String> key = new ArrayList<String>();
	static Hashtable<String, Integer> num = new Hashtable<String, Integer>();

	/* Using Regex to find & recommend similar string to pattern */
	public static void recommendations(String pattern) {
		try {
			// String to be scanned to find the pattern.
			String line = " ";
			String reg = "[\\w]+[@$%^&*()!?{}\b\n\t]*";

			// Pattern object and use it with new matcher object
			Pattern patterns = Pattern.compile(reg);
			Matcher match = patterns.matcher(line);
			int fileCount = 0;
			
			try {
				String filePath = "Resources/ACC URL FILES/ConvertedTextFiles/";
				File dir = new File(filePath);
				
				// Array of all files in the directory
				File[] fileArray = dir.listFiles();
				
				for (int i = 0; i < fileArray.length; i++) {
					findWord(fileArray[i], match, pattern);
					fileCount++;
				}

				Set keys = new HashSet();
				Integer value = 1;
				Integer val = 0;
				int counter = 0;

				System.out.println("\nDid you mean?:");
				System.out.println("---------------------------");
				for (Map.Entry entry : num.entrySet()) {
					if (val == entry.getValue()) 
					{
						break;
					} 
					else 
					{
						if (value == entry.getValue()) 
						{
							if (counter == 0) 
							{
								System.out.println((counter+=1) + ") " + entry.getKey());
							}
							else 
							{
								System.out.println((counter+=1) + ") " + entry.getKey());
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception occurred:" + e);
			} finally {}
		} catch (Exception e) {}
	}

	// findWord() method finds the similar patterns with those strings
	public static void findWord(File source, Matcher match, String str) throws FileNotFoundException, ArrayIndexOutOfBoundsException 
	{	
			try 
			{
				int i = 0, pat = 0;
				FileReader fileObj = new FileReader(source);
				BufferedReader bufferReaderObj = new BufferedReader(fileObj);
				String line = null;
	
				while ((line = bufferReaderObj.readLine()) != null) 
				{
					match.reset(line);
					while (match.find()) 
					{
						key.add(match.group());
					}
				}
				bufferReaderObj.close();
				
				while (pat < key.size()) 
				{
					String s1 = str.toLowerCase();
					String s2 = key.get(pat).toLowerCase();
					num.put(key.get(pat), editDistance(s1, s2));
					pat++;
				}
				
			} catch (Exception e) {
				System.out.println("Exception occurred:" + e);
			}
	}

	// editDistance() method takes edit distance and compare the nearest distance among keyword and similar patterns obtained
	public static int editDistance(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int[][] array = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) 
			array[i][0] = i;

		for (int j = 0; j <= len2; j++) 
			array[0][j] = j;

		// Iterate and then check the last character
		for (int i = 0; i < len1; i++) 
		{
			char c1 = str1.charAt(i);
			for (int j = 0; j < len2; j++) 
			{
				char c2 = str2.charAt(j);

				if (c1 == c2) 
				{
					array[i + 1][j + 1] = array[i][j];
				} 
				else 
				{
					int replace = array[i][j] + 1;
					int ins = array[i][j + 1] + 1;
					int del = array[i + 1][j] + 1;
					int min;
					
					if(replace>ins)
						min = ins;
					else
						min = replace;
					min = del > min ? min : del;
					array[i + 1][j + 1] = min;
				}
			}
		}
		return array[len1][len2];
	}
}
