package models;

import utilities.Enums;

public class Phase03Model {

    private int[][] taskWorkerDurations; // d_ik Matrix
    private int[][][] taskWorkstationWorkerDurations; // d_ijk Matrix -- Please note that the matrix is ordered as [k][i][j], because we want to iterate over workers 1st, then over the [tasks, workstations]. :-)
    private Integer workstation = 0; // (M)

    private int[][] workerWorkstationTrainingCosts; // b1_hj
    private int[][] workerTaskTrainingCosts; // b1_hi

    private float[][] workerWorkstationSalaries; // B2_hj
    private float[][] workerTaskSalaries; // B2_hi

    private int[] workerDailySalaries; // B2_h
    private int[] operationCosts; // l_t
    private int[] sellingRevenues; // q_i

    private Enums.Scheduling_Type scheduling_type;

    private boolean[][] tasksOrders; // Ci >= Ci'

    public Phase03Model() {}

    public Phase03Model(int[][] taskWorkerDurations, int[][][] taskWorkstationWorkerDurations, Integer workstation, int[][] workerWorkstationTrainingCosts, int[][] workerTaskTrainingCosts, float[][] workerWorkstationSalaries, float[][] workerTaskSalaries, int[] workerDailySalaries, int[] operationCosts, int[] sellingRevenues, Enums.Scheduling_Type scheduling_type, boolean[][] tasksOrders) {
        this.taskWorkerDurations = taskWorkerDurations;
        this.taskWorkstationWorkerDurations = taskWorkstationWorkerDurations;
        this.workstation = workstation;
        this.workerWorkstationTrainingCosts = workerWorkstationTrainingCosts;
        this.workerTaskTrainingCosts = workerTaskTrainingCosts;
        this.workerWorkstationSalaries = workerWorkstationSalaries;
        this.workerTaskSalaries = workerTaskSalaries;
        this.workerDailySalaries = workerDailySalaries;
        this.operationCosts = operationCosts;
        this.sellingRevenues = sellingRevenues;
        this.scheduling_type = scheduling_type;
        this.tasksOrders = tasksOrders;
    }

    public int[][] getTaskWorkerDurations() {
        return taskWorkerDurations;
    }

    public void setTaskWorkerDurations(int[][] taskWorkerDurations) {
        this.taskWorkerDurations = taskWorkerDurations;
    }

    public int[][][] getTaskWorkstationWorkerDurations() {
        return taskWorkstationWorkerDurations;
    }

    public void setTaskWorkstationWorkerDurations(int[][][] taskWorkstationWorkerDurations) {
        this.taskWorkstationWorkerDurations = taskWorkstationWorkerDurations;
    }

    public Integer getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Integer workstation) {
        this.workstation = workstation;
    }

    public int[][] getWorkerWorkstationTrainingCosts() {
        return workerWorkstationTrainingCosts;
    }

    public void setWorkerWorkstationTrainingCosts(int[][] workerWorkstationTrainingCosts) {
        this.workerWorkstationTrainingCosts = workerWorkstationTrainingCosts;
    }

    public int[][] getWorkerTaskTrainingCosts() {
        return workerTaskTrainingCosts;
    }

    public void setWorkerTaskTrainingCosts(int[][] workerTaskTrainingCosts) {
        this.workerTaskTrainingCosts = workerTaskTrainingCosts;
    }

    public float[][] getWorkerWorkstationSalaries() {
        return workerWorkstationSalaries;
    }

    public void setWorkerWorkstationSalaries(float[][] workerWorkstationSalaries) {
        this.workerWorkstationSalaries = workerWorkstationSalaries;
    }

    public float[][] getWorkerTaskSalaries() {
        return workerTaskSalaries;
    }

    public void setWorkerTaskSalaries(float[][] workerTaskSalaries) {
        this.workerTaskSalaries = workerTaskSalaries;
    }

    public int[] getWorkerDailySalaries() {
        return workerDailySalaries;
    }

    public void setWorkerDailySalaries(int[] workerDailySalaries) {
        this.workerDailySalaries = workerDailySalaries;
    }

    public int[] getOperationCosts() {
        return operationCosts;
    }

    public void setOperationCosts(int[] operationCosts) {
        this.operationCosts = operationCosts;
    }

    public int[] getSellingRevenues() {
        return sellingRevenues;
    }

    public void setSellingRevenues(int[] sellingRevenues) {
        this.sellingRevenues = sellingRevenues;
    }

    public Enums.Scheduling_Type getScheduling_type() {
        return scheduling_type;
    }

    public void setScheduling_type(Enums.Scheduling_Type scheduling_type) {
        this.scheduling_type = scheduling_type;
    }

    public boolean[][] getTasksOrders() {
        return tasksOrders;
    }

    public void setTasksOrders(boolean[][] tasksOrders) {
        this.tasksOrders = tasksOrders;
    }
}
