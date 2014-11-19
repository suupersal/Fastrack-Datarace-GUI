/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suupe_000
 */
//This class contains all command variables and arguments, final command can be
//retrieved by calling the getCommand();
public class RRCommand {

    private String fileName = "";
    private String tool = "";
    private String arguments = "";
    private String classPath = "";
    private String xmlName = "";
    private String arrayMode = "";

    public String getFileName() {
        return fileName;
    }

    public String getCommand() {
        return "rrrun -noxml" + arrayMode + tool + " " + fileName;
    }

    public void setFileName(String fileName) {
        //dont want to keep extension, so remove it.
        this.fileName = fileName;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public void setArrayMode(String arrayMode) {
        this.arrayMode = arrayMode;
    }

    public void reset() {
        fileName = "";
        tool = "";
        arguments = "";
        classPath = "";
        arrayMode = "";
        xmlName = "";

    }
}
