package main;

import java.util.ArrayList;
import java.util.HashMap;

import solver.MMDSolver;

public class MaxMeanDispersionProblem {

    public String name;
    private float[][] distanceMatrix;
    public int size; // PENDING, HACK? public
    
    // for toString()
    private HashMap<FormatType, String> formatHash;

    String formatA = "%s (%s)";
    String formatB = "Solution: %s%nMean dispersion: %s";
    // Problema | n | ejecucion | md | CPU
    String formatC = "%s | %d | %d | %f s";
    
    public MaxMeanDispersionProblem(float[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.size = distanceMatrix.length; // Infer size.
        
        formatHash = new HashMap<FormatType, String>();
        formatHash.put(FormatType.SIMPLE, formatA);
        formatHash.put(FormatType.VERBOSE, formatB);
        formatHash.put(FormatType.TABLE, formatC);
        
        name = "Untitled";
    }
    
    public MaxMeanDispersionProblem(String name, float[][] distanceMatrix) {
        this(distanceMatrix);
        this.name = name;
    }
    
    public MaxMeanDispersionProblem returnSmallerProblem(int n) throws Exception{
        if (n > size) throw new Exception("Can't return problem bigger than myself");
        float[][] distanceMatrix = new float[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                distanceMatrix[i][j] = this.distanceMatrix[i][j];
            }
        }
        return new MaxMeanDispersionProblem(this.name, distanceMatrix);
    }

    public float getDistanceMatrix(int i, int j) {
        return distanceMatrix[i][j];
    }

    public void setDistanceMatrix(int i, int j, float val) {
        this.distanceMatrix[i][j] = val;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[0].length; j++) {
                str += distanceMatrix[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * Given a solution for this problem, return the mean dispersion value.
     * (Which is the optimality value of said solution).
     * 
     * @param solution
     *            A list of nodes in the solution.
     * @return The mean dispersion that solution returns.
     */
    public float checkSolutionValue(ArrayList<Boolean> solution) {
        if (solution.size() != size) {
            System.err.println("Solution and problem are not the same size!");
            return 0f;
        }

        ArrayList<Integer> nodesInSol = MaxMeanDispersionSolution.nodesInSolution(solution);
        if (nodesInSol.size() == 1 || nodesInSol.isEmpty()) return 0f;

        float solValue = 0f;

//        System.out.println("begin");
//        System.out.println(solValue);
//        System.out.println(nodesInSol);
        // Perform the summation of the values of the vertices between all nodes
        // in the solution.
        for (int i = 0; i < nodesInSol.size() - 1; i++) {
            for (int j = i + 1; j < nodesInSol.size(); j++) {
//                System.out.format("Adding %d to %d (%f)%n", nodesInSol.get(i), nodesInSol.get(j), 
//                        distanceMatrix[nodesInSol.get(i)][nodesInSol.get(j)]);
                solValue += distanceMatrix[nodesInSol.get(i)][nodesInSol.get(j)];
            }
        }
//        System.out.format("solValue(%f) / nodes(%d) = %f%n", solValue, nodesInSol.size(), solValue / nodesInSol.size());
        solValue /= nodesInSol.size();

        return solValue;
    }
    
    public enum FormatType {
        SIMPLE,
        VERBOSE,
        TABLE,
    }
    
    // Problema | n | ejecucion | md | CPU
    public String toString(MMDSolver solver, ArrayList<Boolean> solution, FormatType format) {
        
        String solutionStr = MaxMeanDispersionSolution.toString(solution);
        
        // Problema | n | ejecucion | md | CPU
        switch (format){
        case SIMPLE:
            return String.format("%s (%s)", solutionStr, solution);
        case VERBOSE:
            return String.format("Solution: %s%nMean dispersion: %s", solutionStr, solution);
        case TABLE:
            String probName = this.name;
            Integer n = this.size;
            Integer execNum = solver.getExecNum();
            Float md = this.checkSolutionValue(solution);
            Double cpuTime = solver.getTimer().getTimeCountAsSeconds();
            
            return String.format("%s | %d | %d | %f | %f s", probName, n, execNum, md, cpuTime);
        }
        
        return String.format(formatHash.get(format), solutionStr, 
                this.checkSolutionValue(solution));
    }
    
    // Overengineering...
    
    private static FormatType defaultFormat = FormatType.SIMPLE;
    
    public String toString(MMDSolver solver, ArrayList<Boolean> solution) {
        return this.toString(solver, solution, defaultFormat);
    }
    
    public String toString(ArrayList<Boolean> solution, FormatType format) {
        return this.toString(null, solution, format);
    }
    
    public String toString(ArrayList<Boolean> solution) {
        return this.toString(null, solution, defaultFormat);
    }
}
