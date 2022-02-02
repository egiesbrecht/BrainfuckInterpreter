package interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
	public static String read(String fileName) {
		 try {
			 String ret="";
			 File myObj=new File(fileName);
			 Scanner myReader=new Scanner(myObj);
			 while (myReader.hasNextLine()) {
				 String data=myReader.nextLine();
				 ret+=data+"\n";
			 }
			 myReader.close();
			 return ret;
		 }catch(FileNotFoundException e) {
			 e.printStackTrace();
			 return null;
		 }
	}
}
