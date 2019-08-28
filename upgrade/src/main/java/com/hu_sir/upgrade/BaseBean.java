package com.hu_sir.upgrade;

public class BaseBean {

    /**
     * msg : 查询成功!
     * success : true
     * back : {"id":34,"name":"市场信息收集","version":"1.1.03","dis":"这是测试的:\n1:正式版本与测试版本的区别\n2:显示啥啊\n3:有一次测试\n4:第二次测试","url":"1.1.03_3_20190826080855.apk","code":"3","pack":"com.yj.yjui.mig","consts":0,"relase":0,"dr":0}
     */

    private String msg;
    private boolean success;
    private BackBean back;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BackBean getBack() {
        return back;
    }

    public void setBack(BackBean back) {
        this.back = back;
    }

    public static class BackBean {
        /**
         * id : 34
         * name : 市场信息收集
         * version : 1.1.03
         * dis : 这是测试的:
         1:正式版本与测试版本的区别
         2:显示啥啊
         3:有一次测试
         4:第二次测试
         * url : 1.1.03_3_20190826080855.apk
         * code : 3
         * pack : com.yj.yjui.mig
         * consts : 0  0为强制 1 不强制
         * relase : 0
         * dr : 0
         */

        private int id;
        private String name;
        private String version;
        private String dis;
        private String url;
        private String code;
        private String pack;
        private int consts;
        private int relase;
        private int dr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDis() {
            return dis;
        }

        public void setDis(String dis) {
            this.dis = dis;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPack() {
            return pack;
        }

        public void setPack(String pack) {
            this.pack = pack;
        }

        public int getConsts() {
            return consts;
        }

        public void setConsts(int consts) {
            this.consts = consts;
        }

        public int getRelase() {
            return relase;
        }

        public void setRelase(int relase) {
            this.relase = relase;
        }

        public int getDr() {
            return dr;
        }

        public void setDr(int dr) {
            this.dr = dr;
        }
    }
}
