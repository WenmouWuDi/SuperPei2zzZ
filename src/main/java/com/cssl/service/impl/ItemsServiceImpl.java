package com.cssl.service.impl;

import com.cssl.dao.ItemDao;
import com.cssl.service.ItemsService;
import com.cssl.vo.ItemsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Transactional
@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemDao itemDao;
    @Override
    public int Tou1(ItemsVo itemsVo) {
        return itemDao.Tou1(itemsVo);
    }

    @Override
    public int delPiaoBySid(int id) {
        return itemDao.delPiaoBySid(id);
    }

    @Override
    public int delPiao(int id) {
        return itemDao.delPiao(id);
    }
}
