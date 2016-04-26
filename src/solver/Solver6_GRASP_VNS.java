package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.MaxMeanDispersionProblem;
import main.MaxMeanDispersionSolution;
import solver.Solver0.NeighborMode;

public class Solver6_GRASP_VNS extends Solver0 {
    
    @Override
    public String catchName() {
        return "GRASP-VNS";
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
    
    private ArrayList<Boolean> actualConstructivePhase(MaxMeanDispersionProblem prob) {

        ArrayList<ArrayList<Boolean>> lrc = new ArrayList<ArrayList<Boolean>>();
        // Populate the LRC with randomly generated solutions 
        // (constructive phase).
        for (int i = 0; i < lrcSize; i++)
            lrc.add(constructivePhase(prob));

        // Choose a single candidate randomly.
        ArrayList<Boolean> solution = lrc.get(random.nextInt(lrcSize));
        
        return solution;
    }

    private int lrcSize = 2;

    private ArrayList<Boolean> addRandomNode(ArrayList<Boolean> solution){
        if (solution.size() == MaxMeanDispersionSolution.nodesInSolution(solution).size())
            return solution; // No more nodes can be added.
        boolean added = false;
        while(!added){
            int randInd = random.nextInt(solution.size());
            if(solution.get(randInd) == false){
                solution.set(randInd, true);
                added = true;
            }
        }
        return solution;
    }
    
    private ArrayList<Boolean> removeRandomNode(ArrayList<Boolean> solution){
        if (MaxMeanDispersionSolution.nodesInSolution(solution).size() == 0)
            return solution; // No more nodes can be removed.
        boolean removed = false;
        while(!removed){
            int randInd = random.nextInt(solution.size());
            if(solution.get(randInd) == true){
                solution.set(randInd, false);
                removed = true;
            }
        }
        return solution;
    }
    
    private ArrayList<Boolean> switchRandomNodes(ArrayList<Boolean> solution){
        solution = removeRandomNode(solution);
        solution = addRandomNode(solution);
        return solution;
    }
    
    private ArrayList<Boolean> NkS(int mode, ArrayList<Boolean> solution) throws Exception{
        switch(mode){
        case 0: return removeRandomNode(solution);
        case 1: return addRandomNode(solution);
        case 2: return switchRandomNodes(solution);
        default: throw new Exception("Invalid mode");
        }
    }
    
    @Override
    public ArrayList<Boolean> doSolve(MaxMeanDispersionProblem prob) throws Exception {

        ArrayList<Boolean> S = actualConstructivePhase(prob);
        ArrayList<Boolean> Sbest = new ArrayList<Boolean>(Collections.nCopies(prob.size, true));
        
        for (int k = 1; k == 4;){
            ArrayList<Boolean> S2 = NkS(random.nextInt(3), S);
            for (int i = 1; i == 3; i++){
                ArrayList<Boolean> S1 = NkS(random.nextInt(3), S);
                ArrayList<Boolean> Sloc = optimizeByLocalSearch(S1, prob, NeighborMode.UNRESTRAINED);
                if (prob.checkSolutionValue(Sloc) > prob.checkSolutionValue(S2)){
                    S2 = Sloc;
                }
            }
            if (prob.checkSolutionValue(S2) > prob.checkSolutionValue(S)){
                S = S2;
                k = 1;
            } else {
                k++;
            }
        }
        if ((prob.checkSolutionValue(S) > prob.checkSolutionValue(Sbest)))
            Sbest = S;
        return Sbest;
    }
}
