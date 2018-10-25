package com.xnhb.et.util;

import java.text.DecimalFormat;

/**
 * 作者 oneway on 2018/10/11
 * 描述:
 * 参考链接:
 */
public class MoneyUtils {
    /**
     * 截取小数点后4位
     */
    public static String scaleMoney4(String money) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.0000");
            Double aDouble = Double.valueOf(money);
            if (aDouble == 0) {
                return "0.0000";
            }
            return decimalFormat.format(aDouble);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "0.0000";
        }
    }

    /**
     * 去掉小数点后面的多余的0 這里如果是0 则会显示0.0
     */
//    public static String getPrettyNumber(String number) {
//        return BigDecimal.valueOf(Double.parseDouble(number))
//                .stripTrailingZeros().toPlainString();
//    }

    /**
     * 使用java正则表达式去掉多余的.与0   這里如果是0 则会显示0
     */
    public static String getPrettyNumber(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     *
     */
    public static String check0(String s) {
        if ("0E-8".equals(s)) {
            return "0";
        }
        return s;
    }

    public String foramt(double d) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(d);
    }
}
