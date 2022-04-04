package webSearchEngine;

import java.util.Scanner;

public class Engine {
	public static void main(String args[]) {
		System.out.println("*************Welcome to Web Search Engine!!*************\n");

		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		String baseUrl = "https://www.w3schools.com/";
		do {
			showMenu();
			int choice = sc.nextInt();
			if (choice == 1) {
				baseUrl = Lib.getBaseUrl();
				Crawler crawler = new Crawler();
				System.out.println("Crawling started");
				Dataset dataset = new Dataset();
				dataset.addFiles(baseUrl);
				System.out.println("Crawling done");
			} else if (choice == 2) {
				WordSearch.search();
			} else if (choice == 3) {
				Scrap scrap = new Scrap();
				scrap.scrapUrlPattern(baseUrl);
			} else if (choice == 4) {
				PageRanking.searchWord();
			} else if (choice == 6) {
				flag = false;
				System.out.println("Thanks for using Web Search Engine!!");
			} else {
				System.out.println("Please enter the valid choice");
			}

		} while (flag);
	}

	private static void showMenu() {
		String[] options = { "Crawl the data", "Word Search", "Find url patterns", "Page Ranking", "Exit" };
		System.out.println("Please Enter the choice: ");

		for (int i = 0; i < options.length; i++) {
			System.out.println(i + 1 + ": " + options[i]);
		}
	}
}
