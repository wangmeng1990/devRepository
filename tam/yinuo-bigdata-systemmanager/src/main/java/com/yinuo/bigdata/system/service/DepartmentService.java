package com.yinuo.bigdata.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.bigdata.TBigdataDepartment;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.api.user.UserApi;
import com.yinuo.bigdata.system.mapper.DepartmentMapper;

import com.yinuo.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 部门service
 * @author ：jias
 * @date ：2020/1/7 16:05
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserApi userApi;


    public int add(TBigdataDepartment department){
        department.setCreateTime(new Date());
        return departmentMapper.insert(department);
    }


    public int updata(TBigdataDepartment department){
        department.setUpdateTime(new Date());
        return departmentMapper.updateByPrimaryKey(department);
    }


    public int delete(Long id){
        return departmentMapper.deleteByPrimaryKey(id);
    }

    public Page<TBigdataDepartment> departmentfindAll(int page, int size){
        PageHelper.startPage(page, size);
        List<TBigdataDepartment> selectAll = departmentMapper.selectAll();
        if (selectAll.size()>0){
            for (int i = 0; i < selectAll.size(); i++) {
                if (selectAll.get(i).getId()<10){
                    selectAll.get(i).setDepartmentCode("0"+selectAll.get(i).getId());
                } else {
                    selectAll.get(i).setDepartmentCode("" + selectAll.get(i).getId());
                }
            }
            return (Page<TBigdataDepartment>)selectAll;
        }
        return null;
    }


    public Page<User> findUser(int page, int size, CriteriaUser criteriaUser){
        criteriaUser.setUserType(0);
        return userApi.findUser(page, size, criteriaUser);
    }

    public HttpResult addManageUser(User user){
         user.setUserType(0);
         user.setState(1);
         return userApi.register(user);
    }

    /**
     * 管理用户禁用启用
     * @param id
     * @param state
     * @return
     */
    public int updateManageState(Long id,Integer state){
        return userApi.update(id, state);
    }

    /**
     * 重置管理员密码
     * @param id
     * @return
     */
    public int resetManagePassword(Long id){
        return userApi.resetPassword(id);
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    public int deleteManage(Long id){
        return userApi.delete(id);
    }

    /**
     * 查询所有部门
     * @return
     */
    public List<TBigdataDepartment> findAll(){
        return departmentMapper.selectAll();
    }


    /**
     * 根据部门名称查询
     * @return
     */
    public TBigdataDepartment getDepartmentByName(String name){
        TBigdataDepartment record = new TBigdataDepartment();
        record.setDepartmentName(name);
        return departmentMapper.selectOne(record);
    }


}
