package rotate;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(String[] args) throws LifecycleException,
    InterruptedException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8082);

    Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

    Tomcat.addServlet(ctx, "FileUploadServlet", new FileUploadServlet());
    
    ctx.setAllowCasualMultipartParsing(true);
    ctx.addServletMappingDecoded("/rotate", "FileUploadServlet");
    
    
    tomcat.start();
    tomcat.getConnector();
    }
}