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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NETUtils {
	
	public static String getHost(String url){
		return url.split("/")[2].split(":")[0];
	}
	
	public static byte[] sendHTTPPOST(String url, String dataToSend, HashMap<String,String> htmlHeaderList, boolean ignoreSSLSelfSigned, boolean ignoreSSLWrongCN) throws Exception{
		return sendHTTP(url, "POST", dataToSend, htmlHeaderList, ignoreSSLSelfSigned, ignoreSSLWrongCN);
	}
	
	public static byte[] sendHTTPGET(String url, HashMap<String,String> htmlHeaderList, boolean ignoreSSLSelfSigned, boolean ignoreSSLWrongCN) throws Exception{
		return sendHTTP(url, "GET", null, htmlHeaderList, ignoreSSLSelfSigned, ignoreSSLWrongCN);
	}
	
	public static byte[] sendHTTP(String url, String mode, String dataToSend, HashMap<String,String> htmlHeaderList, boolean ignoreSSLSelfSigned, boolean ignoreSSLWrongCN) throws Exception{
		
		System.setProperty("java.net.useSystemProxies", "true");
		
		if(ignoreSSLSelfSigned){
			TrustManager[] trustAllCerts = new TrustManager[]{
				    new X509TrustManager() {
				        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				            return null;
				        }
				        public void checkClientTrusted(
				            java.security.cert.X509Certificate[] certs, String authType) {
				        }
				        public void checkServerTrusted(
				            java.security.cert.X509Certificate[] certs, String authType) {
				        }
				    }
				};
			SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		}
		if(ignoreSSLWrongCN){
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, javax.net.ssl.SSLSession session) {
					return true;
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		}
		
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		
		if(htmlHeaderList != null)
			for(String htmlHeaderName:htmlHeaderList.keySet())
				connection.setRequestProperty(htmlHeaderName, htmlHeaderList.get(htmlHeaderName));

		connection.setRequestMethod(mode);
		if(dataToSend != null){
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Length", "" + Integer.toString(dataToSend.getBytes("UTF-8").length));
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.write(dataToSend.getBytes("UTF-8"));
			wr.flush();
			wr.close();
		}
		
		byte[] output = new byte[0];
		if(connection.getResponseCode() >= 400 && connection.getErrorStream()!=null)
			output = toByteArray(connection.getErrorStream());
		else
			output = toByteArray(connection.getInputStream());

		connection.disconnect();
		
		return output;
	}
	
	public static ArrayList<String> safeSplitParameters(String parameters){
		if(parameters == null || parameters == "")
			return new ArrayList<String>();
		
		ArrayList<String> ret = new ArrayList<String>();
		
		int index = 0;
		do{
			int nextIndex = parameters.indexOf("&", index);
			if(nextIndex == -1)
				nextIndex = parameters.length();
			
			do{
				if(!parameters.substring(nextIndex).startsWith("&amp;"))
					break;
				nextIndex = parameters.indexOf("&", nextIndex+1);
				if(nextIndex == -1)
					nextIndex = parameters.length();
			}while(true);
			
			ret.add(parameters.substring(index, nextIndex));
			
			index = nextIndex +1;
		}while(index < parameters.length());
		
		return ret;
		//return parameters.split("\\&");
	}
	
	public static ArrayList<String[]> safeGetParameters(String parameters){
		
		ArrayList<String[]> ret = new ArrayList<String[]>();
		
		ArrayList<String> parameterList = safeSplitParameters(parameters);
		
		for(String parameter:parameterList)
			if(parameter.contains("=")){
				String name = parameter.substring(0, parameter.indexOf("="));
				String value = parameter.substring(parameter.indexOf("=")+1, parameter.length());
				ret.add(new String[]{name, value});
			} else {
				ret.add(new String[]{parameter, ""});
			}
		return ret;
	}
	
	public String normalizeECommercial(String data){
		String ret = data;
		for(int i=0;i<ret.length();i++)
			if(ret.charAt(i) == '&' && !ret.substring(i).startsWith("&amp;"))
				ret = ret.substring(0,i) + "&amp;" + ret.substring(i+1, ret.length());
		return ret;
	}
	
	public static String getRandomUserAgent(){
		return getUserAgent(-1);
	}
	
	/*
	 * impostare -1 per avere random
	 * se si imposta un indice fuori range ritorna null
	*/
	public static String getUserAgent(int index){
		ArrayList<String> userAgentList = new ArrayList<String>();
		userAgentList.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		userAgentList.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.5 Safari/537.17");
		userAgentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3 (FM Scene 4.6.1)");
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; de; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3 (.NET CLR 3.5.30729) (Prevx 3.0.5)");
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/0.2.153.1 Safari/525.19");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.7.6) Gecko/20050405 Epiphany/1.6.1 (Ubuntu) (Ubuntu package 1.0.2)");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; en-US; Nautilus/1.0Final) Gecko/20020408");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:0.9.3) Gecko/20010801");
		userAgentList.add("Mozilla/5.0 (X11; Linux i686; U;rv: 1.7.13) Gecko/20070322 Kazehakase/0.4.4.1");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.2b) Gecko/20021007 Phoenix/0.3");
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.1) Gecko/2008092215 Firefox/3.0.1 Orca/1.1 beta 3");
		userAgentList.add("Mozilla/5.0 (X11; U; Linux i686; de-AT; rv:1.8.0.2) Gecko/20060309 SeaMonkey/1.0");
		userAgentList.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.0.1) Gecko/20021219 Chimera/0.6");
		userAgentList.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.0.1) Gecko/20030306 Camino/0.7");

		if(index < 0)
			return userAgentList.get(new Random().nextInt(userAgentList.size()));
		
		if(index >= userAgentList.size())
			return null;
		
		return userAgentList.get(index);
	}
	

    private static byte[] toByteArray(InputStream is) throws Exception{
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copyInputStreamToOutputStream(is, out);
        byte[] ret = out.toByteArray();
        out.close();
        out = null;
        return ret;
    }
    
    private static void copyInputStreamToOutputStream(InputStream input, OutputStream output) throws Exception{
       int n = 0;
       int DEFAULT_BUFFER_SIZE = 1024 * 1024 * 10;
       byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
       while (-1 != (n = input.read(buffer)))
           output.write(buffer, 0, n);
   }
}
