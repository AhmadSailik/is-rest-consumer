package com.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RestConsumer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:foo?repeatCount=1")
//        from("direct:processName")
                .setHeader(Exchange.HTTP_METHOD,simple("GET"))
                .setHeader(Exchange.CONTENT_TYPE,simple("application/json"))
                .to("https://pokeapi.co/api/v2/pokemon/ditto")
                .unmarshal().json(JsonLibrary.Jackson)
                .log("${body[abilities][0][ability][name]}");
    }
}
