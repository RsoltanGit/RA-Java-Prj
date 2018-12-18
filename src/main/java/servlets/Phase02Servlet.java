package servlets;

import models.MainModel;
import models.Phase02Model;
import models.Phase03Model;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "Phase02Servlet", urlPatterns = "/phase02")
public class Phase02Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Phase02Model phase02Model = getPhase02ModelParams(request);
        Phase03Model phase03Model = getPhase03ModelParams(request, phase02Model);

        request.setAttribute("ph02Model", phase02Model);
        request.setAttribute("ph03Model", phase03Model);

        HttpSession session = request.getSession();
        session.setAttribute("ph02Model", phase02Model);
        session.setAttribute("ph03Model", phase03Model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/phase03.jsp");
        requestDispatcher.forward(request, response);
    }

    protected Phase02Model getPhase02ModelParams(HttpServletRequest request) {

        Phase02Model phase02Model = new Phase02Model();
        ArrayList<Integer> setOfStatusArrayK = new ArrayList<>();

        Integer context_type = request.getParameter("context") != null ? Integer.valueOf(request.getParameter("context")) : 0;
        Integer productionLine_type = request.getParameter("productionLineType") != null ? Integer.valueOf(request.getParameter("productionLineType")) : 0;
        switch (context_type) {
            case 1 : phase02Model.setContext_type(Enums.Context_Type.NO_PRODUCTION_LINE); break;
            case 2 : phase02Model.setContext_type(Enums.Context_Type.PRODUCTION_LINE); break;
            default: System.out.println("error!"); break;
        }
        switch (productionLine_type) {
            case 1 : phase02Model.setProductionLine_type(Enums.ProductionLine_Type.NO_PERMUTATION); break;
            case 2 : phase02Model.setProductionLine_type(Enums.ProductionLine_Type.PERMUTATION); break;
            default: System.out.println("error!"); break;
        }

        // TODO: use the following line of code instead of the next While loop after resolving the K1 Array issue! :-)
//        setOfStatusArrayK = Utilities.extractParamListFromRequest(request, "revenue");

        Enumeration<String> parameterNames = request.getParameterNames();
        String[] pvals;
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
//            String val = request.getParameter(key);
//            System.out.println("A= <"+key+"> Value<"+val+">");
            //TODO: replace "revenue" with the workstation value!
            if (StringUtils.equals(key, "revenue")) {
                pvals = request.getParameterValues(key);
                for (int i=0; i <pvals.length; i++){
                    setOfStatusArrayK.add(Integer.valueOf(pvals[i]));
                }
            }
        }
        phase02Model.setSetOfStatus(setOfStatusArrayK);
/*
        Map<String, String[]> allMap = request.getParameterMap();
        for(String key:allMap.keySet()){
            String[] strArr=(String[])allMap.get(key);
            for(String val:strArr){
                System.out.println("Str Array= "+val);
            }
        }
*/

        return phase02Model;
    }

    protected Phase03Model getPhase03ModelParams(HttpServletRequest request, Phase02Model phase02Model) {

        Phase03Model phase03Model = new Phase03Model();
        HttpSession session = request.getSession();

        Enums.Context_Type context_type = phase02Model.getContext_type();

        Integer productionLine_type = request.getParameter("productionLineType") != null ? Integer.valueOf(request.getParameter("productionLineType")) : 0;

        MainModel phase01Model = (MainModel) session.getAttribute("ph01Model");
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

        }
        else if (context_type == Enums.Context_Type.PRODUCTION_LINE) { // Production Line
            Integer workstation = request.getParameter("workstation") != null ? Integer.valueOf(request.getParameter("workstation")) : 0;
            phase03Model.setWorkstation(workstation);
            phase03Model.setTaskWorkstationWorkerDurations(new int[worker][task][workstation]); // d_ijk -- Please note that the matrix is ordered as [k][i][j], because we want to iterate over workers 1st, then over the [tasks, workstations]. :-)
            phase03Model.setWorkerWorkstationTrainingCosts(new int[worker][workstation]); // b1_hj
            phase03Model.setWorkerWorkstationSalaries(new float[worker][workstation]); // B2_hj

            if (productionLine_type == 2) {// Permutation
                return phase03Model;
            }
        }

        //Ci >= Ci' for all tasks - tasksOrders Matrix (with N-1 rows and N columns)
        phase03Model.setTasksOrders(new boolean[task - 1][task]);

        return phase03Model;
    }
}