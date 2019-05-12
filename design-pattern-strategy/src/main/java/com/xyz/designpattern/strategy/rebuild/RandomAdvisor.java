package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;

/**
 * 类: RandomAdvisor <br>
 * 描述: 随机推荐的焰火产品<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:48
 */
public class RandomAdvisor implements Advisor {
    public FireWork recommend(Customer customer) {
        return FireWork.getRandom();
    }
}
