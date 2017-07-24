package com.mehome.service.impl;

import com.mehome.dao.ForumTopicDao;
import com.mehome.domain.ForumTopic;
import com.mehome.requestDTO.TopicBean;
import com.mehome.service.iface.ITopicService;
import com.mehome.utils.CollectionsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("ITopicService")
public class TopicServiceImpl implements ITopicService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ForumTopicDao forumTopicDao;

    @Override
    public List<TopicBean> getListByCondition(TopicBean bean) {
        if ("index".equals(bean.getDisplayLocation())) {
            List<ForumTopic> specialTopics = forumTopicDao.getAllActivities(bean);
            List<TopicBean> beanList = new ArrayList<TopicBean>();
            List<ForumTopic> resultList = CollectionsUtils.page(bean.getSourcePageNow(), bean.getSourcePageSize(), specialTopics);
            if (null != resultList && resultList.size() > 0) {
                for (ForumTopic forumTopic : resultList) {
                    TopicBean newBean = new TopicBean(forumTopic);
                    beanList.add(newBean);
                }
            }
            return beanList;
        } else {
            List<ForumTopic> specialTopics = forumTopicDao.getAllActivities(bean);
            List<ForumTopic> normalTopics = forumTopicDao.getAllNormal(bean);
            List<ForumTopic> TempTopics = new ArrayList<ForumTopic>();
            Iterator<ForumTopic> specialIterator = specialTopics.iterator();
            Iterator<ForumTopic> normalIterator = normalTopics.iterator();
            int i = 1;
            while (specialIterator.hasNext() || normalIterator.hasNext()) {
                i++;
                if (i % 4 == 0) {
                    if (specialIterator.hasNext()) {
                        TempTopics.add(specialIterator.next());
                    }
                } else {
                    if (normalIterator.hasNext()) {
                        TempTopics.add(normalIterator.next());
                    }
                }
            }
//        3条一般资讯＋1条活动／专题＋（6条一般资讯＋1条活动／专题）n…（以iphone6一屏估算），按发布时间倒序
            List<TopicBean> beanList = new ArrayList<TopicBean>();
            List<ForumTopic> resultList = CollectionsUtils.page(bean.getSourcePageNow(), bean.getSourcePageSize(), TempTopics);
            if (null != resultList && resultList.size() > 0) {
                for (ForumTopic forumTopic : resultList) {
                    TopicBean newBean = new TopicBean(forumTopic);
                    beanList.add(newBean);
                }
            }
            return beanList;
        }
    }

    @Override
    public Long getSizeByCondition(TopicBean bean) {
        // TODO Auto-generated method stub
        return forumTopicDao.getSizeByCondition(bean);
    }

    @Override
    public String addTopic(TopicBean bean) {
        // TODO Auto-generated method stub
        ForumTopic topic = bean.beanToPojo();
        int row = forumTopicDao.insert(topic);
        if (row > 0) {
            return Boolean.TRUE.toString();
        } else {
            return Boolean.FALSE.toString();
        }
    }

    @Override
    public String updateTopic(TopicBean bean) {
        // TODO Auto-generated method stub
        String tid = bean.getTid();
        if (StringUtils.isBlank(tid)) {
            log.error("tid属性为空");
            return Boolean.FALSE.toString();
        }
        TopicBean newbean = new TopicBean();
        ForumTopic topic = forumTopicDao.selectById(tid);
        ForumTopic updateTopic = newbean.compareToPojo(topic, bean);
        int row = forumTopicDao.updateByPrimaryKeyWithBLOBs(updateTopic);
        if (row > 0) {
            return Boolean.TRUE.toString();
        } else {
            return Boolean.FALSE.toString();
        }
    }

}
