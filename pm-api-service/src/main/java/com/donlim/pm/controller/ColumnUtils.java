package com.donlim.pm.controller;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ColumnUtils {
    public static List<List<String>> easyExcelHeaderWithDynamicColsGenerator(String[] titles, Integer cols){
        List<ColumnDto> dynamicCol = ColumnUtils.getColumnsByDate(cols, LocalDate.now(),false);
        List<List<String>> headers = new ArrayList<>();
        for (String title : titles) {
            List<String> header = new ArrayList<>();
            header.add(title);
            headers.add(header);
        }
        List<List<String>> collect = dynamicCol.stream().map(e -> {
            List<String> header = new ArrayList<>();
            header.add(e.getTitle());
            return header;
        }).collect(Collectors.toList());
        headers.addAll(collect);
        return headers;
    }

    /**
     * 获取以日期为header的动态行
     * @param size  日期跨度
     * @param start  开始时间
     * @return
     */
    public static List<ColumnDto> getColumnsByDate(long size, LocalDate start,boolean editFlag){
        ArrayList<ColumnDto> result = new ArrayList<>();
        for (int i = 0 ; i < size ; i ++){
            LocalDate tmp = start.plusDays(i);
            ColumnDto column = new ColumnDto();
            column.setWidth(150);
            column.setTitle(tmp.getMonthValue()+"/"+tmp.getDayOfMonth() +tmp.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA));
            column.setDataIndex(tmp.toString());
            if (editFlag){
                column.setWidth(150);
                if (i == 0) {
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_RIGHT);
                }else if( i == size -1){
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_LEFT);
                }else{
                    column.setElem(ColumnDto.FormElem.INPUT_NUMBER_ALL);
                }
            }

            result.add(column);
        }
        return result;
    }
}
