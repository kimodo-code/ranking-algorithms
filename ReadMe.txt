General notes:

Programme is written in Java and was created in Eclipse Version: 2018-09 (4.9.0) on a system running Windows 10.

Programme should be run in Windows.

Java files, class files and input/output files are in the one folder. The class files have been run successfully 
outside of Eclipse from command prompt.

The programme is compiled, the .class files have been included and only basic libraries were used 
so there should be no need for a JDK (although JRE is necessary). If, however, you want to change parts of the
code and re-run the programme then the java files would need to be re-compiled. In that case, here is the link
to a JDK: https://www.oracle.com/technetwork/java/javase/downloads/index.html

Lambda expressions are used in Question 1 and Question 2 so Java 8 is required.

The input and output text files are in the same folder as the java files. All tasks are executed from a class
called "Driver" which contains the main method. This is in the folder along with the other files.

For this assignment, to use your own input/output files, all you need to do is go to Driver and where the QuestionX
object is being created, type in your specific filename in the constructor argument (store your files in the same folder as the programme
files). 

The files used in Question 1 are question1, Query, inputt1.txt, outputt1.txt.
The files used in Question 2 are question2, inputt2.txt, inputWeights.txt, outputt2.txt.
The files used in Question 3 are question3, QueryQuestion3, inputt3, liveData, outputt3. There is also an int sector number. This is
an argument beside the input file arguments in the question3 constructor in Driver. To change the number of sector divisions
change this number.

The programme is executed by three different methods, one for each question under the main method in Driver. To "turn on/off", comment a method out.
	
public static void main (String [] args) 
	{
		question3Run();
		question2Run();
		question1Run();
	}

A small note - ("\n") was used to provide spacing for additions to stringbuilder.

Question 1.

Basic outline:

The input data is parsed in the readFile method. There are four relevant bits of information in each query. These become 4 fields in a 
query class and these query objects are added to an ArrayList in question1 class called queries. All the calculation methods,
precision, recall and the like are in the Query class. There is a processQueries method in question 1 where
the calculation methods are called and a series of for loops, one for each method,  iterates through
all the queries in the queries arraylist enacting the method on each one. A stringbuilder appends the same message
as is outputted to the console and a printFile method outputs to an output file. In driver, three question 1 methods are called each time
question1() is run, readFile, processQueries and printFile.

Issues/Bugs

Question 1 is reasonably robust.

The top 3 search engines by performance...does not output top 3. The problem perhaps is that the hashmap is limited by the number of keys/search engine letters.

Interpolated index. 
This calculates the precise figures for each recall point 10%, 20%, 30% etc and does not keep the higher precision until a new recall point
is reached. 

Question 2.

Basic outline.
As described in the comments, the basic format is:

	 * 
	 * The form that the search engine, document, rank score info will be stored in is the
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
	 * Index 0 SearchEngineA Index 1 SearchEngineB	Index 2 SearchEngineC	Index 3 SearchEngine D

There is first a processLine method to parse the data, then calculation methods, and then a doTask2 method where the calculation methods
are called. Question2 is run from Driver. Unlike question1, the readFile and printFile methods are not in Driver
rather they are called in doTask2.

Something to bear in mind. As Java does not allow mixed type arrays, the three data points search engine, doc no., rank score are 
all stored as doubles. To achieve this, the letter representing the search engine was converted into ASCII.

Issues/Bugs

ComBsum. The code is there for adding the scores of duplicate documents and adding a single document to a combined list. However, it does
not work in actual operation. The problem has been narrowed down to a single if statement. A hashset was used to store the documents and their
scores. The problem with checking for identical documents is that a statement like - if (setofDocs.contains(tempPair) - checks for identical PAIRS,
as in key and value are identical. What is required is code to check the key but not the value. As the contains method only works on objects and int
key is not an object, there is an issue here. getKey does not work. A variety of other approaches failed.

Another issue with comBsum is that the numerically sorted list of documents displays only the documents and not the scores (though obviously
scores were used to sort the list).

LCM.

When applying the weighting to the list of normalised documents, only the last weighting value is applied (and it's applied to the entire list of docs,
not the list for the appropriate search engine). Because of the incorrectly applied weighting, the comBsum method is left uncalled, although the understanding
is there that the weighting is first applied then the comBSum fusion takes place.

The problem with LCM most likely revolves around loops.   

Question 3

Basic outline.
This has a very similar structure to Question 1, indeed the input file and using an array to populate fields on a Query class are
the same as Q1. Just to note, the Query class for Question 1 is called Query, whereas the Query class for Question3 is called
QueryQuestion3. Again, Question 3 replicates Question 1 by having  readFile, processQueries and printFile methods in Driver which
are all called from a question3Run method under main. 

In QueryQuestion3, in place of all the precision/recall methods however are two methods instead, one for dividing the results string
into sectors called stringIntoEqualParts, and another for generating the probfuse model called calculateProbFuse. Both return an
ArrayList, "sectors" for stringIntoEqualParts and "arrliProbFuse" for calulatePRobFuse.

These are called from a processQueries method in Q3, that similarly to Q1, iterates through the queries array so that the methods
are enacted on each query.

stringIntoEqualParts method takes two arguments - string results and int sector number (the number of divisions to be made). In 
readFile of question3, the sectorNumber that comes in through manual input in the question3 constructor in Driver is made to equal a variable
that is declared on top of question3. nParts(the class variable) = sectorNumber(the constructor argument); nParts is then passed
as an argument into stringIntoEqualParts in QueryQuestion3.

The results are fed into the stringIntoEqualParts method as a string of results type RNRNRNRNNRNRNRN etc. that was parsed from the input
document.

Overall, the question3 constructor has three arguments, 	
	
	String inputt3;
	int sectorNumber;
	String liveData;
	
Inputt3 is the training data. The output filename is in the printFile method in Question 3.

Issues/bugs

Combining the probFuse model with the live data was not attempted (although a suggestion of how to go about it is in the comments).
The output file outputs the probFuse model and the sector divisions.




