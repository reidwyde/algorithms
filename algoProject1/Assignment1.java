/*Reid Wyde
*EID: raw3295
*Project 1 - Stable Marriage

 * Class to implement Stable Matching algorithms
 */

import java.util.ArrayList;


public class Assignment1 { 
	
	public static void main(String [] args){
		Assignment1 asn = new Assignment1();

		asn.testNextPerm();	
	}


	//Part1: Implement a Brute Force Solution
	public static ArrayList<Integer> stableMatchBruteForce(Preferences preferences) {
	
		ArrayList<Integer> pairing = new ArrayList<Integer>();
		
		return pairing;		

	}

    	// Part2: Implement Gale-Shapley Algorithm
    	public static ArrayList<Integer> stableMatchGaleShapley(Preferences preferences) {
/*Initially all m  M and w  W are free
2 while m who is free and hasnt proposed to every w  W
3 do Choose such a man m
4 Let w be the highest ranked in ms preference list
to whom m has not yet proposed
5 if w is free
6 then (m, w) become engaged
7 else w is currently engaged to m0
8 if w prefers m0
to m
9 then m remains free
10 else w prefers m to m0
11 (m, w) become engaged
12 m0 becomes free
13 return the set S of engaged pairs
*/	
				
		ArrayList<Integer> pairing = new ArrayList<Integer>();
		
		return pairing;		



	}

    	// Part3: Matching with Costs
    	public static ArrayList<Cost> stableMatchCosts(Preferences preferences) {
		ArrayList<Cost> matchCosts = new ArrayList<Cost>();
		return matchCosts;	

	}


	//Helper functions

	public boolean checkStability(ArrayList<Integer> pairing, Preferences preferences){
		for(int profIndex = 0; profIndex < pairing.size(); profIndex++){
			int currentProf = profIndex;
			int currentPairedStudent = pairing.get(currentProf);
			int currentMatchedProf;

			ArrayList<Integer> preferredStudents = 
				new ArrayList<Integer>(preferences.getProfessors_preference().get(currentProf).subList(0, currentPairedStudent)); 
				//gives an arraylist of all the students the professor would rather have than their current one.
			
			for (int prefStudentIndex = 0; prefStudentIndex < preferredStudents.size(); prefStudentIndex++){
				currentMatchedProf = pairing.indexOf(prefStudentIndex);
				ArrayList<Integer> preferredProfs = 
					new ArrayList<Integer>(preferences.getStudents_preference().get(currentPairedStudent).subList(0, currentMatchedProf));
				//gives an arraylist of all the professors the preferred student would rather have than their current one.
				if (preferredProfs.contains(currentProf)) return false;	
			}
					
		}
		return true;
			
	}

	//next permutation subroutines implement next lexicographical permutation algorithm
	
	//returns the index of the largest non-increasing suffix
	
	int getLargestNonIncSuffixIndex(ArrayList<Integer> perm){
		int suffixIndex = perm.size()-1;	
		while (suffixIndex > 0 && perm.get(suffixIndex-1) >= perm.get(suffixIndex)){
			suffixIndex--;
		}
		
		return suffixIndex;
	}
	
	int getPivotIndex(ArrayList<Integer> perm){
		return getLargestNonIncSuffixIndex(perm)-1;	
	}
	
	int getSwapIndex(ArrayList<Integer> perm, int suffixIndex){
		int swapIndex = suffixIndex;	
		for (int ii = swapIndex; ii<perm.size()-1; ii++){
			if (perm.get(ii)<perm.get(getPivotIndex(perm)) &&
				perm.get(ii)<=perm.get(swapIndex))
					swapIndex = ii;
		}		

		return swapIndex;
	}

	void swapPivot(ArrayList<Integer> perm, int pivotIndex, int swapIndex){
		int pivot = perm.get(pivotIndex);	
		int swap = perm.get(swapIndex);
		perm.set(pivotIndex, swap);
		perm.set(swapIndex, pivot);	
	}
	
	void reverseSubArray(ArrayList<Integer> perm, int subArrayIndex){
		ArrayList<Integer> reversed = new ArrayList<Integer>();
		for (int ii = perm.size()-1; ii>subArrayIndex; ii--){
			reversed.add(perm.get(ii));
		}
		perm.subList(subArrayIndex, perm.size()-1).clear();
		perm.addAll(reversed);	
	}
	
	
	boolean nextPermutationExists(ArrayList<Integer> perm) {
		//we are at the last permuation iff the entire perm is the largest value, which means the largest non-increasing suffix is the whole thing 
 		return(getLargestNonIncSuffixIndex(perm) != 0);
	}

	//assume that we check that the next permutation exists before calling this method 
	public ArrayList<Integer> getNextPerm(ArrayList<Integer> perm){
		int pivotIndex = getPivotIndex(perm);
		int swapIndex = getSwapIndex(perm, pivotIndex+1);
		swapPivot(perm, pivotIndex, swapIndex);
		reverseSubArray(perm, pivotIndex+1);
		return perm;	
	}	
	
	public void testNextPerm(){
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		testArray.add(1);
		testArray.add(2);
		testArray.add(3);
		int index = 0;

		while(nextPermutationExists(testArray)){

			System.out.println("Permutation number " + index + ": ");
			System.out.print("[");
			for (int i = 0; i<testArray.size()-1; i++){
				System.out.print(" " + testArray.get(i) + " ");
			}
			System.out.println("]");
		getNextPerm(testArray);	
	}


	}



}
