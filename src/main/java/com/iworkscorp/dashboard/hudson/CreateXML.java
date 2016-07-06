package com.iworkscorp.dashboard.hudson;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class CreateXML {

    public static void main(String[] args) throws Exception {
        StatusFetcher statuses = new StatusFetcher();
        TestBase.initialize();
        statuses.initialize();
        statuses.connectToHudson();
        ArrayList<Build> builds = statuses.mostRecentBuilds;
        CreateXML create = new CreateXML();
        create.createXML(builds);
    }

    public void createXML(ArrayList<Build> builds) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElementNS("", "Environments");
            //append root element to document
            doc.appendChild(rootElement);




            for(int i = 0; i < builds.size(); i++){
                Element environment = doc.createElement("Environment");
                environment.setAttribute("name", builds.get(i).environment);
                rootElement.appendChild(getBuild(doc, builds.get(i), environment));
            }


            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            //StreamResult file = new StreamResult(new File("C:\\Users\\avillaflor\\Documents\\GitHub\\StatusDashboard\\example.xml"));
            StreamResult file = new StreamResult(new File("C:\\Users\\jshih\\IdeaProjects\\StatusDashboard\\example.xml"));
            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("DONE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getBuild(Document doc, Build build, Element environment){
        environment.appendChild(getElements(doc, environment, "Builder", build.builder));
        environment.appendChild(getElements(doc, environment, "Date", build.dateBuiltFull));
        environment.appendChild(getElements(doc, environment, "Revision", Integer.toString(build.revision)));
        environment.appendChild(getElements(doc, environment, "Status", build.buildStatus));
        environment.appendChild(getElements(doc, environment, "SmokeTestPass", build.smokeTestPass));
        return environment;
    }

    //utility method to create text node
    private static Node getElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}