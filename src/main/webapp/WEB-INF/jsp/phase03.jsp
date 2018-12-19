<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>
        Phase 03 Data Collection
    </title>
</head>
<body>
<h2>Phase 03 Data Collection</h2><br/>

<h4>Please enter the following data based on the above entered elements:</h4>
<form action="phase03" method="post">
    <table>
        <tr>
            <td>
                Basic Daily Salary Per Worker:
            </td>
            <c:forEach items="${ph03Model.workerDailySalaries}" var="workerDailySalary" varStatus="loop">
                <td>
                    <input type="number" step="any" name="workerDailySalary" required maxlength="5" placeholder="salary<c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Cost of Operating The Production Line In Time t:
            </td>
            <c:forEach items="${ph03Model.operationCosts}" var="operationCost" varStatus="loop">
                <td>
                    <input type="number" name="operationCost" required maxlength="5" placeholder="cost<c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Revenue of Selling Element i:
            </td>
            <c:forEach items="${ph03Model.sellingRevenues}" var="revenue" varStatus="loop">
                <td>
                    <input type="number" name="revenue" required maxlength="5" placeholder="revenue<c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
    </table>

    <c:if test="${ph02Model.context_type == 'NO_PRODUCTION_LINE'}">
        <%-- All the Ki s --%>
        <c:if test="${not empty ph01Model.hrMulti || not empty ph01Mode.hrUp}">
            <table>
                <c:if test="${not empty ph01Model.hrMulti}">
                    <%--<c:if test="${ph01Model.hrMulti == 'TRAIN'}">--%>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_train) and (ph01Model.hrMulti == 'TRAIN')}">
                        <tr>
                            <td>
                                Please Enter The Ki Array For "HR Multi-Skill Train Mode" (Ki):
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_train}" var="modeMultiSkillTrain" varStatus="loop">
                                <td>
                                    <input type="number" name="modeMultiSkillTrain" required maxlength="5" placeholder="HR-MultiSkill-Train<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <%--<c:if test="${ph01Model.hrMulti == 'HIRE'}">--%>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_hire) and (ph01Model.hrMulti == 'HIRE')}">
                        <tr>
                            <td>
                                Please Enter The Ki Array For "HR Multi-Skill Hire Mode" (Ki):
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_hire}" var="modeMultiSkillHire" varStatus="loop">
                                <td>
                                    <input type="number" name="modeMultiSkillHire" required maxlength="5" placeholder="HR-MultiSkill-Hire<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${not empty ph01Model.hrUp}">
                    <%--<c:if test="${ph01Model.hrUp == 'TRAIN'}">--%>
                    <c:if test="${(not empty ph02Model.mode_upSkill_train) and (ph01Model.hrUp == 'TRAIN')}">
                        <tr>
                            <td>
                                Please Enter The Ui Array For "HR Up-Skill Train Mode" (Ui):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_train}" var="modeUpSkillTrain" varStatus="loop">
                                <td>
                                    <input type="number" name="modeUpSkillTrain" required maxlength="5" placeholder="HR-UpSkill-Train<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <%--<c:if test="${ph01Model.hrUp == 'HIRE'}">--%>
                    <c:if test="${(not empty ph02Model.mode_upSkill_hire) and (ph01Model.hrUp == 'HIRE')}">
                        <tr>
                            <td>
                                Please Enter The Ui Array For "HR Up-Skill Hire Mode" (Ui):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_hire}" var="modeUpSkillHire" varStatus="loop">
                                <td>
                                    <input type="number" name="modeUpSkillHire" required maxlength="5" placeholder="HR-UpSkill-Hire<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
            </table>
        </c:if>

        <table>
            <tr>
                <td>
                    Duration of Each Procedure (row) When Each Worker (column) Is Assigned To Them:
                </td>
            </tr>
            <c:forEach items="${ph03Model.taskWorkerDurations}" var="taskWorkerDurationz" varStatus="upperLoop">
                <tr>
                    <td>
                        Task <c:out value="${upperLoop.index + 1}"/>
                    </td>
                    <c:forEach items="${taskWorkerDurationz}" var="taskWorkerDuration" varStatus="innerLoop">
                        <td>
                            <input type="number" name="taskWorkerDuration" required maxlength="5" placeholder="task<c:out value="${upperLoop.index + 1}"/>, worker<c:out value="${innerLoop.index + 1}"/> duration"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Cost of Training Each Worker for Each Task:
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerTaskTrainingCosts}" var="workerTaskTrainCosts" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskTrainCosts}" var="workerTaskTrainCost" varStatus="innerLoop">
                        <td>
                            <input type="number" name="workerTaskTrainCost" required maxlength="5" placeholder="worker<c:out value="${loop.index + 1}"/>, task<c:out value="${innerLoop.index + 1}"/> cost"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Coefficient of Salary of Each Worker in in Each Task:
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerTaskSalaries}" var="workerTaskSalaryZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskSalaryZ}" var="workerTaskSalary" varStatus="innerLoop">
                        <td>
                            <input type="number" step="any" name="workerTaskSalary" required maxlength="5" placeholder="worker<c:out value="${loop.index + 1}"/>, task<c:out value="${innerLoop.index + 1}"/> salary"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${ph02Model.context_type == 'PRODUCTION_LINE'}">
        <c:if test="${not empty ph01Model.hrMulti || not empty ph01Mode.hrUp}">
            <table>
                <c:if test="${not empty ph01Model.hrMulti}">
                    <%--<c:if test="${ph01Model.hrMulti == 'TRAIN'}">--%>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_train) and (ph01Model.hrMulti == 'TRAIN')}">
                        <tr>
                            <td>
                                Please Enter The Kj Array For "HR Multi-Skill Train Mode" (Kj):
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_train}" var="modeMultiSkillTrain" varStatus="loop">
                                <td>
                                    <input type="number" name="modeMultiSkillTrain" required maxlength="5" placeholder="HR-MultiSkill-Train<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <%--<c:if test="${ph01Model.hrMulti == 'HIRE'}">--%>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_hire) and (ph01Model.hrMulti == 'HIRE')}">
                        <tr>
                            <td>
                                Please Enter The Kj Array For "HR Multi-Skill Hire Mode" (Kj):
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_hire}" var="modeMultiSkillHire" varStatus="loop">
                                <td>
                                    <input type="number" name="modeMultiSkillHire" required maxlength="5" placeholder="HR-MultiSkill-Hire<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${not empty ph01Model.hrUp}">
                    <%--<c:if test="${ph01Model.hrUp == 'TRAIN'}">--%>
                    <c:if test="${(not empty ph02Model.mode_upSkill_train) and (ph01Model.hrUp == 'TRAIN')}">
                        <tr>
                            <td>
                                Please Enter The Uj Array For "HR Up-Skill Train Mode" (Uj):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_train}" var="modeUpSkillTrain" varStatus="loop">
                                <td>
                                    <input type="number" name="modeUpSkillTrain" required maxlength="5" placeholder="HR-UpSkill-Train<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <%--<c:if test="${ph01Model.hrUp == 'HIRE'}">--%>
                    <c:if test="${(not empty ph02Model.mode_upSkill_hire) and (ph01Model.hrUp == 'HIRE')}">
                        <tr>
                            <td>
                                Please Enter The Uj Array For "HR Up-Skill Hire Mode" (Uj):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_hire}" var="modeUpSkillHire" varStatus="loop">
                                <td>
                                    <input type="number" name="modeUpSkillHire" required maxlength="5" placeholder="HR-UpSkill-Hire<c:out value="${loop.index + 1}"/> mode"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
            </table>
        </c:if>

        <c:forEach items="${ph03Model.taskWorkstationWorkerDurations}" var="durations" varStatus="upperLoop">
            <%--<h4>Worker <c:out value="${upperLoop.index + 1}"/></h4>--%>
            <table>
                <tr>
                    <td>
                        (Task, Workstation) Duration for Worker <c:out value="${upperLoop.index + 1}"/>
                    </td>
                </tr>
                <c:forEach items="${durations}" var="durs" varStatus="innerLoop">
                    <tr>
                        <td>
                            Task <c:out value="${innerLoop.index + 1}"/>
                        </td>
                        <c:forEach items="${durs}" var="duration" varStatus="innerInnerLoop">
                            <td>
                                <%--TODO: put the proper placeholder here! :-)--%>
                                <input type="number" name="duration" required maxlength="5" placeholder="task<c:out value="${innerLoop.index + 1}"/>, workstation<c:out value="${innerInnerLoop.index + 1}"/> duration"/>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
        <table>
            <tr>
                <td>
                    Cost of Training Each Worker in Each Workstation:
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerWorkstationTrainingCosts}" var="workerWorkstationTrainingCostZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationTrainingCostZ}" var="workerWorkstationTrainingCost" varStatus="innerLoop">
                        <td>
                            <input type="number" name="workerWorkstationTrainingCost" required maxlength="5" placeholder="worker<c:out value="${loop.index + 1}"/>, workstation<c:out value="${innerLoop.index + 1}"/> cost"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Coefficient of Salary of Each Worker in Each Workstation:
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerWorkstationSalaries}" var="workerWorkstationSalaryZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationSalaryZ}" var="workerWorkstationSalary" varStatus="innerLoop">
                        <td>
                            <input type="number" step="any" name="workerWorkstationSalary" required maxlength="5" placeholder="worker<c:out value="${loop.index + 1}"/>, workstation<c:out value="${innerLoop.index + 1}"/> salary"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <%--<h2> the length of tasksOrders is = ${fn:length(ph03Model.tasksOrders)} </h2>--%>
    <%--<h2> the length of tasksOrdersInt is = ${fn:length(ph03Model.tasksOrdersInt)} </h2>--%>

    <c:if test="${(ph02Model.context_type == 'NO_PRODUCTION_LINE') or ((ph02Model.context_type == 'PRODUCTION_LINE') and (ph02Model.productionLine_type == 'NO_PERMUTATION'))}">
        <p><b>enter the tasks orders matrix. This matrix represents tasks priorities/precedences of each task against others.</b></p><br/>
        <p>
            Please note that the tasks orders matrix comprises (N-1) rows and (N) columns, where N refers to the number of Tasks.<br/>
            For each row, please enter tasks happening after the current task (NEXT TASKS). For instance, if task2 should happen after task1, then in the 1st row (task1), enter "1" under task2. Put "0" in other positions.<br/>
            For each column, please make sure that all elements with "1" value happen before the current task. For example, if task2 should happen after task1, then under the 2ns column (task2) you should have "1" for task1 (row1) and "0" for others.
        </p>
        <table>
            <tr>
                <td>
                    Please Enter Tasks Orders Matrix:
                </td>
            </tr>
            <%--TODO: maybe the input type checkbox is the best option for boolean value entry/selection here! :-)--%>
            <c:forEach items="${ph03Model.tasksOrders}" var="tasksOrderZ" varStatus="loop">
            <%--<c:forEach items="${ph03Model.tasksOrdersInt}" var="tasksOrderZ" varStatus="loop">--%>
                <tr>
                    <td>
                        Task <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${tasksOrderZ}" var="tasksOrder" varStatus="innerLoop">
                        <td>
                            <input type="number" name="tasksOrder" required maxlength="1" min="0" max="1" placeholder="[task <c:out value="${loop.index + 1}"/>, task <c:out value="${innerLoop.index + 1}"/>] order"/>
                            <%--<input type="checkbox" name="tasksOrder" required/>--%>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <table>
        <tr>
            <td>
                Please Select The Type of Scheduling:
            </td>
            <td>
                <input type="radio" name="schedulingType" value="1" required/> Scheduling Off-Site <br/>
                <input type="radio" name="schedulingType" value="2"/> Scheduling No Production Line
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
