/**
 * LearnPAd - Verification Component - Understandability Plugin
 * 
 *  Copyright (C) 2015 Unicam ISTI CNR
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
 * 
 */

package eu.learnpad.verification.plugin.interfaces.impl;





import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactory;
import eu.learnpad.verification.plugin.bpmn.reader.MyBPMN2ModelReader;
import eu.learnpad.verification.plugin.interfaces.Plugin;
import eu.learnpad.verification.plugin.utils.Utils;
import eu.learnpad.verification.plugin.utils.Utils.LogType;


public class BPMNUnderstandability implements Plugin {

	@Override
	public String[] getVerificationTypeProvided() {
		return new String[]{"UNDERSTANDABILITY"};
	}

	@Override
	public String performVerification(String model, String type) {
		String ret = "";
		try{
			
			if(type.equals("UNDERSTANDABILITY")){
				
				MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();
		
				GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readStringModel(model));
				System.out.println(eg);
				
				JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				StringWriter w = new StringWriter();
				jaxbMarshaller.marshal(eg, System.out);
				jaxbMarshaller.marshal(eg, w);
				
				ret =  w.toString();
			} else
				throw new Exception("ERROR: Verification type " + type + " not supported.");
		}catch(Exception ex){
			ex.printStackTrace();
			Utils.log(ex);
			Utils.log("\nModel involved in the exception:\n"+model, LogType.ERROR);
			ret = "<Result><Status>ERROR</Status><Description>"+ex.getMessage()+"</Description></Result>";
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try{
			if(args[0]!= null){
				MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();
				File file = new File(args[0]);
				GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readFileModel(file.getAbsolutePath()));
				System.out.println(eg);
				
				JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				
				jaxbMarshaller.marshal(eg, System.out);
			}
			
				System.out.println("");
				
			
		}catch(Exception ex){Utils.log(ex);}
	}
	
}
