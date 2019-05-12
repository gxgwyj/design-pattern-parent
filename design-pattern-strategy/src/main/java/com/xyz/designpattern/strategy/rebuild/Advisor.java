package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;

/**
 * 类: Advisor <br>
 * 描述: 重构后的策略接口<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:42
 */
public interface Advisor {
    /**
     * 接受顾客信息，返回烟火产品
     * @param customer
     * @return
     */
    public FireWork recommend(Customer customer);
}
