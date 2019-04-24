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
public class CustomerNew {


    private Advisor advisor;


    public FireWork getRecomended() {
        return getAdvisor().recommend(this);
    }

    private long spendingSince(Date time) {
        return 0;
    }

    private boolean isRegistered() {
        return true;
    }


    private Advisor getAdvisor() {
        return null;
    }
}
