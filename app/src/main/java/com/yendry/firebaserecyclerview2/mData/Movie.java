package com.yendry.firebaserecyclerview2.mData;


/**
 * Created by User on 10/10/2016.
 */

public class Movie {

    private String name;
    private String url;
    private String key;



    public Movie(){
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
