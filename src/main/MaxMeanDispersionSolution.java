package main;

import java.util.ArrayList;

public class MaxMeanDispersionSolution extends ArrayList<Boolean>{
    MaxMeanDispersionSolution(){
        super();
    }
    
    MaxMeanDispersionSolution(ArrayList<Boolean> solution){
        super(solution);
    }

    public static ArrayList<Integer> nodesInSolution(ArrayList<Boolean> solution){
        ArrayList<Integer> nodesInSol = new ArrayList<Integer>();
        
        for (int i = 0; i < solution.size(); i++){
            if (solution.get(i) == true) nodesInSol.add(i);
        }
        
        return nodesInSol;
    }
}
