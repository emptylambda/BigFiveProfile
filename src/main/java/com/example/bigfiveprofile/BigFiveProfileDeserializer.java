package com.example.bigfiveprofile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.type.TypeReference;

@JsonComponent
public class BigFiveProfileDeserializer extends JsonDeserializer<BigFiveProfile> {
    @Override
    public BigFiveProfile deserialize(JsonParser jp, DeserializationContext ctx)
        throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jp);
        String name = node.get("NAME").asText();
        String email = node.get("EMAIL").asText();
        ArrayList<Domain> domains = new ArrayList<Domain>();
        System.out.println("NAME and EMAIL done");
        Iterator<String> fNames = node.fieldNames();
        while(fNames.hasNext()){
            String currentFieldName = fNames.next();
            switch(currentFieldName){
                case "NAME":
                    break;
                case "EMAIL":
                    break;
            default:
                String domainName = currentFieldName;
                JsonNode content = node.get(currentFieldName);
                JsonNode overallS = content.get("Overall Score");
                Domain d = new Domain(domainName, overallS.asInt());
                Iterator<JsonNode> elems = content.elements();
                while(elems.hasNext()){
                    JsonNode elem = elems.next();
                    if(elem.isContainerNode()){
                        Iterator<String> facetNames = elem.fieldNames();
                        while(facetNames.hasNext()){
                            String facetName = facetNames.next();
                            //System.out.println(facetName + " = " + elem.get(facetName));
                            d.addFacet(facetName, elem.get(facetName).asInt());
                        }
                    }
                }
                domains.add(d);
            // default:
            //     System.out.println(currentFieldName);
            //     String newJsonString = "{\"" + currentFieldName + "\":" + node.get(currentFieldName).toString() + "}";
            //     System.out.println(newJsonString);
                // JsonNode n = node.path(currentFieldName);
                // System.out.println(n.getNodeType());
                // JsonParser parser = n.traverse();
                // parser.setCodec(jp.getCodec());
                // Domain d = ctx.readValue(node.get(currentFieldName).traverse(), Domain.class);
                // // Domain d = parser.readValueAs(Domain.class);
                // System.out.println(d.getDomainName());
            }
        }

        // while((jp.nextToken() != JsonToken.NOT_AVAILABLE && jp.getCurrentName() != null) || jp.nextFieldName() != null){
        //     System.out.println(jp.getCurrentName());
        //     switch(jp.getCurrentName()){
        //         case "NAME":
        //             name = jp.getValueAsString();
        //             break;
        //         case "EMAIL":
        //             email = jp.getValueAsString();
        //             break;
        //         default: //TODO now only reads in the FIRST domain not further
        //             JsonNode node = jp.getCodec().readTree(jp);
        //             JsonParser parser = node.traverse();
        //             parser.setCodec(jp.getCodec());
        //             // List<Domain> list = parser.readValueAs(new TypeReference<List<Domain>>() {});
        //             // System.out.println(list.get(0).getDomainName());
        //             Domain d = parser.readValueAs(Domain.class);
        //             System.out.println(d.getDomainName());
        //     }
        // }
        return new BigFiveProfile(name, email, domains);
    }
}
