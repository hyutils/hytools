package com.chaojilaji.hy.developutils;

import java.util.Random;

public class MathUtils {
    /**
     * 计算 log a为底n
     *
     * @param a
     * @param n
     * @return
     */
    public static Double getLogAN(Integer a, Integer n) {
        return Math.log10(n * 1.0) / Math.log10(a * 1.0);
    }

    /**
     * 获取随机整数
     * 这个方法的时间复杂度有点玄学
     * @param l
     * @param r
     * @return
     */
    public static Integer getRandomInt(Integer l, Integer r) {
        Integer ans = new Double(Math.random() * r).intValue();
        while (ans < l || ans >= r) {
            ans = new Double(Math.random() * r).intValue();
        }
        return ans;
    }

    /**
     * 获取正整数
     *
     * @param r
     * @return
     */
    public static Integer getPositiveRandomInt(Integer r) {
        return getRandomInt(0, r);
    }

    /**
     * 获取负整数
     * @param l
     * @return
     */
    public static Integer getNegativeRandomInt(Integer l) {
        return -1 * getRandomInt(0, l + 1);
    }

    /**
     * 随机数
     * @param len 定义随机数的位数
     */
    public static String randomGen(int len) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<len;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }

}
