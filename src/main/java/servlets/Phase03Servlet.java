package servlets;

import models.MainModel;
import models.Phase02Model;
import models.Phase03Model;
import models.Phase04Model;
import utilities.Enums;
import utilities.Utilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Phase03Servlet", urlPatterns = "/phase03")
public class Phase03Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Phase03Model phase03Model = getPhase03ModelParams(request);
        Phase04Model phase04Model = getPhase04ModelParams(request, phase03Model);

        HttpSession session = request.getSession();
        request.setAttribute("ph03Model", phase03Model);
        request.setAttribute("ph04Model", phase04Model);

        session.setAttribute("ph03Model", phase03Model);
        session.setAttribute("ph04Model", phase04Model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/phase04.jsp");
        requestDispatcher.forward(request, response);
    }

    protected Phase03Model getPhase03ModelParams(HttpServletRequest request) {

        Phase03Model phase03Model = ((Phase03Model) request.getSession().getAttribute("ph03Model"));
        Integer workstation = phase03Model.getWorkstation();

        ArrayList<Integer> salaries;
        ArrayList<Integer> operationCosts;
        ArrayList<Integer> revenues;

        salaries = Utilities.extractParamListFromRequest(request, "workerDailySalary");
        operationCosts = Utilities.extractParamListFromRequest(request, "operationCost");
        revenues = Utilities.extractParamListFromRequest(request, "revenue");

        int[] salariesIntArray = Utilities.arrayListOfIntegersToIntArray(salaries);
        int[] operationCostsIntArray = Utilities.arrayListOfIntegersToIntArray(operationCosts);
        int[] revenuesIntArray = Utilities.arrayListOfIntegersToIntArray(revenues);

        phase03Model.setWorkerDailySalaries(salariesIntArray);
        phase03Model.setOperationCosts(operationCostsIntArray);
        phase03Model.setSellingRevenues(revenuesIntArray);

//        Integer context_type = request.getParameter("context") != null ? Integer.valueOf(request.getParameter("context")) : 0;
        MainModel phase01Model = (MainModel) request.getSession().getAttribute("ph01Model");
        Phase02Model phase02Model = (Phase02Model) request.getSession().getAttribute("ph02Model");

        Integer worker = phase01Model.getWorker();
//        Integer time = phase01Model.getTime();
        Integer task = phase01Model.getTask();
        Enums.Context_Type context_type = phase02Model.getContext_type();

        if (context_type == Enums.Context_Type.NO_PRODUCTION_LINE) {// No Production Line
            // Ki and Ui arrays
            extractHRStrategyValues(request, phase01Model, phase02Model);

            ArrayList al01 = Utilities.extractParamListFromRequest(request, "taskWorkerDuration");
            ArrayList al02 = Utilities.extractParamListFromRequest(request, "workerTaskTrainCost");
            ArrayList al03 = Utilities.extractFloatParamListFromRequest(request, "workerTaskSalary");

            int[][] taskWorkerDurations = Utilities.arrayListOfIntToIntMatrix(al01, task, worker);
            int[][] workerTaskTrainCosts = Utilities.arrayListOfIntToIntMatrix(al02, worker, task);
            float[][] workerTaskSalaries = Utilities.arrayListOfFloatToFloatMatrix(al03, worker, task);

            phase03Model.setTaskWorkerDurations(taskWorkerDurations); // d_ik
            phase03Model.setWorkerTaskTrainingCosts(workerTaskTrainCosts); // b1_hi
            phase03Model.setWorkerTaskSalaries(workerTaskSalaries); // B2_hi
        }
        else if (context_type == Enums.Context_Type.PRODUCTION_LINE) { // Production Line
            // Kj and Uj arrays
            extractHRStrategyValues(request, phase01Model, phase02Model);


            ArrayList al04 = Utilities.extractParamListFromRequest(request, "duration");
            ArrayList al05 = Utilities.extractParamListFromRequest(request, "workerWorkstationTrainingCost");
            ArrayList al06 = Utilities.extractFloatParamListFromRequest(request, "workerWorkstationSalary");

            int[][][] durations = Utilities.arrayListOfIntToIntMatrix3D(al04, task, workstation, worker);
            int[][] costs = Utilities.arrayListOfIntToIntMatrix(al05, worker, workstation);
            float[][] salarieZ = Utilities.arrayListOfFloatToFloatMatrix(al06, worker, workstation);

            phase03Model.setWorkstation(workstation); // (M)
            phase03Model.setTaskWorkstationWorkerDurations(durations); // d_ijk
            phase03Model.setWorkerWorkstationTrainingCosts(costs); // b1_hj
            phase03Model.setWorkerWorkstationSalaries(salarieZ); // B2_hj
        }

        Integer scheduling_type = request.getParameter("schedulingType") != null ? Integer.valueOf(request.getParameter("schedulingType")) : 0;
        switch (scheduling_type) {
            case 1 : phase03Model.setScheduling_type(Enums.Scheduling_Type.SCHEDULING_OFF_SITE); break;
            case 2 : phase03Model.setScheduling_type(Enums.Scheduling_Type.SCHEDULING_NO_PRODUCTION_LINE); break;
            default: System.out.println("error!"); break;
        }

        if (phase02Model.getContext_type() == Enums.Context_Type.NO_PRODUCTION_LINE ||
                (phase02Model.getContext_type() == Enums.Context_Type.PRODUCTION_LINE && phase02Model.getProductionLine_type() == Enums.ProductionLine_Type.NO_PERMUTATION)) { // it is "No Production Line" or it is "Production Line", but with "No-Permutation".
            ArrayList al07 = Utilities.extractBooleanParamListFromRequest(request, "tasksOrder");
            ArrayList al08 = Utilities.extractParamListFromRequest(request, "tasksOrder");

            boolean[][] orders = Utilities.arrayListOfBooleanToBooleanMatrix2D(al07, task - 1, task);
            int[][] taskOrdersInt = Utilities.arrayListOfIntToIntMatrix(al08, task - 1, task);

            phase03Model.setTasksOrders(orders); // S_hj - Ci >= Ci' Matrix - Boolean
            phase03Model.setTasksOrdersInt(taskOrdersInt); // S_hj - Ci >= Ci' Matrix - Int
        }

        return phase03Model;
    }

    private void extractHRStrategyValues(HttpServletRequest request, MainModel phase01Model, Phase02Model phase02Model) {

        if (phase01Model.getHr() == Enums.HREnum.MULTI_SKILL) {
            if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.TRAIN) {
                ArrayList al12 = Utilities.extractParamListFromRequest(request, "modeMultiSkillTrain"); // "HR Multi-Skill Train Mode" (Ki or Kj)
                int[] kj_modeMultiSkillTrain = Utilities.arrayListOfIntegersToIntArray(al12);
                phase02Model.setMode_multiSkill_train(kj_modeMultiSkillTrain);
            } else if (phase01Model.getHrMulti() == Enums.HRMultiSkillEnum.HIRE) {
                ArrayList al13 = Utilities.extractParamListFromRequest(request, "modeMultiSkillHire"); // "HR Multi-Skill Hire Mode" (Ki or Kj)
                int[] kj_modeMultiSkillHire = Utilities.arrayListOfIntegersToIntArray(al13);
                phase02Model.setMode_multiSkill_hire(kj_modeMultiSkillHire);
            }
        } else if (phase01Model.getHr() == Enums.HREnum.UP_SKILL) {
            if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.TRAIN) {
                ArrayList al14 = Utilities.extractParamListFromRequest(request, "modeUpSkillTrain"); // "HR Up-Skill Train Mode" (Ui or Uj)
                int[] uj_modeUpSkillTrain = Utilities.arrayListOfIntegersToIntArray(al14);
                phase02Model.setMode_upSkill_train(uj_modeUpSkillTrain);
            } else if (phase01Model.getHrUp() == Enums.HRUPSkillEnum.HIRE) {
                ArrayList al15 = Utilities.extractParamListFromRequest(request, "modeUpSkillHire"); // "HR Up-Skill Hire Mode" (Ui or Uj)
                int[] uj_modeUpSkillHire = Utilities.arrayListOfIntegersToIntArray(al15);
                phase02Model.setMode_upSkill_hire(uj_modeUpSkillHire);
            }
        }
        request.getSession().setAttribute("ph02Model", phase02Model);
    }

    protected Phase04Model getPhase04ModelParams(HttpServletRequest request, Phase03Model phase03Model) {

        Phase04Model phase04Model = new Phase04Model();
        MainModel phase01Model = (MainModel) request.getSession().getAttribute("ph01Model");

        Integer worker = phase01Model.getWorker();
        Integer task = phase01Model.getTask();
        Integer workstation = phase03Model.getWorkstation();

        Enums.Scheduling_Type scheduling_type = phase03Model.getScheduling_type();
        if (scheduling_type == Enums.Scheduling_Type.SCHEDULING_OFF_SITE) {
            phase04Model.setWorkerWorkstationSkillMatrix(new boolean[worker][workstation]); // S_hj (W * M))
        }
        else if (scheduling_type == Enums.Scheduling_Type.SCHEDULING_NO_PRODUCTION_LINE) {
            phase04Model.setWorkerTaskSkillMatrix(new boolean[worker][task]); // S_hi (W * N)
        }

        return phase04Model;
    }
}
