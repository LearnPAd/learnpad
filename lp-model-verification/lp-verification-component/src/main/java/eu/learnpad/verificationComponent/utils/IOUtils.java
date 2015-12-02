/**
 * LearnPAd - Verification Component
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verificationComponent.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
	
    public static HashMap<String, byte[]> unZip(byte[] zipFile) throws Exception{
        if(!(zipFile[0]=='P' && zipFile[1]=='K'))
            throw new Exception("The parameter is not a valid zip file");
        
        HashMap<String, byte[]> ret = new HashMap<String, byte[]>();
        
        ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(zipFile));
        ZipEntry entry = null;
        while((entry=zipIn.getNextEntry())!=null)
            if(!entry.isDirectory())
                ret.put(entry.getName(), toByteArray(zipIn));
        
        zipIn.close();
        
        return ret;
    }
}
