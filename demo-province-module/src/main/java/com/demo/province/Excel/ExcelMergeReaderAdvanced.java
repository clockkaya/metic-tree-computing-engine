package com.sama.ledger.Excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/8/4 10:20
 */
public class ExcelMergeReaderAdvanced {

    /**
     * 通用读取 Excel 并处理合并单元格的方法（支持指定 Sheet 和排除字段）
     *
     * @param file              Excel 文件
     * @param clazz             DO 类型（使用了 @ExcelProperty 注解）
     * @param sheetIndex        Sheet 页索引，从0开始
     * @param excludeFields     不参与合并处理的字段名列表
     * @param <T>               泛型
     * @return                  实体列表
     */
    public static <T> List<T> readExcelWithMergeHandling(File file, Class<T> clazz, int sheetIndex, List<String> excludeFields) {
        List<T> resultList = new ArrayList<>();
        Set<String> excludeFieldSet = new HashSet<>(excludeFields);

        try (InputStream inputStream = new FileInputStream(file)) {
            EasyExcel.read(inputStream, clazz, new AnalysisEventListener<T>() {
                private final List<T> tempList = new ArrayList<>();
                private T lastNotNullRow = null;

                @Override
                public void invoke(T data, AnalysisContext context) {
                    if (data != null) {
                        if (lastNotNullRow != null) {
                            fillMergedCells(data, lastNotNullRow, excludeFieldSet);
                        }
                        tempList.add(data);
                        lastNotNullRow = data;
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    resultList.addAll(tempList);
                }

                private void fillMergedCells(T current, T previous, Set<String> excludeFieldSet) {
                    for (Field field : current.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        // 如果字段在排除列表中，则跳过不处理
                        if (excludeFieldSet.contains(field.getName())) {
                            continue;
                        }

                        try {
                            Object curVal = field.get(current);
                            Object preVal = field.get(previous);
                            if (curVal == null && preVal != null) {
                                field.set(current, preVal);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("字段访问异常：" + field.getName(), e);
                        }
                    }
                }
                //指定sheetIndex
            }).sheet(sheetIndex).headRowNumber(1).doRead();

        } catch (Exception e) {
            throw new RuntimeException("读取 Excel 文件失败", e);
        }
        return resultList;
    }

}
