package solver;

import java.util.ArrayList;
import java.util.Random;

import main.MaxMeanDispersionProblem;

/**
 * Utility class.
 * 
 * @author jose
 */
public abstract class Solver0 implements MMDSolver {

    boolean debug = true;
    Random random = new Random();

    /**
     * @param solution
     * @param problem
     * @return The best neighbor to the given solution, as measured by the given
     *         problem.
     */
    protected ArrayList<Boolean> getBestAdditiveNeighbor(ArrayList<Boolean> solution, MaxMeanDispersionProblem problem) {

        ArrayList<ArrayList<Boolean>> neighbors = getNeighbors(solution);

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
    
    // PENDING: It only does a comparison loop over getNeighbors()'s output.
    // Might be a code smell/ bad practice.
    /**
     * @param solution
     * @param problem
     * @return The best neighbor to the given solution, as measured by the given
     *         problem.
     *         Note: the best neighbor may be worse than the current solution!
     */
    protected ArrayList<Boolean> getBestNeighbor(ArrayList<Boolean> solution, MaxMeanDispersionProblem problem) {

        ArrayList<ArrayList<Boolean>> neighbors = getNeighbors(solution);

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
     * @return The neighboring solutions of the given solution.
     */
    @SuppressWarnings("unchecked")
    protected ArrayList<ArrayList<Boolean>> getNeighbors(ArrayList<Boolean> solution) {

        ArrayList<ArrayList<Boolean>> neighbors = new ArrayList<ArrayList<Boolean>>();

        // One neighbor for each bit we can flip.
        for (int i = 0; i < solution.size(); i++) {
            ArrayList<Boolean> aux = (ArrayList<Boolean>) solution.clone();
            aux.set(i, !solution.get(i)); // Flip the Nth bit of the original
                                          // solution.
            neighbors.add(aux);
        }

        return neighbors;
    }
}
