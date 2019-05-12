package com.xyz.designpattern.strategy.rebuild;


import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;

import java.util.Calendar;

/**
 * 类: CustomerRebuild <br>
 * 描述: 重构过后的广告投放策略<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 15:46
 */
public class CustomerRebuild extends Customer {

    private Advisor advisor;

    private static PromotionAdvisor promotionAdvisor = new PromotionAdvisor();
    private static GroupAdvisor groupAdvisor = new GroupAdvisor();
    private static ItemAdvisor itemAdvisor = new ItemAdvisor();
    private static RandomAdvisor randomAdvisor = new RandomAdvisor();

    @Override
    public FireWork getRecommended() {
        return this.getAdvisor().recommend(this);
    }


    // 路由广告策略
    private Advisor getAdvisor() {
        if (advisor == null) {
            // 是否有促销
            if (promotionAdvisor.hasItem()) {
                advisor = promotionAdvisor;
            // 是否是注册用户
            } else if (isRegistered()) {
                advisor = groupAdvisor;
            } else if (isBigSpender()) {
                advisor = itemAdvisor;
            } else {
                advisor = randomAdvisor;
            }
        }
        return advisor;
    }


    /**
     * 用户的消费金额条件
     * @return
     */
    private boolean isBigSpender(){
        // 判断用户上一年的花费
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return spendingSince(cal.getTime()) > 1000;
    }

}
