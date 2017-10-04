import java.util.ArrayList;

public class Main {


    public static void main(String [] args){
        //Assignment1 asn = new Assignment1();

        testNextPerm();
        testStability1();
        testStability2();
        testStability3();
        testStability4();
        testStability5();
        testStability6();
        testStableMatchBruteForce();
        testStableMatchBruteForce2();
        testGSGivenInputs();
        testGSGivenInputs2();
    }


    public static void testNextPerm(){
        ArrayList<Integer> testArray = new ArrayList<Integer>();
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        int index = 0;


        Assignment1.printList(testArray);
        while(Assignment1.nextPermutationExists(testArray)) {
            testArray = Assignment1.getNextPerm(testArray);
            Assignment1.printList(testArray);
        }
    }

    public static Preferences construct123(){
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

        return new Preferences(3, 3, profPref, studPref);



    }

    public static void testStability1() {

        Preferences p1 = construct123();


        ArrayList<Integer> pairing = new ArrayList<Integer>();

        pairing.add(0);
        pairing.add(1);
        pairing.add(2);

        Assignment1.printList(pairing);

        boolean b1 = Assignment1.checkStability(pairing, p1);
        if (b1) System.out.println("You are a god");
        else System.out.println("no one will ever love you");
    }



    public static void testStability2() {

        Preferences p2 = construct123();
        ArrayList<Integer> pairing = new ArrayList<Integer>();
        pairing.add(0);
        pairing.add(1);
        pairing.add(2);
        //Assignment1.printList(pairing);

        ArrayList<Integer> pairing2 = new ArrayList<Integer>();
        pairing2 = Assignment1.getNextPerm(pairing);
        Assignment1.printList(pairing2);
        boolean b2 = Assignment1.checkStability(pairing2, p2);
        if (!b2) System.out.println("You are a god");
        else System.out.println("no one will ever love you");

    }

    public static void testStability3() {

        Preferences p3 = construct123();
        ArrayList<Integer> pairing = new ArrayList<Integer>();
        pairing.add(0);
        pairing.add(1);
        pairing.add(2);
        //Assignment1.printList(pairing);

        ArrayList<Integer> pairing2;
        pairing2 = Assignment1.getNextPerm(pairing);
        ArrayList<Integer> pairing3;
        pairing3 = Assignment1.getNextPerm(pairing2);
        Assignment1.printList(pairing3);
        boolean b3 = Assignment1.checkStability(pairing3, p3);
        if (!b3) System.out.println("You are a god");
        else System.out.println("no one will ever love you");

    }


    public static void testStability4() {

        Preferences p4 = construct123();
        ArrayList<Integer> pairing = new ArrayList<Integer>();
        pairing.add(0);
        pairing.add(1);
        pairing.add(2);
        //Assignment1.printList(pairing);

        ArrayList<Integer> pairing2;
        pairing2 = Assignment1.getNextPerm(pairing);
        ArrayList<Integer> pairing3;
        pairing3 = Assignment1.getNextPerm(pairing2);
        ArrayList<Integer> pairing4;
        pairing4 = Assignment1.getNextPerm(pairing3);
        Assignment1.printList(pairing4);
        boolean b4 = Assignment1.checkStability(pairing4, p4);
        if (!b4) System.out.println("You are a god");
        else System.out.println("no one will ever love you");

    }



    public static void testStability5() {

        Preferences p5 = construct123();
        ArrayList<Integer> pairing = new ArrayList<Integer>();
        pairing.add(0);
        pairing.add(1);
        pairing.add(2);
        //Assignment1.printList(pairing);

        ArrayList<Integer> pairing2;
        pairing2 = Assignment1.getNextPerm(pairing);
        ArrayList<Integer> pairing3;
        pairing3 = Assignment1.getNextPerm(pairing2);
        ArrayList<Integer> pairing4;
        pairing4 = Assignment1.getNextPerm(pairing3);
        ArrayList<Integer> pairing5 = new ArrayList<Integer>();
        pairing5 = Assignment1.getNextPerm(pairing4);
        Assignment1.printList(pairing5);
        boolean b5 = Assignment1.checkStability(pairing5, p5);
        if (!b5) System.out.println("You are a god");
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


        pairing = Assignment1.stableMatchBruteForce(preferences);

        System.out.print("Pairing is: " );

        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        pairing = Assignment1.stableMatchGaleShapley(preferences);
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
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

        pairing = Assignment1.stableMatchBruteForce(preferences);

        System.out.print("Pairing is: " );

        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        pairing = Assignment1.stableMatchGaleShapley(preferences);
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("GS pairing is stable");
        }

        else{
            System.out.println("GS pairing is not stable");
        }


    }

    public static void testStability6() {

        Preferences p6 = construct123();
        ArrayList<Integer> pairing = new ArrayList<Integer>();
        pairing.add(0);
        pairing.add(1);
        pairing.add(2);
        //Assignment1.printList(pairing);

        ArrayList<Integer> pairing2;
        pairing2 = Assignment1.getNextPerm(pairing);
        ArrayList<Integer> pairing3;
        pairing3 = Assignment1.getNextPerm(pairing2);
        ArrayList<Integer> pairing4;
        pairing4 = Assignment1.getNextPerm(pairing3);

        ArrayList<Integer> pairing5;
        pairing5 = Assignment1.getNextPerm(pairing4);

        ArrayList<Integer> pairing6= new ArrayList<Integer>();
        pairing6 = Assignment1.getNextPerm(pairing5);
        Assignment1.printList(pairing6);
        boolean b6= Assignment1.checkStability(pairing6, p6);
        if (!b6) System.out.println("You are a god");
        else System.out.println("no one will ever love you");


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
        ArrayList<Integer> pairing = Assignment1.stableMatchGaleShapley(preferences);
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }


        System.out.println("Brute Force for 4x4: ");
        pairing = Assignment1.stableMatchBruteForce(preferences);
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }

        else{
            System.out.println("pairing is not stable");
        }

        System.out.println("Professor optimized costs is: ");
        ArrayList<Cost> profCost = Assignment1.stableMatchCosts(preferences);
        for (int ii=0; ii<profCost.size(); ii++){
            printCost(profCost.get(ii));
        }

        System.out.println("Student optimized costs is: ");
        ArrayList<Cost> studCost = Assignment1.stableMatchCostsStudent(preferences);
        for (int ii=0; ii<studCost.size(); ii++){
            printCost(studCost.get(ii));
        }


    }

    public static void printCost(Cost cost){
        System.out.print("[");
        System.out.print(" " + cost.getIndexOfProfessor() + " ");
        System.out.print(" " + cost.getIndexOfStudent() + " ");
        System.out.print(" " + cost.getCostToProfessor() + " ");
        System.out.print(" " + cost.getCostToStudent() + " ");
        System.out.println("]");

    }



    public static void testGSGivenInputs2(){


        /*
        7 7
3 4 2 1 6 7 5
6 4 2 3 5 1 7
6 3 5 7 2 4 1
1 6 3 2 4 7 5
1 6 5 3 4 7 2
1 7 3 4 5 6 2
5 6 2 4 3 7 1
4 5 3 7 2 6 1
5 6 4 7 3 2 1
1 6 5 4 3 7 2
3 5 6 7 2 4 1
1 7 6 4 3 5 2
6 3 7 5 2 4 1
1 7 4 2 6 5 3
         */
        ArrayList<ArrayList<Integer>> profPref = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> studPref = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> prof1Pref = new ArrayList<Integer>();
        //3 4 2 1 6 7 5
        prof1Pref.add(3); prof1Pref.add(4); prof1Pref.add(2); prof1Pref.add(1); prof1Pref.add(6);
        prof1Pref.add(7); prof1Pref.add(5);

        ArrayList<Integer> prof2Pref = new ArrayList<Integer>();
        //6 4 2 3 5 1 7
        prof2Pref.add(6); prof2Pref.add(4); prof2Pref.add(2); prof2Pref.add(3); prof2Pref.add(5);
        prof2Pref.add(1); prof2Pref.add(7);

        ArrayList<Integer> prof3Pref = new ArrayList<Integer>();
        //6 3 5 7 2 4 1
        prof3Pref.add(6); prof3Pref.add(3); prof3Pref.add(5); prof3Pref.add(7); prof3Pref.add(2);
        prof3Pref.add(4); prof3Pref.add(1);

        ArrayList<Integer> prof4Pref = new ArrayList<Integer>();
        //1 6 3 2 4 7 5
        prof4Pref.add(1); prof4Pref.add(6); prof4Pref.add(3); prof4Pref.add(2); prof4Pref.add(4);
        prof4Pref.add(7); prof4Pref.add(5);

        ArrayList<Integer> prof5Pref = new ArrayList<Integer>();
        //1 6 5 3 4 7 2
        prof5Pref.add(1); prof5Pref.add(6); prof5Pref.add(5); prof5Pref.add(3); prof5Pref.add(4);
        prof5Pref.add(7); prof5Pref.add(2);

        ArrayList<Integer> prof6Pref = new ArrayList<Integer>();
        //1 7 3 4 5 6 2
        prof6Pref.add(1); prof6Pref.add(7); prof6Pref.add(3); prof6Pref.add(4); prof6Pref.add(5);
        prof6Pref.add(6); prof6Pref.add(2);

        ArrayList<Integer> prof7Pref = new ArrayList<Integer>();
        //5 6 2 4 3 7 1
        prof7Pref.add(5); prof7Pref.add(6); prof7Pref.add(2); prof7Pref.add(4); prof7Pref.add(3);
        prof7Pref.add(7); prof7Pref.add(1);

        ArrayList<Integer> stud1Pref = new ArrayList<Integer>();
        //4 5 3 7 2 6 1
        stud1Pref.add(4); stud1Pref.add(5); stud1Pref.add(3); stud1Pref.add(7); stud1Pref.add(2);
        stud1Pref.add(6); stud1Pref.add(1);

        ArrayList<Integer> stud2Pref = new ArrayList<Integer>();
        //5 6 4 7 3 2 1
        stud2Pref.add(5); stud2Pref.add(6); stud2Pref.add(4); stud2Pref.add(7); stud2Pref.add(3);
        stud2Pref.add(2); stud2Pref.add(1);

        ArrayList<Integer> stud3Pref = new ArrayList<Integer>();
        //1 6 5 4 3 7 2
        stud3Pref.add(1); stud3Pref.add(6); stud3Pref.add(5); stud3Pref.add(4); stud3Pref.add(3);
        stud3Pref.add(7); stud3Pref.add(2);

        ArrayList<Integer> stud4Pref = new ArrayList<Integer>();
        //3 5 6 7 2 4 1
        stud4Pref.add(3); stud4Pref.add(5); stud4Pref.add(6); stud4Pref.add(7); stud4Pref.add(2);
        stud4Pref.add(4); stud4Pref.add(1);

        ArrayList<Integer> stud5Pref = new ArrayList<Integer>();
        //1 7 6 4 3 5 2
        stud5Pref.add(1); stud5Pref.add(7); stud5Pref.add(6); stud5Pref.add(4); stud5Pref.add(3);
        stud5Pref.add(5); stud5Pref.add(2);

        ArrayList<Integer> stud6Pref = new ArrayList<Integer>();
        //6 3 7 5 2 4 1
        stud6Pref.add(6); stud6Pref.add(3); stud6Pref.add(7); stud6Pref.add(5); stud6Pref.add(2);
        stud6Pref.add(4); stud6Pref.add(1);

        ArrayList<Integer> stud7Pref = new ArrayList<Integer>();
        //1 7 4 2 6 5 3
        stud7Pref.add(1); stud7Pref.add(7); stud7Pref.add(4); stud7Pref.add(2); stud7Pref.add(6);
        stud7Pref.add(5); stud7Pref.add(3);



        profPref.add(prof1Pref);
        profPref.add(prof2Pref);
        profPref.add(prof3Pref);
        profPref.add(prof4Pref);
        profPref.add(prof5Pref);
        profPref.add(prof6Pref);
        profPref.add(prof7Pref);

        studPref.add(stud1Pref);
        studPref.add(stud2Pref);
        studPref.add(stud3Pref);
        studPref.add(stud4Pref);
        studPref.add(stud5Pref);
        studPref.add(stud6Pref);
        studPref.add(stud7Pref);


        Preferences preferences = new Preferences(7,7, profPref, studPref);

        ArrayList<Integer> pairing = Assignment1.stableMatchGaleShapley(preferences);
        System.out.println("Gale Shapely for 7x7 is: ");
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }
        else{
            System.out.println("pairing is not stable");
        }

        pairing = Assignment1.stableMatchBruteForce(preferences);
        System.out.println("Brute force for 7x7 is: ");
        Assignment1.printList(pairing);

        if (Assignment1.checkStability(pairing, preferences)){
            System.out.println("pairing is stable");
        }
        else{
            System.out.println("pairing is not stable");
        }

        System.out.println("Professor optimized costs is: ");
        ArrayList<Cost> profCost = Assignment1.stableMatchCosts(preferences);
        for (int ii=0; ii<profCost.size(); ii++){
            printCost(profCost.get(ii));
        }

        System.out.println("Student optimized costs is: ");
        ArrayList<Cost> studCost = Assignment1.stableMatchCostsStudent(preferences);
        for (int ii=0; ii<studCost.size(); ii++){
            printCost(studCost.get(ii));
        }









    }

}
