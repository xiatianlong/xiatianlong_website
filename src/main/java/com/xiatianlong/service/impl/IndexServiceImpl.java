package com.xiatianlong.service.impl;

import com.xiatianlong.common.Common;
import com.xiatianlong.entity.XtlNoteTagEntity;
import com.xiatianlong.entity.view.XtlArticleNoteShowUnionAllView;
import com.xiatianlong.model.IndexArticleNoteModel;
import com.xiatianlong.service.IndexService;
import com.xiatianlong.utils.DateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Index Service implements
 * Created by xiatianlong on 2017/5/21.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IndexServiceImpl extends BaseServiceImpl implements IndexService {

    /**
     * 获取文章和笔记(首页大列表)
     * @return  model集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<IndexArticleNoteModel> getArticleAndNote() {

        Criteria criteria = getSession().createCriteria(XtlArticleNoteShowUnionAllView.class);
        criteria.setMaxResults(Common.INDEX_LIST_SIZE);
        criteria.addOrder(Order.desc("recommend")).addOrder(Order.desc("browsTimes")).addOrder(Order.desc("createTime"));
        List<XtlArticleNoteShowUnionAllView> list =  criteria.list();

        return getIndexModelList(list);

    }


    /**
     * 封装index model数据
     * @param indexList indexlist
     * @return  model list
     */
    @SuppressWarnings("unchecked")
    private List<IndexArticleNoteModel> getIndexModelList(List<XtlArticleNoteShowUnionAllView> indexList){

        List<IndexArticleNoteModel> modelList = new ArrayList<>();

        List<Integer> noteIds = new ArrayList<>();

        if (indexList != null && !indexList.isEmpty()){
            for(XtlArticleNoteShowUnionAllView index : indexList){
                IndexArticleNoteModel model = new IndexArticleNoteModel();
                if ("note".equals(index.getType())){
                    noteIds.add(index.getId());
                }
                model.setType(index.getType());
                model.setId(index.getId());
                model.setTitle(index.getTitle());
                model.setImage(index.getImage()+Common.IMG_STYLE);
                model.setIntroduction(index.getIntroduction());
                model.setUserId(index.getUserId());
                model.setUserName(index.getUser().getUserName());
                model.setEmail(index.getUser().getEmail());
                model.setBrowswTimes(index.getBrowsTimes());
                model.setRecommend(index.isRecommend());
                model.setCreateTime(DateUtil.getFormatString(index.getCreateTime(), DateUtil.defaultDatePattern));
                modelList.add(model);
            }
        }

        // 获取note的tag
        List<XtlNoteTagEntity> tagEntityList = new ArrayList<>();

        if(!noteIds.isEmpty()){
            Criteria criteria = getSession().createCriteria(XtlNoteTagEntity.class);
            criteria.add(Restrictions.in("noteId", noteIds));
            tagEntityList = criteria.list();
        }

        // 给笔记添加tag
        if(!modelList.isEmpty() && !tagEntityList.isEmpty()){
            for(IndexArticleNoteModel indexModel : modelList){
                if("note".equals(indexModel.getType())){
                    List<String> tags = new ArrayList<>();
                    for (XtlNoteTagEntity tagEntity : tagEntityList){
                        // 用equals()比较避免IntegerCache的大坑
                        if (tagEntity.getNoteId().equals(indexModel.getId())){
                            tags.add(tagEntity.getContent());
                        }
                    }
                    indexModel.setTags(tags);
                }
            }
        }
        return modelList;
    }
}
