package webapi.camel.router;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class ContatoRouter extends RouteBuilder {

    CamelContext ctx = new DefaultCamelContext();

    @Override
    public void configure() throws Exception {

        //restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
        restConfiguration().host("localhost").port(8080);

        // a cada 10s envia uma mensagem para o console Mostrando todos os Contatos cadastrados
        from("timer:rota1?period=10000")
            .transform().simple("estes sao todos os contatos! ")
            .to("rest:get:api/contato/todos")
            .log("${body}");
        
        // criar um novo contato apenas uma vez 
        from("timer://rota2?repeatCount=1")
            .process(exchange -> exchange.getIn().setBody("{\"nome\": \"Joao\", \"idade\": \"21\", \"cidade\": \"Guarulhos\"}"))
            .setHeader(Exchange.HTTP_METHOD, constant("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .to("rest:post:api/contato/novoContato")
            .process(exchange -> log.info("Contato criado: {}", exchange.getIn().getBody(String.class)));

    }

    
    
}
