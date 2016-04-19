package main;

import java.util.ArrayList;

public class MaxMeanDispersionProblem {

    private float[][] distanceMatrix;
    public int size;    // PENDING, HACK? public
    
    public MaxMeanDispersionProblem(float[][] distanceMatrix){
        this.distanceMatrix = distanceMatrix;
        this.size = distanceMatrix.length;  // Infer size.
    }

    public float getDistanceMatrix(int i, int j) {
        return distanceMatrix[i][j];
    }

    public void setDistanceMatrix(int i, int j, float val) {
        this.distanceMatrix[i][j] = val;
    }
    
    public String toString(){
        String str = "";
        for (int i = 0; i < distanceMatrix.length; i++){
            for (int j = 0; j < distanceMatrix[0].length; j++){
                str += distanceMatrix[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
    
    /**
     * Given a solution for this problem, return the mean dispersion value.
     * (Which is the optimality value of said solution).
     * @param solution A list of nodes in the solution.
     * @return  The mean dispersion that solution returns.
     */
    public float checkSolutionValue(ArrayList<Boolean> solution){
        if (solution.size() != size){
            System.err.println("Solution and problem are not the same size!");
            return 0f;
        }
        ArrayList<Integer> nodesInSol = new ArrayList<Integer>();
        for (int i = 0; i < size; i++){
            if (solution.get(i) == true) nodesInSol.add(i);
        }
        
        float solValue = 0f;
        
        // Perform the summation of the values of the vertices between all nodes in the solution.
        for (int i = 0; i < nodesInSol.size(); i++){
            for (int j = i+1; j < nodesInSol.size(); j++){
                solValue += distanceMatrix[i][j];
            }
        }
        solValue /= nodesInSol.size();
        
        return solValue;
    }
}
