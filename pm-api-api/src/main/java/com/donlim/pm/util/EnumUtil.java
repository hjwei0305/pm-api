package com.donlim.pm.util;

import com.donlim.pm.em.FileTypeEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/8/11.
 */
public class EnumUtil {
    /**
     * 判断数值是否属于枚举类的值
     * @param key
     * @return
     */
    public static boolean isIncludeFileTypeEnum(String key){
        boolean include = false;
        for (FileTypeEnum e: FileTypeEnum.values()){
            if(e.name().equals(key)){
                include = true;
                break;
            }
        }
        return include;
    }
}
