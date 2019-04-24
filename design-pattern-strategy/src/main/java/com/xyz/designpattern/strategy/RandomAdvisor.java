package com.xyz.designpattern.strategy;

/**
 * 类: RandomAdvisor <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:48
 */
public class RandomAdvisor implements Advisor {
    public FireWork recommend(Customer customer) {
        return FireWork.getRandom();
    }
}
