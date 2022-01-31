import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver 
{
//execute from main
	public static void main (String [] args) 
	{
		question1Run();
		question2Run();
		question3Run();
		
	}
//run methods follow
	public static void question1Run()
	{
		question1 obj = new question1 ("inputt1.txt", "outputt1.txt");//change your filenames HERE

		try
		{
			obj.readFile();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		obj.processQueries();

		try
		{
			obj.printFile();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public static void question2Run()
	{
		question2 obj = new question2("inputt2.txt", "inputWeights.txt", "outputt2.txt");//change your filenames HERE
		try
		{
			obj.doTask2();
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
		}
	}


	public static void question3Run() {
		question3 obj = new question3 ("inputt3.txt", 3, "liveData.txt");//change your filenames HERE

		try
		{
			obj.readFile();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		obj.doTask3();

		try 
		{
			obj.printFile();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}//end class





