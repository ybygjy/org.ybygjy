package org.ybygjy.pay.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 发号器
 * <p>1.随机</p>
 * <p>2.有序，不支持</p>
 * @author WangYanCheng
 * @version 2016年9月9日
 */
public class SequenceNumber implements Serializable {
    /**serial number*/
    private static final long serialVersionUID = -6182862128417621364L;
    /** 静态内部类*/
    private static final class InnerHolder {
        private static final SequenceNumber sequenceNumber = new SequenceNumber();
    }
    private SequenceNumber() {
    }
    /**
     * 获取实例
     * @return sequenceNumber {@link SequenceNumber}
     */
    public static final SequenceNumber getInstance() {
        return InnerHolder.sequenceNumber;
    }
    /**
     * Classes that need to designate a replacement when an instance of it is read from the stream should implement this special method with the exact signature.
     * @return rtnObj {@link SequenceNumber}
     */
    private Object readResolve() {
        return InnerHolder.sequenceNumber;
    }
    /**
     * 特定业务用，总长30（17位时间+13位随机）
     * @return rtnNum
     */
    public String generateTransactionId() {
        StringBuilder sbud = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        sbud.append(dateFormat.format(new Date()));
        sbud.append(this.generateSeq(13));
        return sbud.toString();
    }
    /**
     * 生成给定长度的随机数字符串
     * <p>-Random#nextInt(n)取值区间[0,n)</p>
     * <p>-生成[a,b]区间内的随机数，Random#nextInt(b-a+1)+a</p>
     * @param len 随机字符串长度
     * @return rtnStr
     */
    private String generateSeq(int len) {
        StringBuilder sbud = new StringBuilder();
        int groupSeed = 8;
        int factor = (int) Math.pow(10, (groupSeed - 1));
        int seed = factor * 9;
        int group = len / groupSeed + (len % groupSeed > 0 ? 1 : 0);
        Random random = new Random();
        for (int i = 0; i < group; i++) {
            int val = random.nextInt(seed);
            if (val < factor) {
                val = val + factor;
            }
            sbud.append(val);
        }
        int tmpLen = sbud.length() - len;
        if (tmpLen > 0) {
            sbud.setLength(len);
        } else if(tmpLen < 0) {
            for(int i = 0; i < tmpLen; i++) {
                sbud.append("0");
            }
        }
        return sbud.toString();
    }
}
