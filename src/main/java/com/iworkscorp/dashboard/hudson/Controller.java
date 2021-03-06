package com.iworkscorp.dashboard.hudson;

import org.openqa.selenium.support.events.EventFiringWebDriver;
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
    XMLHandler xmlHandler;
    /*public static File environmentStatusFile;
    public static File smokeStatusFile;
    public static Document environmentStatusDoc;
    public static Document xmlHandler.smokeStatusDoc;*/
    static ArrayList<individualSmokeTest> results;
    static ArrayList<Build> builds;

    static ArrayList<NodeList> buildInfo;
    static ArrayList<NodeList> smokeInfo;

    TestBase testBase = new TestBase();

    /**
     * Gets the xmls to read from
     * Initializes NodeLists of build info and smoke test info
     */
    public Controller() {
        statusFetcher = new StatusFetcher();
        xmlHandler = new XMLHandler();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        xmlHandler.environmentStatusFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\environmentStatus.xml");
        try {
            xmlHandler.environmentStatusDoc = db.parse(xmlHandler.environmentStatusFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlHandler.smokeStatusFile = new File(("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\smokeStatus.xml"));
        try {
            xmlHandler.smokeStatusDoc = db.parse(xmlHandler.smokeStatusFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            //testBase = new TestBase();
            testBase.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        builds  = new ArrayList<>();
        results = new ArrayList<>();
        buildInfo = xmlHandler.readBuildXML(xmlHandler.environmentStatusDoc);
        smokeInfo = xmlHandler.readSmokeTestXML(xmlHandler.smokeStatusDoc);

    }

   /* public static void main(String[] args) throws Exception {
        Controller con = new Controller();
        //con.generateBuildInfo();
        buildInfo = readBuildXML(xmlHandler.environmentStatusDoc);

        builds = con.createBuildsFromXMLNodes(buildInfo);
        //con.generateSmokeDoc();
        smokeInfo = readSmokeTestXML(xmlHandler.smokeStatusDoc);
        results = con.createSmokeTestObjFromXML(smokeInfo);

        con.checkAndRunSmoke(builds.get(1), results.get(1));
        //con.runAllSmokeTests();
        //con.overWriteSmokeDoc();
    }*/


    /**
     * Pulls information from Hudson and writes it to xml
     * @return
     * @throws Exception
     */
    public Document generateBuildInfo() throws Exception {
        testBase = new TestBase();
        //testBase.initialize();
        testBase.driver = new EventFiringWebDriver(testBase.dr);
        statusFetcher.initialize();

        if(statusFetcher.collectFromHudson()) {
            builds = statusFetcher.getAllMostRecents();
            xmlHandler.environmentStatusDoc = xmlHandler.createXML(builds);
            xmlHandler.writeToXML(xmlHandler.environmentStatusDoc, "\\environmentStatus.xml");
        }
        return xmlHandler.environmentStatusDoc;
    }


    /**
     * Reads the xml and puts all information into an ArrayList of nodelists
     * @param doc
     * @return
     */
    /*public static ArrayList<NodeList> readBuildXML(Document doc){

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

    }*/


    /**
     * Creates new build objects from xml information
     * @param buildNodes
     * @return
     */
    public void createBuildsFromXMLNodes(ArrayList<NodeList> buildNodes){

        ArrayList<Build> tempBuilds = new ArrayList<>();

        for(int i = 0; i < buildNodes.get(0).getLength(); i++){
            Build newBuild = new Build();

            newBuild.environment = buildNodes.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue();
            newBuild.builder = buildNodes.get(1).item(i).getFirstChild().getNodeValue();
            newBuild.dateBuiltFull = buildNodes.get(2).item(i).getFirstChild().getNodeValue();
            newBuild.revision = Integer.parseInt(buildNodes.get(3).item(i).getFirstChild().getNodeValue());
            newBuild.buildStatus = buildNodes.get(4).item(i).getFirstChild().getNodeValue();;
            builds.add(newBuild);
        }
        //return tempBuilds;
    }

    /**
     * initializes smoke test xml with environment names
     * @return
     */
    public Document generateSmokeDoc(){

        results = createAllSmokeTestsandAdd();
        if(results.size() != 0) {
            xmlHandler.smokeStatusDoc = xmlHandler.createXML(results);
            xmlHandler.writeToXML(xmlHandler.smokeStatusDoc, "\\smokeStatus.xml");
        }
        return xmlHandler.smokeStatusDoc;
    }


    /**
     * updates information the smoke status xml
     * @return
     */
    public Document updateSmokeDoc(){
        Document smokeXML = null;
        if(results.size() != 0) {
            smokeXML = xmlHandler.createXML(results);
            xmlHandler.writeToXML(smokeXML, "\\smokeStatus.xml");
        }
        return smokeXML;
    }


    /**
     * Creates initial individual smoke test objects where
     * revision is set to -1 and status is set to false so that
     * smoke tests have to be run
     * @return
     */
    public ArrayList<individualSmokeTest> createAllSmokeTestsandAdd(){
        //ArrayList<individualSmokeTest> results = new ArrayList<>();

        individualSmokeTest baselineSmoke = new individualSmokeTest("Baseline");
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


    /**
     * Creates arraylist of nodelists of data extracted
     * from smoketest xml
     * @param SmokeDoc
     * @return
     */
    /*public static ArrayList<NodeList> readSmokeTestXML(Document SmokeDoc){
        //File SmokeXmlFile = new File("C:\\Users\\mcrowley\\IdeaProjects\\StatusDashboard\\SmokeTest.xml");
        //Document SmokeDoc = db.parse(doc);

        ArrayList<NodeList> smokeTestInfo = new ArrayList<>();

        NodeList SmokeEnvironment = SmokeDoc.getElementsByTagName("Environment");
        NodeList SmokeRevision = SmokeDoc.getElementsByTagName("Revision");
        NodeList SmokeStatus = SmokeDoc.getElementsByTagName("SmokeStatus");

        smokeTestInfo.add(SmokeEnvironment);
        smokeTestInfo.add(SmokeRevision);
        smokeTestInfo.add(SmokeStatus);

        return smokeTestInfo;
    }*/


    /**
     * creates arraylist of individual smoke tests from xml
     * @param smokeNodes
     * @return
     */
    public void createSmokeTestObjFromXML(ArrayList<NodeList> smokeNodes){
        //ArrayList<individualSmokeTest> tempSmokes = new ArrayList<>();

        for(int i = 0; i < smokeNodes.get(0).getLength(); i++){
            individualSmokeTest tempSmokeTest = new individualSmokeTest();

            tempSmokeTest.environmentName = smokeNodes.get(0).item(i).getAttributes().getNamedItem("name").getNodeValue();
            tempSmokeTest.revision = Integer.parseInt(smokeInfo.get(1).item(i).getFirstChild().getNodeValue());
            tempSmokeTest.status = smokeInfo.get(2).item(i).getFirstChild().getNodeValue();
            results.add(tempSmokeTest);
        }

        //return tempSmokes;
    }

    /**
     * runs smoke test and updates the smoke test xml
     * @param env
     * @throws Exception
     */
    public void runSmokeTest(individualSmokeTest env) throws Exception {
        //env.setStatus("In Progress");

        testBase = new TestBase();
        testBase.driver = new EventFiringWebDriver(testBase.dr);
        //testBase.initialize();

        smokeTest = new SmokeTest();
        if(smokeTest.environmentValidation(env.environmentName)){
            env.setStatus("true");
        }
        else{
            env.setStatus("false");
        }

        updateSmokeDoc();
    }


    /**
     * checks if the build and smoke test names are the same,
     * if the build was successful and if the revisions are different.
     * if all are true, a smoke teste needs to be run
     * @param build
     * @param smokeTest
     * @return
     */
    public boolean needsToRunSmokeTest(Build build, individualSmokeTest smokeTest){
        if(build.environment.equals(smokeTest.environmentName) && build.buildStatus.equals("true") && ((build.revision != smokeTest.revision) || smokeTest.status.equals("false"))){
            smokeTest.revision = build.revision;
            return true;
        }
        return false;
    }


    /**
     * runs smoke tests for all environments
     * @throws Exception
     */
    public void runAllSmokeTests() throws Exception {
        for(int i = 0; i < results.size(); i++){

            checkAndRunSmoke(builds.get(i), results.get(i));
        }
    }


    /**
     * checks if a smoke test needs to be run, if true, runs the smoke test
     * @param build
     * @param smokeTest
     * @throws Exception
     */
    public void checkAndRunSmoke(Build build, individualSmokeTest smokeTest) throws Exception {
        if(needsToRunSmokeTest(build, smokeTest)){
            runSmokeTest(smokeTest);
        }
    }

    public void closeBrowser(){
        testBase.quitBrowser();
    }
}

