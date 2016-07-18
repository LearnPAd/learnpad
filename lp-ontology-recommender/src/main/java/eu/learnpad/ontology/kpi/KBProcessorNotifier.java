package eu.learnpad.ontology.kpi;

import java.io.InputStream;

import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;

public interface KBProcessorNotifier {

	public void notifyProcessingStatus (String kbProcessId, KBProcessingStatusType status);

	public void notifyKPIValues(String modelSetId, KPIValuesFormat format, String businessActorId, InputStream cockpitContent) throws LpRestException ;

}
