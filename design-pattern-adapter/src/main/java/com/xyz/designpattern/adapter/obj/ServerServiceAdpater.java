package com.xyz.designpattern.adapter.obj;

/**
 * 对象适配器，通过继承客户端所期望的类，重写其中的方法，将实际的服务对象作为属性来调用其中的方法
 */
public class ServerServiceAdpater extends UserNeedService {

    /**
     * 将实际工作的类的实例作为适配器对象的属性，委派调用（是不是类似代理模式？其实应用场景不同罢了）
     */
    private ServerRealService service = new ServerRealService();

    @Override
    public void add() {
        service.userfulMethod();
    }
}
