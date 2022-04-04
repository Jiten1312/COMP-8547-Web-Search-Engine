package webSearchEngine;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import processing.In;

public class Scrap {
	public void saveUrls() {
		Crawler crawler = new Crawler();
		crawler.startCrawling(Lib.getBaseUrl(), 0);
		List<String> links = crawler.fetchedLinks;

		try {
			File path = new File("Resources/Url/url.txt");
			path.delete();
			FileWriter fileWriter = new FileWriter(path);

			for (String link : links) {
				if (link != "")
					fileWriter.append(link + "\n");
			}

			fileWriter.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void scrapUrlPattern() {
		saveUrls();
		System.out.println("Enter the pattern you want to search in urls");
		Scanner sc = new Scanner(System.in);
		String urlPattern = sc.nextLine();
		
		In inputFile = new In("Resources/url/url.txt");

		String textFromFile = inputFile.readAll();

		int flag = 0;
		String pattern = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		Matcher matcher = Pattern.compile(pattern).matcher(textFromFile);
		while (matcher.find()) {
			if (matcher.group().contains(urlPattern)) {
				System.out.println("URL: " + matcher.group());
				flag = 1;
			}
		}
		if (flag == 0)
			System.out.println("Not found");
		
		// Inverted Index
		System.out.println("\nInverted Index: ");
		String dirPath = "Resources/Text";
	    Map<Integer,String> sources;
	    HashMap<String, HashSet<Integer>> index;
	    File testDir = new File(dirPath);
		String[] testFiles = testDir.list();
		ArrayList<String> fileNames = new ArrayList<String>();
		InvertedIndex i = new InvertedIndex();
        
        i.buildIndex(testFiles);

        fileNames =  i.find(urlPattern);
        
        Iterator<String> itrator = fileNames.iterator();
		while(itrator.hasNext()) 
		{
			System.out.print(itrator.next()+" ");
		}	
		System.out.println("\n");
	}
}
