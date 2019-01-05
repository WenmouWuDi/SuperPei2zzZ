package com.cssl.dao;

import com.cssl.vo.ItemsVo;
import org.springframework.stereotype.Repository;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Repository
public interface ItemDao {

    public int Tou1(ItemsVo itemsVo);

    public int delPiao(int id);

    public int delPiaoBySid(int id);

}
