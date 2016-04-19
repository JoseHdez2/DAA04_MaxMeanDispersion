package main;

import java.util.HashSet;

public class Permutation {
    
    public HashSet<String> perms = new HashSet<>();
    
    Permutation(String str){
        permutate(str);
    }
    
    /**
     * @author http://stackoverflow.com/a/4240323/3399416
     */
    public void permutate(String str) { 
        perms.clear();
        permutation("", str); 
    }

    /**
     * @author http://stackoverflow.com/a/4240323/3399416
     */
    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) perms.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
}
