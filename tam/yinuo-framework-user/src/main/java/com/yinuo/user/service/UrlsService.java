package com.yinuo.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inuol.user.Urls;
import com.yinuo.user.mapper.UrlsMapper;


@Service
@Transactional
public class UrlsService {

    @Autowired
    private UrlsMapper urlsMapper;

    public void addAuthUrl(Urls urls){
        urls.setInsertTime(new Date());
        int i = this.urlsMapper.insert(urls);
    }


}
