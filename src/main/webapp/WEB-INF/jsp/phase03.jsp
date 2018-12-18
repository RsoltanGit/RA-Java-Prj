<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script></script>
</head>
<body>
<h2>Phase 03 Data Collection</h2><br/>

<h4>Phase 02 Data Entered</h4>
<table>
    <tr>
        <td>
            Context Type:
        </td>
        <td>
            <c:out value="${ph02Model.context_type}"/>
        </td>
    </tr>
    <c:if test="${ph02Model.context_type == 'PRODUCTION_LINE'}">
        <tr>
            <td>
                Production Line (Off-Site) Type:
            </td>
            <td>
                <c:out value="${ph02Model.productionLine_type}"/>
            </td>
        </tr>
    </c:if>
</table>

<h4>Please enter the following data based on the above entered elements:</h4>
<form action="phase03" method="post">
    <table>
        <tr>
            <td>
                Basic Daily Salary Per Worker:
            </td>
            <c:forEach items="${ph03Model.workerDailySalaries}" var="workerDailySalary" varStatus="loop">
                <td>
                    <input type="number" step="any" name="workerDailySalary" required maxlength="5" placeholder="salary <c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Cost of Operating The Production Line In Time t:
            </td>
            <c:forEach items="${ph03Model.operationCosts}" var="operationCost" varStatus="loop">
                <td>
                    <input type="number" name="operationCost" required maxlength="5" placeholder="cose <c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Revenue of Selling Element i:
            </td>
            <c:forEach items="${ph03Model.sellingRevenues}" var="revenue" varStatus="loop">
                <td>
                    <input type="number" name="revenue" required maxlength="5" placeholder="revenue <c:out value="${loop.index + 1}"/>"/>
                </td>
            </c:forEach>
        </tr>
        <c:if test="${ph02Model.context_type == 'NO_PRODUCTION_LINE'}">
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
                            <input type="number" name="taskWorkerDuration" required maxlength="5" placeholder="worker <c:out value="${upperLoop.index + 1}"/>'s duration <c:out value="${innerLoop.index + 1}"/>"/>
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
                            <input type="number" name="workerTaskTrainCost" required maxlength="5" placeholder="worker <c:out value="${loop.index + 1}"/>'s cost <c:out value="${innerLoop.index + 1}"/>"/>
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
                            <input type="number" step="any" name="workerTaskSalary" required maxlength="5" placeholder="worker <c:out value="${loop.index + 1}"/>'s salary <c:out value="${innerLoop.index + 1}"/>"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <c:if test="${ph02Model.context_type == 'PRODUCTION_LINE'}">
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
                                <input type="number" name="duration" required maxlength="5" placeholder="duration <c:out value="${innerInnerLoop.index + 1}"/>"/>
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
                            <input type="number" name="workerWorkstationTrainingCost" required maxlength="5" placeholder="worker <c:out value="${loop.index + 1}"/>'s cost <c:out value="${innerLoop.index + 1}"/>"/>
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
                            <input type="number" step="any" name="workerWorkstationSalary" required maxlength="5" placeholder="worker <c:out value="${loop.index + 1}"/>'s salary <c:out value="${innerLoop.index + 1}"/>"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${(ph02Model.context_type == 'NO_PRODUCTION_LINE') || ((ph02Model.context_type == 'PRODUCTION_LINE') && (ph02Model.productionLine_type == 'NO_PERMUTATION'))}">
        <h4>Please enter the tasks orders matrix. This matrix represents tasks priorities/precedences of each task against others.</h4><br/>

        <h4>Please note that the tasks orders matrix comprises (N-1) rows and (N) columns, where N refers to the number of Tasks.</h4><br/>
        <h4>For each row, please enter tasks happening after the current task (NEXT TASKS). For instance, if task2 should happen after task1, then in the 1st row (task1), enter 1 under task2.</h4><br/>
        <h4>For each column, please make sure that all elements with 1, happen before the current task. For example, if task2 should happen after task1, then under the 2ns column (task2) you should have 1 for task1 (row1).</h4>
        <table>
            <tr>
                <td>
                    Please Enter Tasks Orders Matrix:
                </td>
            </tr>
            <%--TODO: maybe the input type checkbox is the best option for boolean value entry/selection here! :-)--%>
            <c:forEach items="${ph03Model.tasksOrders}" var="tasksOrderZ" varStatus="loop">
                <tr>
                    <td>
                        Task <c:out value="${loop.index + 1}"/>
                    </td>
                    <c:forEach items="${tasksOrderZ}" var="tasksOrder" varStatus="innerLoop">
                        <td>
                            <<input type="number" name="tasksOrder" required maxlength="5" placeholder="[task <c:out value="${loop.index + 1}"/>, task <c:out value="${innerLoop.index + 1}"/>] order"/>
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
