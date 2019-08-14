package com.pro.switchlibrary;

public class JsonEntity {


    /**
     * status : 0
     * url : https://ab.76bao.hk
     */

    private String status;
    private String url;

    @Override
    public String toString() {
        return "JsonEntity{" +
                "status=" + status +
                ", url='" + url + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
