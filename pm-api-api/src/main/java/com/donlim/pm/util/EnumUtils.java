package com.donlim.pm.util;

import com.donlim.pm.em.FileType;
import com.donlim.pm.em.NodeType;
import com.donlim.pm.em.SmallNodeType;

import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/8/11.
 */
public class EnumUtils extends com.changhong.sei.util.EnumUtils {
    /**
     * 判断数值是否属于枚举类的值
     * @param key
     * @return
     */
    public static boolean isIncludeFileTypeEnum(String key){
        boolean include = false;
        for (FileType e: FileType.values()){
            if(e.name().equals(key)){
                include = true;
                break;
            }
        }
        return include;
    }

    /**
     * 获取小节点说明
     * @param type
     * @return
     */
    public static String getSmallNodeRemark(SmallNodeType type)
    {
       return getEnumItemRemark(SmallNodeType.class, type);
    }

    /**
     * 获取节点状态
     * @param type
     * @return
     */
    public static String getNodeTypeRemark(NodeType type)
    {
        return getEnumItemRemark(NodeType.class, type);
    }

    /**
     * 根据@Remark获取枚举对象
     * @param enumClass
     * @param remark
     * @return
     */
    public static <E extends Enum<E>> E getEnumByRemark(Class<E> enumClass,String remark) {
        List<EnumEntity> enumEntities = getEnumDataList(enumClass);
        E anEnum = null;
        for (EnumEntity enumEntity : enumEntities) {
            if(remark.equals(enumEntity.getRemark())){
                anEnum = (E) enumEntity.getAnEnum();
            }
        }
        return anEnum;
    }



}
