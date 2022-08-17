package com.donlim.pm.util;

import com.donlim.pm.em.FileType;
import com.donlim.pm.em.NodeType;
import com.donlim.pm.em.SmallNodeType;

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



}
