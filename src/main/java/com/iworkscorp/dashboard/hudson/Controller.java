package com.iworkscorp.dashboard.hudson;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jshih on 7/12/2016.
 */
public class Controller {

    StatusFetcher statusFetcher;
    SmokeTest smokeTest;
    CreateXML xmlCreator;
    static Document environmentStatus;
    static Document smokeStatus;
    static ArrayList<individualSmokeTest> results;
    static ArrayList<Build> builds;

    static ArrayList<NodeList> buildInfo;
    static ArrayList<NodeList> smokeInfo;

    public Controller() throws ParserConfigurationException, IOException, SAXException {
        statusFetcher = new StatusFetcher();
        xmlCreator = new CreateXML();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();


        environmentStatus = db.parse(new File(System.getProperty("user.dir") + "\\environmentStatus.xml"));
        smokeStatus = db.parse(new File((System.getProperty("user.dir") + "\\smokeStatus.xml")));

        builds  = new ArrayList<>();
        results = new ArrayList<>();
        //buildInfo = readBuildXML(environmentStatus);
        //smokeInfo = readSmokeTestXML(smokeStatus);
    }

    public static void main(String[] args) throws Exception {
        Controller con = new Controller();
        //con.generateBuildInfo();
        buildInfo = readBuildXML(environmentStatus);
        System.out.println(buildInfo.get(2).item(0).getFirstChild().getNodeValue());

        //con.generateSmokeDoc();
        //con.runSmokeTest(results.get(0)); e
        smokeInfo = readSmokeTestXML(smokeStatus);

        System.out.print(smokeInfo.get(1).item(0).getFirstChild().getNodeValue());
        //con.checkAndRunSmoke(builds.get(1), results.get(1));
        //con.runAllSmokeTests();
        //con.overWriteSmokeDoc();
    }

    public Document generateBuildInfo() throws Exception {
        TestBase.initialize();
        statusFetcher.initialize();

        if(statusFetcher.collectFromHudson()) {
            builds = statusFetcher.getAllMostRecents();
            environmentStatus = xmlCreator.createXML(builds);
            xmlCreator.writeToXML(environmentStatus, "\\environmentStatus.xml");
        }
        return environmentStatus;
    }

    public static ArrayList<NodeList> readBuildXML(Document doc){

        ArrayList<NodeList> buildInfoList = new ArrayList<>();
        NodeList Environment = doc.getElementsByTagName("Environment");
        NodeList Builder = doc.getElementsByTagName("Builder");
        NodeList Date = doc.getElementsByTagName("Date");
        NodeList Revision = doc.getElementsByTagName("Revision");
        NodeList BuildStatus = doc.getElementsByTagName("BuildStatus");

        buildInfoList.add(Environment);
        buildInfoList.add(Builder);
        buildInfoList.add(Date);
        buildInfoList.add(Revision);
        buildInfoList.add(BuildStatus);

        return buildInfoList;

    }

    public Document generateSmokeDoc(){

        results = createAllSmokeTestsandAdd();
        if(results.size() != 0) {
            smokeStatus = xmlCreator.createXML(results);
            xmlCreator.writeToXML(smokeStatus, "\\smokeStatus.xml");
        }
        return smokeStatus;
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

    public static ArrayList<NodeList> readSmokeTestXML(Document SmokeDoc){
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
        //env.setStatus("In Progress");

        TestBase.initialize();
        smokeTest = new SmokeTest();
        if(smokeTest.environmentValidation(env.environmentName)){
            env.setStatus("true");
        }
        else{
            env.setStatus("false");
        }

        overWriteSmokeDoc();
    }


    public boolean needsToRunSmokeTest(Build build, individualSmokeTest smokeTest){
        if(build.environment.equals(smokeTest.environmentName) && build.buildStatus.equals("true") && (build.revision != smokeTest.revision)){
            smokeTest.revision = build.revision;
            return true;
        }
        return false;
    }

    public void runAllSmokeTests() throws Exception {
        for(individualSmokeTest smokeTest: results){
            runSmokeTest(smokeTest);
        }
    }

    public void checkAndRunSmoke(Build build, individualSmokeTest smokeTest) throws Exception {
        if(needsToRunSmokeTest(build, smokeTest)){
            runSmokeTest(smokeTest);
        }
    }
}

