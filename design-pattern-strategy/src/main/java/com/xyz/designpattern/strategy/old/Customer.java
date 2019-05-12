package com.xyz.designpattern.strategy.old;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * 类: Customer <br>
 * 描述: 广告投放策略<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 15:46
 */
public class Customer {
    /**
     * getRecommended 方法问题很多
     * 1、方法太长
     * 2、方法同时完成了选择策略和执行策略两件，这是两件不同的事情，属于独立功能
     */

    /**
     * 广告策略
     * @return
     */
    public FireWork getRecommended() {
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResourceAsStream("config/strategy.dat"));
            String promotedName = p.getProperty("promote");
            if (promotedName != null) {
                FireWork f = FireWork.lookup(promotedName);
                if (f != null) {
                    return f;
                }
            }
        } catch (IOException e) {
            // 此处异常并不阻断程序的流程，继续执行后面的逻辑
            e.fillInStackTrace();
        }

        // 注册的用户使用Rel8广告引擎
        if (isRegistered()) {
            return (FireWork)Rel8.advise(this);
        }

        // 判断用户上一年的花费
        Calendar cal = Calendar.getInstance();
        // 向前推迟一年
        cal.add(Calendar.YEAR, -1);
        if (spendingSince(cal.getTime()) > 1000) {
            // 使用LikeMyStuff 广告引擎
            return (FireWork) LikeMyStuff.suggest(this);
        }

        // 如果不符合上述条件，随机获取一个烟花产品
        return FireWork.getRandom();
    }

    /**
     * 计算用户以往的消费情况
     * @param time
     * @return
     */
    protected long spendingSince(Date time) {
        return 0;
    }

    /**
     * 判断用户是否注册
     * @return
     */
    protected boolean isRegistered() {
        return true;
    }
}
