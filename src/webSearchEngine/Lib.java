package webSearchEngine;

import java.util.Scanner;

public class Lib {
	private static String baseUrl;

	public static String getBaseUrl() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the url you want to crawl: \n");
		baseUrl = sc.next();
		return baseUrl;
	}
}
