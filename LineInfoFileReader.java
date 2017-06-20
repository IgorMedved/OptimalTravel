package part010_objectvile_travel_final_revised;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;

// Line map reader class is responsible for getting Line information from file and creating a map connecting lineNames with Line objects
public class LineInfoFileReader {
	private LineInfoProcessor mProcessor;
	
	
	public LineInfoFileReader(LineInfoProcessor processor)
	{
		mProcessor = processor;
	}
	
	public void readLineInfoFile()
	{
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.showDialog(null, "Select file with line info");
		File lineInfo = fileChooser.getSelectedFile();
		if (lineInfo == null)
			throw new RuntimeException("You have to select input file containing the subway system network info. Please rerun the program and select the correct file");
		readLineMapFromFile(lineInfo);
	}
	
	private void readLineMapFromFile(File lineInfo) 
	{
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(lineInfo));
		
		    
		    
		    String line = br.readLine();

		    while (line != null) {
		    	mProcessor.processInputLine(line);
		    	line = br.readLine();
		    }
		    mProcessor.endProcessing();
		    
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if (br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		// make list of lines unmodifiable after it was read from the file
		
		

	}
	
}
