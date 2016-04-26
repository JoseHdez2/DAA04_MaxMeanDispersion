package solver;

import java.util.ArrayList;

import main.MaxMeanDispersionProblem;
import main.MyTimer;

public interface MMDSolver {
    
    abstract public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob) throws Exception;
    
    /**
     * And you were?
     */
    abstract public String catchName();
    
    /**
     * Get number of executions this solver has done (be it on the same or different problems).
     */
    abstract public int getExecNum();
    
    /**
     * Reset number of executions to 0.
     */
    abstract public void resetExecNum();
    
    // HACK time is scarce
    abstract public MyTimer getTimer();
}
