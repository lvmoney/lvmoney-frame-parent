package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.common.util
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @describe：一致性hash算法
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 10:23
 */
public class ConsistentHashVirtualNodeUtil {
    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     *
     * @param realNodes: 真实节点列表
     * @param total:     虚拟节点数量，合理的数量使得分布更加均匀
     * @throws
     * @return: java.util.SortedMap<java.lang.Integer, java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 14:37
     */
    public static SortedMap<Integer, String> getVirtualNodes(List<String> realNodes, int total) {
        SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();
        for (String str : realNodes) {
            for (int i = 0; i < total; i++) {
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        return virtualNodes;
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     *
     * @param str:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 10:58
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    /**
     * 得到应当路由到的结点
     *
     * @param key:
     * @param virtualNodes:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 14:34
     */
    private static String getServer(String key, SortedMap<Integer, String> virtualNodes) {
        // 得到该key的hash值
        int hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        //virtualNode虚拟节点名称要截取一下
        if (StringUtils.isNotBlank(virtualNode)) {
            return virtualNode.substring(0, virtualNode.indexOf("&&"));
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> servers = new LinkedList() {{
            add("192.168.0.0:111");
            add("192.168.0.1:111");
            add("192.168.0.2:111");
            add("192.168.0.3:111");
            add("192.168.0.4:111");
        }};
        int num = 200;
        List<String> keys = new ArrayList() {
        };
        SnowflakeIdFactoryUtil snowflakeIdFactoryUtil = new SnowflakeIdFactoryUtil(1, 2);
        for (int n = 0; n < 2000; n++) {
            keys.add(String.valueOf(snowflakeIdFactoryUtil.nextId()));
        }
        for (int m = 0; m < 1000; m++) {
            keys.add("test" + m);

        }
        SortedMap<Integer, String> virtualNodes = getVirtualNodes(servers, num);
        for (int i = 0; i < keys.size(); i++) {
            System.out.println("[" + keys.get(i) + "]的hash值为" +
                    getHash(keys.get(i)) + ", " +
                    "被路由到结点[" + getServer(keys.get(i), servers, num) + "]");
        }

        System.out.println("[1907297242]的hash值为" +
                getHash("1907297242") + ", " +
                "被路由到结点[" + getServer("1907297242", servers, num) + "]");


        System.out.println("1907297242:192.168.0.3:111");

        for (int u = 0; u < 20; u++) {
            System.out.println(getHash(String.valueOf(u)));
        }
    }

    /**
     * 根据物理机总数生成递增的数组数据，从1开始
     * 通过循环生成。用以模拟真实的
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     *
     * @param total:
     * @throws
     * @return: java.lang.String[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 14:21
     */
    public static List<String> getVirServers(int total) {
        return new LinkedList() {{
            for (int i = 1; i < total; i++) {
                add(i);
            }
        }};
    }

    /**
     * 获得自定的value值应该放到哪个真实的物节点
     *
     * @param value:   数据
     * @param servers: 节点列表
     * @param total:   虚拟节点个数，该值越合理越均匀
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 14:58
     */
    public static String getServer(String value, List<String> servers, int total) {
        SortedMap<Integer, String> virtualNodes = getVirtualNodes(servers, total);
        return getServer(value, virtualNodes);
    }

}
