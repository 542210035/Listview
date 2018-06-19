package com.example.sfhan.adapterdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sfhan on 2018/6/13.
 */

public class infoData
{
    public boolean isAa() {
        return aa;
    }

    public void setAa(boolean aa) {
        this.aa = aa;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private boolean aa;
    private String title;
    private List<String> list=new ArrayList<>();

    public infoData(boolean aa, String title, List<String> list){
        this.aa=aa;
        this.title=title;
        this.list=list;

    }




}
