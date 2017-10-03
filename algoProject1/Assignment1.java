/*Reid Wyde
*EID: raw3295
*Project 1 - Stable Marriage

 * Class to implement Stable Matching algorithms
 */

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class Assignment1 {

    public static void main(String [] args){
        Assignment1 asn = new Assignment1();

        asn.testNextPerm();
        asn.testStability();
        asn.testStableMatchBruteForce();
        asn.testStableMatchBruteForce2();
        asn.testGSGivenInputs();


    }


    //Part1: Implement a Brute Force Solution
    public static ArrayList<Integer> stableMatchBruteForce(Preferences preferences) {
        preferences = preProcessIndices(preferences);
        ArrayList<Integer> pairing = new ArrayList<Integer>();

        for (int ii = 0; ii<preferences.getNumberOfProfessors(); ii++){
            pairing.add(ii);
        }

        while((! checkStability(pairing, preferences)) && nextPermutationExists(pairing)){
            pairing = getNextPerm(pairing);
        }

        return pairing;
    }

    // Part2: Implement Gale-Shapley Algorithm
    public static ArrayList<Integer> stableMatchGaleShapley(Preferences preferences) {
        preferences = preProcessIndices(preferences);
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

O(n^2) implementation

Representing men and women
Assume men are named 1...n
Assume women are named 1' ... n'

Engagements
Maintain a list of free men in a queue
maintain two arrays of length n, wife[m] (student) and husband[w] (prof)

    set entry to 0 if unmatched
    if m matched to w, then wife[m] = w and
    husband[w] = m
*/
            int numProfs = preferences.getNumberOfProfessors();
            ArrayList<Integer> queueOfProfs = new ArrayList<Integer>();
            for (int ii = 0; ii<numProfs; ii++){
                queueOfProfs.add(ii);
            }

            ArrayList<Integer> profOfGivenStud = new ArrayList<Integer>(Collections.nCopies(numProfs, -1));
            ArrayList<Integer> studOfGivenProf = new ArrayList<Integer>(Collections.nCopies(numProfs, -1));
/*
Proposals
for each man, maintain a list of women, ordered by preference
maintain an array count[m] that counts the number of proposals made by man m
*/

            ArrayList<Integer> profProposalCount = new ArrayList<Integer>(Collections.nCopies(numProfs, 0));
/*

Women rejecting/accepting
For each woman, create an inverse preference list
allows constant time queries:
        a woman prefers m to m' if inverse[m] < inverse[m']

            //we are creating a double arraylist in O(n^2) time serially with our later O(n^2) time,
            //as opposed to paralleling O(n) with our later O(n^2) time using the indexOf function
*/

            ArrayList<ArrayList<Integer>> studPrefOfGivenProf = studPrefInverse(preferences);

/*

Proposal Process
The first free man, m, in the queue proposes to the woman at the front of his preference list, w
He increments count[m] and removes w from his preference list // I do not remove w from anything in my code

w accepts the proposal if she is unengaged or prefers m to her current match
if w accepts, her former match goes back on the queue of men; otherwise, m proposes to his next favorite

*/
        // first (last) professor in queueOfProfs proposes to the first person in his pref list
        int numProfsLeft = numProfs;
        int currentProposer;    //current proposing prof
        int currentProposee;    //current proposing student
        int currentMatchedProfessor;
        boolean makeSwitch;
        int currentProfScore;
        int proposerScore;


        //loop through all proposals
        while(numProfsLeft > 0) {
            numProfsLeft = queueOfProfs.size();
            currentProposer = queueOfProfs.get(numProfsLeft - 1);
            currentProposee = preferences.getProfessors_preference().get(currentProposer).get(profProposalCount.get(currentProposer));

            while (studOfGivenProf.get(currentProposer) == -1) {
                makeSwitch = false;
                currentProposee = preferences.getProfessors_preference().get(currentProposer).get(profProposalCount.get(currentProposer));

                //find the current match of the proposee - label this currentMatchedProfessor
                currentMatchedProfessor = profOfGivenStud.get(currentProposee);
                //if the currentMatchedProfessor is -1 (unmatched) make the switch
                if (currentMatchedProfessor == -1) makeSwitch = true;
                    //else if rank of the current proposer is less than the currentMatchedProfessor make the switch
                else {
                    currentProfScore = studPrefOfGivenProf.get(currentProposee).get(currentMatchedProfessor);
                    proposerScore = studPrefOfGivenProf.get(currentProposee).get(currentProposer);
                    if (proposerScore < currentProfScore) makeSwitch = true;
                }
                //making the switch
                //assign the current proposer to the student
                //add the professor who just got dumped to the queue of profs.
                if (makeSwitch) {
                    profOfGivenStud.set(currentProposee, currentProposer);
                    studOfGivenProf.set(currentProposer, currentProposee);
                    queueOfProfs.remove(numProfsLeft - 1);
                    if (!(currentMatchedProfessor == -1)){
                        studOfGivenProf.set(currentMatchedProfessor, -1);
                        queueOfProfs.add(currentMatchedProfessor);
                    }
                }
                //else don't make the switch
                //current proposer asks until he finds a mate
                //increment the count of proposals for this prof
                profProposalCount.set(currentProposer, profProposalCount.get(currentProposer) + 1);
            }

            numProfsLeft = queueOfProfs.size();
        }


        return studOfGivenProf;
    }

    // Part3: Matching with Costs
    public static ArrayList<Cost> stableMatchCosts(Preferences preferences) {
        ArrayList<Cost> matchCosts = new ArrayList<Cost>();
        ArrayList<Integer> pairing = stableMatchGaleShapley(preferences);
        ArrayList<ArrayList<Integer>> profPref = preferences.getProfessors_preference();
        ArrayList<ArrayList<Integer>> studPref = preferences.getStudents_preference();
        int numProfs = pairing.size();
        int prof;
        int stud;
        int costToProf;
        int costToStud;

        for (int ii=0; ii<numProfs; ii++) {
            prof = ii;
            stud = pairing.get(prof);
            //calculate cost to professor
            costToProf = profPref.get(prof).indexOf(stud);
            //calculate cost to student
            costToStud = studPref.get(stud).indexOf(prof);
            //construct cost object
            Cost thisCost = new Cost(prof, stud, costToProf, costToStud);
            matchCosts.add(thisCost);

        }

        return matchCosts;

    }


    public static ArrayList<Cost> stableMatchCostsStudent(Preferences preferences) {
        ArrayList<Cost> matchCostsStudsOptimal = new ArrayList<Cost>();

        //let's construct the optimal student by using GS, but flipping the preference object

        int numProfs = preferences.getNumberOfProfessors();
        ArrayList<ArrayList<Integer>> profPref = preferences.getProfessors_preference();
        ArrayList<ArrayList<Integer>> studPref = preferences.getStudents_preference();

        Preferences studOptPreferences = new Preferences(numProfs, numProfs, studPref, profPref);

        ArrayList<Integer> pairing = stableMatchGaleShapley(studOptPreferences);

        int prof;
        int stud;
        int costToProf;
        int costToStud;

        for (int ii=0; ii<numProfs; ii++) {
            stud = ii;
            prof = pairing.get(stud);
            //calculate cost to professor
            costToProf = profPref.get(prof).indexOf(stud);
            //calculate cost to student
            costToStud = studPref.get(stud).indexOf(prof);
            //construct cost object
            Cost thisCost = new Cost(stud, prof, costToStud, costToProf);
            matchCostsStudsOptimal.add(thisCost);

        }

        return matchCostsStudsOptimal;
    }



    //inverse of rank lookup
    //top level entry is a given student
    //second level entry is total profs - 1 - profRank for that student
    public static ArrayList<ArrayList<Integer>> studPrefInverse(Preferences preferences){
        int rank;   //smaller is better
        int prof;
        int numProfs = preferences.getNumberOfProfessors();
        ArrayList<ArrayList<Integer>> studPrefOfGivenProf =  new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> inverseProf = new ArrayList<Integer>(Collections.nCopies(numProfs, -1));


        for (int ii = 0; ii<numProfs; ii++){
            //inverseProf.clear();
            for(int jj = 0; jj<numProfs; jj++){
                prof = preferences.getStudents_preference().get(ii).get(jj);
                rank = jj;
                inverseProf.set(prof, rank);
            }
            studPrefOfGivenProf.add(inverseProf);
        }

        return studPrefOfGivenProf;
    }


    //Helper functions

    public static boolean checkStability(ArrayList<Integer> pairing, Preferences preferences){
        preferences = preProcessIndices(preferences);
        for(int profIndex = 0; profIndex < pairing.size(); profIndex++){
            int currentProf = profIndex;
            int currentPairedStudent = pairing.get(currentProf);
            int indexOfCurrentPairedStudent = preferences.getProfessors_preference().get(currentProf).indexOf(currentPairedStudent);
            int currentMatchedProf;
            int indexOfCurrentMatchedProf;

            ArrayList<Integer> preferredStudents =
                    new ArrayList<Integer>(preferences.getProfessors_preference().get(currentProf).subList(0, indexOfCurrentPairedStudent));
            //gives an arraylist of all the students the professor would rather have than their current one.




            for (int prefStudentIndex = 0; prefStudentIndex < preferredStudents.size(); prefStudentIndex++){

                currentMatchedProf = pairing.indexOf(preferredStudents.get(prefStudentIndex));
                //the professor our preferred student is currently with
                indexOfCurrentMatchedProf = preferences.getStudents_preference().get(prefStudentIndex).indexOf(currentMatchedProf);


                ArrayList<Integer> preferredProfs =
                       // new ArrayList<Integer>(preferences.getStudents_preference().get(pairing.get(prefStudentIndex)).subList(0, indexOfCurrentMatchedProf));
                        new ArrayList<Integer>(preferences.getStudents_preference().get(preferredStudents.get(prefStudentIndex)).subList(0, indexOfCurrentMatchedProf));
                //gives an arraylist of all the professors the preferred student would rather have than their current one.


                if (preferredProfs.contains(currentProf)){
                    //System.out.println("debug");
                    return false;
                }
            }

        }
        return true;

    }

    //next permutation subroutines implement next lexicographical permutation algorithm

    //returns the index of the largest non-increasing suffix

    public static int getLargestNonIncSuffixIndex(ArrayList<Integer> perm){
        int suffixIndex = perm.size()-1;
        while (suffixIndex > 0 && perm.get(suffixIndex-1) >= perm.get(suffixIndex)){
            suffixIndex--;
        }

        return suffixIndex;
    }

    public static int getPivotIndex(ArrayList<Integer> perm){
        return getLargestNonIncSuffixIndex(perm)-1;
    }

    public static int getSwapIndex(ArrayList<Integer> perm, int suffixIndex){
        int swapIndex = suffixIndex;
        int pivot = perm.get(getPivotIndex(perm));

        for (int ii = swapIndex; ii<perm.size(); ii++){
            if (perm.get(ii)>pivot &&
                    perm.get(ii)<=perm.get(swapIndex))
                swapIndex = ii;
        }

        return swapIndex;
    }

    public static void swapPivot(ArrayList<Integer> perm, int pivotIndex, int swapIndex){
        int pivot = perm.get(pivotIndex);
        int swap = perm.get(swapIndex);
        perm.set(pivotIndex, swap);
        perm.set(swapIndex, pivot);
    }

    public static void reverseSubArray(ArrayList<Integer> perm, int subArrayIndex){
        ArrayList<Integer> reversed = new ArrayList<Integer>();
        for (int ii = perm.size()-1; ii>=subArrayIndex; ii--){
            reversed.add(perm.get(ii));
        }
        perm.subList(subArrayIndex, perm.size()).clear();
        perm.addAll(reversed);
    }


    public static boolean nextPermutationExists(ArrayList<Integer> perm) {
        //we are at the last permuation iff the entire perm is the largest value, which means the largest non-increasing suffix is the whole thing
        return(getLargestNonIncSuffixIndex(perm) != 0);
    }

    //assume that we check that the next permutation exists before calling this method
    public static ArrayList<Integer> getNextPerm(ArrayList<Integer> perm){
        ArrayList<Integer> nextPerm = new ArrayList<Integer>(perm);
        int pivotIndex = getPivotIndex(nextPerm);
        int swapIndex = getSwapIndex(nextPerm, pivotIndex+1);
        swapPivot(nextPerm, pivotIndex, swapIndex);
        reverseSubArray(nextPerm, pivotIndex+1);
        return nextPerm;
    }

    public static void testNextPerm(){
        ArrayList<Integer> testArray = new ArrayList<Integer>();
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        int index = 0;


        printList(testArray);
        while(nextPermutationExists(testArray)) {
            testArray = getNextPerm(testArray);
            printList(testArray);
        }
    }

    public static void testStability() {

        ArrayList<Integer> prof1Pref = new ArrayList<Integer>();
        prof1Pref.add(1);
        prof1Pref.add(2);
        prof1Pref.add(3);

        ArrayList<Integer> prof2Pref = new ArrayList<Integer>();
        prof2Pref.add(1);
        prof2Pref.add(2);
        prof2Pref.add(3);

        ArrayList<Integer> prof3Pref = new ArrayList<Integer>();
        prof3Pref.add(1);
        prof3Pref.add(2);
        prof3Pref.add(3);

        ArrayList<Integer> stud1Pref = new ArrayList<Integer>();
        stud1Pref.add(1);
        stud1Pref.add(2);
        stud1Pref.add(3);

        ArrayList<Integer> stud2Pref = new ArrayList<Integer>();
        stud2Pref.add(1);
        stud2Pref.add(2);
        stud2Pref.add(3);

        ArrayList<Integer> stud3Pref = new ArrayList<Integer>();
        stud3Pref.add(1);
        stud3Pref.add(2);
        stud3Pref.add(3);

        ArrayList<ArrayList<Integer>> profPref = new ArrayList<ArrayList<Integer>>();
        profPref.add(prof1Pref);
        profPref.add(prof2Pref);
        profPref.add(prof3Pref);

        ArrayList<ArrayList<Integer>> studPref = new ArrayList<ArrayList<Integer>>();
        studPref.add(stud1Pref);
        studPref.add(stud2Pref);
        studPref.add(stud3Pref);


        Preferences p1 = new Preferences(3, 3, profPref, studPref);
        Preferences p2 = new Preferences(3, 3, profPref, studPref);
        Preferences p3 = new Preferences(3, 3, profPref, studPref);
        Preferences p4 = new Preferences(3, 3, profPref, studPref);
        Preferences p5 = new Preferences(3, 3, profPref, studPref);
        Preferences p6 = new Preferences(3, 3, profPref, studPref);

        ArrayList<Integer> pairing = new ArrayList<Integer>();

        pairing.add(0);
        pairing.add(1);
        pairing.add(2);

        printList(pairing);
        boolean b1 = checkStability(pairing, p1);
        if (b1) System.out.println("You are a god");
        else System.out.println("no one will ever love you");


        ArrayList<Integer> pairing2 = new ArrayList<Integer>();
        pairing2 = getNextPerm(pairing);
        printList(pairing2);
        boolean b2 = checkStability(pairing2, p2);
        if (!b2) System.out.println("You are a god");
        else System.out.println("no one will ever love you");



        ArrayList<Integer> pairing3 = new ArrayList<Integer>();
        pairing3 = getNextPerm(pairing2);
        printList(pairing3);
        boolean b3 = checkStability(pairing3, p3);
        if (!b3) System.out.println("You are a god");
        else System.out.println("no one will ever love you");



        ArrayList<Integer> pairing4= new ArrayList<Integer>();
        pairing4 = getNextPerm(pairing3);
        printList(pairing4);
        boolean b4= checkStability(pairing4, p4);
        if (!b4) System.out.println("You are a god");
        else System.out.println("no one will ever love you");


        ArrayList<Integer> pairing5= new ArrayList<Integer>();
        pairing5 = getNextPerm(pairing4);
        printList(pairing5);
        boolean b5= checkStability(pairing5, p5);
        if (!b5) System.out.println("You are a god");
        else System.out.println("no one will ever love you");



        ArrayList<Integer> pairing6= new ArrayList<Integer>();
        pairing6 = getNextPerm(pairing5);
        printList(pairing6);
        boolean b6= checkStability(pairing6, p6);
        if (!b6) System.out.println("You are a god");
        else System.out.println("no one will ever love you");


    }


    public static void testStableMatchBruteForce(){
        ArrayList<Integer> prof1Pref = new ArrayList<Integer>();
        prof1Pref.add(3);
        prof1Pref.add(2);
        prof1Pref.add(1);

        ArrayList<Integer> prof2Pref = new ArrayList<Integer>();
        prof2Pref.add(3);
        prof2Pref.add(2);
        prof2Pref.add(1);

        ArrayList<Integer> prof3Pref = new ArrayList<Integer>();
        prof3Pref.add(3);
        prof3Pref.add(2);
        prof3Pref.add(1);

        ArrayList<Integer> stud1Pref = new ArrayList<Integer>();
        stud1Pref.add(3);
        stud1Pref.add(2);
        stud1Pref.add(1);

        ArrayList<Integer> stud2Pref = new ArrayList<Integer>();
        stud2Pref.add(3);
        stud2Pref.add(2);
        stud2Pref.add(1);

        ArrayList<Integer> stud3Pref = new ArrayList<Integer>();
        stud3Pref.add(3);
        stud3Pref.add(2);
        stud3Pref.add(1);

        ArrayList<ArrayList<Integer>> profPref = new ArrayList<ArrayList<Integer>>();
        profPref.add(prof1Pref);
        profPref.add(prof2Pref);
        profPref.add(prof3Pref);

        ArrayList<ArrayList<Integer>> studPref = new ArrayList<ArrayList<Integer>>();
        studPref.add(stud1Pref);
        studPref.add(stud2Pref);
        studPref.add(stud3Pref);


        Preferences preferences = new Preferences(3, 3, profPref, studPref);
        ArrayList<Integer> pairing = new ArrayList<Integer>();

        pairing = stableMatchBruteForce(preferences);

        System.out.print("Pairing is: " );

        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        pairing = stableMatchGaleShapley(preferences);
        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("GS pairing is stable");
        }

        else{
            System.out.println("GS pairing is not stable");
        }



    }

    public static void testStableMatchBruteForce2(){
        ArrayList<Integer> prof1Pref = new ArrayList<Integer>();
        prof1Pref.add(1);
        prof1Pref.add(2);
        prof1Pref.add(3);

        ArrayList<Integer> prof2Pref = new ArrayList<Integer>();
        prof2Pref.add(1);
        prof2Pref.add(2);
        prof2Pref.add(3);

        ArrayList<Integer> prof3Pref = new ArrayList<Integer>();
        prof3Pref.add(1);
        prof3Pref.add(2);
        prof3Pref.add(3);

        ArrayList<Integer> stud1Pref = new ArrayList<Integer>();
        stud1Pref.add(1);
        stud1Pref.add(2);
        stud1Pref.add(3);

        ArrayList<Integer> stud2Pref = new ArrayList<Integer>();
        stud2Pref.add(1);
        stud2Pref.add(2);
        stud2Pref.add(3);

        ArrayList<Integer> stud3Pref = new ArrayList<Integer>();
        stud3Pref.add(1);
        stud3Pref.add(2);
        stud3Pref.add(3);

        ArrayList<ArrayList<Integer>> profPref = new ArrayList<ArrayList<Integer>>();
        profPref.add(prof1Pref);
        profPref.add(prof2Pref);
        profPref.add(prof3Pref);

        ArrayList<ArrayList<Integer>> studPref = new ArrayList<ArrayList<Integer>>();
        studPref.add(stud1Pref);
        studPref.add(stud2Pref);
        studPref.add(stud3Pref);


        Preferences preferences = new Preferences(3, 3, profPref, studPref);
        ArrayList<Integer> pairing = new ArrayList<Integer>();

        pairing = stableMatchBruteForce(preferences);

        System.out.print("Pairing is: " );

        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        pairing = stableMatchGaleShapley(preferences);
        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("GS pairing is stable");
        }

        else{
            System.out.println("GS pairing is not stable");
        }


    }

    public static void printList(ArrayList<Integer> list){
        System.out.print("[");
        for (int i = 0; i<list.size(); i++){
            System.out.print(" " + list.get(i) + " ");
        }
        System.out.println("]");
    }


    public static void testGSGivenInputs(){
    /*
        4 4
        4 3 1 2
        2 1 3 4
        1 3 4 2
        4 3 1 2
        3 2 4 1
        2 3 1 4
        3 1 2 4
        3 2 4 1
    */

        ArrayList<Integer> prof1Pref = new ArrayList<Integer>();
        prof1Pref.add(4);
        prof1Pref.add(3);
        prof1Pref.add(1);
        prof1Pref.add(2);

        ArrayList<Integer> prof2Pref = new ArrayList<Integer>();
        prof2Pref.add(2);
        prof2Pref.add(1);
        prof2Pref.add(3);
        prof2Pref.add(4);

        ArrayList<Integer> prof3Pref = new ArrayList<Integer>();
        prof3Pref.add(1);
        prof3Pref.add(3);
        prof3Pref.add(4);
        prof3Pref.add(2);

        ArrayList<Integer> prof4Pref = new ArrayList<Integer>();
        prof4Pref.add(4);
        prof4Pref.add(3);
        prof4Pref.add(2);
        prof4Pref.add(1);


        /*
        3 2 4 1
        2 3 1 4
        3 1 2 4
        3 2 4 1

         */

        ArrayList<Integer> stud1Pref = new ArrayList<Integer>();
        stud1Pref.add(3);
        stud1Pref.add(2);
        stud1Pref.add(4);
        stud1Pref.add(1);

        ArrayList<Integer> stud2Pref = new ArrayList<Integer>();
        stud2Pref.add(2);
        stud2Pref.add(3);
        stud2Pref.add(1);
        stud2Pref.add(4);

        ArrayList<Integer> stud3Pref = new ArrayList<Integer>();
        stud3Pref.add(3);
        stud3Pref.add(1);
        stud3Pref.add(2);
        stud3Pref.add(4);

        ArrayList<Integer> stud4Pref = new ArrayList<Integer>();
        stud4Pref.add(3);
        stud4Pref.add(2);
        stud4Pref.add(4);
        stud4Pref.add(1);


        ArrayList<ArrayList<Integer>> profPref = new ArrayList<ArrayList<Integer>>();
        profPref.add(prof1Pref);
        profPref.add(prof2Pref);
        profPref.add(prof3Pref);
        profPref.add(prof4Pref);

        ArrayList<ArrayList<Integer>> studPref = new ArrayList<ArrayList<Integer>>();
        studPref.add(stud1Pref);
        studPref.add(stud2Pref);
        studPref.add(stud3Pref);
        studPref.add(stud4Pref);


        Preferences preferences = new Preferences(4, 4, profPref, studPref);


        System.out.println("GS Pairing for 4x4: ");
        ArrayList<Integer> pairing = stableMatchGaleShapley(preferences);
        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        System.out.println("Brute Force for 4x4: ");
        pairing = stableMatchBruteForce(preferences);
        printList(pairing);

        if (checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }



    }

    public static Preferences preProcessIndices(Preferences preferences){

        int numProfs = preferences.getNumberOfProfessors();

        Preferences preferences2 = new Preferences(numProfs, numProfs, preferences.getProfessors_preference(), preferences.getStudents_preference());

        boolean needsFix = true;
        for (int ii=0; ii<numProfs; ii++){
            for (int jj = 0; jj<numProfs; jj++){
                int stud = preferences.getProfessors_preference().get(ii).get(jj);
                if (stud == 0) needsFix = false;
            }
        }


        //set everything to 0 indexing
        if (needsFix) {
            for (int ii = 0; ii < numProfs; ii++) {
                for (int jj = 0; jj < numProfs; jj++) {
                    int stud = preferences.getProfessors_preference().get(ii).get(jj);
                    preferences2.getProfessors_preference().get(ii).set(jj, stud-1);
                }
            }

            //set everything to 0 indexing
            for (int ii = 0; ii < numProfs; ii++) {
                for (int jj = 0; jj < numProfs; jj++) {
                    int prof = preferences.getStudents_preference().get(ii).get(jj);
                    preferences2.getStudents_preference().get(ii).set(jj, prof-1);
                }
            }
        }

        return preferences2;

    }


}
