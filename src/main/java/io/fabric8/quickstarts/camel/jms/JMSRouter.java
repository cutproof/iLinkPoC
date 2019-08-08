package io.fabric8.quickstarts.camel.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JMSRouter extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        System.out.println("Configuring route: *********** OPENSHIFT JMS ROUTE ***********");

        from("{{input.queue}}").log(LoggingLevel.DEBUG, log, "*********** New message received ***********").process(exchange ->
        		{
        			System.out.println("Configuring route: *********** Exchange: Started ***********");
                    String convertedMessage = exchange.getIn().getBody() + " is converted *********** ";
                    exchange.getOut().setBody(convertedMessage);
                    System.out.println("Configuring route: *********** Exchange: Ended ***********");
                }
        	).to("{{output.queue}}").log(LoggingLevel.DEBUG, log, "*********** Message sent to the other queue ***********").end();

    }
}