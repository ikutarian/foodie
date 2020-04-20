package com.ikutarian.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ikutarian.annotation.Desensitized;
import com.ikutarian.enums.SensitiveType;

import java.io.IOException;
import java.util.Objects;

/**
 * JSON数据脱敏序列化类
 */
public class DesensitizedSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType sensitiveType;

    public DesensitizedSerializer() {
    }

    public DesensitizedSerializer(SensitiveType sensitiveType) {
        this.sensitiveType = sensitiveType;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        switch (this.sensitiveType) {
            case CHINESE_NAME:
                jsonGenerator.writeString(DesensitizationUtils.chineseName(value));
                break;
            case ID_CARD:
                jsonGenerator.writeString(DesensitizationUtils.idCardNum(value));
                break;
            case FIXED_PHONE:
                jsonGenerator.writeString(DesensitizationUtils.fixedPhone(value));
                break;
            case MOBILE_PHONE:
                jsonGenerator.writeString(DesensitizationUtils.mobilePhone(value));
                break;
            case ADDRESS:
                jsonGenerator.writeString(DesensitizationUtils.address(value, 4));
                break;
            case EMAIL:
                jsonGenerator.writeString(DesensitizationUtils.email(value));
                break;
            case BANK_CARD:
                jsonGenerator.writeString(DesensitizationUtils.bankCard(value));
                break;
            case PASSWORD:
                jsonGenerator.writeString(DesensitizationUtils.cnapsCode(value));
                break;
            case CAR_NUMBER:
                jsonGenerator.writeString(DesensitizationUtils.cnapsCode(value));
                break;
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 为空直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 非 String 类直接跳过
                Desensitized desensitized = beanProperty.getAnnotation(Desensitized.class);
                if (desensitized == null) {
                    desensitized = beanProperty.getContextAnnotation(Desensitized.class);
                }

                if (desensitized != null) {
                    // 如果能得到注解，就将注解的 type 传入 DesensitizedSerializer
                    return new DesensitizedSerializer(desensitized.type());
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        } else {
            return serializerProvider.findNullValueSerializer(null);
        }
    }
}
