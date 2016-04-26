package solver;

import java.util.ArrayList;
import java.util.Collections;

import main.Permutation;

public class Solver4_VNC extends Solver3_GRASP {

    int k;
    
    public Solver4_VNC(int k){
        this.k = k;
    }
    
    @Override
    public String catchName() {
        return "VNC";
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected ArrayList<ArrayList<Boolean>> getNeighbors(ArrayList<Boolean> solution, NeighborMode mode){
        
        // PENDING validate: k <= solution.size
        
        String str = "";
        for (int i = 0; i < k; i++) str += "1";             // Add K number of 1's.
        while (str.length() < solution.size()) str += "0";  // Fill the rest with 0's.
        
        ArrayList<String> flipMasks = new ArrayList<String>(new Permutation(str).perms);
        
        ArrayList<ArrayList<Boolean>> neighbors = new ArrayList<ArrayList<Boolean>>();

        // Random lazy method:
        ArrayList<Boolean> randomNeighbor = 
                applyFlipMask(solution, flipMasks.get(random.nextInt(flipMasks.size())));
        neighbors.add(randomNeighbor);
        // Exhaustive method.
        /*
        // One neighbor for each "flip mask".
        for (String flipMask : flipMasks){
            neighbors.add(applyFlipMask(solution, flipMask));
        }*/
        

        return neighbors;
    }
    
    ArrayList<Boolean> applyFlipMask(ArrayList<Boolean> originalSolution, String flipMask){
        ArrayList<Boolean> newSolution = (ArrayList<Boolean>) originalSolution.clone();
        for (int i = 0; i < newSolution.size(); i++){
            if (flipMask.charAt(i) == '1') newSolution.set(i, !originalSolution.get(i));
        }
        return newSolution;
    }
}
