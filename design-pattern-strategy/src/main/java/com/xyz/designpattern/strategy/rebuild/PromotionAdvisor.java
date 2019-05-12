package com.xyz.designpattern.strategy.rebuild;

import com.xyz.designpattern.strategy.old.Customer;
import com.xyz.designpattern.strategy.old.FireWork;

import java.io.IOException;
import java.util.Properties;

/**
 * 类: PromotionAdvisor <br>
 * 描述: 促销推荐的焰火产品<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年04月24日 16:47
 */
public class PromotionAdvisor implements Advisor {

    private FireWork promoted;

    /**
     * 构造方法
     */
    public PromotionAdvisor() {
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResourceAsStream("config/strategy.dat"));
            String promotedName = p.getProperty("promote");
            if (promotedName != null) {
                promoted = FireWork.lookup(promotedName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            promoted = null;
        }

    }

    public boolean hasItem() {
        return promoted != null;
    }

    public FireWork recommend(Customer customer) {
        return promoted;
    }
}
