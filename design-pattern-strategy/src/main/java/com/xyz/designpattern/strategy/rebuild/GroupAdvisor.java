package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;
import com.xyz.designpattern.strategy.old.Rel8;

/**
 * 类: GroupAdvisor <br>
 * 描述: 注册用户推荐的烟火产品<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:44
 */
public class GroupAdvisor implements Advisor {
    public FireWork recommend(Customer customer) {
        return (FireWork) Rel8.advise(customer);
    }
}
