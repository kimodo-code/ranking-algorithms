import java.util.ArrayList;

/*make a query class with the four pieces of information in question as fields : 
	1;A;RNNRUNNRURRRUNUNNNNRR;10 there are 4 things here separated by semicolons, run number, search engine letter A-Z,
	results and the total number of relevant documents for that query run. These are the fields. Because there
	will be multiple runs from multiple different search engines, a query object will be a neat way of handling this.
	the methods for the various calculations can be enacted on this object and the results outputted.*/
public class QueryQuestion3 {

	public String engine;
	public int runnumber;
	public int relevantAll;
	public String results;
	int rels;
	int ret;
	ArrayList<String> sectors = new ArrayList<>();//this is for divide into sectors method
	ArrayList<Double> arrliProbFuse = new ArrayList<>();//this is for probFuse method
	//a default constructor made explicit...
	public QueryQuestion3()
	{
		engine = null;
		runnumber = 0;
		relevantAll = 0;
		results = null;
	}
	/*this is a constructor with its argument being the array coming from class question1. The array was formed after
	the input string was split on semicolon. Therefore each array represents one line, index 0 is the run no.
	index 1 the search engine letter, [2] the results body and [3] the total no. of relevant docs.*/
	public QueryQuestion3(String[] arr) 
	{
		relevantAll = Integer.parseInt(arr[3]);
		runnumber = Integer.parseInt(arr[0]);
		results = arr[2];
		engine = arr[1];	

	}
	//this toString is just for test printing
	@Override
	public String toString() 
	{
		return "Query [engine=" + engine + ", runnumber=" + runnumber + ", relevantAll=" + relevantAll + ", results="
				+ results + "]";
	}

	public ArrayList<String> stringIntoEqualParts(String results, int nParts)//divide into sectors method
	{
		int countRetrieved = 0;    
		//Counts each character except space   
		for(int i = 0; i < results.length(); i++) 
		{    
			if(results.charAt(i) != ' ')   
			{
				countRetrieved++;    
				ret = countRetrieved;//a way to use this variable outside the method
			} 
		}	
		int len = ret/nParts; //len is the length which every sector/part of the string will have
		if (len == 0) {
			return sectors;
		}
		String tempSector = "";
		for(int i=0; i<results.length(); i++)
		{
			tempSector = tempSector + results.charAt(i); // building new sector
			if (tempSector.length() == len)
			{
				sectors.add(tempSector);
				tempSector = "";
			}
		}
		System.out.println(sectors.toString());
		return sectors;
	}

	public ArrayList<Double> calculateProbFuse() //calculate probfuse method
	{
		double probFuseResults =0;

		for (int i=0; i< sectors.size(); i++)
		{
			int countRetrieved = 0; 
			int countRel = 0;
			char someChar = 'R';
			String eachSectorString = sectors.get(i);
			//System.out.println("each sector string" + eachSectorString);
			for (int j=0; j< eachSectorString.length(); j++)
			{ 
				if(eachSectorString.charAt(j) != ' ')   
				{
					countRetrieved++;    
					ret = countRetrieved;
				} 	
			}	
			for (int k=0; k< eachSectorString.length(); k++)
			{	
				if (eachSectorString.charAt(k) == someChar)
				{
					countRel++; 
					rels = countRel;
				}	
			}		
			//THE PROBFUSE EQUATION
			//P(dk|m) = (sum |Rkq|/k)/Queries
			//(rel/Ret) + (rel/ret)/query number(in this code it's called runnumber)
			// watch out for int division!!!!!	
			double relsDub = (double) rels;
			probFuseResults = ((relsDub/ret) + (relsDub/ret))/runnumber;
			probFuseResults = Math.round(probFuseResults *1000d)/1000d;
			arrliProbFuse.add(probFuseResults);
		}
		return arrliProbFuse;	
	}
}//end class

/* Basic idea for handling the interaction between the probfuse model and the live data. Create a method for taking in a file and reading 
 * live data similar to before. Make a series of string arrays with only two elements, the first element is the
 * search engine letter, the second is a long string of all the doc numbers. Ignore the commas. As the stringIntoEqualParts
 * method accepts a string and a int divisor number which needs to be the same as previously used to calculate the probfuse model
 * the nParts number will remain the same unless manually changed in the meantime and the long string of all the docs
 * can be passed as a method for chopping into equal parts. once done do an if statement
 * like if runnumber within QQ3 probfuse method matches first element of live data array, then apply the probfuse results array to the second element of the
 * live data array.  split the live data on comma and make an array for each sector with docs as elements.
 * Do a pair (String key, double value) for each element in live data array with probfuse value matched like setValue from array element 1 of
 * probfuse (corresponding to sector 1) to each element in the first array of livedata, likewise for second etc. 
 */







