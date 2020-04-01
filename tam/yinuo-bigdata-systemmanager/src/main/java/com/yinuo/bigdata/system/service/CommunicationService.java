package com.yinuo.bigdata.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.bigdata.TBigdataCommunication;
import com.inuol.bigdata.ext.TBigdataCommunicationNode;
import com.yinuo.bigdata.system.mapper.CommunicationMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 通讯录
 * @author ：jias
 * @date ：2020/1/16 14:30
 */
@Service
public class CommunicationService {

    @Autowired
    private CommunicationMapper communicationMapper;

    /**
     * 通讯录添加
     * @param communication
     * @return
     */
    public int addCommunicate(TBigdataCommunication communication){
        return communicationMapper.insertSelective(communication);
    }

    /**
     * 条件查询通讯录列表
     * @param communicationNode
     * @return
     */
    public Page<TBigdataCommunication> findAllCommunicate(TBigdataCommunicationNode communicationNode, int page, int size){
        PageHelper.startPage(page, size);
        Example example = new Example(TBigdataCommunication.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(communicationNode.getDepartmentId())){
            criteria.andEqualTo("c",communicationNode.getDepartmentId());
        }
        if (StringUtils.isNotBlank(communicationNode.getRoleId())){
            criteria.andEqualTo("roleId",communicationNode.getRoleId());
        }
        if (StringUtils.isNotBlank(communicationNode.getKeyWord())){
            Example.Criteria orCriteria = example.createCriteria();
            orCriteria.orLike("communicateName","%"+communicationNode.getKeyWord()+"%")
                      .orLike("phone","%"+communicationNode.getKeyWord()+"%");
            example.and(orCriteria);
        }
       return (Page<TBigdataCommunication>)communicationMapper.selectByExample(example);
    }

    /**
     * 导入数据添加
     * @param communicationList
     * @return
     */
    public int addCommunicateBatch(List<TBigdataCommunication> communicationList){
        return communicationMapper.addCommunicateBatch(communicationList);
    }

    /**
     * 根据姓名和电话查询
     * @return
     */
    public TBigdataCommunication getCommunicationByNameAndByPhone(TBigdataCommunication communication){
        TBigdataCommunication record = new TBigdataCommunication();
        record.setCommunicateName(communication.getCommunicateName());
        record.setPhone(communication.getPhone());
        return communicationMapper.selectOne(record);
    }

    /**
     * 查询通讯录部门和部门人数
     * @return
     */
    public List<TBigdataCommunicationNode> appSelectCommunicate(){
        return communicationMapper.appSelectCommunicate();
    }

    /**
     * 根据部门id查询对应人数详情
     * @param departmentId
     * @return
     */
    /*public List<TBigdataCommunication> selectCommunicateByDepartment(String departmentId){
        TBigdataCommunication record = new TBigdataCommunication();
        record.setDepartmentId(departmentId);
        return communicationMapper.select(record);
    }*/


}
