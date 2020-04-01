package com.yinuo.backstageAuth.controller;

import com.yinuo.backstageAuth.service.OrganizationService;
import com.yinuo.backstageAuth.service.UserService;
import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.OrganizationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Capejor
 * @date 2020-01-09 14:28
 */
@Api(tags = "组织结构")
@RequestMapping("/organization")
@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @ApiOperation("添加")
    @PostMapping("/insertOrganization")
    public HttpResult insertOrganization(@ApiParam(name="传入对象",required=true) @RequestBody @Valid OrganizationVo vo, @ApiIgnore Errors errors){
        if (errors.hasErrors()) {
            List<FieldError> FieldErrors = errors.getFieldErrors();
            FieldError fieldError = FieldErrors.get(0);
            return HttpResult.failure("400", fieldError.getField()+"属性"+fieldError.getDefaultMessage());
        }
        if (vo.getParentId() == 0){
            return HttpResult.failure("400","不能添加顶级组织！");
        }
        return organizationService.insertOrganization(vo);
    }

    @ApiOperation("编辑")
    @PostMapping("/updateOrganization")
    public HttpResult updateOrganization(@ApiParam(name="传入对象",required=true) @RequestBody @Valid OrganizationVo vo, @ApiIgnore Errors errors){
        if (errors.hasErrors()) {
            List<FieldError> FieldErrors = errors.getFieldErrors();
            FieldError fieldError = FieldErrors.get(0);
            return HttpResult.failure("400", fieldError.getField()+"属性"+fieldError.getDefaultMessage());
        }
        if (vo.getParentId() == 0){
            return HttpResult.failure("400","不能编辑顶级组织！");
        }
        return organizationService.updateOrganization(vo);
    }

    @ApiOperation("组织树结构")
    @GetMapping("/selectOrgTree")
    public HttpResult selectOrgTree(){
        return organizationService.selectOrgTree();
    }

//    @ApiOperation("查询所有组织")
//    @GetMapping("/selectAllOrgExceptTop")
//    public Result selectAllOrgExceptTop(){
//        return organizationService.selectAllOrgExceptTop();
//    }

    @ApiOperation("查询下级组织")
    @GetMapping("/selectLowOrg")
    public HttpResult selectOrgByParentId(@ApiParam(name = "id", value = "id") @RequestParam String id){
        if ("0".equals(id)){
            return HttpResult.failure("400","无数据！");
        }
        return organizationService.selectOrgByParentId(id);
    }

    @ApiOperation("删除组织")
    @PostMapping("/deleteOrganization")
    public HttpResult deleteOrganization(Long id){
        int orgResult = organizationService.selectOrgCountByParentId(id);
        int userResult = userService.selectUserCountByOrgId(id);
        if (id==0){
            return HttpResult.failure("400","不能删除顶级组织！");
        }
        if (orgResult > 0 && userResult ==0){
            return HttpResult.failure("400","当前组织结构下存在下级,不能删除！");
        }
        if (orgResult == 0 && userResult > 0){
            return HttpResult.failure("400","当前组织结构下存在用户，不能删除！");
        }
        if (orgResult > 0 && userResult > 0){
            return HttpResult.failure("400","当前组织结构下存在下级和用户，不能删除！");
        }
        return organizationService.deleteOneById(id);
    }

}
