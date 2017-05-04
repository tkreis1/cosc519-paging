package paging_simulator;

import java.lang.Math;
import java.util.ArrayList;

public class Part1 {
	// int pageId;
	private static int processSize = 10000;
	private static int pageSize = 1000;
	private static int currAddress = processSize / 2;
	private static int memAccessCnt = 0;
	private static int pageFaultCnt = 0;
	private static int nextFrameNum = 0;
	private static int algo = 0; ////
	private static int newFrameNum; ////
	private static int prevPageNum; ////

	private class TableEntry {
		private int pageNum;
		private int frameNum;
		private boolean validity;

		public TableEntry(int pageNum, int frameNum, boolean validity) {
			this.pageNum = pageNum;
			this.frameNum = frameNum;
			this.validity = validity;
		}

		public boolean isValid() {
			return validity;
		}

		public void setFrameNum(int frameNum) {
			this.frameNum = frameNum;
			this.validity = true;
		}
	}// end TableEntry class

	public class ProcPageTable {
		private ArrayList<TableEntry> pageTable;
		private int numPages;

		public ProcPageTable(int processSize, int pageSize) {
			pageTable = new ArrayList<TableEntry>();
			int numPages = processSize / pageSize + 1;
			System.out.println("numPages = " + numPages);
			for (int i = 0; i < numPages; i++) {
				pageTable.add(new TableEntry(i, 0, false));
			}
		}

		public TableEntry getTableEntry(int pageNum) {
			// return this.getTableEntry(pageNum);
			return pageTable.get(pageNum);
		}

		public boolean pageIsValid(int pageNum) {
			// TableEntry ptTemp = pageTable.get(pageNum);
			// return ptTemp.isValid();
			return pageTable.get(pageNum).isValid();
		}

		public void setFrameNum(int pageNum, int frameNum) {
			TableEntry tableEntryTemp = pageTable.get(pageNum);
			tableEntryTemp.pageNum = pageNum;
			tableEntryTemp.frameNum = frameNum;
			tableEntryTemp.validity = true;
			pageTable.set(pageNum, tableEntryTemp);
		}
	}// end ProcPageTable class

	public static void main(String[] args) {
		// System.out.println("starting main() ");
		Part1 part1 = new Part1();
		ProcPageTable ppt = part1.new ProcPageTable(processSize, pageSize);
		System.out.println("starting address = " + currAddress);
		for (int i = 0; i < 20; i++) {
			memAccess(ppt);
		}
		System.out.println("memAccessCnt = " + memAccessCnt);
		System.out.println("pageFaultCnt = " + pageFaultCnt);

	}// end main

	public static void memAccess(ProcPageTable ppt) {
		// int memAccessCtr = 0;
		boolean pageValid = false;
		int pageNum;
		// int currAddress = processSize/2;

		// System.out.println("starting memAccess() ");
		// Call locality simulator to get page ID to access
		currAddress = locality(processSize, currAddress);
		System.out.println("currAddress = " + currAddress);

		pageNum = getPageNum(currAddress, pageSize);
		System.out.println("pageNum = " + pageNum);
		// Check page's validity bit in page table
		// if(!pageValid){
		if (ppt.pageIsValid(pageNum)) {
			// update reference bit or count?
		} else {
			// Call do paging
			doPaging(ppt, pageNum);
			// pageFaultCnt++;
		}
		// Increment memory access counter
		memAccessCnt++;
	}

	public static int getPageNum(int currAddress, int pageSize) {
		// System.out.println("starting getPageNum() ");
		return currAddress / pageSize;
	}


	public static void doPaging(ProcPageTable ppt, int pageNum) {
		System.out.println("starting doPaging() ");
		// int pageFaults = 0;
		// If free frame, assign page to it
		System.out.println("Assigning nextFrameNum, " + nextFrameNum + " to pageNum, " + pageNum);
		ppt.setFrameNum(pageNum, nextFrameNum);
		if (nextFrameNum++ > 5) {
			nextFrameNum = 0;
		}

		// Else call replacement algorithm
		//switch statement for algorithms

		switch (algo){ ////
		//Run LRU Algorithm
			case 1: System.out.println("Calling LRU"); ////
			//Get new frame number for page from LRU
			newFrameNum = lruR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////
			
			case 2: System.out.println("Calling FIFO"); ////
			newFrameNum = fifoR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////		
			
			case 3: System.out.println("Calling Random"); ////
			newFrameNum = randomR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////		
			
			case 4: System.out.println("Calling NUR"); ////
			newFrameNum = nurR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////	
			
			case 5: System.out.println("Calling Far"); ////
			newFrameNum = farR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////	
			
			case 6: System.out.println("Calling PFF"); ////
			newFrameNum = pffR(ppt,pageNum); ////
			//Update old page table entry for process previously assigned that frame
			ppt.getTableEntry(pageNum).validity = false; ////
			//Assign the frame to the page
			ppt.setFrameNum(pageNum, newFrameNum); ////
			break; ////			
		}

		// Increment page fault counter
		pageFaultCnt++;
	}

	public static int locality(int processSize, int currAddress) {

		System.out.println("starting locality() ");
		int selector = (int) (Math.random() * 100); 
		System.out.println("selector = " + selector);
		if (selector < 20) {
			currAddress = currAddress + 1;
		} else if (selector < 40) {
			currAddress = currAddress + 4;
		} else if (selector < 60) {
			currAddress = currAddress - 5;
		} else if (selector < 80) {
			currAddress = currAddress + 100;
		} else {
			currAddress = (int) (Math.random() * processSize);
		}
		if (currAddress < 0) {
			currAddress = 100;
		}
		if (currAddress >= processSize) { // should starting address be considered?
			currAddress = processSize - 500;
		}

		return currAddress;

	}
}

////Kelly's stuff for LRU
public static void lru() {

	// For now, I have a fixed amount of pages this will reference
	// And I have a fixed amount of pages slots given
	// This is easily changed to account for when a user presses a start and
	// stop button

	// IF TESTING change 20 to 11 and 5 to 3
	int fixedNumberToReference = 20;
	int fixedNumberForPageSlots = 5;

	String[] memory = new String[fixedNumberForPageSlots];

	for (int i = fixedNumberForPageSlots - 1; i >= 0; i--) {
		Page page = getNextPage();
		memory[i] = page.getName();
		page.incrementReferenceCount();
		print(fixedNumberForPageSlots, memory);
	}

	for (int i = fixedNumberForPageSlots; i < fixedNumberToReference; i++) {

		Page page = getNextPage();

		if (Arrays.asList(memory).contains(page.getName()) && !memory[0].equals(page.getName())) {
			String temp = memory[0];
			memory[0] = page.getName();
			int j = 1;

			while (!memory[j].equals(page.getName())) {
				String tempTwo = memory[j];
				memory[j] = temp;
				temp = tempTwo;
				++j;
			}
			memory[j] = temp;
		} else if (!memory[0].equals(page.getName())) {
			for (int k = fixedNumberForPageSlots - 1; k > 0; k--) {
				memory[k] = memory[k - 1];
			}
			memory[0] = page.getName();
		}

		print(fixedNumberForPageSlots, memory);
	}

}

public static Page getNextPage() {
	// Read all pages from data file -> make them into objects

	// IF YOU ARE TESTING USE THE FILE "data/pageListTEST.txt"
	//String fileName = "data/pageListTEST.txt";
	////String fileName = "data/pageList.txt";
	////ArrayList<Page> pages = new ArrayList<Page>();

	try {
		////FileReader fileReader = new FileReader(fileName);
		////BufferedReader bufferedReader = new BufferedReader(fileReader);
		////String line;

		////while ((line = bufferedReader.readLine()) != null) {
			////String[] temp = line.split(" ");
			////pages.add(new Page(temp[0], Integer.parseInt(temp[1]), false));
		
		char pageName = 'A';
		for(int i = 0; i < 20; i++){ ////
			pageName++;
		}

		////bufferedReader.close();

	} catch (Exception e) {

	}

	/*
	 * if (userInput) { Add user input info to data file Add user input info
	 * into data array Reference page user picks }
	 */
	// else pick random page from data array

	// If you want to test the algorithms to make sure they work for
	// yourself
	// You can comment out this lines and un-comment out the line below
	return pages.get((int)(Math.random() * pages.size()));
	//return pages.get(forTestingOnly());

}

public static int forTestingOnly() {
	return testingOnly++;
}

public static void print(int slotAmount, String[] memory){
	for (int i = 0; i < slotAmount; i++){
		System.out.print(memory[i] + " ");
	}
	System.out.println("");
}
}


////End Kelly's stuff