package com.example.bigfiveprofile;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;


@JsonComponent
public class DomainDeserializer extends JsonDeserializer<Domain> {
    @Override
    public Domain deserialize(JsonParser jp, DeserializationContext ctx)
        throws IOException, JsonProcessingException {
        String domainName = jp.nextFieldName();
        Domain d = new Domain(domainName, 0);
        if(jp.nextValue() == JsonToken.START_OBJECT){
            while(jp.nextToken() != JsonToken.END_OBJECT){
                switch(jp.getCurrentName()){
                case "Overall Score":
                    int overallS = jp.getValueAsInt();
                    System.out.println(overallS);
                    d.updateOverallScore(overallS);
                    break;
                case "Facets":
                    while(jp.nextToken() != JsonToken.END_OBJECT){
                        if(jp.getCurrentName() != "Facets") {
                            d.addFacet(jp.getCurrentName(), jp.getValueAsInt());
                        }
                    }
                    break;
                }
            }
        }
        return d;
    }
}
