package com.lvmoney.frame.core.util;


import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */
public class MerkleTreeUtil<T> {
    /**
     * transaction List
     */
    List<T> txList;
    /**
     * Merkle Root
     */
    String root;

    /**
     * constructor
     *
     * @param txList: transaction List 交易List
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:56
     */
    public MerkleTreeUtil(List<T> txList) {
        this.txList = txList;
        List<T> tempTxList = new ArrayList<T>();
        for (int i = 0; i < this.txList.size(); i++) {
            tempTxList.add(this.txList.get(i));
        }
        List<T> newTxList = getNewTxList(tempTxList);
        while (newTxList.size() != 1) {
            newTxList = getNewTxList(newTxList);
        }
        this.root = (String) newTxList.get(0);
    }

    /**
     * Node Hash List.
     *
     * @param tempTxList:
     * @throws
     * @return: java.util.List<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:57
     */
    private List<T> getNewTxList(List<T> tempTxList) {

        List<T> newTxList = new ArrayList<T>();
        int index = 0;
        while (index < tempTxList.size()) {
            // left
            String left = (String) tempTxList.get(index);
            index++;
            // right
            String right = "";
            if (index != tempTxList.size()) {
                right = (String) tempTxList.get(index);
            }
            // sha2 hex value
            String sha2HexValue = HashUtil.getStringHash(left + right, BaseConstant.SHA_256_SIGNATURE_TYPE);
            newTxList.add((T) sha2HexValue);
            index++;

        }

        return newTxList;
    }

    /**
     * hex string
     *
     * @param str:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:57
     */
    public String getSHA2HexValue(String str) {
        byte[] cipher_byte;
        try {
            MessageDigest md = MessageDigest.getInstance(BaseConstant.SHA_256_SIGNATURE_TYPE);
            md.update(str.getBytes());
            cipher_byte = md.digest();
            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
            for (byte b : cipher_byte) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * get Root
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:57
     */
    public String getRoot() {
        return this.root;
    }

    public static void main(String[] args) {
        List<String> tempTxList = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            tempTxList.add("aasdfasdfadsfadsfadfadsfadsfa11" + i);
        }
        System.out.println(tempTxList.size());
        MerkleTreeUtil MerkleTreeUtil = new MerkleTreeUtil(tempTxList);
        // MerkleTreeUtil.merkle_tree();
        System.out.println("root : " + MerkleTreeUtil.getRoot());
    }
}
