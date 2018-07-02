package com.pdk.pdkgiko.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class GroupAppBean implements Serializable {
    private List<AppBean> appBeanList;

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }

    public void setAppBeanList(List<AppBean> appBeanList) {
        this.appBeanList = appBeanList;
    }

    public static class AppBean {
        public AppBean(boolean isInclude, String appName) {
            this.isInclude = isInclude;
            this.appName = appName;
        }

        private boolean isInclude;
        private String appName;

        public boolean isInclude() {
            return isInclude;
        }

        public void setInclude(boolean include) {
            isInclude = include;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }
    }
}
