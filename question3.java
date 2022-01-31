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

public class question3 
{
	String inputt3;
	int sectorNumber;
	String liveData;
	String outputt3;
	int nParts;
	QueryQuestion3 obj = new QueryQuestion3();
	//String exp = "RRRRRNNNNNKKKKK";
	StringBuilder sb3 = new StringBuilder();
	ArrayList<QueryQuestion3> queries  = new ArrayList<>();//arraylist of all query objects

	public question3 (String inputt3, int sectorNumber, String liveData)
	{
		this.inputt3 = inputt3;
		this.sectorNumber = sectorNumber;
		this.liveData = liveData;
		
	}

	public void readFile() throws FileNotFoundException
	{
		try
		{
			File f5 = new File(inputt3);//file object for input file with the text to be read
			Scanner diskScanner = new Scanner(f5);//scanner object to read file
			while (diskScanner.hasNextLine())
			{//repeat until there are no more lines to read
				String line = diskScanner.nextLine();
				String [] arr ;
				arr = line.split(";");
				QueryQuestion3 obj = new QueryQuestion3(arr);//new query object
				queries.add(obj);
				nParts = sectorNumber;//make this equal to constructor argument input and then pass to divide method
			}	
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}
	}
	public void doTask3() 
	{
		System.out.println("");
		System.out.println("");
		System.out.println("***********THIS IS CONSOLE OUTPUT FOR QUESTION 3**********");
		System.out.println("");
		System.out.println("");
		System.out.println(queries.toString());//TEST OK. Queries loaded into arraylist.

		for (int i = 0; i < queries.size(); i++)
		{
			String resultsVariable = queries.get(i).results;	//for all the elements of queries array, access the results field of the QQ3 constructor, make it a string to pass to the method
			//let's print
			sb3.append("This is a test print of the queries ");
			sb3.append("\n");
			sb3.append(queries.get(i).toString());
			sb3.append("\n");

			ArrayList<String> printDividingMethod = (queries.get(i).stringIntoEqualParts(resultsVariable, nParts));
			ArrayList<Double> printProbFuseModel = (queries.get(i).calculateProbFuse());

			sb3.append("This is when you apply the dividing method stringIntoEqualParts " );
			sb3.append("\n");
			sb3.append(printDividingMethod);
			sb3.append("\n");

			sb3.append("This is the Probability Fusion model. The number is the probability for each segment " );
			sb3.append("\n");
			sb3.append(printProbFuseModel);
			sb3.append("\n");
			sb3.append("\n");
			sb3.append("\n");

			System.out.println(printProbFuseModel);
		}
	}
	public void printFile() throws IOException 
	{

		try
		{
			FileWriter fileWriter = new FileWriter("outputt3.txt"); 

			fileWriter.append(sb3);
			fileWriter.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
	}
}//end class




