package com.iworkscorp.dashboard.hudson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class XMLHandler {

    public static File environmentStatusFile;
    public static File smokeStatusFile;
    public static Document environmentStatusDoc;
    public static Document smokeStatusDoc;

    public XMLHandler(){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;

        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        environmentStatusFile = new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\environmentStatus.xml");
        try {
            environmentStatusDoc = db.parse(environmentStatusFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        smokeStatusFile = new File(("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\smokeStatus.xml"));
        try {
            smokeStatusDoc = db.parse(smokeStatusFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) throws Exception {
        StatusFetcher statuses = new StatusFetcher();
        TestBase.initialize();
        statuses.initialize();
        if(statuses.collectFromHudson()) {
            ArrayList<Build> builds = statuses.getAllMostRecents();
            XMLHandler create = new XMLHandler();
            create.createXML(builds);
        }


    }*/

    /*
     * creates the XML document that the HTML will be read from
     * @param builds the ArrayList of each environment to be translated into the XML
     */
    public Document createXML(ArrayList environments) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElementNS("", "Environments");
            //append root element to document
            doc.appendChild(rootElement);



            if (environments.get(0).getClass() == Build.class){
                ArrayList<Build> builds = (ArrayList<Build>) environments;
                /*
                * Parses through the ArrayList for each Build object and creates
                * an environment Element to hold all of the data
                */
                for (int i = 0; i < builds.size(); i++) {
                    Element environment = doc.createElement("Environment");
                    environment.setAttribute("name", builds.get(i).environment);
                    //calls the getBuild method to append each of the variables in the Build object
                    rootElement.appendChild(getBuild(doc, builds.get(i), environment));
                }
            }
            else if (environments.get(0).getClass() == individualSmokeTest.class){
                ArrayList<individualSmokeTest> smokeStatus = (ArrayList<individualSmokeTest>) environments;
                /*
                * Parses through the ArrayList for each Build object and creates
                * an environment Element to hold all of the data
                */
                for (int i = 0; i < smokeStatus.size(); i++) {
                    Element environment = doc.createElement("Environment");
                    environment.setAttribute("name", smokeStatus.get(i).environmentName);
                    //calls the getBuild method to append each of the variables in the Build object
                    rootElement.appendChild(getStatus(doc, smokeStatus.get(i), environment));
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public void writeToXML(Document doc, String docName){
        //for output to file, console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            //StreamResult console = new StreamResult(System.out);
            //StreamResult file = new StreamResult(new File("C:\\Users\\avillaflor\\Documents\\GitHub\\StatusDashboard\\example.xml"));
            //System.out.print(System.getProperty("user.dir"));
            StreamResult file = new StreamResult(new File(System.getProperty("user.dir") + docName));
            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);

            /*System.out.println("DONE");*/

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        //for pretty print


    }

    /*
     *@param doc the document that the XML is being written in
     *@param environment the Element that will hold the builder, date, revision, and build status of each build
     *@param build the Build of the environment being accessed
     *@return the node that will hold the Element containing all of the build information
     */
    private static Node getBuild(Document doc, Build build, Element environment){
        environment.appendChild(getElements(doc, environment, "Builder", build.builder));
        environment.appendChild(getElements(doc, environment, "Date", build.dateBuiltFull));
        environment.appendChild(getElements(doc, environment, "Revision", Integer.toString(build.revision)));
        environment.appendChild(getElements(doc, environment, "BuildStatus", build.buildStatus));
        //environment.appendChild(getElements(doc, environment, "SmokeTestPass", build.smokeTestPass));
        return environment;
    }

    private static Node getStatus(Document doc, individualSmokeTest indv, Element environment) {
        //environment.appendChild(getElements(doc, environment, "Environme", indv.environmentName));
        environment.appendChild(getElements(doc, environment, "Revision", Integer.toString(indv.revision)));
        environment.appendChild(getElements(doc, environment, "SmokeStatus", indv.status));
        return environment;
    }

    //utility method to create text node
    private static Node getElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


    /**
     * Reads the xml and puts all information into an ArrayList of nodelists
     * @param doc
     * @return
     */
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


    /**
     * Creates arraylist of nodelists of data extracted
     * from smoketest xml
     * @param SmokeDoc
     * @return
     */
    public static ArrayList<NodeList> readSmokeTestXML(Document SmokeDoc){
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
    }


}