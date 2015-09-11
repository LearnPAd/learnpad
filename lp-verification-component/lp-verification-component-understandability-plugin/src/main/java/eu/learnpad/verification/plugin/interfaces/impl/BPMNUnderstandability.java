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
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

				StringWriter w = new StringWriter();
				jaxbMarshaller.marshal(eg, System.out);
				jaxbMarshaller.marshal(eg, w);
				
				ret =  w.toString();
			} else
				throw new Exception("ERROR: Verification type " + type + " not supported.");
		}catch(Exception ex){
			//ex.printStackTrace();
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

/*
 * 
 * @startuml
abstract class abstractGuideline {
	#Collection<FlowElement> elementsBPMN
	#boolean status
	#String NameProcess
	#String IDProcess
	#String id
	#String Name
	#String Description
	#String Suggestion
	#Collection<String> Elements
	~abstractGuideline()
	~abstractGuideline(List<RootElement> diagram)
	#{abstract}void findGL(List<RootElement> diagram)
	+boolean getStatus()
	+String toString()
	+String getid()
	+void setElements(String element)
	+String getDescription()
	+String getName()
	+String getProcessName()
	+String getProcessID()
	+String getSuggestion()
	#{abstract}int searchSubProcess(SubProcess sub, String ret, int i)
}
class explicitGateways {
	~explicitGateways()
	+explicitGateways(List<RootElement> diagram)
	+void findGL(List<RootElement> diagram)
	#int searchSubProcess(SubProcess sub, String ret, int i)
}

class ExplicitStartEndEvents {
	+ExplicitStartEndEvents(List<RootElement> diagram)
	#void findGL(List<RootElement> diagram)
	#int searchSubProcess(SubProcess sub, String ret, int i)
}
class GuidelinesFactory {
	-String processName
	-String processID
	-String status
	-String description
	-List<RootElement> diagram
	-Collection<abstractGuideline> guidelines
	~GuidelinesFactory()
	+GuidelinesFactory(List<RootElement> graph)
	+Collection<abstractGuideline> getGuidelines()
	+String getProcessName()
	+void setProcessName(String nameProcess)
	+String getProcessID()
	+void setProcessID(String processID)
	+String getStatus()
	-void setStatus()
	+String toString()
}
abstractGuideline <|-- ExplicitStartEndEvents
abstractGuideline <|-- explicitGateways
GuidelinesFactory --> abstractGuideline

class MyBPMN2ModelReader {
	+MyBPMN2ModelReader()
	+List<RootElement> readStringModel(String theBPMNString)
	+List<RootElement> readFileModel(String theBPMNFile)
	+void ReadThisModel(String theBPMNFile)
	-{static}Definitions getDefinitions(Resource resource)
}

class BPMNUnderstandability {
	+String[] getVerificationTypeProvided()
	+String performVerification(String model, String type)
	+{static}void main(String[] args)
}
interface Plugin {
}
Plugin <|.. BPMNUnderstandability
BPMNUnderstandability --> MyBPMN2ModelReader 
BPMNUnderstandability --> GuidelinesFactory

@enduml
 * 
 */

