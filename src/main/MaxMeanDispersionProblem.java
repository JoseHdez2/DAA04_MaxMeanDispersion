package main;

public class MaxMeanDispersionProblem {

    private float[][] distanceMatrix;
    
    public MaxMeanDispersionProblem(float[][] distanceMatrix){
        this.distanceMatrix = distanceMatrix;
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
}
