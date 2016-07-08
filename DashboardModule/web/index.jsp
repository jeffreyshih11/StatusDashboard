<%@ page contentType="text/html; charset=utf-8" language="java"
         import="org.w3c.dom.Document,org.w3c.dom.NodeList,javax.xml.parsers.DocumentBuilder" errorPage="" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="java.io.File" %>
<%@ page import="java.text.SimpleDateFormat" %>

<link rel="stylesheet" type="text/css" href="Style.css" title="gray">

<%
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

  DocumentBuilder db = dbf.newDocumentBuilder();

  //Document doc = db.parse("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\DashboardModule\\web\\name.xml");
  //File xmlFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\example.xml");
  File xmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\example.xml");
  Document doc = db.parse(xmlFile);

  NodeList Environment = doc.getElementsByTagName("Environment");
  NodeList Revision = doc.getElementsByTagName("Revision");
  NodeList Builder = doc.getElementsByTagName("Builder");
  NodeList Date = doc.getElementsByTagName("Date");
  NodeList BuildStatus = doc.getElementsByTagName("BuildStatus");
%>

<html>

<center><h2>PROJECT ENVIRONMENTS</h2></center>

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
      <%--<th>Smoke Test Status</th>
      <th>Build Tag</th>--%>
    </tr>
    <%
      int i;
      for(i=0;i<=Environment.getLength()-1;i++)
      {
    %>

    <tr>

      <td>
        <%= Environment.item(i).getAttributes().getNamedItem("name").getNodeValue()
        %>
          <% if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("Baseline")){ %>
         <form> <p align="center"><b>Select a Site </b>
            <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
              <option value="">Select one</option>
              <option value=BASELINE_CP_Url>JVS</option>
              <option value=BASELINE_CATS_Url>CATS</option>
              <option value=BASELINE_ServiceDesk_Url>SD</option></select>
          </p></form>

        <% }
            else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEMO")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=DEMO_CP_Url>JVS</option>
            <option value=DEMO_CATS_Url>CATS</option>
            <option value=DEMO_ServiceDesk_Url>SD</option></select>
        </p></form>

        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEV")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=DEV_CP_Url>JVS</option>
            <option value=DEV_CATS_Url>CATS</option>
            <option value=DEV_ServiceDesk_Url>SD</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("GAT")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=GAT_CP_Url>DEMO</option>
            <option value=GAT_CATS_Url>Yahoo</option>
            <option value=GAT_ServiceDesk_Url>Google</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("QA")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=QA_CP_Url>DEMO</option>
            <option value=QA_CATS_Url>Yahoo</option>
            <option value=QA_ServiceDesk_Url>Google</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("UAT")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=UAT_CP_Url>DEMO</option>
            <option value=UAT_CATS_Url>Yahoo</option>
            <option value=UAT_ServiceDesk_Url>Google</option></select>
        </p></form>

        <% } %>
      </td>
      <td>
        <%= Revision.item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>
        <%= Builder.item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>
        <%= Date.item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>

        <% if (BuildStatus.item(i).getFirstChild().getNodeValue().equals("true")) {%>
        <center><img src="LightG.jpg" title="The most recent build succeeded" height="30" width="30"/></center>
        <% }
        else if (BuildStatus.item(i).getFirstChild().getNodeValue().equals("building")) {
        %>
        <center><img src="LightY.png" title="This environment is currently being built" height="30" width="30"/></center>
        <% }
        else {
        %>
        <center><img src="LightR.jpg" title="The most recent build failed" height="30" width="30"/></center>
        <% }
        %>
      </td>
      <td>
        to be implemented
      </td>

    </tr>


    <%
      }
    %>
  </table>
</center>


<p></p>
<center><p> Last updated: <% SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  out.print(formattedDate.format(xmlFile.lastModified()));
%></p></center>
<p></p>

<center><img src="logo.jpg" alt="logo"/></center>

<p></p>
<p></p>


<hr color="#ff7302" width="80%">

<center><a href="https://office.iworkscorp.com/hudson/login?from=%2Fhudson%2F">Connect to Hudson</a></center>

</html>