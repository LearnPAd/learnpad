package eu.learnpad.ca.gate;

import gate.Gate;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GateServletContextListener implements ServletContextListener{
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GateServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("ServletContextListener destroyed");

	}

	//Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try { 
			ServletContext ctx = sce.getServletContext();
			if(!Gate.isInitialised()){
				// use /path/to/your/webapp/WEB−INF as gate.home 
				File gateHome = new File(ctx.getRealPath("/WEB-INF")); 

				Gate.setGateHome(gateHome); 
				// thus webapp/WEB−INF/plugins is the plugins directory, and 
				// webapp/WEB−INF/gate.xml is the site config file. 

				// Use webapp/WEB−INF/user−gate.xml as the user config file, 
				//  to avoid confusion with your own user config. 
				Gate.setUserConfigFile(new File(gateHome, "gate.xml")); 

				Gate.init(); 
				// load plugins, for example... 
				Gate.getCreoleRegister().registerDirectories( 
						ctx.getResource("/WEB-INF/plugins/ANNIE")); 
				Gate.getCreoleRegister().registerDirectories( 
						ctx.getResource("/WEB-INF/plugins/Tagger_NP_Chunking")); 
			}

		} 
		catch(Exception e) { 
			log.error(e.getMessage());
		} 
		log.info("ServletContextListener started");	
	}

}
