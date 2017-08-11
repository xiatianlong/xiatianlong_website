package com.xiatianlong.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Dictionary Entity
 * Created by xiatianlong on 2017/1/15.
 */
@Entity(name = "xtl_dictionary")
@SuppressWarnings("serial")
public class XtlDictionaryEntity implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * dicCode
     */
    private String dicCode;
    /**
     * dicName
     */
    private String dicName;
    /**
     * parentCode
     */
    private String parentCode;
    /**
     * sequence
     */
    private int sequence;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "dic_code", unique = true, nullable = false, length = 45)
    public String getDicCode() {
        return this.dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    @Column(name = "dic_name", nullable = false, length = 45)
    public String getDicName() {
        return this.dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    @Column(name = "parent_code", nullable = true, length = 45)
    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name = "sequence", nullable = false, length = 10)
    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
