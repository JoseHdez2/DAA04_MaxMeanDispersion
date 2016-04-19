package main;

import java.util.ArrayList;
import java.util.Random;

public abstract class MMDSolver {
    
    /*
     * PENDING:
     * This class would be an interface, if it weren't for this pesky little attribute!
     */
    Random random = new Random();
    
    abstract ArrayList<Boolean> solve(MaxMeanDispersionProblem prob);
    
}
