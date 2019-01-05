package com.cssl.service;

import com.cssl.vo.SubjectVo;

import java.util.List;
import java.util.Map;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
public interface SubjectService {

    public List<Map<String,Object>> showAll(String title);

    public List<Map<String,Object>> showOne(int id);

    public Map<String,Object> showTitle(int id);

    public int XiuGai(SubjectVo subjectVo);

    public int chaRu(SubjectVo subjectVo);

    public int delSubjectBySid(int id);

}
