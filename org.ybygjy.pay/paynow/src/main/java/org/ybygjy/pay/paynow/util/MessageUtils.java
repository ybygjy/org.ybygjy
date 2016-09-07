package org.ybygjy.pay.paynow.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ybygjy.pay.util.EncryDecryUtils;

public class MessageUtils {
    /**
     * 解密报文
     * @param dataStr 报文串
     * @param charset 编码字符集
     * @return
     */
    public List resolveMessage(String dataStr, String charset) {
        List respList = new ArrayList();

        try {
            dataStr = java.net.URLDecoder.decode(dataStr, charset);
            dataStr = dataStr.replaceAll(" ", "+");
        } catch (Exception e) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }

        String resp_code;// 响应码
        String code;// 功能代码
        String data;// 3DES的串
        String sign;// MD5的签名

        String DSEKey = Config.getInstance().getAttr(Config.KEY_DES);// 3DES需要的key
        String MD5Key = Config.getInstance().getAttr(Config.KEY_MD5);// MD5需要的key

        // logger.info("请求的数据做解析base64");
        respList = this.base(dataStr);
        resp_code = (String) respList.get(0);
        if (!"00".equals(resp_code)) {
            respList.clear();
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        code = (String) respList.get(1);
        data = (String) respList.get(2);
        sign = (String) respList.get(3);

        if ("0".equals(code)) {
            respList.clear();
            respList.add("01");
            respList.add(data);
            return respList;
        }

        // logger.info("解析3DES密文");
        respList = this.DESMessage(data, DSEKey);
        resp_code = (String) respList.get(0);
        if (!"00".equals(resp_code)) {
            respList.clear();
            respList.add("01");
            respList.add(data);
            return respList;
        }
        Map<String, String> dataMap = (Map<String, String>) respList.get(1);

        // logger.info("验证MD5签名");
        respList = this.MD5Message(dataMap, MD5Key, sign);
        resp_code = (String) respList.get(0);
        if (!"00".equals(resp_code)) {
            respList.clear();
            respList.add("01");
            respList.add(data);
            return respList;
        }
        respList.clear();
        respList.add(resp_code);
        respList.add(dataMap);
        return respList;
    }

    /**
     * 解base64编码的串
     * @param dataStr 数据
     * @return
     */
    private List<String> base(String dataStr) {
        List<String> respList = new ArrayList<String>();

        String code;// 临时存储第一部分
        String data = "";// 功能代码
        String md5 = "";// 机构码

        try {
            if (dataStr == null || "".equals(dataStr)) {
                // logger.info("解析base64失败");
                respList.add("01");
                respList.add("");
                return respList;
            }

            // logger.info("切分请求字符串三部分");
            String[] st = dataStr.split("\\|");
            code = st[0];

            if ("1".equals(code)) {
                data = st[1];
                md5 = new String(net.iharder.Base64.decode(st[2]));
            }
            if ("0".equals(code)) {
                data = new String(net.iharder.Base64.decode(st[1]));
            }
            respList.add("00");
            respList.add(code);
            respList.add(data);
            respList.add(md5);
        } catch (Exception e) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        return respList;
    }

    /**
     * 解密3DES密文
     * @param DSEStr 3DES密文
     * @param DSEKey 3DES的key
     * @return
     */
    private List DESMessage(String DSEStr, String DSEKey) {
        List respList = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        if (DSEStr == null || DSEKey == null) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        String str = EncryDecryUtils.decryptFromBase64DES(DSEKey, DSEStr).trim();// 存储3DES解密的原文

        if (str == null || "".equals(str)) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        try {
            map = this.parseFormDataPatternReport(str, "utf-8");
        } catch (Exception e) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }

        respList.add("00");
        respList.add(map);
        return respList;
    }

    /**
     * 验证签名
     * @param map 数据
     * @param MD5Key MD5 key
     * @param sign 报文签名串
     * @return
     */
    private List<String> MD5Message(Map<String, String> map, String MD5Key, String sign) {
        List<String> respList = new ArrayList<String>();
        if (map == null) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        if (sign == null || "".equals(sign)) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }

        String toRSAStr = this.postFormLinkReport(map);

        try {
            String signNow = org.apache.commons.codec.digest.DigestUtils.md5Hex((toRSAStr + MD5Key).getBytes("UTF-8"));
            if (!sign.equalsIgnoreCase(signNow)) {
                respList.add("01");
                respList.add("报文解析失败");
                return respList;
            }
        } catch (Exception e) {
            respList.add("01");
            respList.add("报文解析失败");
            return respList;
        }
        respList.add("00");
        return respList;
    }
    /**
     * 表单类型报文解析
     * @param reportContent
     * @param charset
     * @return
     */
    public Map<String, String> parseFormDataPatternReport(String reportContent, String charset) throws Exception {
        String[] domainArray = reportContent.split("&");
        Map<String, String> key_value_map = new HashMap<String, String>();
        for (String domain : domainArray) {
            String[] kvArray = domain.split("=");
            if (kvArray.length == 2) {
                String decodeString = URLDecoder.decode(kvArray[1], charset);
                String lastInnerValue = new String(decodeString.getBytes(charset), "utf-8");
                if (lastInnerValue != null && !"".equals(lastInnerValue)) {
                    key_value_map.put(kvArray[0], lastInnerValue);
                }
            }
        }
        return key_value_map;
    }
    /**
     * 返回串
     * @param funcode 机构号
     * @param dataMap 返回信息
     * @return
     */
    public String packageMessage(String funcode, Map<String, String> dataMap) throws Exception {
        StringBuilder rtnMsg = new StringBuilder();
        rtnMsg.append("funcode=").append(funcode).append("&message=");
        StringBuilder dataBody = new StringBuilder();
        //3DES#key
        String DSEKey = Config.getInstance().getAttr(Config.KEY_DES);
        //MD5#key
        String MD5Key = Config.getInstance().getAttr(Config.KEY_MD5);
        String srt1 = this.toBase64("appId=".concat(Config.getInstance().getAttr(Config.KEY_APPID)));

        //参数数据
        String dataStr = this.postFormLinkReport(dataMap);

        String srt2 = EncryDecryUtils.encryptFromDESBase64(DSEKey.trim(), dataStr.trim());

        String srt3 = this.toMD5Message(dataStr, MD5Key);
        srt3 = this.toBase64(srt3);

        //报文组织
        dataBody.append(srt1);
        dataBody.append("|");
        dataBody.append(srt2);
        dataBody.append("|");
        dataBody.append(srt3);

//        System.out.println(message.toString());
        rtnMsg.append(URLEncoder.encode(dataBody.toString(), "UTF-8"));

//        System.out.println(return_mess.toString());
//        System.out.println(URLDecoder.decode(return_mess.toString(), "UTF-8"));
        return rtnMsg.toString();
    }

    /**
     * 将数据进行base64加密
     * @param dataStr 数据
     * @return
     */
    private String toBase64(String dataStr) {
        String rtnStr = org.apache.commons.codec.binary.Base64.encodeBase64String(dataStr.getBytes(Charset.forName("utf-8")));
        return rtnStr;
    }

    /**
     * 生成MD5签名
     * @param dataStr 数据
     * @param MD5Key MD5 key
     * @return
     */
    private String toMD5Message(String dataStr, String MD5Key) {
        if (dataStr == null || "".equals(dataStr)) {
            return "";
        }
        String signNow;
        try {
            System.out.println(dataStr + MD5Key);
            signNow = org.apache.commons.codec.digest.DigestUtils.md5Hex((dataStr + MD5Key).getBytes("UTF-8"));
        } catch (Exception e) {
            return "";
        }
        return signNow;
    }

    /**
     * 将数据映射表拼接成表单数据POST样式的字符串 key1=value1&key2=value2
     * @param dataMap
     * @return
     */
    private String postFormLinkReport(Map<String, String> dataMap) {
        StringBuilder reportBuilder = new StringBuilder();

        List<String> keyList = new ArrayList<String>(dataMap.keySet());
        Collections.sort(keyList);

        for (String key : keyList) {
            String value = dataMap.get(key);
            if (null == value || "".equals(value.trim())) {
                continue;
            }
            reportBuilder.append(key + "=" + dataMap.get(key) + "&");
        }

        reportBuilder.deleteCharAt(reportBuilder.lastIndexOf("&"));
        return reportBuilder.toString();
    }
}
