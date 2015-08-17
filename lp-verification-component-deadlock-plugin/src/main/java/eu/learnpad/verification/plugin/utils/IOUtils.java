package eu.learnpad.verification.plugin.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class IOUtils {

	public static void writeFile(byte[] data, String filePath, boolean appendData) throws Exception{
		FileOutputStream fos = new FileOutputStream(new File(filePath), appendData);
		fos.write(data);
		fos.flush();
		fos.close();
	}
	
	public static byte[] readFile(String file) throws Exception{
		return readFile(new File(file));
	}
	
	public static byte[] readFile(File file) throws Exception{
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		byte[] ret = new byte[(int)raf.length()];
		raf.read(ret);
		raf.close();
		return ret;
	}
	
	public static void inheritIO(final InputStream src, final PrintStream dest) {
	    new Thread(new Runnable() {
	        public void run() {
	            Scanner sc = new Scanner(src);
	            while (sc.hasNextLine()) {
	                dest.println(sc.nextLine());
	            }
	            sc.close();
	        }
	    }).start();
	}
	
	public static void copyInputStreamToOutputStream(InputStream input, OutputStream output) throws Exception{
		int n = 0;
		int DEFAULT_BUFFER_SIZE = 1024 * 1024 * 10;
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		while (-1 != (n = input.read(buffer)))
			output.write(buffer, 0, n);
	}
	
	public static byte[] toByteArray(InputStream is) throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copyInputStreamToOutputStream(is, out);
		byte[] ret = out.toByteArray();
		out.close();
		out = null;
	    return ret;
	}
	
	//Memory efficient read from InputStream
	public static byte[] toByteArrayEfficient(InputStream is) throws Exception{
		
		int totEstimatedLength = is.available();
		int DEFAULT_BUFFER_SIZE = totEstimatedLength;
		if(DEFAULT_BUFFER_SIZE == 0)
			DEFAULT_BUFFER_SIZE =  1024 * 1024 * 10;
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int totLength = 0;
		int tmpLength = 0;
		byte[] ret = new byte[0];

		while (-1 != (tmpLength = is.read(buffer))){
			if(totEstimatedLength != 0 && tmpLength == totEstimatedLength) //Se è vera verrà fatta solo al primo ciclo e non verranno creati ulteriori array, minimizzando la memoria usata.
				return buffer;
			
			totLength += tmpLength;
			byte[] newRet = new byte[totLength];
			System.arraycopy(ret, 0, newRet, 0, ret.length);
			System.arraycopy(buffer, 0, newRet, ret.length, tmpLength);
			ret = newRet;
			System.gc();
		}
		
		System.gc();
		
		return ret;
	}


}
