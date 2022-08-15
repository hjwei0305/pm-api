package com.donlim.pm.util;

import com.changhong.sei.util.EnumUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ClassName EnumJsonRemarkSerializer
 * @Description 替换枚举类输出值
 * @Author yangjiateng
 * @Date 2022/5/25 10:20
 **/
public class EnumJsonSerializer extends JsonSerializer<Enum> {
    private final static Logger LOG = LoggerFactory.getLogger(com.donlim.pm.util.EnumJsonSerializer.class);

    /**
     * 对添加@Remark的枚举属性，替换原输出
     * @param value
     * @param gen
     * @param serializers
     * @throws IOException
     */
    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        String remark = EnumUtils.getEnumItemRemark(value.getClass(),value);
        try {
            Class<?> contextUtilClazz = Class.forName("com.changhong.sei.core.context.ContextUtil");
            Method method = contextUtilClazz.getMethod("getMessage", String.class);
            remark = (String) method.invoke(null, remark);
        } catch (Exception e) {
            LOG.error("枚举多语言序列化异常.", e);
        }
        //新增属性值
        gen.writeString(remark);
    }
}
