package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import main.MaxMeanDispersionProblem.FormatType;
import solver.MMDSolver;
import solver.Solver1_Greedy;
import solver.Solver2_Greedy2;
import solver.Solver3_GRASP;
import solver.Solver4_VNS;

public class MMDTester {
    
    private ArrayList<MMDSolver> solvers;
    private ArrayList<MaxMeanDispersionProblem> problems;
    
    public MMDTester(int vnc_k) throws Exception{
        solvers = new ArrayList<MMDSolver>();
        solvers.add(new Solver1_Greedy());
        solvers.add(new Solver2_Greedy2());
        solvers.add(new Solver3_GRASP());
        solvers.add(new Solver4_VNS(vnc_k));
//        solvers.add(new Solver5_MultiStart());
        
        problems = new ArrayList<MaxMeanDispersionProblem>();
//        problems.add(MMDProblemFromTXT.doIt(new File("examples/test1.txt")));
        problems.add(MMDProblemFromTXT.doIt(new File("examples/max-mean-div-10.txt")));
        problems.add(MMDProblemFromTXT.doIt(new File("examples/max-mean-div-15.txt")));
        problems.add(MMDProblemFromTXT.doIt(new File("examples/max-mean-div-20.txt")));
        problems.add(MMDProblemFromTXT.doIt(new File("examples/max-mean-div-25.txt")));
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
    
    // Times to solve the same problem (useful for profiling non-deterministic algorithms).
    int CONFIRMATION_REPETITIONS = 3;
    
    public void testAllSolvers(int nMin, int nMax) throws Exception{
        String line = repeatString(n, "-");
        String head = repeatString(n/4, "#"); 
        // String line = Strings.repeat("ab",3);
        for (MMDSolver solver : solvers){
            System.out.format("%s%n%s %s %s%n%s%n",line,head,solver.catchName(),head,line);
            System.out.println("Problema | n | ejecucion | md | CPU");
            for (MaxMeanDispersionProblem problem : problems){

                ArrayList<Boolean> solution = solver.solve(problem);
                System.out.println(problem.toString(solver, solution, FormatType.TABLE));
                /*
                for (int n = nMin; n <= nMax; n++){
                    if (n > problem.size) break;
                    MaxMeanDispersionProblem smallerProblem = problem.returnSmallerProblem(n);
                    for (int i = 0; i < CONFIRMATION_REPETITIONS; i++){
                        ArrayList<Boolean> solution = solver.solve(smallerProblem);
                        System.out.println(problem.toString(solver, solution, FormatType.TABLE));
                    }
                    solver.resetExecNum();
                }*/
            }
        }
    }
}
