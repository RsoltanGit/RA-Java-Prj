<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Export Data To Julia!</title>
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<body>
<h2> These are the data collected from phases 01 to 04 to be exported to the CSV file(s). </h2>
<h4>Data From Phase 01 and Phase 02</h4>
<table <%--id="resultTable"--%>>
    <tr>
        <td>
            N (the maximum number of tasks)
        </td>
        <td>
            <c:out value="${ph01Model.task}"/>
        </td>
    </tr>
    <tr>
        <td>
            W (the maximum number of workers)
        </td>
        <td>
            <c:out value="${ph01Model.worker}"/>
        </td>
    </tr>
    <tr>
        <td>
            T (the maximum time)
        </td>
        <td>
            <c:out value="${ph01Model.time}"/>
        </td>
    </tr>
    <tr>
        <td>
            HR Strategy Type
        </td>
        <td>
            <c:out value="${ph01Model.hr}"/>
        </td>
    </tr>
    <c:if test="${ph01Model.hr == 'MULTI_SKILL'}">
        <tr>
            <td>
                HR Multi-Skill Type
            </td>
            <td>
                <c:out value="${ph01Model.hrMulti}"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${ph01Model.hr == 'UP_SKILL'}">
        <tr>
            <td>
                HR Up-Skill Type
            </td>
            <td>
                <c:out value="${ph01Model.hrUp}"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>
            Context Type
        </td>
        <td>
            <c:out value="${ph02Model.context_type}"/>
        </td>
    </tr>
    <c:if test="${ph02Model.context_type == 'PRODUCTION_LINE'}">
        <tr>
            <td>
                The Production Line (Off-Site) Type
            </td>
            <td>
                <c:out value="${ph02Model.productionLine_type}"/>
            </td>
        </tr>
    </c:if>
</table>

<h4>Data From Phase 03</h4>
<table <%--id="resultTable"--%>>
    <tr>
        <td>
            Scheduling Type
        </td>
        <td>
            <c:out value="${ph03Model.scheduling_type}"/>
        </td>
    </tr>
    <tr>
        <td>
            Basic Daily Salary Per Worker
        </td>
        <c:forEach items="${ph03Model.workerDailySalaries}" var="workerDailySalary" varStatus="loop">
            <td>
                <c:out value="${workerDailySalary}"/>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td>
            Cost of Operating The Production Line In Time t
        </td>
        <c:forEach items="${ph03Model.operationCosts}" var="operationCost" varStatus="loop">
            <td>
                <c:out value="${operationCost}"/>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td>
            Revenue of Selling Element i
        </td>
        <c:forEach items="${ph03Model.sellingRevenues}" var="revenue" varStatus="loop">
            <td>
                <c:out value="${revenue}"/>
            </td>
        </c:forEach>
    </tr>
    <c:if test="${ph02Model.context_type == 'NO_PRODUCTION_LINE'}">
        <%-- All the Ki s --%>
        <c:if test="${not empty ph01Model.hrMulti || not empty ph01Mode.hrUp}">
            <table>
                <c:if test="${not empty ph01Model.hrMulti}">
                    <c:if test="${(not empty ph02Model.mode_multiSkill_train) and (ph01Model.hrMulti == 'TRAIN')}">
                        <tr>
                            <td>
                                The Ki Array For "HR Multi-Skill Train Mode" (Ki)
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_train}" var="modeMultiSkillTrain" varStatus="loop">
                                <td>
                                    <c:out value="${modeMultiSkillTrain}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_hire) and (ph01Model.hrMulti == 'HIRE')}">
                        <tr>
                            <td>
                                The Ki Array For "HR Multi-Skill Hire Mode" (Ki)
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_hire}" var="modeMultiSkillHire" varStatus="loop">
                                <td>
                                    <c:out value="${modeMultiSkillHire}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${not empty ph01Model.hrUp}">
                    <c:if test="${(not empty ph02Model.mode_upSkill_train) and (ph01Model.hrUp == 'TRAIN')}">
                        <tr>
                            <td>
                                The Ui Array For "HR Up-Skill Train Mode" (Ui):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_train}" var="modeUpSkillTrain" varStatus="loop">
                                <td>
                                    <c:out value="${modeUpSkillTrain}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <c:if test="${(not empty ph02Model.mode_upSkill_hire) and (ph01Model.hrUp == 'HIRE')}">
                        <tr>
                            <td>
                                The Ui Array For "HR Up-Skill Hire Mode" (Ui):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_hire}" var="modeUpSkillHire" varStatus="loop">
                                <td>
                                    <c:out value="${modeUpSkillHire}"/>
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
                    Duration of Each Procedure (row) When Each Worker (column) Is Assigned To Them
                </td>
            </tr>
            <c:forEach items="${ph03Model.taskWorkerDurations}" var="taskWorkerDurationz" varStatus="upperLoop">
                <tr>
                    <td>
                        Task <c:out value="${upperLoop.index + 1}"/>
                    </td>
                    <c:forEach items="${taskWorkerDurationz}" var="taskWorkerDuration" varStatus="innerLoop">
                        <td>
                            <c:out value="${taskWorkerDuration}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Cost of Training Each Worker for Each Task
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerTaskTrainingCosts}" var="workerTaskTrainCosts" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskTrainCosts}" var="workerTaskTrainCost" varStatus="innerLoop">
                        <td>
                            <c:out value="${workerTaskTrainCost}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Coefficient of Salary of Each Worker in in Each Task
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerTaskSalaries}" var="workerTaskSalaryZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskSalaryZ}" var="workerTaskSalary" varStatus="innerLoop">
                        <td>
                            <c:out value="${workerTaskSalary}"/>
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
                    <c:if test="${(not empty ph02Model.mode_multiSkill_train) and (ph01Model.hrMulti == 'TRAIN')}">
                        <tr>
                            <td>
                                The Kj Array For "HR Multi-Skill Train Mode" (Kj)
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_train}" var="modeMultiSkillTrain" varStatus="loop">
                                <td>
                                    <c:out value="${modeMultiSkillTrain}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <c:if test="${(not empty ph02Model.mode_multiSkill_hire) and (ph01Model.hrMulti == 'HIRE')}">
                        <tr>
                            <td>
                                The Kj Array For "HR Multi-Skill Hire Mode" (Kj):
                            </td>
                            <c:forEach items="${ph02Model.mode_multiSkill_hire}" var="modeMultiSkillHire" varStatus="loop">
                                <td>
                                    <c:out value="${modeMultiSkillHire}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${not empty ph01Model.hrUp}">
                    <c:if test="${(not empty ph02Model.mode_upSkill_train) and (ph01Model.hrUp == 'TRAIN')}">
                        <tr>
                            <td>
                                The Uj Array For "HR Up-Skill Train Mode" (Uj):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_train}" var="modeUpSkillTrain" varStatus="loop">
                                <td>
                                    <c:out value="${modeUpSkillTrain}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                    <c:if test="${(not empty ph02Model.mode_upSkill_hire) and (ph01Model.hrUp == 'HIRE')}">
                        <tr>
                            <td>
                                The Uj Array For "HR Up-Skill Hire Mode" (Uj):
                            </td>
                            <c:forEach items="${ph02Model.mode_upSkill_hire}" var="modeUpSkillHire" varStatus="loop">
                                <td>
                                    <c:out value="${modeUpSkillHire}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:if>
            </table>
        </c:if>
        <c:forEach items="${ph03Model.taskWorkstationWorkerDurations}" var="durations" varStatus="upperLoop">
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
                                <c:out value="${duration}"/>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
        <table>
            <tr>
                <td>
                    Cost of Training Each Worker in Each Workstation
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerWorkstationTrainingCosts}" var="workerWorkstationTrainingCostZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationTrainingCostZ}" var="workerWorkstationTrainingCost" varStatus="innerLoop">
                        <td>
                            <c:out value="${workerWorkstationTrainingCost}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    Coefficient of Salary of Each Worker in Each Workstation
                </td>
            </tr>
            <c:forEach items="${ph03Model.workerWorkstationSalaries}" var="workerWorkstationSalaryZ" varStatus="loop">
                <tr>
                    <td>
                        Worker <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationSalaryZ}" var="workerWorkstationSalary" varStatus="innerLoop">
                        <td>
                            <c:out value="${workerWorkstationSalary}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${(ph02Model.context_type == 'NO_PRODUCTION_LINE') or ((ph02Model.context_type == 'PRODUCTION_LINE') and (ph02Model.productionLine_type == 'NO_PERMUTATION'))}">
        <table>
            <tr>
                <td>
                    Tasks Orders Matrix
                </td>
            </tr>
            <c:forEach items="${ph03Model.tasksOrders}" var="tasksOrderZ" varStatus="loop">
                <tr>
                    <td>
                        Task <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${tasksOrderZ}" var="tasksOrder" varStatus="innerLoop">
                        <td>
                            <c:out value="${tasksOrder}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</table>

<h4>Data From Phase 04</h4>
<table>
    <c:if test="${ph03Model.scheduling_type == 'SCHEDULING_OFF_SITE'}">
        <tr>
            <td>
                Skill Matrix (worker per workstation)
            </td>
        </tr>
        <c:forEach items="${ph04Model.workerWorkstationSkillMatrix}" var="workerWorkstationSkills" varStatus="loop1">
            <tr>
                <td>
                    Worker <c:out value="${loop1.index + 1}"/>
                </td>
                <c:forEach items="${workerWorkstationSkills}" var="workerWorkstationSkill" varStatus="innerLoop">
                    <td>
                        <c:out value="${workerWorkstationSkill}"/>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${ph03Model.scheduling_type == 'SCHEDULING_NO_PRODUCTION_LINE'}">
        <tr>
            <td>
                Skill Matrix (worker per task)
            </td>
        </tr>
        <c:forEach items="${ph04Model.workerTaskSkillMatrix}" var="workerTaskSkills" varStatus="loop2">
            <tr>
                <td>
                    Worker <c:out value="${loop2.index + 1}"/>
                </td>
                <c:forEach items="${workerTaskSkills}" var="workerTaskSkill" varStatus="innerLoop">
                    <td>
                        <c:out value="${workerTaskSkill}"/>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </c:if>
</table>

<form action="exportData" method="post">
    <input type="submit" value="Export Data To CSV"/>
</form>

</body>
</html>
