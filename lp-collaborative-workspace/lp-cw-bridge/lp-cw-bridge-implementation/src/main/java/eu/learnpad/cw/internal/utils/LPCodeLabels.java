package eu.learnpad.cw.internal.utils;

public class LPCodeLabels {

	private static final String CLASSES_SPACE = "LPCode";
	private static final String BASE_ELEMENT_CLASS = "BaseElementClass";
	private static final String MODEL_CLASS = "ModelClass";
	private static final String MODELSET_CLASS = "ModelSetClass";
	private static final String COLLABORATIVE_DOCUMENT_CLASS = "CollaborativeDocumentClass";
	private static final String FEEDBACK_CLASS = "FeedbackClass";
	
	private static final String MODELSETID_LABEL = "modelsetid";
	private static final String MODELID_LABEL = "modelid";
	private static final String ARTIFACTID_LABEL = "artifactid";

	private static final String ID_LABEL = "id";
	
	
	public static String getCLASSES_SPACE() {
		return CLASSES_SPACE;
	}


	public static String getBASE_ELEMENT_CLASS() {
		return BASE_ELEMENT_CLASS;
	}

	public static String getMODEL_CLASS() {
		return MODEL_CLASS;
	}

	public static String getMODELSET_CLASS() {
		return MODELSET_CLASS;
	}

	public static String getCOLLABORATIVE_DOCUMENT_CLASS() {
		return COLLABORATIVE_DOCUMENT_CLASS;
	}


	public static String getFEEDBACK_CLASS() {
		return FEEDBACK_CLASS;
	}

	public static String getMODELSETID_LABEL() {
		return MODELSETID_LABEL;
	}

	public static String getMODELSETID_LABEL(String className) {
		String value;
		switch (className) {
		case MODELSET_CLASS:
			value = ID_LABEL;
			break;
		default:
			value = MODELSETID_LABEL;
			break;
		}
		return value;
	}

	public static String getMODELID_LABEL() {
		return MODELID_LABEL;
	}

	public static String getMODELID_LABEL(String className) {
		String value;
		switch (className) {
		case MODEL_CLASS:
			value = ID_LABEL;
			break;
		default:
			value = MODELID_LABEL;
			break;
		}
		return value;
	}

	public static String getARTIFACTID_LABEL() {
		return ARTIFACTID_LABEL;
	}

	public static String getARTIFACTID_LABEL(String className) {
		String value;
		switch (className) {
		case BASE_ELEMENT_CLASS:
			value = ID_LABEL;
			break;
		default:
			value = ARTIFACTID_LABEL;
			break;
		}
		return value;
	}
}
