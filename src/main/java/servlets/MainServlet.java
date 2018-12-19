package servlets;

import models.MainModel;
import models.Phase02Model;
import models.Phase03Model;
import utilities.Enums;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//@WebServlet("/main")
@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MainModel phase01Model = getMainModelParams(request);
        Phase02Model phase02Model = getPhase02ModelParams(request, phase01Model);
        Phase03Model phase03Model = getPhase03ModelParams(request, phase01Model, phase02Model);

        String whichPageIsNext;

        request.setAttribute("ph01Model", phase01Model);
        request.setAttribute("ph02Model", phase02Model);
        request.setAttribute("ph03Model", phase03Model);

        HttpSession session = request.getSession();
        session.setAttribute("ph01Model", phase01Model);
        session.setAttribute("ph02Model", phase02Model);
        session.setAttribute("ph03Model", phase03Model);

//        if (getMainModelParams(request).getHr() == Enums.HREnum.SINGLE_SKILL) {
            // TODO: put K=1 in the request and session attributes for phase03!
            whichPageIsNext = "/WEB-INF/jsp/phase03.jsp";
//        } else {
//            whichPageIsNext = "/WEB-INF/jsp/phase02.jsp";
//        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(whichPageIsNext);
        requestDispatcher.forward(request, response);
//        request.getRequestDispatcher("phase02.jsp").forward(request, response);
    }

    protected MainModel getMainModelParams(HttpServletRequest request) {
        //        TODO: validate the input!
        Integer task = request.getParameter("task") != null ? Integer.valueOf(request.getParameter("task")) : 0;
//        Integer workstation = Integer.valueOf(request.getParameter("workstation"));
        Integer worker = request.getParameter("worker") != null ? Integer.valueOf(request.getParameter("worker")) : 0;
        Integer time = request.getParameter("time") != null ? Integer.valueOf(request.getParameter("time")) : 0;
//        Integer mode = Integer.valueOf(request.getParameter("mode"));

        Integer hrType = request.getParameter("hr") != null ? Integer.valueOf(request.getParameter("hr")) : 0;
        Integer hrMultiType = request.getParameter("hrMulti") != null ? Integer.valueOf(request.getParameter("hrMulti")) : 0;
        Integer hrUpType = request.getParameter("hrUp") != null ? Integer.valueOf(request.getParameter("hrUp")) : 0;

        MainModel ph01Model = new MainModel();
        ph01Model.setTask(task);
//        ph01Model.setWorkstation(workstation);
        ph01Model.setWorker(worker);
        ph01Model.setTime(time);
//        ph01Model.setMode(mode);
        switch (hrType) {
            case 1 : ph01Model.setHr(Enums.HREnum.SINGLE_SKILL); break;
            case 2 : ph01Model.setHr(Enums.HREnum.MULTI_SKILL); break;
            case 3 : ph01Model.setHr(Enums.HREnum.UP_SKILL); break;
            default: System.out.println("error!"); break;
        }
        switch (hrMultiType) {
            case 1 : ph01Model.setHrMulti(Enums.HRMultiSkillEnum.TRAIN); break;
            case 2 : ph01Model.setHrMulti(Enums.HRMultiSkillEnum.HIRE); break;
            default: System.out.println("error!"); break;
        }
        switch (hrUpType) {
            case 1 : ph01Model.setHrUp(Enums.HRUPSkillEnum.TRAIN); break;
            case 2 : ph01Model.setHrUp(Enums.HRUPSkillEnum.HIRE); break;
            default: System.out.println("error!"); break;
        }
        return ph01Model;
    }

    protected Phase02Model getPhase02ModelParams(HttpServletRequest request, MainModel phase01Model) {

        Phase02Model phase02Model = new Phase02Model();

        phase02Model.setSalaries(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getWorker(), 0)));
        phase02Model.setCosts(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getTime(), 0)));
        phase02Model.setRevenues(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getTask(), 0)));

        ArrayList<Integer> setOfStatusArrayK = new ArrayList<>();

        Integer context_type = request.getParameter("context") != null ? Integer.valueOf(request.getParameter("context")) : 0;
        Integer productionLine_type = request.getParameter("productionLineType") != null ? Integer.valueOf(request.getParameter("productionLineType")) : 0;

        switch (context_type) {
            case 1 : // all "i" s based on tasks
                phase02Model.setContext_type(Enums.Context_Type.NO_PRODUCTION_LINE);
                if (phase01Model.getHr() == Enums.HREnum.SINGLE_SKILL) {
                    phase02Model.setMode_singleSkill(1);    // K = 1
                } else if (phase01Model.getHr() == Enums.HREnum.MULTI_SKILL) {
                    if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.TRAIN) {
                        phase02Model.setMode_multiSkill_train(new int[phase01Model.getTask()]); // K = Ki
                    } else if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.HIRE) {
                        phase02Model.setMode_multiSkill_hire(new int[phase01Model.getTask()]); // K = Ki
                    }
                } else if (phase01Model.getHr() == Enums.HREnum.UP_SKILL) {
                    if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.TRAIN) {
                        phase02Model.setMode_upSkill_train(new int[phase01Model.getTask()]); // U = Ui
                    } else if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.HIRE) {
                        phase02Model.setMode_upSkill_hire(new int[phase01Model.getTask()]); // U = Ui
                    }
                }
                break;
            case 2 : // all "j" s based on workstations
                phase02Model.setContext_type(Enums.Context_Type.PRODUCTION_LINE);
                Integer workstation = request.getParameter("workstation") != null ? Integer.valueOf(request.getParameter("workstation")) : 0;
                phase02Model.setWorkstation(workstation);
                if (phase01Model.getHr() == Enums.HREnum.SINGLE_SKILL) {
                    phase02Model.setMode_singleSkill(1); // K = 1
                } else if (phase01Model.getHr() == Enums.HREnum.MULTI_SKILL) {
                    if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.TRAIN) {
                        phase02Model.setMode_multiSkill_train(new int[phase02Model.getWorkstation()]); // K = Kj
                    } else if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.HIRE) {
                        phase02Model.setMode_multiSkill_hire(new int[phase02Model.getWorkstation()]); // K = Kj
                    }
                } else if (phase01Model.getHr() == Enums.HREnum.UP_SKILL) {
                    if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.TRAIN) {
                        phase02Model.setMode_upSkill_train(new int[phase02Model.getWorkstation()]); // U = Uj
                    } else if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.HIRE) {
                        phase02Model.setMode_upSkill_hire(new int[phase02Model.getWorkstation()]); // U = Uj
                    }
                }
                break;
            default:
                System.out.println("error!");
                break;
        }
        switch (productionLine_type) {
            case 1 :
                phase02Model.setProductionLine_type(Enums.ProductionLine_Type.NO_PERMUTATION);
                break;
            case 2 :
                phase02Model.setProductionLine_type(Enums.ProductionLine_Type.PERMUTATION);
                break;
            default:
                System.out.println("errorzz!");
                break;
        }

        return phase02Model;
    }

    protected Phase03Model getPhase03ModelParams(HttpServletRequest request, MainModel phase01Model, Phase02Model phase02Model) {

        Phase03Model phase03Model = new Phase03Model();
        Enums.Context_Type context_type = phase02Model.getContext_type();
        Integer worker = phase01Model.getWorker();
        Integer time = phase01Model.getTime();
        Integer task = phase01Model.getTask();

        phase03Model.setWorkerDailySalaries(new int[worker]); // B2_h
        phase03Model.setOperationCosts(new int[time]); // l_t
        phase03Model.setSellingRevenues(new int[task]); // q_t
        phase03Model.setWorkerTaskTrainingCosts(new int[worker][task]); // b1_hi
        phase03Model.setWorkerTaskSalaries(new float[worker][task]); // B2_hi

        if (context_type == Enums.Context_Type.NO_PRODUCTION_LINE) {// No Production Line
            phase03Model.setTaskWorkerDurations(new int[task][worker]);
            //Ci >= Ci' for all tasks - tasksOrders Matrix (with N-1 rows and N columns)
            phase03Model.setTasksOrders(new boolean[task - 1][task]);
//            phase03Model.setTasksOrdersInt(new int[task - 1][task]); // for UI use only - on Phase03.jsp file. :-)
        }
        else if (context_type == Enums.Context_Type.PRODUCTION_LINE) { // Production Line
            Integer workstation = phase02Model.getWorkstation();
            phase03Model.setWorkstation(workstation);
            phase03Model.setTaskWorkstationWorkerDurations(new int[worker][task][workstation]); // d_ijk -- Please note that the matrix is ordered as [k][i][j], because we want to iterate over workers 1st, then over the [tasks, workstations]. :-)
            phase03Model.setWorkerWorkstationTrainingCosts(new int[worker][workstation]); // b1_hj
            phase03Model.setWorkerWorkstationSalaries(new float[worker][workstation]); // B2_hj

            if (phase02Model.getContext_type() == Enums.Context_Type.NO_PRODUCTION_LINE ||
                    (phase02Model.getContext_type() == Enums.Context_Type.PRODUCTION_LINE && phase02Model.getProductionLine_type() == Enums.ProductionLine_Type.NO_PERMUTATION)) {// Permutation
                //Ci >= Ci' for all tasks - tasksOrders Matrix (with N-1 rows and N columns)
                phase03Model.setTasksOrders(new boolean[task - 1][task]);
//                phase03Model.setTasksOrdersInt(new int[task - 1][task]); // for UI use only - on Phase03.jsp file. :-)
            }
        }

        // deciding on which context type should be selected to ask on Ki, Kj OR Ui, Uj Arrays.
/*
        if (phase02Model.getContext_type() == Enums.Context_Type.NO_PRODUCTION_LINE) {
            phase02Model.setContextDecisionBranch(Enums.Context_Decision_Branch.NO_PRODUCTION_LINE);
        } else if (phase02Model.getContext_type() == Enums.Context_Type.PRODUCTION_LINE && phase02Model.getProductionLine_type() == Enums.ProductionLine_Type.NO_PERMUTATION) {
            phase02Model.setContextDecisionBranch(Enums.Context_Decision_Branch.PRODUCTION_LINE_NO_PERMUTATION);
        } else if (phase02Model.getContext_type() == Enums.Context_Type.PRODUCTION_LINE && phase02Model.getProductionLine_type() == Enums.ProductionLine_Type.PERMUTATION) {
            phase02Model.setContextDecisionBranch(Enums.Context_Decision_Branch.PRODUCTION_LINE_PERMUTATION);
        }
*/

        return phase03Model;
    }
}
