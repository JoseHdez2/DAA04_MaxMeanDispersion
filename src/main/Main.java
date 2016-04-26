package main;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        MMDTester tester = new MMDTester(2);
        tester.testAllSolvers(2, 3);
    }
}
