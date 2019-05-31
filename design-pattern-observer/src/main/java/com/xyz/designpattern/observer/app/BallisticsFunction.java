package com.xyz.designpattern.observer.app;

/**
 * 燃烧率和推力的计算公式（通用接口）
 */
public interface BallisticsFunction {

    double function(double t, double tPeak);
}
