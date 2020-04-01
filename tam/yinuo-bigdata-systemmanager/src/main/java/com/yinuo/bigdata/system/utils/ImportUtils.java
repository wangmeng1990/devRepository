package com.yinuo.bigdata.system.utils;

import com.inuol.bigdata.TBigdataCommunication;
import com.inuol.bigdata.TBigdataDepartment;
import com.inuol.user.Roles;
import com.yinuo.api.user.UserApi;
import com.yinuo.bigdata.system.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *批量导入工具类
 * @author ：jias
 * @date ：2020/1/19 18:15
 */
@Component
public class ImportUtils {

    @Autowired
    private  DepartmentService departmentService;

    private  List<TBigdataDepartment> departmentList; // 部门集合

    private  List<Roles> rolesList;

    @Autowired
    private  RedisTemplate redisTemplate;

    @Autowired
    private  UserApi userApi;


    /**
     * 初始化数据
     */
    @PostConstruct
    public void init(){
       this.departmentList = departmentService.findAll();
       this.rolesList = (List<Roles>)redisTemplate.boundHashOps("rolesList").get("rolesList");
    }

    /**
     * 通讯录批量导入准备
     * @param file
     * @return
     */
    public Map<String,Object> communicateBatchImportPrepare(MultipartFile file){
        String fileName = file.getOriginalFilename();
        Map<String,Object> map = new HashMap<>();
        List<TBigdataCommunication> communicationList = new ArrayList<TBigdataCommunication>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            //result.put("msg","上传文件格式不正确");
            map.put("msg","上传文件格式不正确");
            return map;
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        //InputStream is;
        Workbook wb = null;
        try {
            InputStream is = file.getInputStream();
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","数据异常！！！");
            return map;
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet.getLastRowNum()< 1){
             map.put("msg","数据为空！！！");
             return map;
        }
        TBigdataCommunication communication;
        List<String> errorList = new LinkedList<>();
        System.out.println("================================总行数为："+sheet.getLastRowNum());
        int trueCount = 0;
        int errorCount = 0;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            boolean tag = true;
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            String communicateName = row.getCell(0).getStringCellValue();
            if(StringUtils.isBlank(communicateName)){
                //result.put("msg","导入失败(第"+(r+1)+"行,字段名未填写)");
                if (tag){
                    tag = false;
                    errorCount++;
                }
                errorList.add("第"+(r+1)+"行：【姓名】该字段不能为空");
            }
            row.getCell(1).setCellType(CellType.STRING);
            String phone = row.getCell(1).getStringCellValue();
            if(StringUtils.isBlank(phone)){
                if (tag){
                    tag = false;
                    errorCount++;
                }
                errorList.add("第"+(r+1)+"行：【手机号码】该字段不能为空");
            }
            row.getCell(2).setCellType(CellType.STRING);
            String officeMobile = row.getCell(2).getStringCellValue();
            String departmentId = row.getCell(3).getStringCellValue();
            if(StringUtils.isBlank(departmentId)){
                if (tag){
                    tag = false;
                    errorCount++;
                }
                errorList.add("第"+(r+1)+"行：【所属部门】该字段不能为空");
            }
            String roleId = row.getCell(4).getStringCellValue();
            if(StringUtils.isBlank(roleId)){
                if (tag){
                    tag = false;
                    errorCount++;
                }
                errorList.add("第"+(r+1)+"行：【所属职位】该字段不能为空");
            }
            String sex = row.getCell(5).getStringCellValue();
            row.getCell(6).setCellType(CellType.STRING);
            String birthday = row.getCell(6).getStringCellValue();
            String jiguan = row.getCell(7).getStringCellValue();
            if (tag){
                communication = new TBigdataCommunication();
                if (StringUtils.isNotBlank(communicateName)){
                    communication.setCommunicateName(communicateName);
                }
                if (StringUtils.isNotBlank(phone)){
                    communication.setPhone(phone);
                }
                if (StringUtils.isNotBlank(officeMobile)){
                    communication.setOfficeMobile(officeMobile);
                }
                if (StringUtils.isNotBlank(departmentId)){
                    communication.setDepartmentId(getDepartmentId(departmentId));
                }
                if (StringUtils.isNotBlank(roleId)){
                    communication.setRoleId(getRolesId(roleId));
                }
                if (StringUtils.isNotBlank(sex)){
                    communication.setSex(sex);
                }
                if (StringUtils.isNotBlank(birthday)){
                    communication.setBirthday(birthday);
                }
                if (StringUtils.isNotBlank(jiguan)){
                    communication.setJiguan(jiguan);
                }
                communication.setCreateTime(new Date());
                trueCount++;
                communicationList.add(communication);
            }
        }
           if (communicationList.size()>0){
               map.put("communicationList",communicationList);
           }
           map.put("trueCount",trueCount);
           map.put("errorCount",errorCount);
           map.put("errorList",errorList);
        return  map;
    }


    /**
     * 根据部门名字获取对应id
     * @param departmentName
     * @return
     */
    public String getDepartmentId(String departmentName){
        for (TBigdataDepartment department:departmentList) {
              if (department.getDepartmentName().equals(departmentName)){
                  return department.getId().toString();
              }
        }
        departmentList = departmentService.findAll();
        TBigdataDepartment department = departmentService.getDepartmentByName(departmentName);
        if (department!=null){
            return department.getId().toString();
        }
        return null;
    }

    /**
     * 根据角色名字获取对应id
     * @param RolesName
     * @return
     */
    public String getRolesId(String RolesName){
        for (Roles roles:rolesList) {
            if (roles.getName().equals(RolesName)){
                return roles.getId().toString();
            }
        }
        rolesList = (List<Roles>)redisTemplate.boundHashOps("rolesList").get("rolesList");
        Roles roles = userApi.getRolesByName(RolesName);
        if (roles != null) {
            roles.getId().toString();
        }
        return null;
    }

}
