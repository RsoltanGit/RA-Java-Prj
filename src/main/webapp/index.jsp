<html>
<head>
    <script>
        function resetOthers() {
            var x = document.getElementById('hrMultiSkill');
            var y = document.getElementById('hrUpSkill');
            if (x != null && x.style.display == 'block') {
                x.style.display = 'none';
            }
            if (y != null && y.style.display == 'block') {
                y.style.display = 'none';
            }
        }

        function pickTrainHireMultiSkill() {
            var x = document.getElementById('hrMultiSkill');
            var y = document.getElementById('hrUpSkill');
            x.style.display = 'block';
            if (y != null && y.style.display == 'block') {
                y.style.display = 'none';
            }
        }

        function pickTrainHireUpSkill() {
            var x = document.getElementById('hrMultiSkill');
            var y = document.getElementById('hrUpSkill');
            y.style.display = 'block';
            if (x != null && x.style.display == 'block') {
                x.style.display = 'none';
            }
        }

        //from "phase02.jsp"
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
<h2>Phase 01 & 02 Data Collection</h2>
<form action="main" method="post">
    <table>
        <tbody>
            <tr>
                <td>
                    N (the maximum number of tasks)
                </td>
                <td>
                    <input type="number" name="task" required maxlength="5" placeholder="tasks number"/>
                </td>
            </tr>
<%--
            <tr>
                <td>
                    M (the maximum number of workstations)
                </td>
                <td>
                    <input type="text" name="workstation"/>
                </td>
            </tr>
--%>
            <tr>
                <td>
                    W (the maximum number of workers)
                </td>
                <td>
                    <input type="number" name="worker" required maxlength="5" placeholder="workers number"/>
                </td>
            </tr>
            <tr>
                <td>
                    T (the maximum time)
                </td>
                <td>
                    <input type="number" name="time" required maxlength="5" placeholder="max time desired"/>
                </td>
            </tr>
<%--
            <tr>
                <td>
                    K (the mode)
                </td>
                <td>
                    <input type="text" name="mode"/>
                </td>
            </tr>
--%>
            <tr>
                <td>
                    Please Select The Type of HR
                </td>
                <td>
                    <input type="radio" name="hr" value="1" onclick="resetOthers();" required/> Single-Skill <br/>
                    <input type="radio" name="hr" value="2" onclick="pickTrainHireMultiSkill();"/> Multi-Skill <br/>
                    <input type="radio" name="hr" value="3" onclick="pickTrainHireUpSkill();"/> Up-Skill
                </td>
            </tr>
            <tr id="hrMultiSkill" style="display: none;">
                <td>
                    Please Select HR Multi-Skill Type
                </td>
                <td>
                    <input type="radio" name="hrMulti" value="1"/> Train <br/>
                    <input type="radio" name="hrMulti" value="2"/> Hire
                </td>
            </tr>
            <tr id="hrUpSkill" style="display: none;">
                <td>
                    Please Select HR Up-Skill Type
                </td>
                <td>
                    <input type="radio" name="hrUp" value="1"/> Train <br/>
                    <input type="radio" name="hrUp" value="2"/> Hire
                </td>
            </tr>

            <%--from "phase02.jsp" - start--%>
<%--
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
--%>
            <%--from "phase02.jsp" - end--%>

            <tr>
                <td>
                    <input type="submit"/>
                </td>
                <td>
                    <input type="reset"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>
</body>
</html>

