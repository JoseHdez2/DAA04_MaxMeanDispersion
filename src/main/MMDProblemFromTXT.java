package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author jose
 *
 *         Creates MaxMeanDispersionProblem from a TXT file.
 */
public abstract class MMDProblemFromTXT {

    public static MaxMeanDispersionProblem doIt(File file) throws Exception {
        String content = new Scanner(file).useDelimiter("\\Z").next();
        return parseTXT(file.getName(), content);
    }

    private static MaxMeanDispersionProblem parseTXT(String title, String content) {
        ArrayList<String> lines = prepareLines(content);

        int size = Integer.valueOf(lines.get(0).split(SEPARATOR)[0]);
        lines.remove(0);

        float[][] distMat = new float[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                String[] tokenLine = lines.get(0).split(SEPARATOR);
                distMat[i][j] = Float.valueOf(tokenLine[0]);
                distMat[j][i] = Float.valueOf(tokenLine[0]);
                lines.remove(0); // Consume the line.
            }
        }

        /*
         * // Infer size of matrix from number of tokens in first line. int size
         * = lines.get(0).split(SEPARATOR).length;
         * 
         * float[][] distMat = new float[size][size];
         * 
         * for (int i = 0; i < lines.size(); i++){ String[] tokenLine =
         * lines.get(i).split(SEPARATOR); for (int j = 0; j <
         * lines.get(i).length(); j++){ distMat[i][j] =
         * Float.valueOf(tokenLine[j]); } }
         */

        return new MaxMeanDispersionProblem(title, distMat);
    }

    // Token separator, for visibly reducing whitespace, etc. TODO: weird
    // phrasing
    public static String SEPARATOR = "#";

    private static Predicate<String> emptyLine() {
        return p -> p.isEmpty();
    }

    /**
     * Prepare lines for parsing: remove comments and empty lines, and reduce
     * whitespace.
     * 
     * @param programFileContent
     * @return
     */
    private static ArrayList<String> prepareLines(String programFileContent) {
        String[] lines = programFileContent.split(System.getProperty("line.separator")); // http://stackoverflow.com/a/1096633/3399416

        for (int i = 0; i < lines.length; i++) {
            // Remove comments. Only keep what's before the ';' (comment
            // separator).
            lines[i] = lines[i].split(";")[0];
            // Replace whitespace with separators.
            lines[i] = lines[i].trim().replaceAll("\\s+", SEPARATOR);
        }
        ArrayList<String> lines2 = new ArrayList<String>(Arrays.asList(lines));

        // Only keep the lines with code.
        lines2.removeIf(emptyLine());

        System.out.println(lines2);
        return lines2;
    }
}
