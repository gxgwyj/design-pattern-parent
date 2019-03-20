package com.xyz.designpattern.adapter.clazz;

/**
 * 类: BizServiceProcessAdpater <br>
 * 描述: 适配器类,该类继承了拥有可用代码的旧类，实现了提供给客户端调用的接口，在实现客户端接口的方法时
 * 将具体的行为，适配到原来的旧类功能中，使用的是适配器模式<br>
 * 作者:  Administrator<br>
 * 时间: 2018年08月26日 15:13
 */
public class BizServiceProcessAdpater extends BizServiceProcess implements BizService {
    @Override
    public void bizMethod() {
        super.usefulMethod();
    }
}
