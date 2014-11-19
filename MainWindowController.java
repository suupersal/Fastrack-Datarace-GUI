/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author steven
 */
public class MainWindowController implements Initializable {

    static FastTrackRunner runner = new FastTrackRunner();
    static RRCommand command = new RRCommand();

    @FXML
    private Label title;
    public RadioButton fastTrackRadioButton;
    public RadioButton fastTrackCASRadioButton;
    public RadioButton arrayNoneRadio;
    public RadioButton arrayFineRadio;
    public RadioButton arrayCourseRadio;
    public RadioButton arraySpecialRadio;
    public TextArea outputArea;

    @FXML
    private void selectToolFT(ActionEvent event) {
        fastTrackRadioButton.setSelected(true);
        fastTrackCASRadioButton.setSelected(false);
        command.setTool(" -tool=FT");
    }

    @FXML
    private void selectToolFTCAS(ActionEvent event) {
        fastTrackRadioButton.setSelected(false);
        fastTrackCASRadioButton.setSelected(true);
        command.setTool(" -tool=FT_CAS");
    }

    @FXML
    private void selectFile(ActionEvent event) {
        File file;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");
        file = fileChooser.showOpenDialog(null);
        //This is the comand will show the files selected path        
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.length() - 5);

        command.setFileName(fileName);
    }

    @FXML
    private void selectArrayNone(ActionEvent event) {
        arrayNoneRadio.setSelected(true);
        arrayFineRadio.setSelected(false);
        arrayCourseRadio.setSelected(false);
        arraySpecialRadio.setSelected(false);
        command.setArrayMode(" -array=NONE");
    }

    @FXML
    private void selectArrayFine(ActionEvent event) {
        arrayNoneRadio.setSelected(false);
        arrayFineRadio.setSelected(true);
        arrayCourseRadio.setSelected(false);
        arraySpecialRadio.setSelected(false);
        command.setArrayMode(" -array=FINE");
    }

    @FXML
    private void selectArrayCourse(ActionEvent event) {
        arrayNoneRadio.setSelected(false);
        arrayFineRadio.setSelected(false);
        arrayCourseRadio.setSelected(true);
        arraySpecialRadio.setSelected(false);
        command.setArrayMode(" -array=COURSE");
    }

    @FXML
    private void selectArraySpecial(ActionEvent event) {
        arrayNoneRadio.setSelected(false);
        arrayFineRadio.setSelected(false);
        arrayCourseRadio.setSelected(false);
        arraySpecialRadio.setSelected(true);
        command.setArrayMode(" -array=SPECIAL");
    }

    @FXML
    private void runFastTrack(ActionEvent event) {
        runner.compileInputFile(command.getFileName());
        runner.runCommand(command);
        OutputParser parser = new OutputParser("log.xml");
String pretty=parser.getPrettyPrint() +"\n XML -------- \n";
String xmlOut=parser.getOutput();
        outputArea.setText(pretty + xmlOut);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
        outputArea.setEditable(false);
        outputArea.setText("Welcome \n 1. Please Make sure all input files are in input folder. \n 2. Select options and select your .java input file. \n 3.This tool will compile and run your file. ");
    }

}
