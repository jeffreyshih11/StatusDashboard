package com.iworkscorp.dashboard.hudson;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class CreateXML {

    public static void main(String[] args) {
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
            StatusFetcher statuses = new StatusFetcher();
            statuses.connectToHudson();
            ArrayList<Build> builds = statuses.mostRecentBuilds;

            for(int i = 0; i < builds.size(); i++){
                rootElement.appendChild(getBuild(doc, builds.get(i)));
            }

            //append first child element to root element
            rootElement.appendChild(getEmployee(doc, "1", "Pankaj", "29", "Java Developer", "Male"));

            //append second child
            rootElement.appendChild(getEmployee(doc, "2", "Lisa", "35", "Manager", "Female"));

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("C:\\Users\\avillaflor\\Documents\\GitHub\\StatusDashboard\\example.xml"));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("DONE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getBuild(Document doc, Build build){
        Element environment = doc.createElement("Environment");
        environment.appendChild(getElements(doc, environment, "Builder", build.builder));
        return environment;
    }

    private static Node getEmployee(Document doc, String id, String name, String age, String role,
                                    String gender) {
        Element employee = doc.createElement("Employee");

        //set id attribute
        employee.setAttribute("id", id);

        //create name element
        employee.appendChild(getElements(doc, employee, "name", name));

        //create age element
        employee.appendChild(getElements(doc, employee, "age", age));

        //create role element
        employee.appendChild(getElements(doc, employee, "role", role));

        //create gender element
        employee.appendChild(getElements(doc, employee, "gender", gender));

        return employee;
    }


    //utility method to create text node
    private static Node getElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}