package com.lvmoney.frame.office.excel.util;/**
 * 描述:
 * 包名:com.scltlvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/12
 * Copyright 四川******科技有限公司
 */


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.office.common.exception.OfficeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @describe：excel文档的导入导出，使用的网络上的demo，封装不是很合理。需要改造。用到实体是：BaseExcelByteOutVo，ETemplateVo。 由于网上demo的可用性较高，改造暂未实现。word导入导出的改造主要是byte[]的改造。由于excel的导入导出没有保存到mongo库，所以暂未改造
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class ExcelUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding(BaseConstant.CHARACTER_ENCODE_UTF8_UPPER);
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, BaseConstant.CHARACTER_ENCODE_UTF8_UPPER));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error("excel文档导出下载报错:{}", e);
            throw new BusinessException(OfficeException.Proxy.EXCEL_DOWNLOAD_ERROR);
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("excel模板文件导入为空:{}", e);
            throw new BusinessException(OfficeException.Proxy.EXCEL_TEMPLATE_ERROR);
        } catch (Exception e) {
            logger.error("excel模板文件导入为空:{}", e);
            throw new BusinessException(OfficeException.Proxy.EXCEL_TEMPLATE_ERROR);
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            logger.error("excel文件导入为空:{}", e);
            throw new BusinessException(OfficeException.Proxy.EXCEL_IMPORT_ERROR);
        } catch (Exception e) {
            logger.error("excel导入为空:{}", e);
            throw new BusinessException(OfficeException.Proxy.EXCEL_IMPORT_ERROR);
        }
        return list;
    }

    /**
     * excel 导入
     *
     * @param inputStream 文件输入流
     * @param titleRows   标题行
     * @param headerRows  表头行
     * @param params      自定义验证
     * @param pojoClass   pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows, ImportParams params, Class<T> pojoClass) throws IOException {
        if (inputStream == null) {
            return null;
        }
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setSaveUrl("/excel/");
        params.setNeedSave(true);
        try {
            return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new IOException("excel文件不能为空");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
