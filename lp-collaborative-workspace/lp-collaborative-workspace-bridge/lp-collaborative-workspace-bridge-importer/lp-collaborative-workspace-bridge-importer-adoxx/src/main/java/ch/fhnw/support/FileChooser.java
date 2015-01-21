package ch.fhnw.support;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	static JFileChooser chooser;
	static String choosertitle;
	static JFileChooser vuoto = new JFileChooser();

	/**
	 * 
	 * @return The file selected
	 */
	public static final JFileChooser fileChooser() {

		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file", "xml");
		chooser.addChoosableFileFilter(filter);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		//    
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 

			return chooser;
		}
		else {
			System.out.println("No File Selected");
			return vuoto;
		}


	}


}
