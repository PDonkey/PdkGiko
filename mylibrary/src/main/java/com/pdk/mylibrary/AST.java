package com.pdk.mylibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by uatql90533 on 2018/4/27.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface AST {
    /**
     * 类型 0 插入点  1 需要插入的原始代码块
     */
    int type();

    /**
     * 插桩的类型的id，支持多种需求的代码插桩
     */
    int value();

    /**
     * 类型为0时 0表示前插 1表示后
     * 类型为1时 level表示优先级 0在最前 插入时排序的优先级越低
     */
    int level();

    /**
     * 类型 0插入点 1需要插入的代码块
     */
    interface TYPE {
        int TARGET = 0;
        int SOURCE = 1;
    }

    /**
     * 插入类型的id
     */
    interface ID {
        int MODULE_INIT = 0x0001;//模块初始化的插桩
        int ROUTER_INIT = 0x0002;//路由注册的插桩
    }

    /**
     * 类型 为0时
     */
    interface LEVEL {
        int BEFORE = 0;
        int AFTER = 1;
    }
}

