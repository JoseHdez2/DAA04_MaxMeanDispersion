package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.MaxMeanDispersionProblem;

public class Solver1_Greedy extends Solver0 {

    @Override
    public String catchName() {
        return "Greedy";
    }
    
    @Override
    public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob) {
        
        ArrayList<Boolean> solution = 
                new ArrayList<Boolean>(Collections.nCopies(prob.size, false));
       
        // We start with the best initial pair.
        solution = bestInitialPair(prob);
        
        // We keep adding all possibilities until we reach a local maximum.
        solution = optimizeByLocalSearch(solution, prob, NeighborMode.ONLY_ADDITIVE);
        
        return solution;
    }

}
