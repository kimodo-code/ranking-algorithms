import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class question1 {

	String input1;
	String output1;
	String stringOfEverything = "hello";
	StringBuilder sb = new StringBuilder();
	ArrayList<Query> queries  = new ArrayList<>();//arraylist of all query objects
	HashMap <String, Double> unsortMap = new HashMap<String, Double>();

	public question1 (String input1, String output1)
	{
		this.input1 = input1;
		this.output1 = output1;
	}

	public void readFile() throws FileNotFoundException 
	{
		try{
			File f1 = new File(input1);//file object for input file with the text to be read
			Scanner diskScanner = new Scanner(f1);//scanner object to read file
			while (diskScanner.hasNextLine())
			{//repeat until there are no more lines to read
				String line = diskScanner.nextLine();
				String [] arr ;
				arr = line.split(";");
				Query obj = new Query(arr);
				queries.add(obj);
				//System.out.println(Arrays.toString(arr));
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}		
	}
	//do the calculation methods here
	public void processQueries() 
	{
		ArrayList<Double> MAPArrayLi  = new ArrayList<>();//mean average precision array li
		//System.out.println(queries.toString());//TEST OK. Queries loaded into arraylist.
		System.out.println("");
		System.out.println("");
		System.out.println("***********THIS IS CONSOLE OUTPUT FOR QUESTION 1**********");
		System.out.println("");
		System.out.println("");

		for (int i = 0; i < queries.size(); i++)
		{
			queries.get(i).countRelevants();//these methods have to be called or else the value of rels will never be updated. Rels field in Query is used in other methods
			queries.get(i).countRetrieved();//ditto for retrieved
			double printPrecision = queries.get(i).calculatePrecision();//variable for printing
			System.out.println("Precision for query at index " + i + " is " + printPrecision);//TEST OK. When Index 0 changed to All R's Precision is 100%. When everything you find is relevant precision is 100%.
			sb.append("Precision at index " + i + " = ");
			sb.append(printPrecision);
			sb.append(" ");
			sb.append("\n");
		}

		for (int j = 0; j < queries.size(); j++)	
		{	
			queries.get(j).countRelevants();
			queries.get(j).countRetrieved();
			double printRecall = queries.get(j).calculateRecall();
			System.out.println("Recall for query at index " + j + " is " + printRecall);//TEST OK. When Index 1 no.of R's matches end number (relevant set) Recall is 100%. !00% recall is when you find all relevant docs.
			sb.append("Recall at index " + j + " = ");
			sb.append(printRecall);
			sb.append(" ");
			sb.append("\n");
		}

		for (int k = 0; k < queries.size(); k++)	
		{
			queries.get(k).countRelevants();
			queries.get(k).countRetrieved();
			double printPrecisionAtFive = queries.get(k).calculatePrecisionAtFive();
			System.out.println("Precision@5 for query at index " + k + " is " + printPrecisionAtFive);//TEST OK
			sb.append("Precision@5 at index " + k + " = ");
			sb.append(printPrecisionAtFive);
			sb.append(" ");
			sb.append("\n");
		}

		for (int l = 0; l < queries.size(); l++)
		{
			queries.get(l).countRelevants();
			queries.get(l).countRetrieved();
			double valuesForBestList = queries.get(l).findPrecisionAtNoughtPointFive();//TEST OK. Calculation performed was find recall at 0.5 which was taken at the point where the seen relevants were half or greater than the relevant set. Then the precision retrieved set was taken to be all the preceding documents from that recall point equaling 0.5. Error message will appear if no 50% recall in query.
			//*********special lines of code for creating a list of best three search engines using p@R=0.5 as metric
			double printPrecisionAtNoughtPointFive = valuesForBestList;
			String keysForBestList = queries.get(l).engine;
			unsortMap.put(keysForBestList, valuesForBestList);
			System.out.println("The precision@recall = 0.5 for index " + l + " is " + printPrecisionAtNoughtPointFive);
			sb.append("Precision@Recall=0.5 at index " + l + " = ");
			sb.append(printPrecisionAtNoughtPointFive);
			sb.append(" ");
			sb.append("\n");
		}

		for (int m = 0; m < queries.size(); m++)
		{
			queries.get(m).countRelevants();
			queries.get(m).countRetrieved();
			double printAveragePrecision = queries.get(m).AveragePrecision();
			System.out.println("The average precision for index  " + m + " is " + printAveragePrecision);//TEST OK.
			sb.append("Average Precision at index " + m + " = ");
			sb.append(printAveragePrecision);
			sb.append(" ");
			sb.append("\n");
		}
/*
		for (int n = 0; n < queries.size(); n++)
		{
			double goIntoMAPArrayLi = queries.get(n).AveragePrecision();//inside the for loop so iterating through each query
			MAPArrayLi.add(goIntoMAPArrayLi);//adding the average values to the arraylist with each iteration
		}
*/
		for (int o =0; o < queries.size(); o++) 
		{	
			queries.get(o).countRelevants();
			queries.get(o).countRetrieved();
			TreeMap<Double, Double> printInterpolated = queries.get(o).Interpolate();
			System.out.println("The interpolated recall(key)/precision(value) for index  " + o + " is " + printInterpolated);
			sb.append("The interpolated recall(key)/precision(value) for index  " + o + " is " + printInterpolated);
			sb.append(printInterpolated);
			sb.append(" ");
			sb.append("\n");
		}

		//now add all these calculations to a stringbuilder so that the stringbuilder can be outputted to an output file
		//Outside the for loops
		//This part calculates the Mean Average Precision. 
	for (int p = 0; p< queries.size(); p++) 
	{
		
		queries.get(p).countRelevants();
		queries.get(p).countRetrieved();
		double printMeanAveragePrecision = queries.get(p).MeanAveragePrecision();
		System.out.println("Mean Average Precision for Search Engines A - Z " + p + " is " + printMeanAveragePrecision);
		sb.append("Mean Average Precision for Search Engines A - Z " + p + " = " + printMeanAveragePrecision);
		sb.append(printMeanAveragePrecision);
		sb.append(" ");
		sb.append("\n");
			
			
			
		}
		
		
		


		//sort the hashmap of key(search engine letter) values (results of the p@0.5 calculation) by value using lambda expression
		Map<String, Double> result2 = new LinkedHashMap<>();
		unsortMap.entrySet().stream()
		.sorted(Map.Entry.<String, Double>comparingByValue().reversed())
		.forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
		System.out.println("The top 3 search engines by performance are: " + result2);
		sb.append("The top 3 search engines by performance are: " + result2);//print top (3). Result is not correct. Problem with hashmap being limited by the number of keys(search engine letters)??
	}

	

	public void printFile() throws IOException 
	{	
		try
		{	
			FileWriter fileWriter = new FileWriter(output1); 	
			fileWriter.append(sb);
			fileWriter.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}	
	}
}//end class



