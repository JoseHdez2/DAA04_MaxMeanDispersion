package main;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        MaxMeanDispersionProblem prob = null;
        try {
            prob = MMDProblemFromTXT.doIt(new File("examples/test1.txt"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(prob.toString());
        System.out.println("funciona");
    }
}
