package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.MaxMeanDispersionProblem;
import main.MaxMeanDispersionSolution;

/**
 * Utility class.
 * 
 * @author jose
 */
public abstract class Solver0 implements MMDSolver {

    boolean debug = true;
    Random random = new Random();

    /**
     * @param problem
     * @return Solution with initial pair on it.
     */
    protected ArrayList<Boolean> bestInitialPair(MaxMeanDispersionProblem problem){
        double longestDispersion = Double.MIN_VALUE;
        int longestI = 0, longestJ = 0;
        for (int i = 0; i < problem.size; i++){
            for (int j = 0; j < problem.size; j++){
                if (problem.getDistanceMatrix(i, j) > longestDispersion){
                    longestDispersion = problem.getDistanceMatrix(i, j);
                    longestI = i;
                    longestJ = j;
                }
            }
        }
        
        ArrayList<Boolean> solution = new ArrayList<Boolean>(Collections.nCopies(problem.size, false));
        solution.set(longestI, true);
        solution.set(longestJ, true);
        return solution;
    }
    
    // Enum passed to algorithms to determine their behavior regarding neighboring solutions.
    protected enum NeighborMode {
        ONLY_ADDITIVE,      // Ignore neighbors which subtract nodes from the solution.
        ONLY_SUBTRACTIVE,   // Ignore neighbors which add nodes to the solution.
        UNRESTRAINED,       // Don't ignore any neighbors.
    }
    
    /**
     * Local search.
     * Look for best neighbor of solution and replace if it improves the
     * solution, until a local maximum is reached.
     * @param solution
     * @param prob
     */
    protected ArrayList<Boolean> optimizeByLocalSearch(ArrayList<Boolean> solution, 
                                                      MaxMeanDispersionProblem prob,
                                                      NeighborMode mode){

        if (debug) System.out.format("Unoptimized solution: " + prob.toString(solution) + "%n");
        
        ArrayList<Boolean> bestNeighbor = getBestNeighbor(solution, prob, mode);
        if (debug) System.out.format("Best neighbor: " + prob.toString(bestNeighbor) + "%n");

        while (prob.checkSolutionValue(bestNeighbor) > prob.checkSolutionValue(solution)) {
            if (debug) System.out.println("Best neighbor is the new solution. Pivoting...");
            solution = bestNeighbor;
            bestNeighbor = getBestNeighbor(solution, prob, mode);
        }
        if (debug) System.out.format("Local maximum: " + prob.toString(solution) + "%n");
        
        return solution;
    }

    // PENDING: It only does a comparison loop over getNeighbors()'s output.
    // Might be a code smell/ bad practice.
    /**
     * @param solution
     * @param problem
     * @return The best neighbor to the given solution, as measured by the given
     *         problem. Note: the best neighbor may be worse than the current
     *         solution!
     */
    protected ArrayList<Boolean> getBestNeighbor(ArrayList<Boolean> solution,
                                                    MaxMeanDispersionProblem problem,
                                                    NeighborMode mode) {

        ArrayList<ArrayList<Boolean>> neighbors = getNeighbors(solution, mode);

        if (neighbors.size() == 0)
            return null;
        
        if (neighbors.size() == 1)
            return neighbors.get(0);

        ArrayList<Boolean> bestNeighbor = neighbors.get(0);
        float bestNeighborVal = problem.checkSolutionValue(bestNeighbor);

        for (int i = 1; i < neighbors.size(); i++) {
            if (problem.checkSolutionValue(neighbors.get(i)) > bestNeighborVal) {
                bestNeighbor = neighbors.get(i);
                bestNeighborVal = problem.checkSolutionValue(neighbors.get(i));
            }
        }

        return bestNeighbor;
    }

    /**
     * @param solution
     * @param onlyAdditive
     *            Ignore neighbors that remove a node from the solution.
     * @return The neighboring solutions of the given solution.
     */
    @SuppressWarnings("unchecked")
    protected ArrayList<ArrayList<Boolean>> getNeighbors(ArrayList<Boolean> solution, NeighborMode mode) {

        ArrayList<ArrayList<Boolean>> neighbors = new ArrayList<ArrayList<Boolean>>();

        // We "create" one neighbor for each bit we can flip.
        for (int i = 0; i < solution.size(); i++) {

            switch(mode){
            case ONLY_ADDITIVE:
                if (solution.get(i) == true) continue; // Would remove: ignore.
                break;
            case ONLY_SUBTRACTIVE:
                if (solution.get(i) == false) continue; // Would add: ignore.
                break;
            case UNRESTRAINED:
                break;
            }
            ArrayList<Boolean> aux = (ArrayList<Boolean>) solution.clone();
            aux.set(i, !solution.get(i)); // Flip the Nth bit of the
                                          // original
                                          // solution.
            neighbors.add(aux);
        }

        return neighbors;
    }
}
