package eu.leranpad.core.impl.others;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.mv.rest.data.FinalResultType;
import eu.learnpad.mv.rest.data.FormalVerificationResult;
import eu.learnpad.mv.rest.data.UnderstandabilityResult;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationResults.Results;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.StatusType;

public class MVStubber {

	private static boolean isCompleted = false;
	private static String fakeVerificationID = "96e5736d-this-is-fake-e94e97ffe0b2"; 
	
	public static VerificationId getVerificationId(){
		VerificationId vID = new VerificationId();
		vID.setId(fakeVerificationID);
		return vID;
	}
	
	public static VerificationStatus getVerificationStatus(){
		VerificationStatus status = new VerificationStatus();
		StatusType value;
		if (isCompleted){
			value = StatusType.COMPLETED;
			isCompleted = false;			
		}else{
			value = StatusType.IN_PROGRESS;
			isCompleted = true;
		}
		status.setStatus(value);
		return status;
	}
	
	public static boolean isFakeVerificationID(String verificationID){
		return verificationID.equals(fakeVerificationID);
	}

	public static VerificationResults getVerificationResults() throws LpRestExceptionXWikiImpl{
		VerificationResults results = new VerificationResults();		
        Results values = new Results();

        String fakeFormalResults = "<FormalVerificationResult><VerificationType>FAKE FORMAL</VerificationType><DefinitionID>def_30278</DefinitionID><Status>KO</Status><Description>This is a Fake</Description></FormalVerificationResult>";
        String fakeUnderstandabilityResults = "<UnderstandabilityResult><VerificationType>FAKE UNDERSTANDABILITY</VerificationType><DefinitionID>def_30278</DefinitionID><Status>KO</Status><Description>This is a Fake</Description><Guidelines><Guideline id=\"2\" Name=\"FAKE\"><Description>Do not follow this FAKE guidelines</Description><Suggestion>FAKE</Suggestion></Guideline></Guidelines></UnderstandabilityResult>";
        
        try{                    	
            StringReader fakeReader = new StringReader(fakeFormalResults);
            JAXBContext jaxbContext = JAXBContext.newInstance(FormalVerificationResult.class);
            FormalVerificationResult formalResults = (FormalVerificationResult) jaxbContext.createUnmarshaller().unmarshal(fakeReader);
            		
            jaxbContext = JAXBContext.newInstance(UnderstandabilityResult.class);
            fakeReader = new StringReader(fakeUnderstandabilityResults);
            UnderstandabilityResult undestrandabilityResults = (UnderstandabilityResult) jaxbContext.createUnmarshaller().unmarshal(fakeReader);
            
            values.getAny().add(formalResults);
            values.getAny().add(undestrandabilityResults);
        }catch(Exception ex){
            throw new LpRestExceptionXWikiImpl("ERROR while stubbing the Results for " + fakeVerificationID);
        }
		
		results.setVerificationID(fakeVerificationID);
		results.setVerificationType("FAKE!!");
		results.setTime(String.valueOf(System.currentTimeMillis()));
		results.setFinalResult(FinalResultType.OK);
		results.setResults(values);
		
		return results;
	}
	
	public static void main (String args[]){
		try {
			MVStubber.getVerificationResults();
		} catch (LpRestExceptionXWikiImpl e) {
			e.printStackTrace();
		}
	}
}
