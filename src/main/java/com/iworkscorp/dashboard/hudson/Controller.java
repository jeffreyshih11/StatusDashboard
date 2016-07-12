package com.iworkscorp.dashboard.hudson;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by jshih on 7/12/2016.
 */
public class Controller {

    StatusFetcher statusFetcher;
    ArrayList<SmokeTest> smokeTestArrayList;
    CreateXML xmlCreator;

    public Controller(){
        statusFetcher = new StatusFetcher();
        smokeTestArrayList = new ArrayList<>();
        xmlCreator = new CreateXML();
    }


    public boolean generateBuildInfo() throws Exception {
        TestBase.initialize();
        statusFetcher.initialize();
        if(statusFetcher.collectFromHudson()) {
            ArrayList<Build> builds = statusFetcher.getAllMostRecents();
            xmlCreator.createXML(builds);
        }
        return true;
    }

    public ArrayList<NodeList> readBuildXML(Document doc){
        /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();*/

        //Document doc = db.parse("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\DashboardModule\\web\\name.xml");
        //File xmlFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\example.xml");
        //File xmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\example.xml");
        //Document doc = db.parse(xmlFile);

        ArrayList<NodeList> buildInfoList = new ArrayList<>();
        NodeList Environment = doc.getElementsByTagName("Environment");
        NodeList Revision = doc.getElementsByTagName("Revision");
        NodeList Builder = doc.getElementsByTagName("Builder");
        NodeList Date = doc.getElementsByTagName("Date");
        NodeList BuildStatus = doc.getElementsByTagName("BuildStatus");

        buildInfoList.add(Environment);
        buildInfoList.add(Revision);
        buildInfoList.add(Builder);
        buildInfoList.add(Date);
        buildInfoList.add(BuildStatus);

        return buildInfoList;

    }

    public ArrayList<NodeList> readSmokeTestXML(Document SmokeDoc){
        //File SmokeXmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\SmokeTest.xml");
        //Document SmokeDoc = db.parse(doc);

        ArrayList<NodeList> smokeTestInfo = new ArrayList<>();

        NodeList SmokeEnvironment = SmokeDoc.getElementsByTagName("SmokeEnvironment");
        NodeList SmokeRevision = SmokeDoc.getElementsByTagName("SmokeRevision");
        NodeList SmokeStatus = SmokeDoc.getElementsByTagName("SmokeStatus");

        smokeTestInfo.add(SmokeEnvironment);
        smokeTestInfo.add(SmokeRevision);
        smokeTestInfo.add(SmokeStatus);

        return smokeTestInfo;
    }

}

