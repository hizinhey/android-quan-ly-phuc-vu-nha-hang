package com.example.quanlyphucvunhahang.models.modelAdapter;

public class MonAnItemAdapter {
    private String name;
    private String linkImage;

    public MonAnItemAdapter(String name, String linkImage) {
        this.name = name;
        this.linkImage = linkImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
