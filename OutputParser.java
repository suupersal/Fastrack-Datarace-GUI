
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jraguilo
 *
 */
//parses the xml output into a more readable form
public class OutputParser {

    private String output = "";

    public OutputParser(String filename) {
        try {
            generateOutput("./inputFiles/log/" + filename);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateOutput(String filename) throws Exception {
        //Get the DOM Builder Factory
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        //Get the DOM Builder
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        //Load and Parse the XML Document
        Document document = builder.parse(ClassLoader.getSystemResourceAsStream(filename));

        //Iterate through nodes and extract data
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        NodeList childNodes;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            switch (item.getNodeName()) {
                case "data":
                    output = output + "Data: " + item.getTextContent() + "\n";
                    break;

                case "options":
                    output = output + "Options: \n";
                    childNodes = item.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node optionItem = childNodes.item(j);
                        output = output + optionItem.getTextContent().trim() + "\n";
                    }
                    break;
                case "counters":
                    output = output + "Counters: \n";
                    childNodes = item.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node counterItem = childNodes.item(j);
                        output = output + counterItem.getTextContent().trim() + "\n";
                    }
                    break;
                case "errorCountPerErrorType":
                    output = output + "Errors Per Error Type: \n";
                    childNodes = item.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node errorItem = childNodes.item(j);
                        output = output + errorItem.getTextContent().trim() + "\n";
                    }
                    break;
            }
        }
    }

    public String getPrettyPrint() {
        String content = "-->";
        try {
            content = new Scanner(new File("./inputFiles/prettyPrintLog.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error getting prity print: " + ex.getMessage());
        }

        return content;
    }

    public String getOutput() {
        return output;
    }

}
