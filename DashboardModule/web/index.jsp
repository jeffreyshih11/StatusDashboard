<%@ page contentType="text/html; charset=utf-8" language="java"
         import="org.w3c.dom.Document,org.w3c.dom.NodeList,javax.xml.parsers.DocumentBuilder" errorPage="" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="java.io.File" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>

<link rel="stylesheet" type="text/css" href="Style.css" title="gray">

<%
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

  DocumentBuilder db = dbf.newDocumentBuilder();

  //Document doc = db.parse("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\DashboardModule\\web\\name.xml");
  File xmlFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\example.xml");
  //File xmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\example.xml");
  Document doc = db.parse(xmlFile);

  NodeList Environment = doc.getElementsByTagName("Environment");
  NodeList Revision = doc.getElementsByTagName("Revision");
  NodeList Builder = doc.getElementsByTagName("Builder");
  NodeList Date = doc.getElementsByTagName("Date");
  NodeList BuildStatus = doc.getElementsByTagName("BuildStatus");

  Properties CONFIG = null;
  final String CONFIG_PATH = "//src//main//resources//data//";
  CONFIG = new Properties();
  //FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
  FileInputStream fn = new FileInputStream("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\src\\main\\resources\\config.properties");
  CONFIG.load(fn);

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
              <option value=<%=CONFIG.getProperty("BASELINE_CP_Url")%>>JVS</option>
              <option value=<%=CONFIG.getProperty("BASELINE_CATS_Url")%>>CATS</option>
              <option value=<%=CONFIG.getProperty("BASELINE_ServiceDesk_Url")%>>SD</option></select>
          </p></form>

        <% }
            else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEMO")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("DEMO_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("DEMO_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("DEMO_ServiceDesk_Url")%>>SD</option></select>
        </p></form>

        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEV")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("DEV_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("DEV_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("DEV_ServiceDesk_Url")%>>SD</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("GAT")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("GAT_CP_Url")%>>DEMO</option>
            <option value=<%=CONFIG.getProperty("GAT_CATS_Url")%>>Yahoo</option>
            <option value=<%=CONFIG.getProperty("GAT_ServiceDesk_Url")%>>Google</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("QA")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("QA_CP_Url")%>>DEMO</option>
            <option value=<%=CONFIG.getProperty("QA_CATS_Url")%>>Yahoo</option>
            <option value=<%=CONFIG.getProperty("QA_ServiceDesk_Url")%>>Google</option></select>
        </p></form>
        <% }
        else if (Environment.item(i).getAttributes().getNamedItem("name").getNodeValue().equals("UAT")){ %>
        <form><p align="center"><b>Select a Site </b>
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: #0000FF" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("UAT_CP_Url")%>>DEMO</option>
            <option value=<%=CONFIG.getProperty("UAT_CATS_Url")%>>Yahoo</option>
            <option value=<%=CONFIG.getProperty("UAT_ServiceDesk_Url")%>>Google</option></select>
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