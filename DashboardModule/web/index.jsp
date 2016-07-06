<html>


<link rel="stylesheet" type="text/css" href="Style.css" title="gray">

<center><h2>DISS PROJECT ENVIRONMENTS</h2></center>

<p></p>
<p></p>

<center>
  <table>
    <tr>
      <th>Environment</th>
      <th>Revision</th>
      <th>Builder</th>
      <th>Date</th>
      <th>Build Status</th>
      <th>Smoke Test Status</th>
      <th>Build Tag</th>
    </tr>
    <tr>

      <td>Baseline</td>
      <td>20673</td>
      <td>N/A</td>
      <td>N/A 00:00</td>
      <td><%
        String BaseBuild = "successful";

        if (BaseBuild.equals("successful")) {%>
        <center><img src="LightG.jpg" title ="Most recent baseline build was successful" height="30" width="30"/></center>
        <% } else if (BaseBuild.equals("building")) { %>
        <center><img src="LightY.png" title ="Baseline is currently building" height="30" width="30"/></center>
        <% } else { %>
        <center><img src="LightR.jpg" title ="Most recent baseline build failed" height="30" width="30"/></center>
        <% }
        %>

        </font></td>
      <td><%
        String BaseSmoke = "building";

        if(BaseSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(BaseSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>
    </tr>
    <tr>
      <td>DEMO</td>
      <td>20658</td>
      <td>Jermaine</td>
      <td>04/04/16 00:00</td>
      <td><%
        String DemoBuild = "successful";

        if (DemoBuild.equals("successful")) {%>
        <center><img src="LightG.jpg" title ="Most recent demo build was successful" height="30" width="30"/></center>
        <% } else if (DemoBuild.equals("building")) { %>
        <center><img src="LightY.png" title ="Demo is currently building" height="30" width="30"/></center>
        <% } else { %>
        <center><img src="LightR.jpg" title="Most recent demo build failed" height="30" width="30"/></center>
        <% }
        %></td>
      <td><%
        String DemoSmoke = "successful";

        if(DemoSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(DemoSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>
    </tr>

    <tr>
      <td>DEV</td>
      <td>20644</td>
      <td>N/A</td>
      <td>N/A 00:00</td>
      <td><%
        String DevBuild = "red";

        if (DevBuild.equals("successful")) {%>
        <center><img src="LightG.jpg" title ="Most recent dev build was successful"height="30" width="30"/></center>
        <% } else if (DevBuild.equals("building")) { %>
        <center><img src="LightY.png" title ="Dev is currently building" height="30" width="30"/></center>
        <% } else { %>
        <center><img src="LightR.jpg" title ="Most recent dev build failed" height="30" width="30"/></center>
        <% }
        %></td>
      <td><%
        String DevSmoke = "red";

        if(DevSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(DevSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>

    </tr>
    <tr>
      <td>GAT</td>
      <td>20658</td>
      <td>Ketan</td>
      <td>11/19/15 00:00</td>
      <td><%
        String GatBuild = "building";
        if (GatBuild.equals("successful")) {%>
        <center><img src="LightG.jpg" title ="Most recent GAT build was successful" height="30" width="30"/></center>
        <% } else if (GatBuild.equals("building")) { %>
        <center><img src="LightY.png" title ="GAT is currently building" height="30" width="30"/></center>
        <% } else { %>
        <center><img src="LightR.jpg" title ="Most recent GAT build failed" height="30" width="30"/></center>
        <% }
        %></td>
      <td><%
        String GatSmoke = "building";

        if(GatSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(GatSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>
    </tr>
    <tr>
      <td>QA</td>
      <td>20673</td>
      <td>Jeremy</td>
      <td>04/13/16 00:00</td>
      <td><%
        String QaBuild = "successful";

        if (QaBuild.equals("successful")) {%>
        <center><img src="LightG.jpg" title ="Most recent QA build was successful" height="30" width="30"/></center>
        <% } else if (QaBuild.equals("building")) { %>
        <center><img src="LightY.png" title ="QA is currently building" height="30" width="30"/></center>
        <% } else { %>
        <center><img src="LightR.jpg" title ="Most recent QA build failed" height="30" width="30"/></center>
        <% }
        %></td>
      <td><%
        String QaSmoke = "successful";

        if(QaSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(QaSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>
    </tr>
    <tr>
      <td>UAT</td>
      <td>20673</td>
      <td>Jermaine</td>
      <td>04/12/16 00:00</td>

      <td>
        <%
          String UatBuild = "red";

          if(UatBuild.equals("successful")){%>
        <center><img src="LightG.jpg" title ="Most recent UAT build was successful" height = "30" width = "30"/></center>
        <% }
        else if(UatBuild.equals("building")){ %>
        <center><img src="LightY.png" title ="UAT is currently building" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" title ="Most recent UAT build failed" height = "30" width = "30"/></center>
        <% }
        %>
      </td>

      <td><%
        String UatSmoke = "red";

        if(UatSmoke.equals("successful")){%>
        <center><img src="LightG.jpg" height = "30" width = "30"/></center>
        <% }
        else if(UatSmoke.equals("building")){ %>
        <center><img src="LightY.png" height = "30" width = "30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" height = "30" width = "30"/></center>
        <% }
        %></td>
    </tr>
  </table>
</center>


<p></p>
<p></p>

<center><img src="logo.jpg" alt="logo" /></center>

<p></p>
<p></p>

<hr color="#ff7302" width="80%">

</html>