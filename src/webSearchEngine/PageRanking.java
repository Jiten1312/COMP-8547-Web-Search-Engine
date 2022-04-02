package webSearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Parth
 *
 */
public class PageRanking {
	
	
	/**
	 * This method search word into web pages and call rankWebPages() method to rank all web pages.
	 */
	public static void searchWord() {
		
		System.out.print("\nEnter word for search : ");
		Scanner scanner = new Scanner(System.in);
		String word = scanner.nextLine();
		
		File file = new File("Resources/Text");
		File[] fileList = file.listFiles(); // create list of all files
		
		int occurrence = 0;
		int occurrenceInFile = 0;
		Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
		
		for (int i=0; i<fileList.length; i++) {
			try {
				occurrence = WordSearch.searchGivenWord(fileList[i], word); // call searchGivenWord() method from WordSearch class to find occurrence
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (occurrence != 0) {
				hashtable.put(fileList[i].getName(), occurrence);
				occurrenceInFile++;
			}
		}
		
		
		// Rank web pages
		if(occurrenceInFile!=0) {
			PageRanking.rankWebPages(hashtable, occurrenceInFile);
		}
	}
	
	/**
	 * This method rank all web pages according to highest number of word occurrence in the page.
	 * Using Collections.sort() that do Merge sort 
	 * @param hashtable
	 * @param occurrenceInFile 
	 */
	public static void rankWebPages(Hashtable<?, Integer> hashtable, int occurrenceInFile){
		
		ArrayList<Map.Entry<?, Integer>> arrayList = new ArrayList(hashtable.entrySet()); // convert HashTable into ArrayList
		
		Collections.sort(arrayList, new Comparator<Map.Entry<?, Integer>>(){
	        public int compare(Map.Entry<?, Integer> page1, Map.Entry<?, Integer> page2) {
	            return page1.getValue().compareTo(page2.getValue());
	        }
	    }); //use collections for comparing value and sort pages according to occurrence of word
	      
	    Collections.reverse(arrayList); // reverse the arrayList for descending order
	    System.out.println("\n-------------------Web Page Ranking-------------------");
	    
	    if(occurrenceInFile==1) {
	    	for(int i=0; i<arrayList.size(); i++) {
		    	System.out.print("\n ("+ (i+1) +") "+ arrayList.get(i).getKey()+" | Occurrence of word : "+arrayList.get(i).getValue()+"\n");
		    }
	    } else {
	    	for(int i=0; i<10 && arrayList.size()>i ; i++) {
		    	System.out.print("\n ("+ (i+1) +") "+ arrayList.get(i).getKey()+" | Occurrence of word : "+arrayList.get(i).getValue()+"\n");
		    }
	    }
	    System.out.println("\n");
	}
}
