import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Query {

	/*make a query class with the four pieces of information in question as fields : 
	1;A;RNNRUNNRURRRUNUNNNNRR;10 there are 4 things here separated by semicolons, run number, search engine letter A-Z,
	results and the total number of relevant documents for that query run. These are the fields. Because there
	will be multiple runs from multiple different search engines, a query object will be a neat way of handling this.
	the methods for the various calculations can be enacted on this object and the results outputted.*/

	public String engine;
	public int runnumber;
	public int relevantAll;
	public String results;
	int rels;
	int ret;
	double retAtFive;
	double relAtFive;
	String subsetOfResults = null;
	int relretSubset = 0;
	int relretAtNoughtPointFive = 0;
	double countRetrievedSubset = 0.0;
	double retrievedSubset = 0;
	double precisionAtNoughtPointFive = 0.0;
	int unseenRels = 0;
	ArrayList<Integer> arrli = new ArrayList<Integer>();
	ArrayList<Double> arrliPrecisionPoint = new ArrayList<Double>();
	ArrayList<Double> arrliMAP = new ArrayList<Double>();
	ArrayList<ArrayList<Double>> arrliAlphabet = new ArrayList<ArrayList<Double>>();
	
	
	



	//a default constructor made explicit...
	public Query()
	{
		engine = null;
		runnumber = 0;
		relevantAll = 0;
		results = null;
	}
	/*this is a constructor with its argument being the array coming from class question1. The array was formed after
	the input string was split on semicolon. Therefore each array represents one line, index 0 is the run no.
	index 1 the search engine letter, [2] the results body and [3] the total no. of relevant docs.*/
	public Query(String[] arr) 
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
	//this method counts the number of R's. We need to know the precise breakdown of relevant/irrelevant for
	//the various equations to be performed so this method will sit inside the equation methods (or sometimes the code is repeated.)
	public int countRelevants()
	{
		char someChar = 'R';
		int countRel = 0;
		for (int i = 0; i < results.length(); i++)
		{
			if (results.charAt(i) == someChar)
			{
				countRel++; 
				rels = countRel;//a way to use this variable outside the method
			}
		}
		return rels;
	}
	//we'll also need to count the number of retrieved documents because they may not be the same for each run.
	public int countRetrieved()
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
		return ret;
	}

	//Precision is the fraction of the set of retrieved documents(RET) that are relevant (i.e.  they are also in REL)
	//is the number of R over retrieved relret/ret
	public double calculatePrecision()
	{
		//System.out.println("rels rels rels " + rels);
		double relret = rels;//rels still int so change to double
		/*
		 * All values in a mixed arithmetic operation (+, -, *, /) 
		 * are converted to double type before the arithmetic operation is performed.
		 */
		double precision = relret/ret;//precision equation
		precision = Math.round(precision *100.00)/100.00;//round to two decimal places
		return precision;
	}
	//recall is relret/rel
	public double calculateRecall()
	{
		double relret = rels;//rels still int so change to double
		/*
		 * All values in a mixed arithmetic operation (+, -, *, /) 
		 * are converted to double type before the arithmetic operation is performed.
		 */
		double recall = relret/relevantAll;//recall equation
		recall = Math.round(recall *100.00)/100.00;//round to two decimal places
		return recall;
	}
	//precision at five - imagine string was only 5 characters long and find out proportion of relevants/irrelevants from this string
	public double calculatePrecisionAtFive() 
	{//we need to stop after 5th character in a string so use substring
		String subsetOfResults = "";
		subsetOfResults = results.substring( 0,  5);
		char someChar = 'R';
		int countRel = 0;
		for (int i = 0; i < subsetOfResults.length(); i++)
		{
			if (subsetOfResults.charAt(i) == someChar)
			{
				countRel++; 
				rels = countRel;//a way to use this variable outside the method
			}
		}
		double precisionAtFive = countRel/5.0;
		precisionAtFive = Math.round(precisionAtFive *100.00)/100.00;
		return precisionAtFive;
	}
	public double findPrecisionAtNoughtPointFive()
	{
		char someChar = 'R';
		int countRel = 0;
		int countRetrieved = 0;    
		int countRelAgain = 0;
		int i = 0;

		for(i = 0; i < results.length(); i++) //for the length of the string
		{    
			if(results.charAt(i) != ' ')    //count total characters and number of R's in lockstep
			{
				countRetrieved++; //counting total number of characters
				if (results.charAt(i) == someChar)
				{
					countRel++; //counting relevants
					if (countRel >= (double)(relevantAll/2))//so that as soon as the number of R's is equal to half the end number(relevant set) or in other words as soon as recall = 50% or 0.5 
					{
						subsetOfResults = results.substring( 0,  countRetrieved);//chop the string at the point after where the last R is. countRetrieved advanced simultaneously with countRelevant so will be at correct point
						break;//don't keep recalculating the chopping point, exit now
					}
				}
			}
		}
		if (subsetOfResults == null)
		{//avoid null pointer exception if end of array is reached with not enough seen relevants for R=0.5
			System.out.println("Recall point of 0.5 was never reached, sorry! System will return -1 error");
			return -1;
		}
		//do the precision calculation from scratch with this new shortened string
		for (int k = 0; k < subsetOfResults.length(); k++)
		{
			if (subsetOfResults.charAt(k) == someChar)
			{
				countRelAgain++; //how many R's are in this shortened string?
			}
			if(subsetOfResults.charAt(k) != ' ')    
			{
				countRetrievedSubset++; //how many characters are in this shortened string?
			}
		}
		//System.out.println("count rel again " + countRel);//check
		//System.out.println("retrieved subset " + subsetOfResults);//check
		precisionAtNoughtPointFive = countRelAgain/countRetrievedSubset;//precision equation itself
		precisionAtNoughtPointFive = Math.round(precisionAtNoughtPointFive *100.00)/100.00;//round to two decimals
		return precisionAtNoughtPointFive;
	}

	public double AveragePrecision()
	{
		ArrayList<Double> arrliPrecisionPt = new ArrayList<>();//this will store the values
		double counter = 0;
		for ( int i = 0; i < results.length(); i++) //go through the string, 
		{
			if (results.charAt(i) == 'R')//if you get an R (index i is where the R is)
			{//watch out if there is an R in the index 0 position...
				counter++;//push the counter on by one
				//System.out.println(counter/(i+1));//this is the precision equation. counter represents relret, i + 1 represents retrieved.
				arrliPrecisionPt.add(counter/(i+1));//store the precision values in an arraylist directly
				//System.out.println(arrliPrecisionPt);
			}
		}
		//System.out.println(arrliPrecisionPt);
		double sum = 0;//we need a sum variable because average is sum of all values divided by number of values
		double result = 0;
		for(int i = 0;  i < arrliPrecisionPt.size(); i++) 
		{
			sum += arrliPrecisionPt.get(i);//sum the values that were stored in the arraylist
			//unseen results get a score of p = 0. they should be added to the arraylist so that 
			//the number of values is correct. HOWEVER, the figure after the string, relevant all,
			//is the set of all relevant documents, so that has the correct number of values
			//Seeing as the value of an unseen document is 0, adding them wouldn't change
			//the actual value.Therefore we don't need to add them to the arraylist, we can
			//just divide the sum of all values by relevantAll.
		}
		result = sum/relevantAll;
		result = Math.round(result *1000d)/1000d;
		return result;
	}
	
	public double MeanAveragePrecision() {
		
		//this is for calculating mean average precision. the averages from all the queries that share a search engine letter
		//go into the appropriate arraylist
			ArrayList<Double> A = new ArrayList<Double>();
			ArrayList<Double> B = new ArrayList<Double>();
			ArrayList<Double> C = new ArrayList<Double>();
			ArrayList<Double> D = new ArrayList<Double>();
			ArrayList<Double> E = new ArrayList<Double>();
			ArrayList<Double> F = new ArrayList<Double>();
			ArrayList<Double> G = new ArrayList<Double>();
			ArrayList<Double> H = new ArrayList<Double>();
			ArrayList<Double> I = new ArrayList<Double>();
			ArrayList<Double> J = new ArrayList<Double>();
			ArrayList<Double> K = new ArrayList<Double>();
			ArrayList<Double> L = new ArrayList<Double>();
			ArrayList<Double> M = new ArrayList<Double>();
			ArrayList<Double> N = new ArrayList<Double>();
			ArrayList<Double> O = new ArrayList<Double>();
			ArrayList<Double> P = new ArrayList<Double>();
			ArrayList<Double> Q = new ArrayList<Double>();
			ArrayList<Double> R = new ArrayList<Double>();
			ArrayList<Double> S = new ArrayList<Double>();
			ArrayList<Double> T = new ArrayList<Double>();
			ArrayList<Double> U = new ArrayList<Double>();
			ArrayList<Double> V = new ArrayList<Double>();
			ArrayList<Double> W = new ArrayList<Double>();
			ArrayList<Double> X = new ArrayList<Double>();
			ArrayList<Double> Y = new ArrayList<Double>();
			ArrayList<Double> Z = new ArrayList<Double>();

		if (this.engine.equals("A"))
		{		
		A.add(AveragePrecision());	
		}
		else if (this.engine.equals("B"))
		{		
		B.add(AveragePrecision());	
		}
		else if (this.engine.equals("C"))
		{		
			C.add(AveragePrecision());	
			}
		else if (this.engine.equals("D"))
		{		
			D.add(AveragePrecision());	
			}
		else if (this.engine.equals("E"))
		{		
			E.add(AveragePrecision());	
			}
		else if (this.engine.equals("F"))
		{		
			F.add(AveragePrecision());	
			}
		else if (this.engine.equals("G"))
		{		
			G.add(AveragePrecision());	
			}
		else if (this.engine.equals("H"))
		{		
			H.add(AveragePrecision());	
			}
		else if (this.engine.equals("I"))
		{		
			I.add(AveragePrecision());	
			}
		else if (this.engine.equals("J"))
		{		
			J.add(AveragePrecision());	
			}
		else if (this.engine.equals("K"))
		{		
			K.add(AveragePrecision());	
			}
		else if (this.engine.equals("L"))
		{		
			L.add(AveragePrecision());	
			}
		else if (this.engine.equals("M"))
		{		
			M.add(AveragePrecision());	
			}
		else if (this.engine.equals("N"))
		{		
			N.add(AveragePrecision());	
			}
		else if (this.engine.equals("O"))
		{		
			O.add(AveragePrecision());	
			}
		else if (this.engine.equals("P"))
		{		
			P.add(AveragePrecision());	
			}
		else if (this.engine.equals("Q"))
		{		
			Q.add(AveragePrecision());	
			}
		else if (this.engine.equals("R"))
		{		
			R.add(AveragePrecision());	
			}
		else if (this.engine.equals("S"))
		{		
			S.add(AveragePrecision());	
			}
		else if (this.engine.equals("T"))
		{		
			T.add(AveragePrecision());	
			}
		else if (this.engine.equals("U"))
		{		
			U.add(AveragePrecision());	
			}
		else if (this.engine.equals("V"))
		{		
			V.add(AveragePrecision());	
			}
		else if (this.engine.equals("W"))
		{		
			W.add(AveragePrecision());	
			}
		else if (this.engine.equals("X"))
		{		
			X.add(AveragePrecision());	
			}
		else if (this.engine.equals("Y"))
		{		
			Y.add(AveragePrecision());	
			}
		else if (this.engine.equals("Z"))
		{		
			Z.add(AveragePrecision());	
			}
		arrliAlphabet.add(A);
		arrliAlphabet.add(B);
		arrliAlphabet.add(C);
		arrliAlphabet.add(D);
		arrliAlphabet.add(E);
		arrliAlphabet.add(F);
		arrliAlphabet.add(G);
		arrliAlphabet.add(H);
		arrliAlphabet.add(I);
		arrliAlphabet.add(J);
		arrliAlphabet.add(K);
		arrliAlphabet.add(L);
		arrliAlphabet.add(M);
		arrliAlphabet.add(N);
		arrliAlphabet.add(O);
		arrliAlphabet.add(P);
		arrliAlphabet.add(Q);
		arrliAlphabet.add(R);
		arrliAlphabet.add(S);
		arrliAlphabet.add(T);
		arrliAlphabet.add(U);
		arrliAlphabet.add(V);
		arrliAlphabet.add(W);
		arrliAlphabet.add(X);
		arrliAlphabet.add(Y);
		arrliAlphabet.add(Z);
		
		
		double sum = 0.0;
		
		double avg = 0.0;

		for (int q = 0; q < arrliAlphabet.size(); q++)
		{
		for (double val : arrliAlphabet.get(q))
		{
			sum += val;//add up all the values
			avg = (sum / (arrliAlphabet.size()));//divide by number of elements(TO DO don't divide by 0....)
		
		}
		}
		avg = Math.round(avg *1000d)/1000d;
		return avg;
	}
	



	public TreeMap <Double, Double> Interpolate()
	{
		TreeMap<Double, Double> mapRecallPtPrecision = new TreeMap<>();//this will store the values
		double counter = 0;
		for ( int i = 0; i < results.length(); i++) //go through the string, 
		{
			if (results.charAt(i) == 'R')//if you get an R (index i is where the R is)
			{//watch out if there is an R in the index 0 position...
				counter++;//push the counter on by one 
				//System.out.println(counter/(i+1));//this is the precision equation. counter represents relret, i + 1 represents retrieved
				double recall = counter/relevantAll;//counter is the number of R's, relevant all is the relevant set, therefore this is the recall equation
				double precision = counter/(i+1);//this is the precision equation. counter represents relret, i + 1 represents retrieved.
				/*
				 * 
				 * Some algebra.
				 * 
				 * if recall = counter/relevantAll
				 * then recall(relevantAll) = counter
				 * 
				 * if precision = counter/(i+1)
				 * then precision(i+1) = counter
				 * 
				 * 
				 *
				 * Therefore Recall(RelevantAll) = Precision(i +1)
				 * Therefore Recall(RelevantAll)/(i+1) = Precision
				 * 
				 * When recall = 0.1 ....plug figures into equation ...to find precision at that point
				 * When recall = 0.2 do same all the way to recall = 1.0
				 *  
				 * 
				 */

				double precision0;
				double recall0;
				recall0 = 0.0;//recall at 0%
				precision0 = (0.0*relevantAll)/(i+1);//precision when recall = 0%
				precision0 = Math.round(precision0 *1000d)/1000d;
				mapRecallPtPrecision.put(recall0, precision0);//add the recall/precision pairs to the hashmap

				double precision1;
				double recall1;
				recall1 = 0.1;//recall at 10%
				precision1 = (0.1*relevantAll)/(i+1);//precision when recall = 10%
				precision1 = Math.round(precision1 *1000d)/1000d;
				mapRecallPtPrecision.put(recall1, precision1);//add the recall/precision pairs to the hashmap

				double precision2;
				double recall2;
				recall2 = 0.2;//recall at 20%
				precision2 = (0.2*relevantAll)/(i+1);//precision when recall = 20%
				precision2 = Math.round(precision2 *1000d)/1000d;
				mapRecallPtPrecision.put(recall2, precision2);//add the recall/precision pairs to the hashmap

				double precision3;
				double recall3;
				recall3 = 0.3;//recall at 30%
				precision3 = (0.3*relevantAll)/(i+1);//precision when recall = 30%
				precision3 = Math.round(precision3 *1000d)/1000d;
				mapRecallPtPrecision.put(recall3, precision3);//add the recall/precision pairs to the hashmap

				double precision4;
				double recall4;
				recall4 = 0.4;//recall at 40%
				precision4 = (0.4*relevantAll)/(i+1);//precision when recall = 40%
				precision4 = Math.round(precision4 *1000d)/1000d;
				mapRecallPtPrecision.put(recall4, precision4);//add the recall/precision pairs to the hashmap

				double precision5;
				double recall5;
				recall5 = 0.5;//recall at 50%
				precision5 = (0.5*relevantAll)/(i+1);//precision when recall = 50%
				precision5 = Math.round(precision5 *1000d)/1000d;
				mapRecallPtPrecision.put(recall5, precision5);//add the recall/precision pairs to the hashmap

				double precision6;
				double recall6;
				recall6 = 0.6;//recall at 60%
				precision6 = (0.6*relevantAll)/(i+1);//precision when recall = 60%
				precision6 = Math.round(precision6 *1000d)/1000d;
				mapRecallPtPrecision.put(recall6, precision6);//add the recall/precision pairs to the hashmap

				double precision7;
				double recall7;
				recall7 = 0.7;//recall at 70%
				precision7 = (0.7*relevantAll)/(i+1);//precision when recall = 70%
				precision7 = Math.round(precision7 *1000d)/1000d;
				mapRecallPtPrecision.put(recall7, precision7);//add the recall/precision pairs to the hashmap

				double precision8;
				double recall8;
				recall8 = 0.8;//recall at 80%
				precision8 = (0.8*relevantAll)/(i+1);//precision when recall = 80%
				precision8 = Math.round(precision8 *1000d)/1000d;
				mapRecallPtPrecision.put(recall8, precision8);//add the recall/precision pairs to the hashmap

				double precision9;
				double recall9;
				recall9 = 0.9;//recall at 90%
				precision9 = (0.9*relevantAll)/(i+1);//precision when recall = 90%
				precision9 = Math.round(precision9 *1000d)/1000d;
				mapRecallPtPrecision.put(recall9, precision9);//add the recall/precision pairs to the hashmap

				double precision10;
				double recall10;
				recall10 = 1.0;//recall at 100%
				precision10 = (1.0*relevantAll)/(i+1);//precision when recall = 100%
				precision10 = Math.round(precision10 *1000d)/1000d;
				mapRecallPtPrecision.put(recall10, precision10);//add the recall/precision pairs to the hashmap
			}
		}
		return mapRecallPtPrecision;
	}

}//class bracket



