package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import solver.MMDSolver;
import solver.Solver2_Greedy2;
import solver.Solver3_GRASP;
import solver.Solver4_VNC;

public class MMDTester {
    
    private ArrayList<MMDSolver> solvers;
    private MyTimer timer;
    
    public MMDTester(int vnc_k){
        solvers = new ArrayList<MMDSolver>();
        solvers.add(new Solver2_Greedy2());
        solvers.add(new Solver3_GRASP());
        solvers.add(new Solver4_VNC(vnc_k));
        // solvers.add(new Solver5_MultiStart());
        timer = new MyTimer();
    }
    
    int n = 20;
    
    /**
     * @param n     Times to repeat.
     * @param str   String to repeat.
     * @author http://stackoverflow.com/a/4903603/3399416
     * @return Repeated string.
     */
    private String repeatString(int n, String str){
        return new String(new char[n]).replace("\0", str);
    }
    
    public void test(MaxMeanDispersionProblem problem){
        String line = repeatString(n, "-");
        String head = repeatString(n/4, "#"); 
        // String line = Strings.repeat("ab",3);
        for (MMDSolver solver : solvers){
            System.out.format("%s%n%s %s %s%n%s%n",line,head,solver.catchName(),head,line);
            timer.restart();
            ArrayList<Boolean> solution = solver.solve(problem);
            timer.stop();
            System.out.format("Time: %2.4f s%n", timer.getTimeCountAsSeconds());
            System.out.format("Solution: %s%n", MaxMeanDispersionProblem.nodesInSolution(solution));
            System.out.format("Mean dispersion: %4.2f%n%n", problem.checkSolutionValue(solution));
        }
    }
}
