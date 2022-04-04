package webSearchEngine;

import java.io.*;
import java.util.*;

/*
 * InvertedIndex - Given a set of text files, implement a program to create an 
 * inverted index. Also create a user interface to do a search using that inverted 
 * index which returns a list of files that contain the query term / terms.
 * The search index can be in memory. 
 */

/**
 * @author Kenil(110077576)
 *
 */
public class InvertedIndex {
	Map<Integer, String> sources;
	HashMap<String, HashSet<Integer>> index;

	InvertedIndex() {
		sources = new HashMap<Integer, String>();
		index = new HashMap<String, HashSet<Integer>>();
	}

	/**
	 * This method will read provided files and generate index using HashSet
	 * 
	 * @param files
	 */
	public void buildIndex(File[] files) {
		int i = 0;
		for (File file : files) {
			try (BufferedReader thisfile = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
				sources.put(i, file.getName());
				String ln;
				while ((ln = thisfile.readLine()) != null) {
					String[] strings = ln.split("\\W+");
					for (String string : strings) {
						string = string.toLowerCase();
						if (!index.containsKey(string))
							index.put(string, new HashSet<Integer>());
						index.get(string).add(i);
					}
				}
			} catch (IOException e) {
				System.out.println(file + "NOT FOUND");
			}
			i++;
		}
	}

	/**
	 * 
	 * @param phrase
	 * @return ArrayList of files having phrase
	 */
	public ArrayList<String> find(String phrase) {
		ArrayList<String> fileNames;
		try {
			fileNames = new ArrayList<String>();
			String[] words = phrase.split("\\W+");
			String hashKey = words[0].toLowerCase();
			if (index.get(hashKey) == null) {
				System.out.println("Not found.");
				return null;
			}
			HashSet<Integer> res = new HashSet<Integer>(index.get(hashKey));
			for (String word : words) {
				res.retainAll(index.get(word));
			}

			if (res.size() == 0) {
				System.out.println("Not found.");
				return null;
			}
			for (int num : res) {
				fileNames.add(sources.get(num));
			}
		} catch (Exception e) {
			System.out.println("Phrase Not Found");
			System.out.println("Exception Occurred:" + e.getMessage());
			return null;
		}
		return fileNames;
	}

}
