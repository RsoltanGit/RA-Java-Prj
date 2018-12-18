<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <script></script>
    </title>
</head>
<body>
<h2>Phase 04 Data Collection</h2><br/>

<h4>Phase 03 Data Entered</h4>
<table>
    <tr>
        <td>
            The Type of Scheduling:
        </td>
        <td>
            <c:out value="${ph03Model.scheduling_type}"/>
        </td>
    </tr>
</table>

<h2>Please Enter The Skill Matrix Based on The Data You Entered on The Previous Phases.</h2><br/>
<form action="phase04" method="post">
    <c:if test="${phase03Model.scheduling_type == 'SCHEDULING_OFF_SITE'}">
        <table>
            <tr>
                <td>
                    Skill Matrix (worker per workstation):
                </td>
            </tr>
            <c:forEach items="${phase04Model.workerWorkstationSkillMatrix}" var="workerWorkstationSkills" varStatus="loop1">
                <tr>
                    <td>
                        Worker <c:out value="${loop1.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationSkills}" var="workerWorkstationSkill" varStatus="innerLoop">
                        <td>
                            <input type="number" name="workerWorkstationSkills" required maxlength="5" placeholder="skill <c:out value="${innerLoop.index + 1}"/>"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${phase03Model.scheduling_type == 'SCHEDULING_NO_PRODUCTION_LINE'}">
        <table>
            <tr>
                <td>
                    Skill Matrix (worker per task):
                </td>
            </tr>
            <c:forEach items="${phase04Model.workerTaskSkillMatrix}" var="workerTaskSkills" varStatus="loop2">
                <tr>
                    <td>
                        Worker <c:out value="${loop2.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskSkills}" var="workerTaskSkill">
                        <td>
                            <<input type="number" name="workerTaskSkill" required maxlength="5"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</form>

</body>
</html>
