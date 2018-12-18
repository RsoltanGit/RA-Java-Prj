package servlets;

import com.sun.deploy.net.HttpRequest;
import models.MainModel;
import models.Phase02Model;
import models.Phase03Model;
import models.Phase04Model;
import org.apache.commons.lang3.StringUtils;
import utilities.Enums;
import utilities.Utilities;

import javax.rmi.CORBA.Util;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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
        session.setAttribute("ph03Model", phase04Model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/phase04.jsp");
        requestDispatcher.forward(request, response);
    }

    protected Phase03Model getPhase03ModelParams(HttpServletRequest request) {

        Phase03Model phase03Model = new Phase03Model();
        Integer workstation = ((Phase03Model) request.getSession().getAttribute("ph03Model")).getWorkstation();

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
        Phase02Model phase02Model = (Phase02Model) request.getSession().getAttribute("ph02Model");
        Enums.Context_Type context_type = phase02Model.getContext_type();

        MainModel phase01Model = (MainModel) request.getSession().getAttribute("ph01Model");
        Integer worker = phase01Model.getWorker();
//        Integer time = phase01Model.getTime();
        Integer task = phase01Model.getTask();

        if (context_type == Enums.Context_Type.NO_PRODUCTION_LINE) {// No Production Line
            ArrayList al01 = Utilities.extractParamListFromRequest(request, "taskWorkerDuration");
            ArrayList al02 = Utilities.extractParamListFromRequest(request, "workerTaskTrainCost");
            ArrayList al03 = Utilities.extractParamListFromRequest(request, "workerTaskSalary");

            int[][] taskWorkerDurations = Utilities.arrayListOfIntToIntMatrix(al01, task, worker);
            int[][] workerTaskTrainCosts = Utilities.arrayListOfIntToIntMatrix(al02, worker, task);
            float[][] workerTaskSalaries = Utilities.arrayListOfFloatToFloatMatrix(al03, worker, task);

            phase03Model.setTaskWorkerDurations(taskWorkerDurations); // d_ik
            phase03Model.setWorkerTaskTrainingCosts(workerTaskTrainCosts); // b1_hi
            phase03Model.setWorkerTaskSalaries(workerTaskSalaries); // B2_hi
        }
        else if (context_type == Enums.Context_Type.PRODUCTION_LINE) { // Production Line
            ArrayList al04 = Utilities.extractParamListFromRequest(request, "duration");
            ArrayList al05 = Utilities.extractParamListFromRequest(request, "workerWorkstationTrainingCost");
            ArrayList al06 = Utilities.extractParamListFromRequest(request, "workerWorkstationSalary");

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
            ArrayList al07 = Utilities.extractParamListFromRequest(request, "tasksOrder");
            boolean[][] orders = Utilities.arrayListOfBooleanToBooleanMatrix2D(al07, task - 1, task);
            phase03Model.setTasksOrders(orders); // S_hj - Ci >= Ci' Matrix
        }

        return phase03Model;
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
