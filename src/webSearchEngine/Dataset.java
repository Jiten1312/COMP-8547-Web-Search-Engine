package webSearchEngine;

import java.io.File;
import java.util.List;


/**
 * @author Jiten(110069329)
 *
 */
public class Dataset {
	private final String baseUrl = "https://www.w3schools.com/";
	private final int threshold = 100;

	public void addFiles() {
		Crawler crawler = new Crawler();
		crawler.startCrawling(baseUrl, 0);

		List<String> links = crawler.fetchedLinks;
		deleteFiles();
		for (int i = 0; i < links.size(); i++) {
			File[] filesH = new File("Resources/Html").listFiles();

			if (links.get(i) != "") {
				System.out.println("dataset: " + links.get(i));
				crawler.startCrawling(links.get(i), 0);
			}

			if (filesH.length > this.threshold) {
				break;
			}
		}
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
