package com.lvmoney.demo.video.test;/**
 * 描述:
 * 包名:com.lvmoney.demo.video.test
 * 版本信息: 版本1.0
 * 日期:2020/6/3
 * Copyright XXXXXX科技有限公司
 */


import java.io.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/3 10:58
 */
public class VideoTest {

    String videoPath = "E:/data/";
    String rtspServer = "rtsp://127.0.0.1/test";

    public boolean pushVideoAsRTSP(long id, String fileName) throws IOException {
        Process process = null;
        boolean flag = false;
        // ffmpeg位置，最好写在配置文件中
        String ffmpegPath = "E:/pkg/ffmpeg-20200601-dd76226-win64-static/ffmpeg-20200601-dd76226-win64-static/bin//";
        try {
            // 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
            if (process != null) {
                process.destroy();
                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
            }
            // cmd命令拼接，注意命令中存在空格
            String command = ffmpegPath; // ffmpeg位置
            command += "ffmpeg -re"; // ffmpeg开头，-re代表按照帧率发送，在推流时必须有
            command += " -i " + videoPath + id + "/" + fileName; // 指定要推送的视频
            command += " -f rtsp " + rtspServer; // 指定推送服务器，-f：指定格式
            System.out.println("ffmpeg推流命令：" + command);

            // 运行cmd命令，获取其进程
            process = Runtime.getRuntime().exec(command);
            // 输出ffmpeg推流日志
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("视频推流信息[" + line + "]");
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        VideoTest videoTest = new VideoTest();
        videoTest.pushVideoAsRTSP(1, "dnxk.mp4");
    }
}
