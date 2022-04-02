package webSearchEngine;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import processing.In;

public class Scrap {
	public void scrapUrlPattern() {
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
	}
}
