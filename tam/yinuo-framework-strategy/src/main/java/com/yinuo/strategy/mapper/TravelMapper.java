package com.yinuo.strategy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inuol.dto.strategy.CollectionsDto;
import com.inuol.dto.strategy.CommentDto;
import com.inuol.dto.strategy.CommentPraisesDto;
import com.inuol.dto.strategy.PraisesDto;
import com.inuol.entity.strategy.Comment;
import com.inuol.entity.strategy.Travel;
import com.inuol.vo.strategy.TravelCommentVo;
import com.inuol.vo.strategy.TravelPcVo;
import com.inuol.vo.strategy.TravelVo;

import java.util.List;
import java.util.Map;

public interface TravelMapper extends BaseMapper<Travel>{
	/*// 攻略新增 -app
	int insertTravel(Travel travel);*/

	//查询攻略列表 -app
	List<Travel> selectTravelList(Map parm);

	// 查询攻略详情 -app
	TravelVo selectTravelDetail(Map parm);
	/*攻略点击数累加 */
	int updateTravelDetailClicks(long id);

	/*攻略点赞数累加 */
	int updateTravelDetailPraisesAdd(long id);

	/*攻略点赞数减少 */
	int updateTravelDetailPraisesSub(long id);

	/*攻略评论数累加 */
	int updateTravelDetailCommentsAdd(long id);

	/*攻略评论数减少 */
	int updateTravelDetailCommentsSub(long id);

	/*攻略收藏数累加 */
	int updateTravelDetailCollectionsAdd(long id);

	/*攻略收藏数减少 */
	int updateTravelDetailCollectionsSub(long id);

	/*攻略点赞 查询是否存在*/
	int selectPraisesCount(PraisesDto PraisesDto);

	/*攻略点赞 查询是否存在 过滤*/
	int selectPraisesCountStatus(PraisesDto PraisesDto);
	/*攻略点赞 新增*/
	int insertPraises(PraisesDto PraisesDto);
	/*攻略点赞 更新*/
	int updatePraises(PraisesDto PraisesDto);

	/**
	 *  攻略评论发布
	 * @param commentDto
	 * @return
	 */
	int insertComment(CommentDto commentDto);

	/*攻略评论查询*/
	Comment selectCommentById(Long id);
	/**
	 *  攻略评论删除
	 * @param id 攻略评论id
	 * @return
	 */
	int deleteComment(Long id);

	/**
	 *  攻略评论列表
	 * @param parm
	 * @return
	 */
	List<TravelCommentVo> selectTravelCommentList(Map parm);

	/*攻略评论点赞 查询是否存在*/
	int selectCommentPraisesCount(CommentPraisesDto commentPraisesDto);

	/*攻略评论点赞 查询是否存在 过滤*/
	int selectCommentPraisesCountStatus(CommentPraisesDto commentPraisesDto);
	/*攻略评论点赞 新增*/
	int insertCommentPraises(CommentPraisesDto commentPraisesDto);
	/*攻略评论点赞 更新*/
	int updateCommentPraises(CommentPraisesDto commentPraisesDto);

	/*攻略评论点赞数累加 */
	int updateTravelCommentPraisesAdd(long id);

	/*攻略评论点赞数减少 */
	int updateTravelCommentPraisesSub(long id);
	/*用于判断是否已收藏和已取消收藏**/
	int selectCollectionsCount(CollectionsDto collectionsDto);
	/*攻略收藏*/
	int insertCollections(CollectionsDto collectionsDto);

	/*攻略取消收藏*/
	int deleteCollections(CollectionsDto collectionsDto);

	/*我的攻略收藏列表*/
	List<Travel> selectUserCollectionsList(String uid);
	/**  旅游攻略 列表 -pc */
	List<TravelPcVo> selectTravelPcVoList(Map parm);
	//  旅游攻略 置顶 -pc
	int top(Long id);
	//  旅游攻略 更新状态 -pc
	int updateStatus(Map parm);
	//  旅游攻略评论 更新状态 -pc
	int updateCommentStatus(Map parm);
}
