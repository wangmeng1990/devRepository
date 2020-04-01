package com.yinuo.strategy.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.inuol.dto.strategy.CollectionsDto;
import com.inuol.dto.strategy.CommentDto;
import com.inuol.dto.strategy.CommentPraisesDto;
import com.inuol.dto.strategy.PraisesDto;
import com.inuol.entity.strategy.Travel;
import com.inuol.vo.strategy.TravelCommentVo;
import com.inuol.vo.strategy.TravelVo;
import com.yinuo.common.common.HttpResult;

import java.util.Map;

public interface TravelService extends IService<Travel> {
	/**
	 * 
	 *
	 * Title: insertTravel
	 *
	 * Description: 
	 * 					新增
	 * 
	 * @param travel
	 * @return
	 *
	 */
	int insertTravel(Travel travel);

    /**
     *      攻略列表
     * @param pageNum
     * @param pageSize
     * @return
     */
	HttpResult<PageInfo<Travel>> selectList(Integer pageNum, Integer pageSize);

	/**
	 *      我的攻略列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	HttpResult<PageInfo<Travel>> selectUserList(Integer pageNum, Integer pageSize,String uid);

	/**
	 *      我的收藏攻略列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	HttpResult<PageInfo<Travel>> selectUserCollectionsList(Integer pageNum, Integer pageSize,String uid);
    /**
     *    详情
     */
    HttpResult<TravelVo> selectTravelDetail(Long id, Integer userPageSize);

	/**
	 * 攻略评论列表接口
	 */
	HttpResult<PageInfo<TravelCommentVo>> selectTravelCommentList(Integer pageNum, Integer pageSize, Long id);
	/**
	 *  攻略点赞与取消点赞
	 * @param PraisesDto
	 * @return
	 */
	HttpResult praises(PraisesDto PraisesDto);

	/**
	 *  攻略评论发布
	 * @param commentDto
	 * @return
	 */
	HttpResult insertComment(CommentDto commentDto);

	/**
	 *  攻略评论删除
	 * @param commentDto 攻略评论
	 * @return
	 */
	HttpResult deleteComment(CommentDto commentDto);

	/**
	 *  攻略评论点赞与取消点赞
	 * @param commentPraisesDto
	 * @return
	 */
	HttpResult commentPraises(CommentPraisesDto commentPraisesDto);


	/**
	 *  攻略收藏
	 * @param collectionsDto
	 * @return
	 */
	HttpResult insertCollections(CollectionsDto collectionsDto);

	/**
	 *  攻略取消收藏
	 * @param collectionsDto
	 * @return
	 */
	HttpResult deleteCollections(CollectionsDto collectionsDto);

	/**
	 *   旅游攻略 列表 -pc
	 * @param parm
	 * @return
	 */
	HttpResult selectTravelPcVoList(int pageNum, int pageSize,Map parm);

	// 旅游攻略 置顶  -pc
	HttpResult top(Long id);

	// 旅游攻略 更新状态 -pc
	HttpResult updateStatus(Long id,int status,Long uid);

	//评论屏蔽/取消屏蔽

	HttpResult updateCommentStatus(Long id,int status,Long uid);
}
