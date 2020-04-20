package com.ikutarian.annotation;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ikutarian.enums.SensitiveType;
import com.ikutarian.util.DesensitizedSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 信息脱敏注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizedSerializer.class)
public @interface Desensitized {

    /**
     * 敏感信息的类型
     */
    SensitiveType type();
}
