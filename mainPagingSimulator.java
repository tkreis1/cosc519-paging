package paging_simulator;

import java.util.Scanner;

public class mainPagingSimulator {
	public static void main(String[] args) {

		// Start with demoMode off
		int demoMode = 0, framesAllocated = 4, numProcesses = 2;
		double pageFaultRate = 0.0;
		String input;
		Scanner scanner = new Scanner(System.in);

		/**
		 * System.out.print("Enter 0 to run in data collection mode or 1 to run
		 * in demo mode: "); input = scanner.next();
		 * 
		 * demoMode = Integer.valueOf(input);
		 * 
		 * //Initialize number of processes System.out.print("Current number of
		 * processes to simulate is 2. Enter 0 to continue or a different value
		 * to change it: "); input = scanner.next();
		 * 
		 * numProcesses = Integer.valueOf(input);
		 * 
		 * if(numProcesses == 0){ numProcesses = 2; } //char processArray [] =
		 * null; char[] processArray = new char[10]; char j = 'A';
		 * 
		 * for(int i = 0; i < numProcesses; i++){ processArray[i] = j; j++; }
		 * //Initialize frame allocation to processes System.out.print("Current
		 * frame allocation is 4 per process. Enter 0 to change it or 1 to
		 * continue: "); input = scanner.next();
		 * 
		 * if(input.equals("0")){ System.out.print("Enter number of frames per
		 * process: "); input = scanner.next(); framesAllocated =
		 * Integer.valueOf(input); }
		 * 
		 * int totalFrames = numProcesses * framesAllocated;
		 * 
		 * //Initialize prioritized process simulator double addr1H = 0.75,
		 * addrL = 0.12, addrH = 0.12, addrR = 0.01;
		 **/
		// Initialize locality simulator

		// Initialize replacement algorithms

		// Perform data collection
		if (demoMode == 0) {
			// pageFaultRate = pageFaults / memAccessCtr; from Part1.java
		}

		// Run program in demoMode
		else if (demoMode == 1) {

		}
	}// end main

}// end mainPagingSimulator class