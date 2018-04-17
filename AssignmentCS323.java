/**
*Design and Analysis of Algorithms
*Programming Assignment Due Date: 9 April 2018
*part_one
*@author Yang Yu
*/
import java.io.*;

class AssignmentCS323
{
	public static void main(String[] args){
		
		try{
		ReadFile rf = new ReadFile();
		System.out.println("========================================");
		System.out.println("Part_One:");
		rf.partOne(rf.list_Files("NumFiles-PARTONE"));
		System.out.println("========================================");
		System.out.println("Part_Two:");
		rf.partTwo(rf.list_Files("NumFiles-PARTTWO"));

		}	
		catch(IOException e){
			System.out.println(e);

		}
		//rf.writeResult();

	}
}