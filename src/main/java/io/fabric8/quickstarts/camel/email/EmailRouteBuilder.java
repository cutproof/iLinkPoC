/**
 * 
 */
package io.fabric8.quickstarts.camel.email;

import org.apache.camel.builder.RouteBuilder;

public class EmailRouteBuilder extends RouteBuilder
{
	@Override
	public void configure() throws Exception 
	{
		from("file://C:/tmp/?fileName=person.xml&charset=utf-8&noop=true").doTry()
				.setHeader("subject", simple("US-CIS: New Request"))
				.setHeader("to", simple("harjeet.parmar@ilinksolution.com"))
				.to("smtps://smtp.gmail.com:465?username=cutproof@gmail.com&password=Idams0!");
	}
}
