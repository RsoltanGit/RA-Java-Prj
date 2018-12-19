<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        Phase 04 Data Collection
    </title>
</head>
<body>
<h2>Phase 04 Data Collection</h2><br/>

<h4>Please Enter The Skill Matrix Based on The Data You Entered on The Previous Phases.</h4><br/>
<form action="phase04" method="post">
    <table>
        <c:if test="${ph03Model.scheduling_type == 'SCHEDULING_OFF_SITE'}">
            <tr>
                <td>
                    Skill Matrix (worker per workstation):
                </td>
            </tr>
            <c:forEach items="${ph04Model.workerWorkstationSkillMatrix}" var="workerWorkstationSkills" varStatus="loop1">
                <tr>
                    <td>
                        Worker <c:out value="${loop1.index + 1}"/>
                    </td>
                    <c:forEach items="${workerWorkstationSkills}" var="workerWorkstationSkill" varStatus="innerLoop">
                        <td>
                            <input type="number" name="workerWorkstationSkill" required maxlength="1" min="0" max="1" placeholder="worker<c:out value="${loop1.index + 1}"/> workstation<c:out value="${innerLoop.index + 1}"/> skill"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${ph03Model.scheduling_type == 'SCHEDULING_NO_PRODUCTION_LINE'}">
            <tr>
                <td>
                    Skill Matrix (worker per task):
                </td>
            </tr>
            <c:forEach items="${ph04Model.workerTaskSkillMatrix}" var="workerTaskSkills" varStatus="loop2">
                <tr>
                    <td>
                        Worker <c:out value="${loop2.index + 1}"/>
                    </td>
                    <c:forEach items="${workerTaskSkills}" var="workerTaskSkill" varStatus="innerLoop">
                        <td>
                            <input type="number" name="workerTaskSkill" required maxlength="1" min="0" max="1" placeholder="worker<c:out value="${loop2.index + 1}"/> task<c:out value="${innerLoop.index + 1}"/> skill"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td>
                <input type="submit"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
