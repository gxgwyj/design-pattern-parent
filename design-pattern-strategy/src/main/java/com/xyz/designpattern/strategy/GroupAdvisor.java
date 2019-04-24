package com.xyz.designpattern.strategy;

/**
 * 类: GroupAdvisor <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:44
 */
public class GroupAdvisor implements Advisor {
    public FireWork recommend(Customer customer) {
        return (FireWork) Rel8.advise(customer);
    }
}
