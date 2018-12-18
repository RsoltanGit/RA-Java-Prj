package models;

import utilities.Enums;

public class MainModel {

    private Integer task = 0; // (N)
//    private Integer workstation = 0; // (M)
    private Integer worker = 0; // (W)
    private Integer time = 0; // (T)
//    private Integer mode = 0; // (K)

    private Enums.HREnum hr;
    private Enums.HRMultiSkillEnum hrMulti;
    private Enums.HRUPSkillEnum hrUp;

    public MainModel() {}

    public MainModel(Integer task, Integer worker, Integer time) {
        this.task = task;
        this.worker = worker;
        this.time = time;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Enums.HREnum getHr() {
        return hr;
    }

    public void setHr(Enums.HREnum hr) {
        this.hr = hr;
    }

    public Enums.HRMultiSkillEnum getHrMulti() {
        return hrMulti;
    }

    public void setHrMulti(Enums.HRMultiSkillEnum hrMulti) {
        this.hrMulti = hrMulti;
    }

    public Enums.HRUPSkillEnum getHrUp() {
        return hrUp;
    }

    public void setHrUp(Enums.HRUPSkillEnum hrUp) {
        this.hrUp = hrUp;
    }
}
