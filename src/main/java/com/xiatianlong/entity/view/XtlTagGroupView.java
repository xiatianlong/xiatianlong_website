package com.xiatianlong.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TagGroup View
 * Created by xiatianlong on 2017/6/03.
 */
@Entity(name = "xtl_v_tag_group")
public class XtlTagGroupView {

    /**
     * tag name
     */
    private String tag;

    /**
     * count
     */
    private int count;


    /**
     * 获取 tag name
     */
    @Id
    @Column(name = "tag")
    public String getTag() {
        return this.tag;
    }

    /**
     * 设置 tag name
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取 count
     */
    @Column(name = "count")
    public int getCount() {
        return this.count;
    }

    /**
     * 设置 count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
