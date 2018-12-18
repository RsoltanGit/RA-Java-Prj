package models;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import utilities.Enums;

import java.util.ArrayList;
import java.util.List;

public class Phase02Model {

    private ArrayList<Integer> salaries = new ArrayList<Integer>(); // B2_h Array
    private ArrayList<Integer> costs = new ArrayList<Integer>(); // l_t Array
    private ArrayList<Integer> revenues = new ArrayList<Integer>(); //q_i Array

    private ArrayList<Integer> setOfStatus = new ArrayList<Integer>(); // K Array

//    private List<List<Integer>> skills = new ArrayList<List<Integer>>();
    private int[][] skills; //S_hj Matrix
    private int[][] trainingCosts; // b1_h_j Matrix
    private float[][] multiSkilledWorkerSalaries; // B2_h_j Matrix

    private int[][][] durations; // d_ijk Matrix

    private Enums.Context_Type context_type;
    private Enums.ProductionLine_Type productionLine_type;

    public Phase02Model() {}

    public Phase02Model(ArrayList<Integer> salaries, ArrayList<Integer> costs, ArrayList<Integer> revenues, ArrayList<Integer> setOfStatus, int[][] skills, int[][] trainingCosts, float[][] multiSkilledWorkerSalaries, int[][][] durations, Enums.Context_Type context_type, Enums.ProductionLine_Type productionLine_type) {
        this.salaries = salaries;
        this.costs = costs;
        this.revenues = revenues;
        this.setOfStatus = setOfStatus;
        this.skills = skills;
        this.trainingCosts = trainingCosts;
        this.multiSkilledWorkerSalaries = multiSkilledWorkerSalaries;
        this.durations = durations;
        this.context_type = context_type;
        this.productionLine_type = productionLine_type;
    }

    public ArrayList<Integer> getSalaries() {
        return salaries;
    }

    public void setSalaries(ArrayList<Integer> salaries) {
        this.salaries = salaries;
    }

    public ArrayList<Integer> getCosts() {
        return costs;
    }

    public void setCosts(ArrayList<Integer> costs) {
        this.costs = costs;
    }

    public ArrayList<Integer> getRevenues() {
        return revenues;
    }

    public void setRevenues(ArrayList<Integer> revenues) {
        this.revenues = revenues;
    }

    public ArrayList<Integer> getSetOfStatus() {
        return setOfStatus;
    }

    public void setSetOfStatus(ArrayList<Integer> setOfStatus) {
        this.setOfStatus = setOfStatus;
    }

    public int[][] getSkills() {
        return skills;
    }

    public void setSkills(int[][] skills) {
        this.skills = skills;
    }

    public int[][] getTrainingCosts() {
        return trainingCosts;
    }

    public void setTrainingCosts(int[][] trainingCosts) {
        this.trainingCosts = trainingCosts;
    }

    public float[][] getMultiSkilledWorkerSalaries() {
        return multiSkilledWorkerSalaries;
    }

    public void setMultiSkilledWorkerSalaries(float[][] multiSkilledWorkerSalaries) {
        this.multiSkilledWorkerSalaries = multiSkilledWorkerSalaries;
    }

    public int[][][] getDurations() {
        return durations;
    }

    public void setDurations(int[][][] durations) {
        this.durations = durations;
    }

    public Enums.Context_Type getContext_type() {
        return context_type;
    }

    public void setContext_type(Enums.Context_Type context_type) {
        this.context_type = context_type;
    }

    public Enums.ProductionLine_Type getProductionLine_type() {
        return productionLine_type;
    }

    public void setProductionLine_type(Enums.ProductionLine_Type productionLine_type) {
        this.productionLine_type = productionLine_type;
    }
}
