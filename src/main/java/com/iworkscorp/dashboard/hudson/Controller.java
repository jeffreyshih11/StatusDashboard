package com.iworkscorp.dashboard.hudson;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by jshih on 7/12/2016.
 */
public class Controller {

    StatusFetcher statusFetcher;
    SmokeTest smokeTest;
    CreateXML xmlCreator;
    static ArrayList<individualSmokeTest> results = new ArrayList<>();

    public Controller(){
        statusFetcher = new StatusFetcher();
        xmlCreator = new CreateXML();
    }

    public static void main(String[] args) throws Exception {
        Controller con = new Controller();
        //con.generateBuildInfo();
        con.generateSmokeDoc();
        con.runSmokeTest(results.get(1));
        con.overWriteSmokeDoc();
    }

    public Document generateBuildInfo() throws Exception {
        TestBase.initialize();
        statusFetcher.initialize();
        Document buildXML = null;
        if(statusFetcher.collectFromHudson()) {
            ArrayList<Build> builds = statusFetcher.getAllMostRecents();
            buildXML = xmlCreator.createXML(builds);
            xmlCreator.writeToXML(buildXML, "\\environmentStatus.xml");
        }
        return buildXML;
    }

    public ArrayList<NodeList> readBuildXML(Document doc){

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

    public Document generateSmokeDoc(){
        Document smokeXML = null;
        results = createAllSmokeTestsandAdd();
        if(results.size() != 0) {
            smokeXML = xmlCreator.createXML(results);
            xmlCreator.writeToXML(smokeXML, "\\smokeStatus.xml");
        }
        return smokeXML;
    }

    public Document overWriteSmokeDoc(){
        Document smokeXML = null;
        if(results.size() != 0) {
            smokeXML = xmlCreator.createXML(results);
            xmlCreator.writeToXML(smokeXML, "\\smokeStatus.xml");
        }
        return smokeXML;
    }

    public ArrayList<individualSmokeTest> createAllSmokeTestsandAdd(){
        //ArrayList<individualSmokeTest> results = new ArrayList<>();

        individualSmokeTest baselineSmoke = new individualSmokeTest("BASELINE");
        individualSmokeTest demoSmoke = new individualSmokeTest("DEMO");
        individualSmokeTest devSmoke = new individualSmokeTest("DEV");
        individualSmokeTest gatSmoke = new individualSmokeTest("GAT");
        individualSmokeTest qaSmoke = new individualSmokeTest("QA");
        //individualSmokeTest localSmoke = new individualSmokeTest("LOCAL");
        individualSmokeTest uatSmoke = new individualSmokeTest("UAT");

        results.add(baselineSmoke);
        results.add(demoSmoke);
        results.add(devSmoke);
        results.add(gatSmoke);
        results.add(qaSmoke);
        //results.add(localSmoke);
        results.add(uatSmoke);

        return results;

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

    public void runSmokeTest(individualSmokeTest env) throws Exception {
        env.setStatus("In Progress");

        TestBase.initialize();
        smokeTest = new SmokeTest();
        if(smokeTest.environmentValidation(env.environmentName)){
            env.setStatus("true");
        }
        else{
            env.setStatus("false");
        }

    }



}

