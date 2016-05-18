

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="pageController")
@SessionScoped
public class PageController implements Serializable {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PageController.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584289279328839768L;

	public String navigatePage1() {
		log.info("Redirect to Page 1");
		
		return "page1?faces-redirect=true";
	}
	
	public String navigatePage2() {
		log.info("Redirect to Page 2");
		
		return "page2?faces-redirect=true";
	}
	
	public String navigatePageGetAnalysis() {
		log.info("Redirect to Page getanalysis");
		
		return "getanalysis?faces-redirect=true";
	}
	
	public String navigatepageAnalysis(){
		log.info("Redirect to Page Analysis");
		return "pageAnalysis?faces-redirect=true";
	}
	
	public String navigatePageCF() {
		log.info("Redirect to Page 2");
		
		return "contentform?faces-redirect=true";
	}
	
	public String action() {
		log.info("Action Fired");
		
		return null;
	}
	
	public void actionListener(ActionEvent event) {
		log.info("ActionListener Fired");
	}
}
