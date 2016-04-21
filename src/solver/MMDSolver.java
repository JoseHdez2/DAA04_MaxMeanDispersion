package solver;

import java.util.ArrayList;

import main.MaxMeanDispersionProblem;

public interface MMDSolver {
    
    abstract public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob);
    
    /**
     * And you were?
     */
    abstract public String catchName();
}
