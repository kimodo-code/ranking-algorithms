import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.*;
import javafx.util.*;
import java.util.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;


public class question2 {

	/* declare variables. String input 1 & 2 for read file method.
	 * 
	 * The basic form that the search engine, document, rank score info will be stored in is the
	 * document and score to be stored as key, value pairs
	 * 
	 * Pair [1, 1.0]
	 * Pair [44, 0.9]
	 * Pair [55, 0.8]
	 *  
	 * in an arraylist which represents the
	 * document list
	 *  
	 * Inner Arraylist
	 * 
	 *Index 0 	Doc 1
	 *Index 1 	Doc 44
	 *Index 2	Doc 55	
	 * 
	 * in another arraylist which represents the search engines.
	 * 
	 * Outer Arraylist called SearchEngines
	 * Index 0 SearchEngine1 Index 1 SearchEngine2	Index 2 SearchEngine3	Index 3 SearchEngine 4
	 */

	String input2;
	String inputWeights;
	String output2;
	public ArrayList<ArrayList<Pair<Integer, Double>>> SearchEngines = new ArrayList<>();
	ArrayList<Pair<Integer, Double>> temp = new ArrayList<>();//make the inner arraylist into a temp variable
	boolean firstRead=true;//this is for the cutoff point in process line method
	StringBuilder sb2 = new StringBuilder();

	public question2 (String input2, String inputWeights, String output2)
	{
		this.input2 = input2;
		this.inputWeights = inputWeights;
		this.output2 = output2;
	}

	public void readFile() throws FileNotFoundException 
	{
		try
		{
			System.out.println("");
			System.out.println("");
			System.out.println("***********THIS IS CONSOLE OUTPUT FOR QUESTION 2**********");
			System.out.println("");
			System.out.println("");
			System.out.println("The search engine letter has been changed into its ASCII equivalent!");
			sb2.append("The search engine letter has been changed into its ASCII equivalent! The following is the array once inputted ");
			sb2.append("\n");
			File f2 = new File(input2);//file object for input file with the text to be read
			Scanner diskScanner = new Scanner(f2);//scanner object to read file
			while (diskScanner.hasNextLine())
			{//repeat until there are no more lines to read
				this.processLine(diskScanner.nextLine());
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}

	}	
	public void processLine(String s) {
		String [] arr ;
		arr = s.split("\\t");//take in file input as string then split on tab and store in string array
		//System.out.println(Arrays.toString(arr));//test print
		if (firstRead) {//it's initialised as true in declaration so it definitely will read once, once it's read once change to false
			firstRead = false;//flip the boolean

			/*a potential problem with reading the file input is, what if the search engines have
			 * varying result sets, with some longer than others? How will split on tab work if there is 
			 * just an empty space where a result would be? Therefore make a cutoff point where
			 * the shortest result set ends. So terminate the programme ("done reading") at end
			 * of shortest result set. 
			 */

			for(int i=0; i<arr.length; i++)
			{
				String[] arrOneSearchEngine = arr[i].split(";");//string array of the individual components, search eng, doc, score
				//System.out.println(Arrays.toString(arrOneSearchEngine));//test print
				//we need to sort out the first letter that represents the search engine, we can't just put in an all double array
				String x = arrOneSearchEngine[0];//first get the letter in string form (it's always the first item in the array hence index 0)
				char c = x.charAt(0);//convert into char
				int ascii = (int) c;//get the ascii value of char (we can change it back again later)
				//System.out.println(ascii);
				String asciiString = Integer.toString(ascii);//make it string to fit back in string array, it's really a number now though so we can convert the whole lot to double later
				//System.out.println(asciiString);
				arrOneSearchEngine[0] = asciiString;
				// System.out.println(Arrays.toString(arrOneSearchEngine));//test print
				double[] arrOneSearchEngDouble = {0, 0, 0};//convert to double array
				for(int j=0; j<3; j++)
				{// j < 3 because there are only 3 figures to deal with, search eng number, doc number, score number
					arrOneSearchEngDouble[j] = Double.valueOf(arrOneSearchEngine[j]);//get the value of when converting from string to double
				}	
				//System.out.println(Arrays.toString(arrOneSearchEngDouble));//test print
				ArrayList<Pair<Integer, Double>> temp = new ArrayList<>();//make the inner arraylist into a temp variable
				temp.add(new Pair<Integer, Double>((int) arrOneSearchEngDouble[1], arrOneSearchEngDouble[2] ));//then add the keys and values. In {0,1,2} Index 1 & Index 2 are the keys and values respectively	
				SearchEngines.add(i, temp);//now for the outer arraylist, iterate through to add the inner arraylist to it	
			}
		}
		else if (arr.length == SearchEngines.size())
		{
			for(int i=0; i<arr.length; i++)
			{
				String[] arrOneSearchEngine = arr[i].split(";");
				String x = arrOneSearchEngine[0];//first get the letter in string form (it's always the first item in the array hence index 0)
				char c = x.charAt(0);//convert into char
				int ascii = (int) c;//get the ascii value of char (we can change it back again later)
				// System.out.println(ascii);
				String asciiString = Integer.toString(ascii);//make it string to fit back in string array, it's really a number now though so we can convert the whole lot to double later
				//System.out.println(asciiString);
				arrOneSearchEngine[0] = asciiString;
				double[] arrOneSearchEngDouble = {0, 0, 0};
				for(int j=0; j<3; j++)
				{
					arrOneSearchEngDouble[j] = Double.valueOf(arrOneSearchEngine[j]);
				}
				System.out.println(Arrays.toString(arrOneSearchEngDouble));
				sb2.append(Arrays.toString(arrOneSearchEngDouble));
				sb2.append("\n");
				SearchEngines.get(i).add(new Pair<Integer, Double>((int) arrOneSearchEngDouble[1], arrOneSearchEngDouble[2] ));
			}
		}
		else 
		{
			System.out.println("done reading");
		}
	}

	public ArrayList<ArrayList<Pair<Integer, Double>>> normaliseScores() 
	{
		System.out.println("");
		System.out.println("this is normalise scores method being called");
		System.out.println("");
		sb2.append("\n");
		sb2.append("");
		sb2.append("this is normalise scores method being called ");
		sb2.append("");

		int i;
		for(i = 0; i<SearchEngines.size(); i++){
			//outer for-loop iterates over individual search engines, A, B, C, ...
			//that is, it iterates over the list SearchEngines. The elements of that list are lists.


			double maxValue = SearchEngines.get(i).get(0).getValue();//top document is max value therefore index 0
			double minValue = SearchEngines.get(i).get(SearchEngines.get(i).size()-1).getValue();//last element in arraylist is bottom document which has the lowest score, therefore min value



			int j;
			for(j = 0; j<SearchEngines.get(i).size(); j++){
				//inner for-loop iterates over the individual documents of every individual search engine
				//the for-loop iterates over elements of every arrayList of search engines, those elements are pairs.
				double unNormScore = SearchEngines.get(i).get(j).getValue();//unnormalised score is the score ranking for each document
				double normScore = Math.abs((unNormScore - minValue) / (maxValue - minValue));//here is the normalisation equation (Math.abs to get rid of -0)

				normScore = Math.round(normScore * 1000d) / 1000d;
				//System.out.println("maxVal = " + maxValue + ", minVal = " + minValue + ", unNormScore = " + unNormScore + ", normScore = " + normScore);

				int keyCopy = SearchEngines.get(i).get(j).getKey();//awkward but there was issue setting normalised score values (no setValue method) so make copy of key.
				Pair<Integer, Double> y2 = new Pair<Integer, Double>(keyCopy, normScore);//each pair contains doc and new normalised score
				SearchEngines.get(i).set(j, y2);
			}
		}
		return SearchEngines;
	}




	public void interleaving()
	{
		System.out.println("This is interleaving ");
		System.out.println("");
		sb2.append("\n");
		sb2.append("This is interleaving ");
		sb2.append("");
		/* what we want to do is go through the outer array list index by index, so Search Engine A, pluck top document
		 *which will be index 0, copy that top doc and put in top of interleavedArrayList. Next will be search Engine B
		 *top doc (index 0 again), put in second place in interleavedArray and so on. When the end of the outer array is
		 * reached, go back to start, Search Engine A, and now go for index 1 of temp (inner) array.
		Find out the number of results per search engine (its the same for every search engine) */
		ArrayList<Pair<Integer, Double>> placeHolder = new ArrayList<>();
		int nResults = SearchEngines.get(0).size();
		for(int i=0; i<nResults; i++)
		{
			for(int j=0; j<SearchEngines.size(); j++)
			{
				placeHolder.add(SearchEngines.get(j).get(i));
				j++;//was adding the document three times in the same place so had to shift it on
				j++;//
			}
		}
		System.out.println(placeHolder.toString());
		sb2.append(placeHolder.toString());
		sb2.append("\n");
	}

	public Set<Pair<Integer, Double>> comBsum()
	{
		normaliseScores();
		Set<Pair<Integer, Double>> setofDocs = new HashSet<>();
		List<Pair<Integer, Double>> fusedList = new ArrayList<Pair<Integer, Double>>(setofDocs);

		//go in and grab all the documents, 
		for (int i = 0; i< SearchEngines.size(); i++) 
		{//move through outer arraylist
			for(int j=0; j<SearchEngines.get(i).size(); j++) 
			{//move through inner arraylist
				int key = SearchEngines.get(i).get(j).getKey();//get the doc
				double value = SearchEngines.get(i).get(j).getValue();
				Pair<Integer, Double> tempPair = new Pair<>(key, value);
				//TO DO IF DUPLICATE ADD SCORE!!!!!!!!!
				//TO DO sort by value
				//bypass if loop first time because if nothing has been added, nothing is contained, add a temp pair then next time check to see if a doc is contained
				if (setofDocs.contains(tempPair.getKey())) 
				{//if doc is contained. Problem is can't compare tempPair objects because key & value will nearly always be different, we want to find duplicate KEYS
					for (Iterator<Pair<Integer, Double>> it = setofDocs.iterator(); it.hasNext(); ) 
					{
						System.out.println("anything************************************");
						Pair<Integer, Double> f = it.next();
						int newKey = f.getKey();
						double newValue = f.getValue() + tempPair.getValue();//add values
						setofDocs.remove(f);//remove original
						Pair<Integer, Double> newPair = new Pair<>(newKey, newValue);//new key is actually old key
						setofDocs.add(newPair);//add pair that has its values added together  
					}					
				}			
				setofDocs.add(tempPair);// (else) add temp pair
			}
		}

		//now for how to sort by desc value. difficult to sort pairs, treesets & collection.sort don't work
		//so use lambda expression 
		for (Iterator<Pair<Integer, Double>> it = setofDocs.iterator(); it.hasNext(); )
		{//we want the post sorted set
			Pair<Integer, Double> sortPair = it.next();
			fusedList.add(sortPair);//add the pairs to an arraylist

		}

		final Comparator<Pair<Integer, Double>> c = reverseOrder(comparing(Pair::getValue));
		Collections.sort(fusedList, c);

		System.out.println("This is the sorted ComBSum list with the highest rated document on top ");

		fusedList.stream()
		.sorted(c)
		.map(Pair::getKey)//can't get double value as well
		.forEach(System.out::println);

		sb2.append("This is the sorted ComBSum list with the highest rated document on top ");

		fusedList.stream()
		.sorted(c)
		.map(Pair::getKey)//can't get double value as well
		.forEach( s-> sb2.append(s + " "));//stringbuilder

		System.out.println("the unsorted hashset of combsummed docs is ");
		sb2.append("the unsorted hashset of combsummed docs is ");
		return setofDocs;
	}







	public void LCM(ArrayList<ArrayList<Pair<Integer, Double>>> SearchEngines, String inputWeights) throws FileNotFoundException
	{
		ArrayList<ArrayList<Pair<Integer, Double>>> copySearchEngines = new ArrayList<ArrayList<Pair<Integer, Double>>>(SearchEngines);//safer to use a copy


		System.out.println("");
		System.out.println("LCM weight array is ");
		System.out.println("");
		sb2.append("\n");
		sb2.append("");
		sb2.append("LCM weight array is ");
		sb2.append("");
		sb2.append("\n");

		String store = "";//put everything from the input file here
		String[] arrOneWeights = null; //this will store as an array the engine letter/number and the weighting
		//we need to take in the arraylist with all the doc rank score search engine info as well as the information regarding weights which is provided in the inputWeights file
		//do LCM weighting on original scores
		//then call the comBsum method on arraylist
		//we'll process the inputweights file here and now
		try
		{
			File f4 = new File(inputWeights);//file object for input file with the text to be read
			Scanner diskScanner = new Scanner(f4);//scanner object to read file
			while (diskScanner.hasNextLine())
			{//repeat until there are no more lines to read
				store += diskScanner.nextLine();
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}
		String [] arrWeights ;
		arrWeights = store.split("\\t");//take in file input as string then split on tab and store in string array	
		for(int a=0; a<arrWeights.length; a++)
		{
			arrOneWeights = arrWeights[a].split(";");//string array of the individual components, search eng, doc, score
			//we need to sort out the first letter that represents the search engine, we can't just put in an all double array
			String x = arrOneWeights[0];//first get the letter in string form (it's always the first item in the array hence index 0)
			char c = x.charAt(0);//convert into char
			int ascii = (int) c;//get the ascii value of char (we can change it back again later)
			//System.out.println(ascii);
			String asciiString = Integer.toString(ascii);//make it string to fit back in string array, it's really a number now though so we can convert the whole lot to double later
			//System.out.println(asciiString);
			arrOneWeights[0] = asciiString;
			//System.out.println(Arrays.toString(arrOneWeights));
			sb2.append(Arrays.toString(arrOneWeights));
			System.out.println(Arrays.toString(arrOneWeights));


			sb2.append("");
			sb2.append("\n");
			//now everything is in the same format, with letters in the SearchEngine document arraylist and letters in the weighting document both in ascii
			//time to do the actual LCM multiplication

			//System.out.println(Arrays.toString(arrOneWeights));

		}

		String getVal = arrOneWeights[1];
		System.out.println("getVal " + getVal);

		double multiVal = Double.valueOf(getVal);//convert to double


		//System.out.println(SearchEngines.get(0));//WHERE HAS THE SEARCH ENGINE NUMBER GONE??
		//we can perhaps bypass letter and do it on the basis of columns
		//so array index 1 multiplication value of first lcm weights array could be applied to all of first element of Search Engines etc;	
		for (int i = 0; i< copySearchEngines.size(); i++)
		{//move through outer arraylist
			for(int j=0; j<copySearchEngines.get(i).size(); j++)
			{//move through inner arraylist
				int key = copySearchEngines.get(i).get(j).getKey();//get the doc
				double val = copySearchEngines.get(i).get(j).getValue();//get the rank score
				//there should be some form of loop here to move through the LCM weighting array.....
				double postLCMval = val*multiVal;//HERE IS THE ACTUAL APPLICATION OF THE WEIGHTING
				postLCMval = Math.round(postLCMval * 1000d) / 1000d;
				int keyCopy = copySearchEngines.get(i).get(j).getKey();
				Pair<Integer, Double> y2 = new Pair<Integer, Double>(keyCopy, postLCMval);
				copySearchEngines.get(i).set(j, y2);

			}
		}

		System.out.println("this is lcm before combining ");
		System.out.println(copySearchEngines);	
		sb2.append("this is lcm before combining ");
		sb2.append(copySearchEngines);

		//call comBSum method
	} 

	//break code into methods for each action then group all these methods under a single task 2 method and call that under main method in driver. 
	public void doTask2() throws FileNotFoundException 
	{
		try {
			readFile();
			interleaving();
			System.out.println(normaliseScores());
			System.out.println(comBsum());
			LCM(SearchEngines, inputWeights);

			sb2.append(normaliseScores());//stringbuilder
			sb2.append(comBsum());//stringbuilder
			//calling these methods to add it to stringbuilder activates the printline statements again, sorry

			try 
			{
				printFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}
	}			
	public void printFile() throws IOException 
	{
		try
		{
			FileWriter fileWriter = new FileWriter(output2); 
			fileWriter.append(sb2);
			fileWriter.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
	}
}//end class
