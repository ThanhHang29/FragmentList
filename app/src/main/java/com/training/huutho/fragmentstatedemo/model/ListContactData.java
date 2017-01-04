package com.training.huutho.fragmentstatedemo.model;

import java.io.Serializable;

/**
 * Created by HuuTho on 1/4/2017.
 */

public class ListContactData implements Serializable {
    private String name;
    private String phone;
    private String id;
    private boolean isCheck = false;

    public ListContactData() {
    }
    // lần sau nên dùng cái Parcelable vì nó nhanh và không tốn tài nguyên hơn
    public ListContactData(String name, String phone, String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
