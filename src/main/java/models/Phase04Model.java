package models;

public class Phase04Model {

    boolean[][] workerWorkstationSkillMatrix; // S_hj - Boolean
    boolean[][] workerTaskSkillMatrix; // S_hi - Boolean
    int[][] workerWorkstationSkillMatrixInt; // S_hj - Integer
    int[][] workerTaskSkillMatrixInt; // S_hi - Integer

    public Phase04Model() {
    }

    public Phase04Model(boolean[][] workerWorkstationSkillMatrix, boolean[][] workerTaskSkillMatrix) {
        this.workerWorkstationSkillMatrix = workerWorkstationSkillMatrix;
        this.workerTaskSkillMatrix = workerTaskSkillMatrix;
    }

    public Phase04Model(boolean[][] workerWorkstationSkillMatrix, boolean[][] workerTaskSkillMatrix, int[][] workerWorkstationSkillMatrixInt, int[][] workerTaskSkillMatrixInt) {
        this.workerWorkstationSkillMatrix = workerWorkstationSkillMatrix;
        this.workerTaskSkillMatrix = workerTaskSkillMatrix;
        this.workerWorkstationSkillMatrixInt = workerWorkstationSkillMatrixInt;
        this.workerTaskSkillMatrixInt = workerTaskSkillMatrixInt;
    }

    public boolean[][] getWorkerWorkstationSkillMatrix() {
        return workerWorkstationSkillMatrix;
    }

    public void setWorkerWorkstationSkillMatrix(boolean[][] workerWorkstationSkillMatrix) {
        this.workerWorkstationSkillMatrix = workerWorkstationSkillMatrix;
    }

    public boolean[][] getWorkerTaskSkillMatrix() {
        return workerTaskSkillMatrix;
    }

    public void setWorkerTaskSkillMatrix(boolean[][] workerTaskSkillMatrix) {
        this.workerTaskSkillMatrix = workerTaskSkillMatrix;
    }

    public int[][] getWorkerWorkstationSkillMatrixInt() {
        return workerWorkstationSkillMatrixInt;
    }

    public void setWorkerWorkstationSkillMatrixInt(int[][] workerWorkstationSkillMatrixInt) {
        this.workerWorkstationSkillMatrixInt = workerWorkstationSkillMatrixInt;
    }

    public int[][] getWorkerTaskSkillMatrixInt() {
        return workerTaskSkillMatrixInt;
    }

    public void setWorkerTaskSkillMatrixInt(int[][] workerTaskSkillMatrixInt) {
        this.workerTaskSkillMatrixInt = workerTaskSkillMatrixInt;
    }
}
