

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="pageController")
@SessionScoped
public class PageController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4584289279328839768L;

	public String navigatePage1() {
		System.out.println("Redirect to Page 1");
		
		return "page1?faces-redirect=true";
	}
	
	public String navigatePage2() {
		System.out.println("Redirect to Page 2");
		
		return "page2?faces-redirect=true";
	}
	
	public String navigatePageGetAnalysis() {
		System.out.println("Redirect to Page getanalysis");
		
		return "getanalysis?faces-redirect=true";
	}
	
	public String navigatepageAnalysis(){
		System.out.println("Redirect to Page Analysis");
		return "pageAnalysis?faces-redirect=true";
	}
	
	public String navigatePageCF() {
		System.out.println("Redirect to Page 2");
		
		return "contentform?faces-redirect=true";
	}
	
	public String action() {
		System.out.println("Action Fired");
		
		return null;
	}
	
	public void actionListener(ActionEvent event) {
		System.out.println("ActionListener Fired");
	}
}
