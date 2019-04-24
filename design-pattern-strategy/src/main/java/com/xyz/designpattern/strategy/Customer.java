package com.xyz.designpattern.strategy;

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
            e.printStackTrace();
        }

        // 判断用户是否注册
        if (isRegistered()) {
            return (FireWork)Rel8.advise(this);
        }

        // 判断用户上一年的花费
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        if (spendingSince(cal.getTime()) > 1000) {
            return (FireWork) LikeMyStuff.suggest(this);
        }

        // 返回默认(随机)
        return FireWork.getRandom();
    }

    private long spendingSince(Date time) {
        return 0;
    }

    private boolean isRegistered() {
        return true;
    }
}
