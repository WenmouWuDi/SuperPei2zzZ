package com.cssl.dao;

import com.cssl.vo.OptionsVo;
import org.springframework.stereotype.Repository;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Repository
public interface OptionsDao {

    public int XiuGaiOptions(OptionsVo optionsVo);

    public int AddOptions(OptionsVo optionsVo);

    public int deleteOptions(int id);

    public int delOptionsBySid(int id);

    public int isExistOption(String content1);

}
