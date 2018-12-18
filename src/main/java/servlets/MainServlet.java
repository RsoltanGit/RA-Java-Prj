package servlets;

import models.MainModel;
import models.Phase02Model;
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
import java.util.List;

//@WebServlet("/main")
@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MainModel phase01Model = getMainModelParams(request);
        Phase02Model phase02Model = getPhase02ModelParams(request);
        String whichPageIsNext;

        request.setAttribute("ph01Model", phase01Model);
        request.setAttribute("ph02Model", phase02Model);

        HttpSession session = request.getSession();
        session.setAttribute("ph01Model", phase01Model);
        session.setAttribute("ph02Model", phase02Model);

        if (getMainModelParams(request).getHr() == Enums.HREnum.SINGLE_SKILL) {
            // TODO: put K=1 in the request and session attributes for phase03!
            whichPageIsNext = "/WEB-INF/jsp/phase03.jsp";
        } else {
            whichPageIsNext = "/WEB-INF/jsp/phase02.jsp";
        }
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

    protected Phase02Model getPhase02ModelParams(HttpServletRequest request) {

        // TODO: check whether you need this "phase02Model" here! :-)
        Phase02Model ph02Model = new Phase02Model();
        ph02Model.setSalaries(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getWorker(), 0)));
        ph02Model.setCosts(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getTime(), 0)));
        ph02Model.setRevenues(new ArrayList<Integer>(Collections.nCopies(getMainModelParams(request).getTask(), 0)));
/*
        for(int i = 0; i < worker; i++){
            ph02Model.setSkills(Collections.singletonList(Collections.nCopies(workstation, 0)));
        }
*/
//        ph02Model.setSkills(new Integer[Collections.nCopies(worker, 0)][Collections.nCopies(workstation, 0)]);

//        TODO: will be used later. :-)
//        ph02Model.setSkills(new int[worker][workstation]);

/*
        for (int i = 0; i < ph02Model.getSkills().length; i++){
            for (int j = 0; j < ph02Model.getSkills()[i].length; j++){
                ph02Model.setSkills();
            }
        }
*/

//      TODO: will be used later. :-)
/*
        ph02Model.setTrainingCosts(new int[worker][workstation]);
        ph02Model.setMultiSkilledWorkerSalaries(new float[worker][workstation]);
        ph02Model.setDurations(new int[mode][workstation][worker]);
*/
    return ph02Model;
    }
}
