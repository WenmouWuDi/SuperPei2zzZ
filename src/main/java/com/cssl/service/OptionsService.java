package com.cssl.service;

import com.cssl.vo.OptionsVo;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
public interface OptionsService {

    public int XiuGaiOptions(OptionsVo optionsVo);

    public int AddOptions(OptionsVo optionsVo);

    public int deleteOptions(int id);

    public int delOptionsBySid(int id);

    public int isExistOption(String content1);
}
