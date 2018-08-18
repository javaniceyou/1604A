package com.example.xml_parser.bean;

import java.util.List;

public class apps {


    private AppsBean apps;

    public AppsBean getApps() {
        return apps;
    }

    public void setApps(AppsBean apps) {
        this.apps = apps;
    }

    public static class AppsBean {
        /**
         * name : google map
         * id : 1
         * version : 1
         */

        private List<AppBean> app;

        public List<AppBean> getApp() {
            return app;
        }

        public void setApp(List<AppBean> app) {
            this.app = app;
        }

        public static class AppBean {
            private String name;
            private int id;
            private int version;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }
    }
}
