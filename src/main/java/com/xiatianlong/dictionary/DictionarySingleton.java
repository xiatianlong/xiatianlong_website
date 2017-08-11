package com.xiatianlong.dictionary;

import com.xiatianlong.entity.XtlDictionaryEntity;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dictionary singleton
 * Created by xiatianlong on 2017/1/16.
 */
public class DictionarySingleton {

    private List<XtlDictionaryEntity> dictionaryList;

    /**
     * 指向自己实例的私有静态引用
     */
    public static DictionarySingleton dictionarySingleton = null;

    /**
     * 私有无参构造
     */
    private DictionarySingleton(){}

    /**
     * 以自己实例为返回值的静态的公有的方法
     * @return DictionarySingleton
     */
    public static DictionarySingleton getInstance(){
        if (dictionarySingleton == null){
            dictionarySingleton = new DictionarySingleton();
        }
        return dictionarySingleton;
    }

    /**
     * 设置字典数据(工程启动通过监听器来设值初始化)
     * @param dictionaryList    字典集合
     */
    public void setDictionaryList(List<XtlDictionaryEntity> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    /**
     * 获取字典的dic_name
     * @param code  dic_code
     * @return  dic_name
     */
    public String getName(String code){
        if (dictionaryList !=null && !dictionaryList.isEmpty()){
            for (XtlDictionaryEntity dictionary : dictionaryList){
                if (!StringUtils.isEmpty(code) && code.equals(dictionary.getDicCode())){
                    return dictionary.getDicName();
                }
            }
        }
        return null;
    }

    /**
     * 获取字典的parent_code
     * @param code  dic_code
     * @return  parent_code
     */
    public String getParentCode(String code){
        if (dictionaryList !=null && !dictionaryList.isEmpty()){
            for (XtlDictionaryEntity dictionary : dictionaryList){
                if (!StringUtils.isEmpty(code) && code.equals(dictionary.getDicCode())){
                    return StringUtils.isEmpty(dictionary.getParentCode()) ? dictionary.getDicCode() : dictionary.getParentCode();
                }
            }
        }
        return null;
    }

    /**
     * 获取父节点下面的全部子节点
     * @param parentCode    父节点dic_code
     * @return  子节点集合
     */
    public List<XtlDictionaryEntity> getDicList(String parentCode){
        if (dictionaryList !=null && !dictionaryList.isEmpty()){
            List<XtlDictionaryEntity> dicList = new ArrayList<>();
            for (XtlDictionaryEntity dictionary : dictionaryList){
                if (!StringUtils.isEmpty(parentCode) && parentCode.equals(dictionary.getParentCode())){
                    dicList.add(dictionary);
                }
            }
            return dicList;
        }
        return null;

    }

}
