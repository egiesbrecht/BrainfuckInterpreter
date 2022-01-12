package interpreter;

import java.util.Scanner;

public class Interpreter {
	private static Scanner scr=new Scanner(System.in);
	
	public static void interpret(String input) {
		interpret(input, false);
	}
	
	public static void interpret(String input, boolean printAsByte) {
		char[] ic=input.toCharArray();
		
		//max for a 16 bit integer
		int maxSize=65535;
		byte[] memory=new byte[maxSize];
		
		int pointer=0, c=0;
		
		for(int i=0; i<ic.length; i++) {
			switch(ic[i]) {
			case '>':
				if(pointer==maxSize-1) pointer=0;
				else pointer++;
				break;
				
			case '<':
				if(pointer==0) pointer=maxSize-1;
				else pointer--;
				break;
				
			case '+':
				memory[pointer]++;
				break;
				
			case '-':
				memory[pointer]--;
				break;
				
			case '.':
				if(printAsByte) System.out.print(memory[pointer]);
				else System.out.print((char) memory[pointer]);
				break;
				
			case ',':
				memory[pointer]=(byte) scr.next().charAt(0);
				break;
				
			case '[':
				if(memory[pointer]==0) {
					i++;
					while(c>0 || ic[i]!=']') {
						if(ic[i]=='[') c++;
						else if(ic[i]==']') c--;
						i++;
					}
				}
				break;
				
			case ']':
				if(memory[pointer]!=0) {
					i--;
					while(c>0 || ic[i]!='[') {
						if(ic[i]==']') c++;
						else if(ic[i]=='[') c--;
						i--;
					}
					i--;
				}
				break;
			}
		}
	}
	
	
	public static void main(String[]args) {
		String in="";
		if(args.length>0) {
			in=FileHandler.read(args[0]);
		}else {
			System.out.println("Type your code:");
			in=scr.nextLine();
			System.out.println("Output/Program input:");
		}
		interpret(in);
	}
	
	
	public static void printHelloWorld() {
		String code=FileHandler.read("HelloWorld.bf");
		interpret(code);
	}
}
