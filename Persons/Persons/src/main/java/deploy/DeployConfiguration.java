package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeployConfiguration implements ServletContextListener
{
    public static String PU_NAME = "JPAPU";

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {        
        Map<String, String> env = System.getenv();

        if (env.keySet().contains("DODeployment"))
        {
            PU_NAME = "JPAPU";
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
    }
}