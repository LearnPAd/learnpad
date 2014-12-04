package ch.fhnw.wiki;

public class Xwiki {

	private static String BPMN = null;
	private static String Company = null;
	private static String WikiUser;

	public Xwiki(){


	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public static String getBPMN() {
		return BPMN;
	}

	public void setBPMN(String bPMN) {
		BPMN = bPMN;
	}

	public static String getWikiUser() {
		return WikiUser;
	}

	public void setWikiUser(String wikiUser) {
		WikiUser = wikiUser;
	}

}
