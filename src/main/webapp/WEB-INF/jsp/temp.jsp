<%--
  Created by IntelliJ IDEA.
  User: e16380
  Date: 11/12/2018
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title> Title </title>
</head>
<body>

<h4>Phase 01 Data Entered</h4>
<table>
    <tr>
        <td>
            Task (N):
        </td>
        <td>
            <c:out value="${ph01Model.task}"/>
            <%--jsp: <jsp:getProperty name="model" property="task"/>--%>
        </td>
    </tr>
    <%--
            <tr>
                <td>
                    Workstation (M):
                </td>
                <td>
                    <c:out value="${ph01Model.workstation}"/>
                    &lt;%&ndash;<jsp:getProperty name="model" property="workstation"/>&ndash;%&gt;
                </td>
            </tr>
    --%>
    <tr>
        <td>
            Worker (W):
        </td>
        <td>
            <c:out value="${ph01Model.worker}"/>
            <%--<jsp:getProperty name="model" property="worker"/>--%>
        </td>
    </tr>
    <tr>
        <td>
            Time (T):
        </td>
        <td>
            <c:out value="${ph01Model.time}"/>
            <%--<jsp:getProperty name="model" property="time"/>--%>
        </td>
    </tr>
    <%--
            <tr>
                <td>
                    Mode (K):
                </td>
                <td>
                    <c:out value="${ph01Model.mode}"/>
                    &lt;%&ndash;<jsp:getProperty name="model" property="mode"/>&ndash;%&gt;
                </td>
            </tr>
    --%>
    <tr>
        <td>
            HR Skill Type:
        </td>
        <td>
            <c:out value="${ph01Model.hr}"/>
            <%--<c:out value="${ph01Model.hrMulti}"/> <br/>--%>
            <%--<c:out value="${ph01Model.hrUp}"/>--%>
        </td>
    </tr>
</table>

<h4>Please enter the following data based on the above entered elements:</h4>
<form action="phase02" method="post">
    <table>
        <tr>
            <td>
                Basic daily salary of each worker
            </td>
            <%--<c:forEach begin="1" end="10" varStatus="loop">--%>
            <%--${loop.index}--%>
            <%--</c:forEach>--%>
            <c:forEach items="${ph02Model.salaries}" var="salary">
                <td>
                    <input type="text" name="salary"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Cost of operating the production line in each time
            </td>
            <c:forEach items="${ph02Model.costs}" varStatus="cost">
                <td>
                    <input type="text" name="cost"/>
                </td>
            </c:forEach>
        </tr>
        <tr>
            <td>
                Revenue of selling each element
            </td>
            <c:forEach items="${ph02Model.revenues}" var="revenue">
                <td>
                    <input type="text" name="revenue"/>
                </td>
            </c:forEach>
        </tr>
    </table>
    <%--
            <table>
                <tr>
                    <td>
                        Skill matrix ('1' if the worker i can be allocated to the station j; '0' otherwise)
                    </td>
                </tr>
                <c:forEach items="${ph02Model.skills}" var="workersSkills">
                    <tr>
                        <c:forEach items="${workersSkills}" var="skill">
                            <td>
                                <input type="text" name="skill"/>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
    --%>
    <input type="submit"/>
</form>

</body>
</html>
