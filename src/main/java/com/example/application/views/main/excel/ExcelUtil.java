package com.example.application.views.main.excel;

import cn.hutool.core.bean.BeanUtil;
import com.example.application.views.main.model.Person;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ExcelUtil {

    public static <T> byte[] prepare(Class<T> clazz, Stream<T> items) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            XSSFSheet sheet = workbook.createSheet("Details");
            addHeader(clazz, sheet);
            items.forEach(withCounter((rowNum, item) -> addRow(sheet, rowNum, item)));

            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void addRow(XSSFSheet sheet, Integer rowNum, T item) {
        XSSFRow row = sheet.createRow(rowNum + 1);
        BeanUtil.beanToMap(item)
                .entrySet()
                .forEach(withCounter((index, entry) -> {
                    XSSFCell cell = row.createCell(index);
                    cell.setCellValue(String.valueOf(entry.getValue()));
                }));
    }

    private static <T> void addHeader(Class<T> clazz, XSSFSheet sheet) {
        try {
            XSSFRow headerRow = sheet.createRow(0);
            BeanUtil.beanToMap(clazz.getConstructor().newInstance())
                    .keySet()
                    .forEach(withCounter((index, fieldName) -> {
                        XSSFCell cell = headerRow.createCell(index);
                        cell.setCellValue(fieldName);
                    }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> Consumer<T> withCounter(BiConsumer<Integer, T> biConsumer) {
        AtomicInteger index = new AtomicInteger(0);
        return person -> biConsumer.accept(index.getAndIncrement(), person);
    }
}
