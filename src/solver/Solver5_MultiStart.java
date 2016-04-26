package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.MaxMeanDispersionProblem;
import main.MaxMeanDispersionSolution;
import solver.Solver0.NeighborMode;

public class Solver5_MultiStart extends Solver0 {
    
    @Override
    public String catchName() {
        return "GRASP";
    }
    
    /**
     * Constructive phase (?)
     * 
     * @return A candidate to add into the LRC(?).
     */
    private ArrayList<Boolean> constructivePhase(MaxMeanDispersionProblem prob) {
        ArrayList<Boolean> solution = new ArrayList<Boolean>();

        // Discard random solutions with less than 2 nodes in them.
        while(MaxMeanDispersionSolution.nodesInSolution(solution).size() < 2){
            solution = new ArrayList<Boolean>();
            for (int i = 0; i < prob.size; i++)
                solution.add(random.nextBoolean());
        }

        return solution;
    }

    private int lrcSize = 3;

    @Override
    public ArrayList<Boolean> doSolve(MaxMeanDispersionProblem prob) {

        ArrayList<ArrayList<Boolean>> lrc = new ArrayList<ArrayList<Boolean>>();
        // Populate the LRC with randomly generated solutions 
        // (constructive phase).
        for (int i = 0; i < lrcSize; i++)
            lrc.add(constructivePhase(prob));

        // Choose a single candidate.
        ArrayList<Boolean> solution = lrc.get(random.nextInt(lrcSize));

        // Local search.
        solution = optimizeByLocalSearch(solution, prob, NeighborMode.UNRESTRAINED);

        return solution;
    }
}
