package io.fabric8.quickstarts.camel.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import io.fabric8.quickstarts.camel.email.Emailer;

@Slf4j
@Component
public class JMSRouter extends RouteBuilder
{
    @Override
    public void configure() throws Exception
    {
        System.out.println("Configuring route: *********** OPENSHIFT JMS ROUTE ***********");
        Emailer emailer = new Emailer();

        //from("{{input.queue}}").log(LoggingLevel.DEBUG, log, "*********** New message received ***********").process(exchange ->
        from("file://C:/tmp/?fileName=person.xml&charset=utf-8&noop=true").log(LoggingLevel.DEBUG, log, "*********** New message received ***********").process(exchange ->
        		{
        			System.out.println("Exchange: Converting Message: *********** Exchange: Started ***********");
                    String convertedMessage = (String)exchange.getIn().getBody();
                    System.out.println("The Converted Message is: " + convertedMessage);
                    exchange.getOut().setBody(convertedMessage);
                    System.out.println("Exchange: Converting Message: *********** Exchange: Ended ***********");
                }
        	).to("{{output.queue}}").log(LoggingLevel.DEBUG, log, "*********** Message sent to the other queue ***********").end();
        
        emailer.emailer();
    }
}