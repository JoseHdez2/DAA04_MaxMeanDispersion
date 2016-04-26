package main;

import java.util.ArrayList;

public class MaxMeanDispersionSolution extends ArrayList<Boolean> {
    MaxMeanDispersionSolution() {
        super();
    }

    MaxMeanDispersionSolution(ArrayList<Boolean> solution) {
        super(solution);
    }

    /*
     * We don't sacrifice modularity/reusability since we can still use with
     * unwrapped objects if we want to (right?).
     * A revolution in paper-thin wrappers!
     */

    public ArrayList<Integer> nodesInSolution() {
        return nodesInSolution(this); // Now you're thinking with portals.
    }
    
    public String toString(){
        return toString(this);
    }

    public static ArrayList<Integer> nodesInSolution(ArrayList<Boolean> solution) {
        ArrayList<Integer> nodesInSol = new ArrayList<Integer>();

        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == true)
                nodesInSol.add(i);
        }

        return nodesInSol;
    }
    
    public static String toString(ArrayList<Boolean> solution){
        return nodesInSolution(solution).toString();
    }   
}
