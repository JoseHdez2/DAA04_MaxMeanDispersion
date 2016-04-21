package main;

import java.util.ArrayList;

public class Solver4_VNC extends Solver3_GRASP {

    int k;
    
    Solver4_VNC(int k){
        this.k = k;
    }
    
    @Override
    public String catchName() {
        return "VNC";
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected ArrayList<ArrayList<Boolean>> getNeighbors(ArrayList<Boolean> solution){
        
        // PENDING validate: k <= solution.size
        
        String str = "";
        for (int i = 0; i < k; i++) str += "1";             // Add K number of 1's.
        while (str.length() < solution.size()) str += "0";  // Fill the rest with 0's.
        
        ArrayList<String> flipMasks = new ArrayList<String>(new Permutation(str).perms);
        
        ArrayList<ArrayList<Boolean>> neighbors = new ArrayList<ArrayList<Boolean>>();

        // One neighbor for each "flip mask".
        for (String flipMask : flipMasks){
            ArrayList<Boolean> aux = (ArrayList<Boolean>) solution.clone();
            for (int i = 0; i < aux.size(); i++){
                if (flipMask.charAt(i) == '1') aux.set(i, !solution.get(i));
            }
            neighbors.add(aux);
        }

        return neighbors;
    }
}
