package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;

/**
 * 类: Advisor <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:42
 */
public interface Advisor {
    public FireWork recommend(Customer customer);
}
