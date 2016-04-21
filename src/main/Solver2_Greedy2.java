package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Solver2_Greedy2 extends MMDSolver {

    @Override
    public String catchName() {
        return "Greedy 2";
    }
    
    @Override
    public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob) {
        
        ArrayList<Boolean> solution = 
                new ArrayList<Boolean>(Collections.nCopies(prob.size, false));
        
        int i = new Random().nextInt(prob.size);
        
        solution.set(i, true); // We choose a random initial node.
        
        return solution;
    }

}
