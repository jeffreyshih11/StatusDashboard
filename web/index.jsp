<%@ page contentType="text/html; charset=utf-8" language="java"
         import="org.w3c.dom.Document,org.w3c.dom.NodeList,javax.xml.parsers.DocumentBuilder" errorPage="" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Properties" %>

<link rel="stylesheet" type="text/css" href="Style.css" title="gray">

<%
  /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

  DocumentBuilder db = dbf.newDocumentBuilder();

  //Document doc = db.parse("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\DashboardModule\\web\\name.xml");
  File xmlFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\example.xml");
  //File xmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\example.xml");
  Document doc = db.parse(xmlFile);

  NodeList Environment = doc.getElementsByTagName("Environment");
  NodeList Revision = doc.getElementsByTagName("Revision");
  NodeList Builder = doc.getElementsByTagName("Builder");
  NodeList Date = doc.getElementsByTagName("Date");
  NodeList BuildStatus = doc.getElementsByTagName("BuildStatus");*/

  /*File SmokeXmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\SmokeTest.xml");
  Document SmokeDoc = db.parse(SmokeXmlFile);

  NodeList SmokeEnvironment = SmokeDoc.getElementsByTagName("SmokeEnvironment");
  NodeList SmokeRevision = SmokeDoc.getElementsByTagName("SmokeRevision");
  NodeList SmokeStatus = SmokeDoc.getElementsByTagName("SmokeStatus");*/

  com.iworkscorp.dashboard.hudson.Controller controller = new com.iworkscorp.dashboard.hudson.Controller();

  //build status doc
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  File xmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\environmentStatus.xml");
  Document environmentXML = db.parse(xmlFile);
  ArrayList<NodeList> buildInfo = controller.readBuildXML(environmentXML);


  //smoke test status doc

  File SmokeXmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\smokeStatus.xml");
  Document SmokeDoc = db.parse(SmokeXmlFile);
  ArrayList<NodeList> smokeStatus = controller.readSmokeTestXML(SmokeDoc);

  Properties CONFIG = null;
  final String CONFIG_PATH = "//src//main//resources//data//";
  CONFIG = new Properties();
  //FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
  FileInputStream fn = new FileInputStream("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\src\\main\\resources\\config.properties");
  CONFIG.load(fn);

%>

<html>

<center><h2>PROJECT ENVIRONMENTS</h2></center>

<p></p>
<p></p>

<center>
  <table BORDER=3 RULES=ALL FRAME=BORDER style="width:800px">
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
      for(i=0;i<=buildInfo.get(0).getLength()-1;i++)
      {
    %>

    <tr>

      <td>
        <b><%= buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue()
        %></b>
          <% if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("Baseline")){ %>
         <form> <p align="left">Go to Site
            <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
              <option value="">Select one</option>
              <option value=<%=CONFIG.getProperty("BASELINE_CP_Url")%>>JVS</option>
              <option value=<%=CONFIG.getProperty("BASELINE_CATS_Url")%>>CATS</option>
              <option value=<%=CONFIG.getProperty("BASELINE_ServiceDesk_Url")%>>SD</option></select>
          </p></form>

        <% }
            else if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEMO")){ %>
        <form><p align="left">Go to Site
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("DEMO_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("DEMO_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("DEMO_ServiceDesk_Url")%>>SD</option></select>
        </p></form>

        <% }
        else if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("DEV")){ %>
        <form><p align="left">Go to Site
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("DEV_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("DEV_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("DEV_ServiceDesk_Url")%>>SD</option></select>
        </p></form>
        <% }
        else if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("GAT")){ %>
        <form><p align="left">Go to Site
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("GAT_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("GAT_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("GAT_ServiceDesk_Url")%>>SD</option></select>
        </p></form>
        <% }
        else if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("QA")){ %>
        <form><p align="left">Go to Site
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("QA_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("QA_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("QA_ServiceDesk_Url")%>>SD</option></select>
        </p></form>
        <% }
        else if (buildInfo.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue().equals("UAT")){ %>
        <form><p align="left">Go to Site
          <select onchange="window.open(setit.options[setit.selectedIndex].value)" id="setit" style="color: black" size="1" name="test">
            <option value="">Select one</option>
            <option value=<%=CONFIG.getProperty("UAT_CP_Url")%>>JVS</option>
            <option value=<%=CONFIG.getProperty("UAT_CATS_Url")%>>CATS</option>
            <option value=<%=CONFIG.getProperty("UAT_ServiceDesk_Url")%>>SD</option></select>
        </p></form>

        <% } %>
      </td>
      <td>
        <%= buildInfo.get(1).item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>
        <%= buildInfo.get(2).item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>
        <%= buildInfo.get(3).item(i).getFirstChild().getNodeValue()%>
      </td>
      <td>

        <% if (buildInfo.get(4).item(i).getFirstChild().getNodeValue().equals("true")) {%>
        <center><img src="LightG.jpg" title="The most recent build succeeded" height="30" width="30"/></center>
        <% }
        else if (buildInfo.get(4).item(i).getFirstChild().getNodeValue().equals("building")) {
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
        <% if(smokeStatus.get(2).item(i).getFirstChild().getNodeValue().equals("true")){ %>
        <center><img src="LightG.jpg" title="The most recent smoke test succeeded" height="30" width="30"/></center>
        <% }
        else{ %>
        <center><img src="LightR.jpg" title="The most recent smoke test failed" height="30" width="30"/></center>
        <% } %>
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