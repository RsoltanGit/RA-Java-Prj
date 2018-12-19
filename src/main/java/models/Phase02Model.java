package models;

import utilities.Enums;

import java.util.ArrayList;

public class Phase02Model {

    private ArrayList<Integer> salaries = new ArrayList<Integer>(); // B2_h Array
    private ArrayList<Integer> costs = new ArrayList<Integer>(); // l_t Array
    private ArrayList<Integer> revenues = new ArrayList<Integer>(); //q_i Array

    private ArrayList<Integer> setOfStatus = new ArrayList<Integer>(); // K Array

    // K array
    private int mode_singleSkill = 1; // K = 1 - for the HR Strategy "Single-Skill"
    private int[] mode_multiSkill_train; // K = Ki or Kj - for the HR Strategy "Multi-Skill" & "Train"
    private int[] mode_multiSkill_hire; // K = Ki or Kj - for the HR Strategy "Multi-Skill" & "Hire"
    private int[] mode_upSkill_train; // K = Ui or Uj - for the HR Strategy "Up-Skill" & "Train"
    private int[] mode_upSkill_hire; // K = Ui or Uj - for the HR Strategy "Up-Skill" & "Hire"

    private Integer workstation = 0; // (M)

    //    private List<List<Integer>> skills = new ArrayList<List<Integer>>();
    private int[][] skills; //S_hj Matrix
    private int[][] trainingCosts; // b1_h_j Matrix
    private float[][] multiSkilledWorkerSalaries; // B2_h_j Matrix

    private int[][][] durations; // d_ijk Matrix

    private Enums.Context_Type context_type;
    private Enums.ProductionLine_Type productionLine_type;
//    private Enums.Context_Decision_Branch contextDecisionBranch;

    public Phase02Model() {}

    public Phase02Model(ArrayList<Integer> salaries, ArrayList<Integer> costs, ArrayList<Integer> revenues, ArrayList<Integer> setOfStatus, int mode_singleSkill, int[] mode_multiSkill_train, int[] mode_multiSkill_hire, int[] mode_upSkill_train, int[] mode_upSkill_hire, Integer workstation, int[][] skills, int[][] trainingCosts, float[][] multiSkilledWorkerSalaries, int[][][] durations, Enums.Context_Type context_type, Enums.ProductionLine_Type productionLine_type /*, Enums.Context_Decision_Branch contextDecisionBranch*/) {
        this.salaries = salaries;
        this.costs = costs;
        this.revenues = revenues;
        this.setOfStatus = setOfStatus;
        this.mode_singleSkill = mode_singleSkill;
        this.mode_multiSkill_train = mode_multiSkill_train;
        this.mode_multiSkill_hire = mode_multiSkill_hire;
        this.mode_upSkill_train = mode_upSkill_train;
        this.mode_upSkill_hire = mode_upSkill_hire;
        this.workstation = workstation;
        this.skills = skills;
        this.trainingCosts = trainingCosts;
        this.multiSkilledWorkerSalaries = multiSkilledWorkerSalaries;
        this.durations = durations;
        this.context_type = context_type;
        this.productionLine_type = productionLine_type;
//        this.contextDecisionBranch = contextDecisionBranch;
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

    public Integer getMode_singleSkill() {
        return mode_singleSkill;
    }

    public void setMode_singleSkill(Integer mode_singleSkill) {
        this.mode_singleSkill = mode_singleSkill;
    }

    public void setMode_singleSkill(int mode_singleSkill) {
        this.mode_singleSkill = mode_singleSkill;
    }

    public int[] getMode_multiSkill_train() {
        return mode_multiSkill_train;
    }

    public void setMode_multiSkill_train(int[] mode_multiSkill_train) {
        this.mode_multiSkill_train = mode_multiSkill_train;
    }

    public int[] getMode_multiSkill_hire() {
        return mode_multiSkill_hire;
    }

    public void setMode_multiSkill_hire(int[] mode_multiSkill_hire) {
        this.mode_multiSkill_hire = mode_multiSkill_hire;
    }

    public int[] getMode_upSkill_train() {
        return mode_upSkill_train;
    }

    public void setMode_upSkill_train(int[] mode_upSkill_train) {
        this.mode_upSkill_train = mode_upSkill_train;
    }

    public int[] getMode_upSkill_hire() {
        return mode_upSkill_hire;
    }

    public void setMode_upSkill_hire(int[] mode_upSkill_hire) {
        this.mode_upSkill_hire = mode_upSkill_hire;
    }

    public Integer getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Integer workstation) {
        this.workstation = workstation;
    }

/*
    public Enums.Context_Decision_Branch getContextDecisionBranch() {
        return contextDecisionBranch;
    }

    public void setContextDecisionBranch(Enums.Context_Decision_Branch contextDecisionBranch) {
        this.contextDecisionBranch = contextDecisionBranch;
    }
*/
}
