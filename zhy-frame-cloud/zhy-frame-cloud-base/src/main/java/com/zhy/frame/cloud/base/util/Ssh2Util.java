package com.zhy.frame.cloud.base.util;/**
 * 描述:
 * 包名:com.zhy.k8s.base.util
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import com.zhy.frame.base.core.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.zhy.frame.cache.redis.converter.FrameFastJsonRedisSerializer.DEFAULT_CHARSET;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 22:23
 */
public class Ssh2Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ssh2Util.class);

    /**
     * @param host     主机地址
     * @param username 用户名
     * @param password 密码
     * @Description: 建立ssh2连接
     * @return: Connection
     * @author: lvmoney
     * @time: 2019-03-25 20:46:32
     */
    public static Connection openConnection(String host, int port, String username, String password) {
        Connection connection;

        try {
            connection = new Connection(host, port);

            // 建立ssh2 连接
            connection.connect();

            // 校验用户名密码
            boolean login = connection.authenticateWithPassword(username, password);

            // 登录成功返回连接
            if (login) {
                return connection;
            } else {
                throw new RuntimeException("用户名密码不正确");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param connection ssh2 连接对象
     * @Description: 执行一条命令
     * @return: ExecCmdResult 执行结果, 封装执行状态和执行结果
     * @author: lvmoney
     * @time: 2019-03-25 20:47:14
     */
    public static ExecCmdResult execCommand(Connection connection, String command) {

        ExecCmdResult execCmdResult = new ExecCmdResult();

        Session session = null;

        try {
            session = connection.openSession();

            // 执行命令
            session.execCommand(command);

            // 解析结果
            String result = parseResult(session.getStdout());

            // 若解析结果为空, 则表示执行命令发生了错误, 尝试解析错误输出
            if (result.isEmpty()) {
                result = parseResult(session.getStderr());
            } else {
                execCmdResult.setSuccess(true);
            }

            // 设置响应结果
            execCmdResult.setResult(result);

            return execCmdResult;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * @param connection        ssh2 连接对象
     * @param remoteFilePathAbs 远程文件绝对路径名
     * @param localDir          本地文件夹
     * @Description: 下载文件, 只能下载文件类型, 如果是目录则抛出异常
     * @author: lvmoney
     * @time: 2019-03-25 21:41:33
     */
    public static void download(Connection connection, String localDir, String... remoteFilePathAbs) {

        // 如果传参为空, 则返回
        if (remoteFilePathAbs == null) {
            return;
        }

        SCPClient scpClient = new SCPClient(connection);

        try {
            scpClient.get(remoteFilePathAbs, localDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param connection       ssh2 连接对象
     * @param remoteDirAbsPath 远程文件绝对路径
     * @param fileNamePattern  文件名通配符匹配模式
     * @param localDir         本地目录, 可接受相对绝对路径
     * @Description: 通配符方式下载文件
     * @author: lvmoney
     * @time: 2019-03-25 22:20:07
     */
    public static void downloadByPattern(Connection connection, String localDir, String remoteDirAbsPath, String fileNamePattern) {

        ExecCmdResult execCmdResult = execCommand(connection, "ls " + remoteDirAbsPath + "/" + fileNamePattern);

        if (execCmdResult.isSuccess()) {

            String[] files = execCmdResult.getResult().split("\n");

            SCPClient scpClient = new SCPClient(connection);

            try {
                scpClient.get(files, localDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param connection       ssh2 连接对象
     * @param remoteDirAbsPath 远程服务器文件夹, 必须是绝对路径
     * @param localFile        本地文件列表, 可接受绝对路径相对路径参数
     * @Description: 上传文件至远程服务器. 本地文件不存在时, 抛出异常,但会上传一个0字节的文件; 远程目录不存在时, 抛出异常.
     * @author: lvmoney
     * @time: 2018-03-25 21:44:07
     */
    public static void upload(Connection connection, String remoteDirAbsPath, String... localFile) {

        // 如果传参为空, 则返回
        if (localFile == null) {
            return;
        }

        SCPClient scpClient = new SCPClient(connection);

        try {
            scpClient.put(localFile, remoteDirAbsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param inputStream 输入流
     * @Description: 解析命令结果
     * @return: String 字符串
     * @author: lvmoney
     * @time: 2019-03-25 21:06:23
     */
    private static String parseResult(InputStream inputStream) throws IOException {
        // 读取输出流内容
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_CHARSET));
        StringBuffer resultSb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            resultSb.append(line + "\n");
        }
        return resultSb.toString();
    }
}
