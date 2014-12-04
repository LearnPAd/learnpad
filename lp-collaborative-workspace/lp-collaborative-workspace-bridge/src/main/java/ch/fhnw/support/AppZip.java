package ch.fhnw.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

public class AppZip
{
	List<String> fileList;
	private static String OUTPUT_ZIP_FILE;
	private static String SOURCE_FOLDER;

	AppZip(){
		fileList = new ArrayList<String>();
	}

	/**
	 * 
	 * @param directory This is the directory where to create the ZIP file 
	 * @return A message of creation succeeded or not
	 */
	public static String appZip(JFileChooser directory)
	{
		OUTPUT_ZIP_FILE = directory.getSelectedFile().getName().replaceAll(".xml", ".zip");
		SOURCE_FOLDER = directory.getCurrentDirectory().toString() + "\\xml";

		AppZip appZip = new AppZip();
		appZip.generateFileList(new File(SOURCE_FOLDER));
		appZip.zipIt(OUTPUT_ZIP_FILE);

		File oldfile =new File(OUTPUT_ZIP_FILE);
		File newfile =new File(directory.getCurrentDirectory().toString() + "\\" + directory.getSelectedFile().getName().replaceAll(".xml", "") + ".xar");

		if(oldfile.renameTo(newfile)){
			DeleteDirectory.deleteDirectory(SOURCE_FOLDER);
			System.out.println("Creation and Rename of the file succesful");
			return "Creation of the file completed successfully";
		}else{
			System.out.println("Rename failed, the file already exist");
			return "Creation failed, the file already exist";
		}
	}

	/**
	 * Zip it
	 * @param zipFile output ZIP file location
	 */
	public void zipIt(String zipFile){

		byte[] buffer = new byte[1024];

		try{

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			System.out.println("Output to Zip : " + zipFile);

			for(String file : this.fileList){

				System.out.println("File Added : " + file);
				ZipEntry ze= new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			//remember close it
			zos.close();

			System.out.println("\nDone! Zip file created successfully!\n");
		}catch(IOException ex){
			ex.printStackTrace();   
		}
	}

	/**
	 * Traverse a directory and get all files,
	 * and add the file into fileList  
	 * @param node file or directory
	 */
	public void generateFileList(File node){

		//add file only
		if(node.isFile()){
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				generateFileList(new File(node, filename));
			}
		}

	}

	/**
	 * Format the file path for zip
	 * @param file file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file){
		return file.substring(SOURCE_FOLDER.length()+1, file.length());
	}
}
