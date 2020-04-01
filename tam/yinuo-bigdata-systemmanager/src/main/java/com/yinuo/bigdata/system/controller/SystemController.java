package com.yinuo.bigdata.system.controller;

import com.github.pagehelper.Page;
import com.inuol.bigdata.*;
import com.inuol.bigdata.ext.TBigdataCommunicationNode;
import com.inuol.bigdata.ext.TBigdataDictionarieNode;
import com.inuol.user.Roles;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.bigdata.system.service.*;
import com.yinuo.bigdata.system.utils.ImportUtils;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * @author ：jias
 * @date ：2020/1/7 16:10
 */
@RestController
@Api(value = "系统管理",description = "系统管理接口")
public class SystemController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RolesManageService rolesManageService;

    @Autowired
    private DictionarieService dictionarieService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ImportUtils importUtils;



    @PostMapping("/departmentAdd")
    @ApiOperation("部门增加")
    public HttpResult departmentAdd(@RequestBody TBigdataDepartment department){
        int i = departmentService.add(department);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }


    @PostMapping("/departmentUpdata")
    @ApiOperation("部门修改")
    public HttpResult departmentUpdata(@RequestBody TBigdataDepartment department){
        int i = departmentService.updata(department);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure("修改失败！！！");
    }

    @GetMapping("/departmentDelete/{id}")
    @ApiOperation("部门删除")
    public HttpResult departmentDelete(@PathVariable("id") long id){
        int i = departmentService.delete(id);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure("删除失败！！！");
    }

    @GetMapping("/departmentfindAll/{page}/{size}")
    @ApiOperation("查询所有部门数据")
    public HttpResult departmentfindAll(@PathVariable("page") int page,@PathVariable("size") int size){
        Page<TBigdataDepartment> all = departmentService.departmentfindAll(page, size);
        if (all!=null) {
           return HttpResult.success(all.getTotal(),all.getResult());
        }
        return null;
    }

    @GetMapping("/findAllDepartment")
    @ApiOperation("查询所有部门数据不分页")
    public HttpResult findAllDepartment(){
        List<TBigdataDepartment> all = departmentService.findAll();
        if (all!=null) {
            return HttpResult.success(all);
        }
        return null;
    }


    @PostMapping("/findManageUser/{page}/{size}")
    @ApiOperation("查询管理用户")
    public HttpResult findManageUser(@PathVariable("page") int page,
                                              @PathVariable("size") int size,
                                              @RequestBody CriteriaUser criteriaUser){
        Page<User> user = departmentService.findUser(page, size, criteriaUser);
        if (user!=null) {
            return HttpResult.success(user.getTotal(),user.getResult());
        }
        return HttpResult.success();
    }

    @PostMapping("/addManageUser")
    @ApiOperation("新增管理用户")
    public HttpResult addManageUser(@RequestBody User user){
       return departmentService.addManageUser(user);
    }

    @GetMapping("/updateManageState")
    @ApiOperation("用户禁用||启用")
    public HttpResult<String> updateManageState(Long id, Integer state){
        int i = departmentService.updateManageState(id, state);
        if (i>0) {
            return HttpResult.success("操作成功！！！");
        }
        return HttpResult.failure(null,"操作失败！！！");
    }

    @GetMapping("/resetManagePassword")
    @ApiOperation("重置管理员密码")
    public HttpResult<String> resetManagePassword(@RequestParam("id") Long id){
        int i = departmentService.resetManagePassword(id);
        if (i>0) {
            return HttpResult.success("重置密码成功！！！");
        }
        return HttpResult.success("重置密码失败！！！");
    }

    @GetMapping("/deleteManage")
    @ApiOperation("删除管理员")
    public HttpResult<String> deleteManage(@RequestParam("id") Long id){
        int i = departmentService.deleteManage(id);
        if (i>0) {
            return HttpResult.success("删除管理员成功！！！");
        }
        return HttpResult.success("删除管理员失败！！！");
    }


    @GetMapping("/findAllRole/{page}/{size}")
    @ApiOperation("查询所有角色")
    public HttpResult findAllRole(@PathVariable("page") int page, @PathVariable("size") int size){
        Page<Roles> roles = rolesManageService.findAllRole(page, size);
        if (roles == null) {
            return HttpResult.success();
        }
        return HttpResult.success(roles.getTotal(),roles.getResult());
    }

    @PostMapping("/addRole")
    @ApiOperation("新增角色")
    public HttpResult addRole(@RequestBody Roles roles){
        try {
            HttpResult httpResult = rolesManageService.addRole(roles);
            List<Roles> rolesList = rolesManageService.findRoles();
            if (rolesList.size()>0){
                redisTemplate.boundHashOps("rolesList").put("rolesList",rolesList);
            }
            return httpResult;
        } catch (Exception e){
            e.printStackTrace();
        }
       return HttpResult.failure("添加失败！！！");

    }

    @GetMapping("/dictionarieList")
    @ApiOperation("查询字典数据")
    public List<TBigdataDictionarieNode> dictionarieList(){
        return dictionarieService.dictionarieList();
    }

    @PostMapping("/addDictionarie")
    @ApiOperation("新增字典数据")
    public HttpResult addDictionarie(@RequestBody TBigdataDictionarie dictionarie){
        int i = dictionarieService.addDictionarie(dictionarie);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/deleteDictionarie/{id}")
    @ApiOperation("删除字典数据")
    public HttpResult deleteDictionarie(@PathVariable("id") Long id){
        int i = dictionarieService.deleteDictionarie(id);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/updateDictionarie")
    @ApiOperation("更新字典数据")
    public HttpResult updateDictionarie(@RequestBody TBigdataDictionarie dictionarie){
        int i = dictionarieService.updateDictionarie(dictionarie);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }


    @PostMapping("/selectUserById/{id}")
    @ApiOperation("根据用id查询用户基本信息")
    public HttpResult selectUserById(@PathVariable("id") Long id){
        User user = personalService.selectUserById(id);
        if (user!=null) {
            return HttpResult.success(user);
        }
        return HttpResult.success(null);
    }

    @PostMapping("/updateUser")
    @ApiOperation("修改用户基本信息")
    public HttpResult updateUser(@RequestBody User user){
        int i = personalService.updateUser(user);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @GetMapping("/updateUserPassword")
    @ApiOperation("修改用户密码")
    public HttpResult updateUserPassword(@RequestParam("password") String password,
                                         @RequestParam("newpassword") String newpassword,
                                         @RequestHeader(value = "uid") String uid){
        int i = personalService.updateUserPassword(Long.valueOf(uid),password,newpassword);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/addTemplate")
    @ApiOperation("模板添加")
    public HttpResult addTemplate(@RequestBody TBigdataTemplate template, @RequestHeader(value = "uid") String uid){
        template.setClassifyId(uid);
        int i = templateService.addTemplate(template);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/updateTemplate")
    @ApiOperation("模板修改")
    public HttpResult updateTemplate(@RequestBody TBigdataTemplate template){
        int i = templateService.updateTemplate(template);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/deleteTemplate/{id}")
    @ApiOperation("模板删除")
    public HttpResult deleteTemplate(@PathVariable("id") Long id){
        int i = templateService.deleteTemplate(id);
        if (i>0) {
            return HttpResult.success();
        }
        return HttpResult.failure();
    }

    @PostMapping("/findAllTemplate/{page}/{size}")
    @ApiOperation("条件查询模板")
    public HttpResult findAllTemplate(@RequestBody TBigdataTemplate template,
                                      @PathVariable("page") int page,
                                      @PathVariable("size") int size){
        Page<TBigdataTemplate> templatePage = templateService.findAllTemplate(page, size, template);
        return HttpResult.success(templatePage.getTotal(),templatePage.getResult());

    }


    @PostMapping("/findMyMessage/{page}/{size}")
    @ApiOperation("条件查询消息")
    public HttpResult findMyMessage(@PathVariable("page") int page,
                                    @PathVariable("size") int size,
                                    @RequestBody TBigdataMessage message ){
        Page<TBigdataMessage> myMessage = messageService.findMyMessage(page, size,message);

        return HttpResult.success(myMessage.getTotal(),myMessage.getResult());

    }

    @GetMapping("/updateMessageType")
    @ApiOperation("标记消息已读")
    public HttpResult updateMessageType(@RequestParam("ids") Long[] ids){
        int i = messageService.updateMessageType(ids);
        if (i>0){
            return HttpResult.success("标记已读成功！！！");
        }
        return HttpResult.failure("标记已读失败！！！");
    }

    @GetMapping("/deleteBatchMessage")
    @ApiOperation("消息删除")
    public HttpResult deleteBatchMessage(@RequestParam("ids") Long[] ids){
        int i = messageService.deleteBatchMessage(ids);
        if (i>0){
            return HttpResult.success("删除消息成功！！！");
        }
        return HttpResult.failure("删除消息失败！！！");
    }


    @PostMapping("/addCommunicate")
    @ApiOperation("添加通讯录")
    public HttpResult addCommunicate(@Valid @RequestBody TBigdataCommunication communication, BindingResult bindingResult){
        String result = bindingResult(bindingResult);
        if (StringUtils.isNotBlank(result)){
            return HttpResult.failure(result);
        }
        int i = communicationService.addCommunicate(communication);
        if (i>0){
            return HttpResult.success("添加通讯录成功！！！");
        }
        return HttpResult.failure("添加通讯录失败！！！");
    }


    @PostMapping("/findAllCommunicate")
    @ApiOperation("条件查询通讯录列表")
    public HttpResult findAllCommunicate(@RequestBody TBigdataCommunicationNode communicationNode, int page, int size){
        Page<TBigdataCommunication> communicationPage = communicationService.findAllCommunicate(communicationNode, page, size);
        return HttpResult.success(communicationPage.getTotal(),communicationPage.getResult());
    }

    /**
     * 通讯录批量导入准备
     * @param file
     * @return
     */
    @PostMapping(value = "/communicateBatchImportPrepare")
    @ApiOperation("通讯录批量导入准备")
    public HttpResult communicateBatchImportPrepare(@RequestParam(value = "file") MultipartFile file) {
        Map<String, Object> map = importUtils.communicateBatchImportPrepare(file);
        return HttpResult.success(map);
    }

    /**
     * 通讯录批量导入
     * @param
     * @return
     */
    @PostMapping(value = "/communicateBatchImport")
    @ApiOperation("通讯录批量导入")
    public HttpResult communicateBatchImport(@RequestBody List<TBigdataCommunication> communicationList,String is) {
        if (is.equals("1")){
            for (int i = 0; i < communicationList.size(); i++) {
                TBigdataCommunication communication = communicationService.getCommunicationByNameAndByPhone(communicationList.get(i));
                if (communication!=null){
                    communicationList.remove(communicationList.get(i));
                    i--;
                }
            }
        }
        int i = communicationService.addCommunicateBatch(communicationList);
        if (i>0){
            if (is.equals("1")) {
                return HttpResult.success("成功导入去重通讯录数据"+communicationList.size()+"条！！！");
            }
            return HttpResult.success("成功导入通讯录数据"+communicationList.size()+"条！！！");
        }
        return HttpResult.failure("批量导入失败！！！");
    }



}
