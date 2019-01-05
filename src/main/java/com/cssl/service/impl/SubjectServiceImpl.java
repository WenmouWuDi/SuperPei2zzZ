package com.cssl.service.impl;

import com.cssl.dao.SubjectDao;
import com.cssl.service.SubjectService;
import com.cssl.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public int chaRu(SubjectVo subjectVo) {
        return subjectDao.chaRu(subjectVo);
    }

    @Override
    public int delSubjectBySid(int id) {
        return subjectDao.delSubjectBySid(id);
    }

    @Override
    public int XiuGai(SubjectVo subjectVo) {
        return subjectDao.XiuGai(subjectVo);
    }

    @Override
    public Map<String, Object> showTitle(int id) {
        return subjectDao.showTitle(id);
    }

    @Override
    public List<Map<String,Object>> showOne(int id){
        return subjectDao.showOne(id);
    }

    @Override
    public List<Map<String, Object>> showAll(String title) {
        return subjectDao.showAll(title);
    }
}
