package cn.lframe.user.util;

import java.util.Random;

/**
 * @author Lframe
 * @create2018 -05 -03 -9:29
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }


}
