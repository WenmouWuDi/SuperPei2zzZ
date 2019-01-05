package com.cssl.service.impl;

import com.cssl.dao.OptionsDao;
import com.cssl.service.OptionsService;
import com.cssl.vo.OptionsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Service
public class OptionsServiceImpl implements OptionsService {
    @Autowired
    private OptionsDao optionsDao;

    @Override
    public int XiuGaiOptions(OptionsVo optionsVo) {
        return optionsDao.XiuGaiOptions(optionsVo);
    }

    @Override
    public int delOptionsBySid(int id) {
        return optionsDao.delOptionsBySid(id);
    }

    @Override
    public int isExistOption(String content1) {
        return optionsDao.isExistOption(content1);
    }

    @Override
    public int deleteOptions(int id) {
        return optionsDao.deleteOptions(id);
    }

    @Override
    public int AddOptions(OptionsVo optionsVo) {
        return optionsDao.AddOptions(optionsVo);
    }
}
