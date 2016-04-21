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
        
        int i = new Random().nextInt(prob.size);
        
        solution.set(i, true); // We choose a random initial node.
        
        getBestNeighbor(solution, prob);
        
        return solution;
    }

}
