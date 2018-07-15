package com.xyz.designpattern.factory;

import java.math.BigDecimal;

/**
 * Created by Lenovo on 2018/7/15.
 */
public class Phone extends Goods{
    @Override
    public BigDecimal getRealPrice() {
        return new BigDecimal("3000");
    }
}
