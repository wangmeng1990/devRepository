package com.yinuo.manage.service;


import com.inuol.dto.BaseRequestDto;
import com.inuol.dto.PopRequestDto;
import com.inuol.vo.BannerVo;
import com.inuol.vo.CulturalCreationVo;
import com.inuol.vo.FunctionVo;
import com.inuol.vo.InformationVo;
import com.inuol.vo.MessageVo;
import com.inuol.vo.NoticeVo;
import com.inuol.vo.PopVo;
import com.inuol.vo.VideoVo;
import com.yinuo.common.common.HttpResult;

public interface ManageService {
	HttpResult<Object> getPopList(PopRequestDto pop);

	HttpResult<Object> updateOrSavePop(PopVo pop);

	HttpResult<Object> getMessageList(String type, String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveMessage(MessageVo vo);

	HttpResult<Object> getNoticeList(String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveNotice(NoticeVo vo);

	HttpResult<Object> getBannerList(String type, String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveBanner(BannerVo vo);

	HttpResult<Object> getSubjectList(String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveSubject(BannerVo vo);

	HttpResult<Object> getInformationList(String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveInformation(InformationVo vo);

	HttpResult<Object> getFunctionList(String name, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveFunction(FunctionVo vo);

	HttpResult<Object> updateFunctionState(Long id, int state);

	HttpResult<Object> updateInformationState(Long id, int state);

	HttpResult<Object> updateSubjectState(Long id, int state);

	HttpResult<Object> updateBannerState(Long id, int state);

	HttpResult<Object> updateNoticeState(Long id, int state);

	HttpResult<Object> updateMessageState(Long id, int state);

	HttpResult<Object> updatePopState(Long id, int state);

	HttpResult<Object> getVideoList(String type,String classify,String subJect, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveVideo(VideoVo vo);

	HttpResult<Object> updateVideoState(Long id, int state);

	HttpResult<Object> getCulturalCreationList(String backGround, String backGroundType, BaseRequestDto dto);

	HttpResult<Object> updateOrSaveCulturalCreation(CulturalCreationVo vo);

	HttpResult<Object> updateCulturalCreationState(Long id, int state);
}
