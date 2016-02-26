/*
Abel Berhane
Homework 4 and 5

How to use:
There are 5 batch files saved with this directory, each with a command to copy the dictionary
file and create a file that contains a tag (the tags names are defined in each batch file).

This program creates 5 threads, each executing a command to use a different batch file.

Note: These commands only work for Windows Operating Systems.
 */

package hw5;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;


public class driver {
    public static void main(String [] args) throws IOException, InterruptedException {       
    	
        ExecutorService executor = Executors.newFixedThreadPool(30);
        //ArrayList for ProcessBuilders
        ArrayList<ProcessBuilder> probuilds = new ArrayList<ProcessBuilder>();
        
        //ArrayList of windows commands
        ArrayList<ArrayList<String>> newCommands = new ArrayList<ArrayList<String>>();
        
        //Windows commands
        ArrayList<String> newCommand = new ArrayList<String>();
        ArrayList<String> newCommand2 = new ArrayList<String>();
        ArrayList<String> newCommand3= new ArrayList<String>();
        ArrayList<String> newCommand4 = new ArrayList<String>();
        ArrayList<String> newCommand5 = new ArrayList<String>();
        
        //Insert commands for each thread
        newCommand.add("cmd");
        newCommand.add("/c");
        newCommand.add("start");
        newCommand.add("dummyBatch1.bat");
        newCommands.add(newCommand);
        
        newCommand2.add("cmd");
        newCommand2.add("/c");
        newCommand2.add("start");
        newCommand2.add("dummyBatch2.bat");
        newCommands.add(newCommand2);
        
        newCommand3.add("cmd");
        newCommand3.add("/c");
        newCommand3.add("start");
        newCommand3.add("dummyBatch3.bat");
        newCommands.add(newCommand3);
        
        newCommand4.add("cmd");
        newCommand4.add("/c");
        newCommand4.add("start");
        newCommand4.add("dummyBatch4.bat");
        newCommands.add(newCommand4);
        
        newCommand5.add("cmd");
        newCommand5.add("/c");
        newCommand5.add("start");
        newCommand5.add("dummyBatch5.bat");
        newCommands.add(newCommand5);

    	//Insert commands into process builders
        for(int i=0; i<5; i++)
        {
        	// Process Builder
            final ProcessBuilder pb = new ProcessBuilder(newCommands.get(i));
            // Directory for Process Builder
            pb.directory(new File(System.getProperty("user.dir")));
            
            //Store ProcessBuilders in a list
            probuilds.add(pb);
            
           
        }
        
        //Execute each thread
        for(int i=0; i<probuilds.size(); i++)
        {
        	MyRunnable r = new MyRunnable(probuilds.get(i));
        	
        	executor.execute(r);
        }
       
       //Shutdown threads
       executor.shutdown();
    	

    }
    
    //Thread
    public static class MyRunnable implements Runnable
    {
    	private ProcessBuilder probuilds2;
    	
    	//Constructor
    	MyRunnable(ProcessBuilder probuilds)
    	{
    		this.probuilds2 = probuilds;
    	}
    	
    	//Overridden run method
    	public void run()
    	{ try
    		{
    			//Start process
    			Process process = probuilds2.start();
            	OutputStream in = process.getOutputStream();
            	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
            
            	//Print output (if the myCommand prints to the screen)
            	InputStream myInStream = process.getInputStream();
            	InputStreamReader isr = new InputStreamReader(myInStream);
            	BufferedReader br = new BufferedReader(isr);
            	String printLine;
            	while ((printLine = br.readLine()) != null) {
            		System.out.println(printLine);
            	}
            
            	// Wait for completion
            	process.waitFor();

        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }
    }
   
}

