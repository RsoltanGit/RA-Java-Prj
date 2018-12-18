<%--TODO: This JSP file "phase02.jsp" along with the "Phase02Servlet.java" file will be commented due to the client's request in merging the phase01 and phase02 items together. Should be uncommented if needed in the future.--%>
<%--<jsp:include page="utils/init.jsp"/>--%>

<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script>
        function resetOtherz() {
            var x = document.getElementById('productionLine1');
            var y = document.getElementById('productionLine2');
            if (x != null && x.style.display == 'block') {
                x.style.display = 'none';
            }
            if (y != null && y.style.display == 'block') {
                y.style.display = 'none';
            }
        }

        function pickProductionLine() {
            var x = document.getElementById('productionLine1');
            var y = document.getElementById('productionLine2');
            x.style.display = 'block';
            y.style.display = 'block';
        }
    </script>
</head>
<body>
<h2>Phase 02 Data Collection</h2><br/>

<h4>Phase 01 Data Entered</h4>
    <table>
        <tr>
            <td>
                Task (N):
            </td>
            <td>
                <c:out value="${ph01Model.task}"/>
            </td>
        </tr>
        <tr>
            <td>
                Worker (W):
            </td>
            <td>
                <c:out value="${ph01Model.worker}"/>
            </td>
        </tr>
        <tr>
            <td>
                Time (T):
            </td>
            <td>
                <c:out value="${ph01Model.time}"/>
            </td>
        </tr>
        <tr>
            <td>
                HR Skill Type:
            </td>
            <td>
                <c:out value="${ph01Model.hr}"/>
            </td>
        </tr>
        <c:if test="${not empty ph01Model.hrMulti}">
            <tr>
                <td>
                    HR Multi-Skill Type:
                </td>
                <td>
                    <c:out value="${ph01Model.hrMulti}"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${not empty ph01Model.hrUp}">
            <tr>
                <td>
                    <c:out value="${ph01Model.hrUp}"/>
                </td>
            </tr>
        </c:if>
    </table>

    <h4>Please enter the following data based on the above entered elements:</h4>
    <form action="phase02" method="post">
        <table>
            <tr>
                <td>
                    K1 Array Info:
                </td>
                <td>
                    <c:forEach items="${ph02Model.revenues}" var="revenue" varStatus="loop">
                        <td>
                            <input type="number" name="revenue" required maxlength="5" placeholder="revenue <c:out value="${loop.index + 1}"/>"/>
                        </td>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>
                    Please Select Whether Or Not You are Working on Production Line:
                </td>
                <td>
                    <input type="radio" name="context" value="1" required onclick="resetOtherz();"/> No Production Line <br/>
                    <input type="radio" name="context" value="2" onclick="pickProductionLine();"/> Production Line (Off-Site)
                </td>
            </tr>
            <tr id="productionLine1" style="display: none;">
                <td>
                    Please Enter The Number of Workstations (M):
                </td>
                <td>
                    <input type="number" name="workstation" required maxlength="5"/>
                </td>
            </tr>
            <tr id="productionLine2" style="display: none;">
                <td>
                    Please Select Whether You Are Working with Permutation On The Production Line:
                </td>
                <td>
                    <input type="radio" name="productionLineType" value="1"/> No_Permutation <br/>
                    <input type="radio" name="productionLineType" value="2"/> Permutation
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
--%>
