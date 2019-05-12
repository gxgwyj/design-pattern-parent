package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;
import com.xyz.designpattern.strategy.old.LikeMyStuff;

/**
 * 类: ItemAdvisor <br>
 * 描述: 未注册用户的推荐焰火产品<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:46
 */
public class ItemAdvisor implements Advisor{
    public FireWork recommend(Customer customer) {
        return (FireWork) LikeMyStuff.suggest(customer);
    }
}
