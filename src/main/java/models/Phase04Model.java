package models;

public class Phase04Model {

    boolean[][] workerWorkstationSkillMatrix; // S_hj
    boolean[][] workerTaskSkillMatrix; // S_hi

    public Phase04Model() {
    }

    public Phase04Model(boolean[][] workerWorkstationSkillMatrix, boolean[][] workerTaskSkillMatrix) {
        this.workerWorkstationSkillMatrix = workerWorkstationSkillMatrix;
        this.workerTaskSkillMatrix = workerTaskSkillMatrix;
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
}
