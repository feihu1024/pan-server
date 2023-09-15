package com.feihu1024.panserver.oauth2;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * oauth2异常序列化
 */
public class CustomAuthExceptionJackson2Serializer extends StdSerializer<CustomAuthException> {

    public CustomAuthExceptionJackson2Serializer() {
        super(CustomAuthException.class);
    }

    @Override
    public void serialize(CustomAuthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeBooleanField("success",false);
        jsonGenerator.writeNumberField("code", e.getHttpErrorCode());
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeObjectField("data",null);
        jsonGenerator.writeEndObject();
    }
}
