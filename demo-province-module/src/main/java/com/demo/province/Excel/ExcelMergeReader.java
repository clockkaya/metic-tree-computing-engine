package com.sama.ledger.Excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelMergeReader {

    /**
     * 通用读取 Excel 并处理合并单元格的方法（支持指定 Sheet）
     *
     * @param file        Excel 文件
     * @param clazz       DO 类型（使用了 @ExcelProperty 注解）
     * @param sheetIndex  Sheet 页索引，从0开始
     * @param <T>         泛型
     * @return            实体列表
     */
    public static <T> List<T> readExcelWithMergeHandling(File file, Class<T> clazz, int sheetIndex) {
        List<T> resultList = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(file)) {
            EasyExcel.read(inputStream, clazz, new AnalysisEventListener<T>() {
                private final List<T> tempList = new ArrayList<>();
                private T lastNotNullRow = null;

                @Override
                public void invoke(T data, AnalysisContext context) {
                    if (data != null) {
                        if (lastNotNullRow != null) {
                            fillMergedCells(data, lastNotNullRow);
                        }
                        tempList.add(data);
                        lastNotNullRow = data;
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    resultList.addAll(tempList);
                }

                private void fillMergedCells(T current, T previous) {
                    for (Field field : current.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
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


    /**
     * @param file                Excel 文件
     * @param sheetNo             第几个 sheet（从 0 开始）
     * @param headRowNumInExcel   表头行数（从 Excel 的第几行开始，1-based）
     * @param dataStartRowInExcel 数据起始行（Excel 里看到的“第几行”，1-based）
     */
    public static <T> List<T> readSheet(File file, int sheetNo, int headRowNumInExcel, int dataStartRowInExcel, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        try (InputStream is = new FileInputStream(file)) {
            // 转换为 0-based index
            int headRowIndex = headRowNumInExcel - 1;
            int dataStartRowIndex = dataStartRowInExcel - 1;

            EasyExcel.read(is, clazz, new ReadListener<T>() {
                        @Override
                        public void invoke(T data, AnalysisContext context) {
                            int rowIndex = context.readRowHolder().getRowIndex();
                            if (rowIndex >= dataStartRowIndex) {
                                dataList.add(data);
                            }
                        }

                        @Override
                        public void onException(Exception exception, AnalysisContext context) throws Exception {
                            if (exception instanceof ExcelDataConvertException) {
                                ExcelDataConvertException ex = (ExcelDataConvertException) exception;
                                System.err.println("❌ Excel 读取异常：");
                                System.err.println("   ➤ 错误的sheet页: " + sheetNo);
                                System.err.println("   ➤ 行号（从1开始）: " + (ex.getRowIndex() + 1));
                                System.err.println("   ➤ 列号（从0开始）: " + ex.getColumnIndex());
                                System.err.println("   ➤ 错误单元格原始数据: " + ex.getCellData());
                            } else {
                                System.err.println("❌ 未知异常：" + exception.getMessage());
                            }
                            exception.printStackTrace();
                            throw exception;
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {}
                    })
                    .sheet(sheetNo)
                    .headRowNumber(headRowIndex + 1) // 这里仍传 headRowNum，因为 EasyExcel 要知道“多少行作为表头”
                    .doRead();

            return dataList;
        } catch (Exception e) {
            throw new RuntimeException("读取 Excel 文件失败: " + file.getName(), e);
        }
    }


}
