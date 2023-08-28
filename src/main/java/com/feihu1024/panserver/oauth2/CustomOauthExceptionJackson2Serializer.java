package com.feihu1024.panserver.oauth2;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * oauth2异常序列化
 */
public class CustomOauthExceptionJackson2Serializer extends StdSerializer<CustomOAuth2Exception> {

    public CustomOauthExceptionJackson2Serializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("success",false);
        jsonGenerator.writeNumberField("code", e.getHttpErrorCode());
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeObjectField("data",null);
        jsonGenerator.writeEndObject();
    }
}
