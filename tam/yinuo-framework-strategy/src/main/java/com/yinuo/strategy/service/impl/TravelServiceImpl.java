package com.yinuo.strategy.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.dto.strategy.CollectionsDto;
import com.inuol.dto.strategy.CommentDto;
import com.inuol.dto.strategy.CommentPraisesDto;
import com.inuol.dto.strategy.PraisesDto;
import com.inuol.entity.strategy.Comment;
import com.inuol.user.User;
import com.inuol.vo.strategy.TravelCommentVo;
import com.inuol.vo.strategy.TravelPcVo;
import com.inuol.vo.strategy.TravelVo;
import com.inuol.vo.strategy.UserVo;
import com.yinuo.api.user.UserApi;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.DateUtils;
import com.yinuo.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inuol.entity.strategy.Travel;
import com.yinuo.strategy.mapper.TravelMapper;
import com.yinuo.strategy.service.TravelService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements TravelService {
    private  final Logger logger = LoggerFactory.getLogger(TravelServiceImpl.class);
    @Autowired
    private UserApi userApi;


	@Override
	public int insertTravel(Travel travel){
		return baseMapper.insert(travel);
	}

	@Override
	public HttpResult<PageInfo<Travel>> selectList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
       /* EntityWrapper<Travel> wrapper = new EntityWrapper();
        wrapper.like("title", "ceshi标题");
        List<Travel> list = baseMapper.selectList(wrapper);*/
        Map parm = new HashMap();
       /* if(pageNum<=1){
            parm.put("frist",1);
        }*/
        List<Travel> list = baseMapper.selectTravelList(parm);
        for(Travel travel :list){
          User user =  userApi.getUserById(travel.getCreateUserId());
          if(user!=null){
              if(!StringUtil.isNull(user.getRealityName())){
                  travel.setUserName(user.getRealityName());
              }
              if(!StringUtil.isNull(user.getLogo())){
                  travel.setUserImgUrl(user.getLogo());
              }
          }
        }
        PageInfo<Travel>  pageInfo = new PageInfo<Travel>(list);
		return HttpResult.success(pageInfo);
	}
    @Override
    public HttpResult<PageInfo<Travel>> selectUserList(Integer pageNum, Integer pageSize, String uid) {
        PageHelper.startPage(pageNum, pageSize);

        Map parm = new HashMap();
        /*if(pageNum<=1){
            parm.put("frist",1);
        }*/
        parm.put("uid",uid);
        List<Travel> list = baseMapper.selectTravelList(parm);
        User user =  userApi.getUserById(Long.parseLong(uid));
        for(Travel travel :list){
            if(user!=null){
                if(!StringUtil.isNull(user.getRealityName())){
                    travel.setUserName(user.getRealityName());
                }
                if(!StringUtil.isNull(user.getLogo())){
                    travel.setUserImgUrl(user.getLogo());
                }
            }
        }
        PageInfo<Travel>  pageInfo = new PageInfo<Travel>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult<PageInfo<Travel>> selectUserCollectionsList(Integer pageNum, Integer pageSize, String uid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Travel> list = baseMapper.selectUserCollectionsList(uid);
        User user =  userApi.getUserById(Long.parseLong(uid));
        for(Travel travel :list){
            if(user!=null){
                if(!StringUtil.isNull(user.getRealityName())){
                    travel.setUserName(user.getRealityName());
                }
                if(!StringUtil.isNull(user.getLogo())){
                    travel.setUserImgUrl(user.getLogo());
                }
            }
        }
        PageInfo<Travel>  pageInfo = new PageInfo<Travel>(list);
        return HttpResult.success(pageInfo);
    }


    @Override
    public HttpResult<TravelVo> selectTravelDetail(Long id, Integer userPageSize) {
        Map parm = new HashMap();
        if(id!=null ){
            parm.put("id",id);
        }else{
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        if(userPageSize!=null ){
            parm.put("userPageSize",userPageSize);
        }

        // 点击数累加
        baseMapper.updateTravelDetailClicks(id);
        TravelVo travelVo =  baseMapper.selectTravelDetail(parm);
        // 用户数据赋值
        User user =  userApi.getUserById(travelVo.getCreateUserId());
        if(user!=null){
            if(!StringUtil.isNull(user.getRealityName())){
                travelVo.setUserName(user.getRealityName());
            }
            if(!StringUtil.isNull(user.getLogo())){
                travelVo.setUserImgUrl(user.getLogo());
            }
        }
        List<UserVo> listUserVo = travelVo.getPraisesUsers();
        for(UserVo userVo:listUserVo){
            User userTmp =  userApi.getUserById(userVo.getId());
            if(user!=null){
                if(!StringUtil.isNull(userTmp.getRealityName())){
                    userVo.setName(userTmp.getRealityName());
                }
                if(!StringUtil.isNull(userTmp.getLogo())){
                    userVo.setUserImgUrl(userTmp.getLogo());
                }
            }
        }
        return HttpResult.success(travelVo);
    }

    @Override
    public HttpResult<PageInfo<TravelCommentVo>> selectTravelCommentList(Integer pageNum, Integer pageSize, Long id) {

	    Map parm = new HashMap();
        parm.put("id",id);
        TravelVo travelVo =  baseMapper.selectTravelDetail(parm);
        if(travelVo==null){
            return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TravelCommentVo> list =  baseMapper.selectTravelCommentList(parm);

        for(TravelCommentVo travelCommentVo : list){
            // 用户数据赋值
            // 评论用户
            User user =  userApi.getUserById(travelCommentVo.getUid());
            if(user!=null){
                if(!StringUtil.isNull(user.getRealityName())){
                    travelCommentVo.setUserName(user.getRealityName());
                }
                if(!StringUtil.isNull(user.getLogo())){
                    travelCommentVo.setUserImgUrl(user.getLogo());
                }
            }
            // 回复用户
            if(travelCommentVo.getReplyUid()!=null){
                User userReply =  userApi.getUserById(travelCommentVo.getReplyUid());
                if(userReply!=null){
                    if(!StringUtil.isNull(userReply.getRealityName())){
                        travelCommentVo.setReplyUserName(userReply.getRealityName());
                    }
                    if(!StringUtil.isNull(userReply.getLogo())){
                        travelCommentVo.setReplyUserImgUrl(userReply.getLogo());
                    }
                }
            }
            // 是否是回复消息
            if(travelCommentVo.getReplyUid()!=null){
                travelCommentVo.setReplyStatus(1);
            }else{
                travelCommentVo.setReplyStatus(2);
            }
            //是否是发布者
            travelCommentVo.setCreateUid(travelVo.getCreateUserId());
            if(travelVo.getCreateUserId().longValue()==travelCommentVo.getUid().longValue()){
                travelCommentVo.setAuthStatus(1);
            }else{
                travelCommentVo.setAuthStatus(2);
            }
            // 格式化发布时间距离当前时间
            travelCommentVo.setDateStr(DateUtils.getDatePoor(travelCommentVo.getCreateTime(),new Date()));
        }
        PageInfo<TravelCommentVo>  pageInfo = new PageInfo<TravelCommentVo>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    @Transactional
    public HttpResult praises(PraisesDto praisesDto) {
	    try {
            // 重复操作过滤
            if(baseMapper.selectPraisesCountStatus(praisesDto)<=0){
                //先查询 不存在 新增 存在更新
                if(praisesDto.getStatus()==1 ){
                    baseMapper.updateTravelDetailPraisesAdd(praisesDto.getTid());
                    if(baseMapper.selectPraisesCount(praisesDto)>0){
                        praisesDto.setUpdateTime(new Date());
                        baseMapper.updatePraises(praisesDto);
                        return HttpResult.success();
                    }else{
                        praisesDto.setId(IdWorker.getId());
                        baseMapper.insertPraises(praisesDto);
                        return HttpResult.success();
                    }
                }else{
                    baseMapper.updateTravelDetailPraisesSub(praisesDto.getTid());
                    praisesDto.setUpdateTime(new Date());
                    baseMapper.updatePraises(praisesDto);
                    return HttpResult.success();
                }
            }else{
                return HttpResult.failure(ErrCodeAndMsg.OPER_RATE_LIMIT);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
	        throw new BusinessException("700","点赞操作失败");
        }
    }

    @Override
    @Transactional
    public HttpResult insertComment(CommentDto commentDto) {
        try {
	    // 插入
            baseMapper.updateTravelDetailCommentsAdd(commentDto.getTid());
            commentDto.setId(IdWorker.getId());
            // 未做内容检测
            commentDto.setDelFlag(1);
            baseMapper.insertComment(commentDto);
            return HttpResult.success();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","攻略评论新增失败");
        }
    }

    @Override
    @Transactional
    public HttpResult deleteComment(CommentDto commentDto) {
        try {
	    // 判断该评论是否是自己发布的
            Comment comment = baseMapper.selectCommentById(commentDto.getId());
            if(comment==null){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
            if(comment.getUid()!=commentDto.getUid()){
                return HttpResult.failure(ErrCodeAndMsg.FAIL_AUTHORIZED);
            }
            // 删除
            baseMapper.updateTravelDetailCommentsSub(commentDto.getTid());
            baseMapper.deleteComment(commentDto.getId());
            return HttpResult.success();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","攻略评论删除失败");
        }
    }

    @Override
    @Transactional
    public HttpResult commentPraises(CommentPraisesDto commentPraisesDto) {
        try {
        // 重复操作过滤
            if(baseMapper.selectCommentPraisesCountStatus(commentPraisesDto)<=0){
                //先查询 不存在 新增 存在更新
                if(commentPraisesDto.getStatus()==1 ){
                    baseMapper.updateTravelCommentPraisesAdd(commentPraisesDto.getTid());
                    if(baseMapper.selectCommentPraisesCount(commentPraisesDto)>0){
                        commentPraisesDto.setUpdateTime(new Date());
                        baseMapper.updateCommentPraises(commentPraisesDto);
                        return HttpResult.success();
                    }else{
                        commentPraisesDto.setId(IdWorker.getId());
                        baseMapper.insertCommentPraises(commentPraisesDto);
                        return HttpResult.success();
                    }
                }else{
                    baseMapper.updateTravelCommentPraisesSub(commentPraisesDto.getTid());
                    commentPraisesDto.setUpdateTime(new Date());
                    baseMapper.updateCommentPraises(commentPraisesDto);
                    return HttpResult.success();
                }
            }else{
                return HttpResult.failure(ErrCodeAndMsg.OPER_RATE_LIMIT);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","评论点赞操作失败");
        }
    }

    @Override
    @Transactional
    public HttpResult insertCollections(CollectionsDto collectionsDto) {
        try {
            if(baseMapper.selectCollectionsCount(collectionsDto)>0){
                return HttpResult.failure(ErrCodeAndMsg.OPER_RATE_LIMIT);
            }
            collectionsDto.setId(IdWorker.getId());
            baseMapper.updateTravelDetailCollectionsAdd(collectionsDto.getTid());
           int res = baseMapper.insertCollections(collectionsDto);
           return res==1?HttpResult.success():HttpResult.failure("新增失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","旅游攻略收藏操作失败");
        }
    }

    @Override
    @Transactional
    public HttpResult deleteCollections(CollectionsDto collectionsDto) {
        try {
            if(baseMapper.selectCollectionsCount(collectionsDto)<=0){
                return HttpResult.failure(ErrCodeAndMsg.OPER_RATE_LIMIT);
            }
            baseMapper.updateTravelDetailCollectionsSub(collectionsDto.getTid());
            int res = baseMapper.deleteCollections(collectionsDto);
            return  res==1?HttpResult.success():HttpResult.failure("删除失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","旅游攻略取消收藏操作失败");
        }
    }

    @Override
    public HttpResult selectTravelPcVoList(int pageNum,int pageSize, Map parm) {
        if(parm.get("createTimeStartStr")!=null){
           Date createTimeStart = DateUtils.string2Date(parm.get("createTimeStartStr").toString());
           if(createTimeStart!=null){
               parm.put("createTimeStart",createTimeStart);
           }
        }
        if(parm.get("createTimeEndStr")!=null){
            Date createTimeEnd = DateUtils.string2Date(parm.get("createTimeEndStr").toString());
            if(createTimeEnd!=null){
                parm.put("createTimeEnd",createTimeEnd);
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TravelPcVo> list = baseMapper.selectTravelPcVoList(parm);
        // 用户数据未赋值 用户手机号码查询条件未实现
        /*for(TravelPcVo travelPcVo : list){

        }*/
        PageInfo<TravelPcVo>  pageInfo = new PageInfo<TravelPcVo>(list);
        return HttpResult.success(pageInfo);
    }

    @Override
    public HttpResult top(Long id) {
        try {
            int res = baseMapper.top(id);
            return  res==1?HttpResult.success():HttpResult.failure("置顶失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","旅游攻略置顶操作失败");
        }
    }

    @Override
    public HttpResult updateStatus(Long id, int status,Long uid) {
        try {
            if(id==null || uid==null || status==0){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
            Map parm = new HashMap();
            parm.put("id",id);
            parm.put("status",status);
            parm.put("uid",uid);
            int res = baseMapper.updateStatus(parm);
            return res==1?HttpResult.success():HttpResult.failure("操作失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","旅游攻略更新操作失败");
        }
    }

    @Override
    public HttpResult updateCommentStatus(Long id, int status, Long uid) {
        try {
            if(id==null || uid==null || status==0){
                return HttpResult.failure(ErrCodeAndMsg.REQ_PARAM_ERROR);
            }
            Map parm = new HashMap();
            parm.put("id",id);
            parm.put("status",status);
            parm.put("uid",uid);
            int res = baseMapper.updateCommentStatus(parm);
            return res==1?HttpResult.success():HttpResult.failure("操作失败");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new BusinessException("700","旅游攻略评论更新操作失败");
        }
    }
}
