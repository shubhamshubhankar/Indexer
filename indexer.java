
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class indexer
{

	public static void main(String[] args) throws FileNotFoundException
	{
		HashMap<String, Integer> filenames = new HashMap<>();// Storing the names of the file in a hashmap
		HashMap<String, ArrayList<Integer> > dictionary = new HashMap<>();// Input the file containing names of file to be index.
		ArrayList<String> stopwords =new ArrayList<String>();
		FileReader fr = new FileReader(new File("doc_name.txt"));// Scanning the file to get the distinct file name.
		Scanner sc = new Scanner(fr);
		Integer counter=0;
		// To add all the stopwords into the database.
		FileReader f1= new FileReader(new File("stopwords"));
		Scanner scan= new Scanner(f1);
		while(scan.hasNext())
		{
			String abc= scan.next();
			stopwords.add(abc);
		}

		while (sc.hasNext())
		{
			String s = sc.next();// Input the name of the each file.
			filenames.put(s,++counter);//  Setting the doc_no for each of the files.
			FileReader fr1= new FileReader(new File(s));// Opening file one by one.
			Scanner sc1=new Scanner(fr1); // Scanning new file.

			while (sc1.hasNext())
			{
				String s1=sc1.next();
				if(!stopwords.contains(s1))			// Checking whether a word is a stop word or not.
				{
					// Stemming the words.
					Stemmer stemmer = new Stemmer();
					s1.toLowerCase();
					stemmer.add( s1.toCharArray(), s1.length());
					stemmer.stem();
					s1= stemmer.toString();
					if(dictionary.containsKey(s1))
					{
						ArrayList<Integer> flag = dictionary.get(s1);	//dummy arraylist.
						if(!flag.contains(counter))
						{
							flag.add(counter);
							dictionary.put(s1,flag);
						}
					}
					else
					{
						ArrayList<Integer> flag = new ArrayList<Integer> ();	//dummy arraylist.
						flag.add(counter);
						dictionary.put(s1,flag);
					}
				}

			}

		}
		System.out.println(dictionary);
	}
}
