package main;

import java.util.ArrayList;
import java.util.Random;

public abstract class MMDSolver {
    
    Random random = new Random();
    
    abstract ArrayList<Boolean> solve(MaxMeanDispersionProblem prob);
    
    /**
     * And you were?
     */
    abstract String catchName();
}
