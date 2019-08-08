/**
 * 
 */
package io.fabric8.quickstarts.camel.email;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author cutproof
 *
 */
public class Emailer
{
    public void emailer()
    {
        EmailRouteBuilder routeBuilder = new EmailRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();
        try
        {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
