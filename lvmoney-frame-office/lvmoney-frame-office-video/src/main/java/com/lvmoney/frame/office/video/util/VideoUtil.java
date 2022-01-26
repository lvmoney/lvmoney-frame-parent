package com.lvmoney.frame.office.video.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.office.video.util
 * 版本信息: 版本1.0
 * 日期:2021/10/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.core.util.FileUtil;
import com.lvmoney.frame.office.video.vo.GetDurationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;

import static com.lvmoney.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/10/12 11:43
 */
public class VideoUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoUtil.class);

    /**
     * 默认scale
     */
    private static final Integer DEFAULT_SCALE = 2;


    /**
     * 获得视频或者音频的时间长度
     *
     * @param fileUrl:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/10/12 11:47
     */
    public static GetDurationVo getDuration(String fileUrl) {
        File source = new File(fileUrl);
        try {
            MultimediaObject instance = new MultimediaObject(source);
            MultimediaInfo result = instance.getInfo();
            long ls = result.getDuration() / LONG_SEC_MS;
            Integer hour = (int) (ls / LONG_HOUR_SEC);
            Integer minute = (int) (ls % LONG_HOUR_SEC) / LONG_MIN_SEC;
            Integer second = (int) (ls - hour * LONG_HOUR_SEC - minute * LONG_MIN_SEC);
            GetDurationVo getDurationVo = new GetDurationVo();
            getDurationVo.setHour(hour);
            getDurationVo.setMin(minute);
            getDurationVo.setSec(second);
            return getDurationVo;
        } catch (Exception e) {
            LOGGER.error("获得文件:{}的时长报错:{}", fileUrl, e);
            return null;
        }

    }

    /**
     * 获得文件流的时长
     *
     * @param buf:
     * @throws
     * @return: GetDurationVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/10/13 8:31
     */
    public static GetDurationVo getDuration(byte[] buf, String filePath, String fileName) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File source = new File(filePath + File.separator + fileName);
        try (FileOutputStream fos = new FileOutputStream(source); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(buf);
            MultimediaObject instance = new MultimediaObject(source);
            MultimediaInfo result = instance.getInfo();
            long ls = result.getDuration() / LONG_SEC_MS;
            Integer hour = (int) (ls / LONG_HOUR_SEC);
            Integer minute = (int) (ls % LONG_HOUR_SEC) / LONG_MIN_SEC;
            Integer second = (int) (ls - hour * LONG_HOUR_SEC - minute * LONG_MIN_SEC);
            GetDurationVo getDurationVo = new GetDurationVo();
            getDurationVo.setHour(hour);
            getDurationVo.setMin(minute);
            getDurationVo.setSec(second);
            getDurationVo.setDuration(ls);
            return getDurationVo;
        } catch (Exception e) {
            LOGGER.error("获得文件流的时长报错:{}", e);
            return null;
        }

    }

    /**
     * 获得文件大小 ,单位兆
     *
     * @param source:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/10/12 11:47
     */
    public static String getSize(File source) {
        String size = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(source);
        } catch (FileNotFoundException e) {
            LOGGER.error("获取文件{}的FileInputStream报错:{}", e);
        }

        try (FileChannel fc = fis.getChannel()) {
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize.divide(new BigDecimal(OMEN_SIZE), DEFAULT_SCALE, RoundingMode.HALF_UP).toString();
        } catch (FileNotFoundException e) {
            LOGGER.error("获取文件{}报错:{}", e);
        } catch (IOException e) {
            LOGGER.error("文件{}io报错:{}", e);
        }
        return size;
    }

    /**
     * 获得文件流大小
     *
     * @param buf:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/10/12 11:47
     */
    public static String getSize(byte[] buf) {
        String size = "";
        InputStream inputStream = FileUtil.byte2Input(buf);
        BigDecimal fileSize = null;
        try {
            fileSize = new BigDecimal(inputStream.available());
        } catch (IOException e) {
            LOGGER.error("获取:文件流长度报错{}", e);
        }
        size = fileSize.divide(new BigDecimal(OMEN_SIZE), DEFAULT_SCALE, RoundingMode.HALF_UP).toString();
        return size;
    }
}
