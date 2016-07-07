package com.iworkscorp.dashboard.load;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

//import org.w3c.dom.NamedNodeMap;
//File fXmlFile = new File("\\Users\\jshih\\Documents\\GitHub\\StatusDashboard\\src\\staff.xml");


public class ReadXMLFile {

    public static final String FILE_NAME = "data/staff.xml";

    public static void main(String argv[]) {
        ReadXMLFile fileReader = new ReadXMLFile();
        if (fileReader.parseFile(FILE_NAME) == 0) {
            System.out.println("\n\n***** Success *****");
        } else {
            System.out.println("\n\n***** Fail *****");
        }
    }

    public ReadXMLFile() {
    }

    public int parseFile(String fileName) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File xmlFile = new File(classLoader.getResource(fileName).getFile());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            System.out.println("----------------------------");

            System.out.println(this.documentToString(document));

            System.out.println("----------------------------");
        } catch (Exception e) {
            System.out.println("Error while processing resource file: " + e.getStackTrace());
            return -1;
        }
        return 0;
    }

    private String documentToString(Document document) {
        StringBuilder stringBuilder = new StringBuilder();

        NodeList nodeList = document.getElementsByTagName("staff");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                stringBuilder.append("\n" + node.getNodeName() + "<id : " + element.getAttribute("id") + ">:");

                stringBuilder.append("\n\tFirst Name : " + element.getElementsByTagName("firstname").item(0).getTextContent());
                stringBuilder.append("\n\tLast Name : " + element.getElementsByTagName("lastname").item(0).getTextContent());
                stringBuilder.append("\n\tNickname : " + element.getElementsByTagName("nickname").item(0).getTextContent());
                stringBuilder.append("\n\tSalary : " + element.getElementsByTagName("salary").item(0).getTextContent() + "\n");

            }
        }

        return stringBuilder.toString();
    }
}