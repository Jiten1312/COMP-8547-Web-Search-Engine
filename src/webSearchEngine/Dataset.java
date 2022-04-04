package webSearchEngine;

import java.io.File;
import java.util.List;

/**
 * @author Jiten(110069329)
 *
 */
public class Dataset {
	private final int threshold = 100;

	public void addFiles(String baseUrl) {
		long startTime = 0, endTime = 0, totalTime = 0;
		deleteFiles();
		Crawler crawler = new Crawler();
		crawler.startCrawling(baseUrl, 0);

		List<String> links = crawler.fetchedLinks;
		
		for (int i = 0; i < links.size(); i++) {
			File[] filesH = new File("Resources/Html").listFiles();

			if (links.get(i) != "") {
				System.out.println("dataset: " + links.get(i));
				startTime = System.currentTimeMillis();
				crawler.startCrawling(links.get(i), 0);
				endTime = System.currentTimeMillis();
				totalTime += endTime - startTime;
			}

			if (filesH.length > this.threshold) {
				break;
			}
		}
		System.out.println("---------------------------------------------------------------------------------\n");
		System.out.println("\nThe Crawling took  " + totalTime + " Milli Seconds to complete\n");
	}

	public void deleteFiles() {
		String[] dirNames = { "Html/", "Text/" };
		for (String dir : dirNames) {
			File path = new File("Resources/" + dir);
			for (File file : path.listFiles())
				file.delete();
		}
	}
}
