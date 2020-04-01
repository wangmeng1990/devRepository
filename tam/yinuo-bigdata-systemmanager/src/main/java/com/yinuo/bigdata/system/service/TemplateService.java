package com.yinuo.bigdata.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.bigdata.TBigdataTemplate;
import com.yinuo.bigdata.system.mapper.TemplateMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ：jias
 * @date ：2020/1/15 13:42
 */
@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 模板添加
     * @param template
     * @return
     */
    public int addTemplate(TBigdataTemplate template){

      return templateMapper.insert(template);

    }

    /**
     * 模板修改
     * @param template
     * @return
     */
    public int updateTemplate(TBigdataTemplate template) {

        return templateMapper.updateByPrimaryKeySelective(template);

    }

    /**
     * 删除模板
     * @param id
     * @return
     */
    public int deleteTemplate(Long id){

        return templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询模板
     * @param template
     * @return
     */
    public Page<TBigdataTemplate> findAllTemplate(int page, int size, TBigdataTemplate template){
        PageHelper.startPage(page,size);
        Example example = new Example(TBigdataTemplate.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(template.getTemplateContent())){
            criteria.andLike("templateContent","%"+template.getTemplateContent()+"%");
        }
        if (StringUtils.isNotBlank(template.getClassifyId())){
            criteria.andEqualTo("classifyId",template.getClassifyId());
        }
        List<TBigdataTemplate> templateList = templateMapper.selectByExample(example);
        return (Page<TBigdataTemplate>) templateList;
    }

}
