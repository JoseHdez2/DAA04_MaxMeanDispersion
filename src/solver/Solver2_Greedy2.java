package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.MaxMeanDispersionProblem;

public class Solver2_Greedy2 extends Solver0 {

    @Override
    public String catchName() {
        return "Greedy 2";
    }
    
    @Override
    public ArrayList<Boolean> doSolve(MaxMeanDispersionProblem prob) {
        
        // We begin with all nodes in the solution.
        ArrayList<Boolean> solution = 
                new ArrayList<Boolean>(Collections.nCopies(prob.size, true));
        
        // We find neighboring solutions by substraction until we reach a local maximum.
        solution = optimizeByLocalSearch(solution, prob, NeighborMode.ONLY_SUBTRACTIVE);
        
        return solution;
    }

}
