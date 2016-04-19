package main;

import java.util.ArrayList;
import java.util.Collections;

public class Solver3_GRASP extends MMDSolver {

    /**
     * Constructive phase (?)
     * 
     * @return A candidate to add into the LRC(?).
     */
    private ArrayList<Boolean> constructivePhase(MaxMeanDispersionProblem prob) {
        ArrayList<Boolean> solution = new ArrayList<Boolean>();

        for (int i = 0; i < prob.size; i++)
            solution.add(random.nextBoolean());

        return solution;
    }

    private int lrcSize = 3;

    @Override
    public ArrayList<Boolean> solve(MaxMeanDispersionProblem prob) {

        ArrayList<ArrayList<Boolean>> lrc = new ArrayList<ArrayList<Boolean>>(lrcSize);

        // Populate the LRC with randomly generated solutions (constructive
        // phase).
        for (int i = 0; i < lrcSize; i++)
            lrc.set(i, constructivePhase(prob));

        // Choose a single candidate.
        ArrayList<Boolean> solution = lrc.get(random.nextInt(lrcSize));

        // Local search.
        // Look for best neighbor of solution and replace if it improves the
        // solution, until a local maximum is reached.
        ArrayList<Boolean> bestNeighbor = getBestNeighbor(solution, prob);

        while (prob.checkSolutionValue(bestNeighbor) > prob.checkSolutionValue(solution)) {
            solution = bestNeighbor;
            bestNeighbor = getBestNeighbor(solution, prob);
        }

        return solution;
    }

    // PENDING: It only does a comparison loop over getNeighbors()'s output.
    // Might be a code smell/ bad practice.
    /**
     * @param solution
     * @param problem
     * @return
     */
    private ArrayList<Boolean> getBestNeighbor(ArrayList<Boolean> solution, MaxMeanDispersionProblem problem) {

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
     * @return The neighboring solutions of @solution.
     */
    private ArrayList<ArrayList<Boolean>> getNeighbors(ArrayList<Boolean> solution) {

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
