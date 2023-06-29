package com.xyz.designpattern.common.dependency;

public class BClass {

    // 依赖关系情况1:成员变量
    private AClass aClass;


    // 依赖关系情况2:方法参数
    public void doWork(AClass aClass){

    }

    public void doWork(){
        // 依赖关系情况3: 方法内的局部变量
        AClass aClass;
    }

}
