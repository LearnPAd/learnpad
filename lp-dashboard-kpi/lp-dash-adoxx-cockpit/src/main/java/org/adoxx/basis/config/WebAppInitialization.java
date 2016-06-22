package org.adoxx.basis.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.adoxx.dashboard.utils.DashboardTransformer;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class WebAppInitialization extends HttpServlet {

    Logger log = Logger.getLogger(WebAppInitialization.class);

    private String dataRoot;

    @Override
    public void init() throws ServletException {
        log.info("----------   WebAppInitialization Servlet Initialized started   ----------");
        log.info("----------   WebAppInitialization Servlet - Transformation stared at file base: " + getServletContext().getRealPath("/") + "   ----------");
        Properties prop = new Properties();
        ClassLoader classLoader = WebAppInitialization.class.getClassLoader();
        InputStream in = null;
        try{
            in = classLoader.getResourceAsStream("application.properties");
            prop.load(in);
            dataRoot = prop.getProperty("working.directory", "");
            File dataRootDirectory = new File(dataRoot);
            if(!dataRootDirectory.exists()){
                dataRoot = prop.getProperty("working.directory.alternative", "");
                dataRootDirectory = new File(dataRoot);
                if(!dataRootDirectory.exists()){
                    log.error("Working directory not specified in application properties or does not exist.");
                }
            }
        }catch(Exception ex){
            log.error("Error when loading application properties.", ex);
            return;
        }finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException ex) {
                    log.error("Error when loading application properties.", ex);
                    return;
                }
            }
        }
        log.info("---------- WebAppInitialization Servlet Initialized successfully ----------");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessActorId = req.getParameter("businessActorId");
        if (businessActorId == null) {
            log.info("Business actor not defined (request parameter 'businessActorId=<id>'). Load default page.");
            return;
        }

        resp.setContentType("application/json");
        DashboardTransformer.transform(getServletContext().getRealPath("/"), dataRoot, businessActorId, resp.getOutputStream());
    }
    
}
