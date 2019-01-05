package com.cssl.service;

import com.cssl.vo.ItemsVo;

/**
 * Created by 我的电脑 on 2018/12/15.
 */

public interface ItemsService {

    public int Tou1(ItemsVo itemsVo);

    public int delPiao(int id);

    public int delPiaoBySid(int id);

}
