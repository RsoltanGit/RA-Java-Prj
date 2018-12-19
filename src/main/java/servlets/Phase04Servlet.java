package servlets;

import models.MainModel;
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

@WebServlet(name = "Phase04Servlet", urlPatterns = "/phase04")
public class Phase04Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Phase04Model phase04Model = getPhase04Model(request);
        HttpSession session = request.getSession();

        request.setAttribute("ph04Model", phase04Model);
        session.setAttribute("ph04Model", phase04Model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/exportToJulia.jsp");
        requestDispatcher.forward(request, response);
    }

    protected Phase04Model getPhase04Model(HttpServletRequest request) {

        Phase04Model phase04Model = (Phase04Model) request.getSession().getAttribute("ph04Model");
        Phase03Model phase03Model = (Phase03Model) request.getSession().getAttribute("ph03Model");
        MainModel phase01Model = (MainModel) request.getSession().getAttribute("ph01Model");

        Integer worker = phase01Model.getWorker();
        Integer task = phase01Model.getTask();
        Integer workstation = phase03Model.getWorkstation();

        // Check whether it is "Off-Site" or "No Production Line" Scheduling.
        if (phase03Model.getScheduling_type() == Enums.Scheduling_Type.SCHEDULING_OFF_SITE) { // S_hj
            ArrayList<Integer> workerWrkstSkll = Utilities.extractParamListFromRequest(request, "workerWorkstationSkill");
            int[][] wrkWksSkll = Utilities.arrayListOfIntToIntMatrix(workerWrkstSkll, worker, workstation);
            phase04Model.setWorkerWorkstationSkillMatrixInt(wrkWksSkll);
        } else if (phase03Model.getScheduling_type() == Enums.Scheduling_Type.SCHEDULING_NO_PRODUCTION_LINE) { // S_hi
            ArrayList<Integer> workerTskSkll = Utilities.extractParamListFromRequest(request, "workerTaskSkill");
            int[][] wrkTskSkll = Utilities.arrayListOfIntToIntMatrix(workerTskSkll, worker, task);
            phase04Model.setWorkerTaskSkillMatrixInt(wrkTskSkll);
        }

        return phase04Model;
    }
}
