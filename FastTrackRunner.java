/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;

/**
 *
 * @author suupe_000
 *This calass in in charge or running all commands, any command that needs to be run must be ran using this class.
 *This class will provide methods to compile rrun, any required setup and run final commands.
 */
public class FastTrackRunner {

//Retrieve copy or run time
 Runtime run = Runtime.getRuntime();
 
 //This will compile the road ruuner tool
    public void rrCompile() {

         try {
            Process pr = run.exec("ant",null,new File("RoadRunner/"));
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            line = buf.readLine();
            while (line != null) {

                System.out.println(line);
                line = buf.readLine();
            }
        } catch (Exception e) {
            System.out.print("compile error: "+e.getMessage());


        }

    }
 
 //This method will set up source and environmental variables. 
       public void rrSourceSetup() {
//export JVM_ARGS="-Xmx1g -Xms1g"
//

//export PATH=${PATH}:`pwd`/auto/bin:`pwd`/bin

//export RR_MODE=FAST
//export RR_HOME=`pwd`
//export RR_TOOLPATH=`pwd`/classes/rr/simple:`pwd`/classes/tools
         try {
            Process pr = run.exec("export PATH=${PATH}:`pwd`/auto/bin:`pwd`/bin");
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            line = buf.readLine();
            while (line != null) {

                System.out.println(line);
                line = buf.readLine();
            }
        } catch (Exception e) {

            System.out.print(e.getMessage());

        }

    }

	//This method will run MOST commands sent to it, it will NOT run source commands.
    public void runCommand(RRCommand cmd) {

        try {
            System.out.println("THE COMMAND: "+cmd.getCommand());
            Process pr = run.exec(cmd.getCommand(),null, new File("inputFiles/"));
            pr.waitFor();

            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            line = buf.readLine();
            while (line != null) {

                System.out.println(line);
                line = buf.readLine();
            }
pr.destroy();
        } catch (Exception e) {
            System.out.print("run command exeption: "+e.getMessage());

        }

    }
	
	public void compileInputFile(String fileName){
	
	
         try {
            Process pr = run.exec("javac "+fileName+".java",null,new File("inputFiles/"));
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            line = buf.readLine();
            while (line != null) {

                System.out.println(line);
                line = buf.readLine();
            }
        } catch (Exception e) {
            System.out.print("Error Comping Input: "+e.getMessage());


        }

	
	}
}
