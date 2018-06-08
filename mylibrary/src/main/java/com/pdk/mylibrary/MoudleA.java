package com.pdk.mylibrary;

/**
 * Created by uatql90533 on 2018/4/27.
 */
public class MoudleA {
    @AST(type = AST.TYPE.SOURCE, value = AST.ID.MODULE_INIT, level = 1)
    public static void autoInitMoudle() {
        (new com.pdk.mylibrary.AModule()).afterConnected();
    }

}
