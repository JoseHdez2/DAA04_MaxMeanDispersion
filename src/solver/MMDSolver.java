package solver;

import java.util.ArrayList;
import java.util.Random;

import main.MaxMeanDispersionProblem;

public abstract class MMDSolver {
    
    Random random = new Random();
    
    abstract public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob);
    
    /**
     * And you were?
     */
    abstract public String catchName();
}
