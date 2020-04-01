package com.yinuo.manage.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.dto.BaseRequestDto;
import com.inuol.dto.PopRequestDto;
import com.inuol.entity.Banner;
import com.inuol.entity.BannerCriteria;
import com.inuol.entity.CulturalCreation;
import com.inuol.entity.CulturalCreationCriteria;
import com.inuol.entity.Function;
import com.inuol.entity.FunctionCriteria;
import com.inuol.entity.InformationCriteria;
import com.inuol.entity.InformationWithBLOBs;
import com.inuol.entity.Message;
import com.inuol.entity.MessageCriteria;
import com.inuol.entity.Notice;
import com.inuol.entity.NoticeCriteria;
import com.inuol.entity.Pop;
import com.inuol.entity.PopCriteria;
import com.inuol.entity.Subject;
import com.inuol.entity.SubjectCriteria;
import com.inuol.entity.Video;
import com.inuol.entity.VideoCriteria;
import com.inuol.entity.PopCriteria.Criteria;
import com.inuol.vo.BannerVo;
import com.inuol.vo.CulturalCreationVo;
import com.inuol.vo.FunctionVo;
import com.inuol.vo.InformationVo;
import com.inuol.vo.MessageVo;
import com.inuol.vo.NoticeVo;
import com.inuol.vo.PopVo;
import com.inuol.vo.VideoVo;
import com.yinuo.common.common.DeviceType;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.BeanCopierUtils;
import com.yinuo.common.utils.DataUtils;
import com.yinuo.manage.mapper.BannerMapper;
import com.yinuo.manage.mapper.CulturalCreationMapper;
import com.yinuo.manage.mapper.FunctionMapper;
import com.yinuo.manage.mapper.InformationMapper;
import com.yinuo.manage.mapper.MessageMapper;
import com.yinuo.manage.mapper.NoticeMapper;
import com.yinuo.manage.mapper.PopMapper;
import com.yinuo.manage.mapper.SubjectMapper;
import com.yinuo.manage.mapper.VideoMapper;
import com.yinuo.manage.service.ManageService;
@Service
public class ManageServiceImpl implements ManageService {
	@Autowired
	PopMapper popMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	NoticeMapper noticeMapper;
	@Autowired
	BannerMapper bannerMapper;
	@Autowired
	SubjectMapper subjectMapper;
	@Autowired
	InformationMapper informationMapper;
	@Autowired
	FunctionMapper functionMapper;
	@Autowired
	VideoMapper videoMapper;
	@Autowired
	CulturalCreationMapper culturalCreationMapper;
	/**
	 *查询弹窗列表
	 */
	@Override
	public HttpResult<Object> getPopList(PopRequestDto dto) {
		
		if(DataUtils.isNotEmpty(dto.getPageNum())&&DataUtils.isNotEmpty(dto.getPageSize())&&dto.getPageNum().intValue()>0&&dto.getPageSize()>0) {
			PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
		}
		PopCriteria example=new PopCriteria();
		Criteria criteria = example.createCriteria();
		
		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if(DataUtils.isNotEmpty(dto.getDeviceType())) {
			criteria.andDeviceTypeEqualTo(dto.getDeviceType());
		}
		if(DataUtils.isNotEmpty(dto.getName())) {
			criteria.andNameLike("%"+dto.getName()+"%");
		}
		if(DataUtils.isNotEmpty(dto.getType())) {
			criteria.andTypeEqualTo(dto.getType());
		}
		example.setOrderByClause(" create_date desc");
		List<Pop> list= popMapper.selectByExample(example);
		if(DataUtils.isNotEmpty(dto.getPageNum())&&DataUtils.isNotEmpty(dto.getPageSize())&&dto.getPageNum().intValue()>0&&dto.getPageSize()>0) {
			PageInfo<Pop> pageInfo =new PageInfo<Pop>(list);
			HttpResult.success(list);
		}
		return HttpResult.success(list);
	}



	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSavePop(PopVo pop) {
		Pop record=new Pop();
		BeanCopierUtils.copy(pop, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(popMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(popMapper.insertSelective(record));
		}
	}

	

	@Override
	public HttpResult<Object> getMessageList(String type, String subJect, BaseRequestDto dto) {

		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		MessageCriteria example = new MessageCriteria();
		com.inuol.entity.MessageCriteria.Criteria criteria = example.createCriteria();

		
		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(dto.getDeviceType())) {
			if(DeviceType.PC.equals(dto.getDeviceType())) {
				criteria.andDeviceTypeEqualTo(dto.getDeviceType());
			}else {
				criteria.andDeviceTypeEqualTo(DeviceType.APP.getType());
			}
		}
		if (DataUtils.isNotEmpty(type)) {
			criteria.andTypeEqualTo(type);
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectEqualTo(subJect);
		}
		example.setOrderByClause(" create_date desc");
		List<Message> list = messageMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		}
		return HttpResult.success(list);

	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveMessage(MessageVo vo) {
		Message record=new Message();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(messageMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(messageMapper.insertSelective(record));
		}
	}

	

	@Override
	public HttpResult<Object> getNoticeList(String subJect, BaseRequestDto dto) {
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		NoticeCriteria example = new NoticeCriteria();
		com.inuol.entity.NoticeCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(dto.getDeviceType())) {
			if(DeviceType.PC.equals(dto.getDeviceType())) {
				criteria.andDeviceTypeEqualTo(dto.getDeviceType());
			}else {
				criteria.andDeviceTypeEqualTo(DeviceType.APP.getType());
			}
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectEqualTo(subJect);
		}
		example.setOrderByClause(" create_date desc");
		List<Notice> list = noticeMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);
		}
		return HttpResult.success(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveNotice(NoticeVo vo) {
		Notice record=new Notice();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(noticeMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(noticeMapper.insertSelective(record));
		}
	}



	@Override
	public HttpResult<Object> getBannerList(String type, String subJect, BaseRequestDto dto) {
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		BannerCriteria example = new BannerCriteria();
		com.inuol.entity.BannerCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(dto.getDeviceType())) {
			if(DeviceType.PC.equals(dto.getDeviceType())) {
				criteria.andDeviceTypeEqualTo(dto.getDeviceType());
			}else {
				criteria.andDeviceTypeEqualTo(DeviceType.APP.getType());
			}
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectEqualTo(subJect);
		}
		if (DataUtils.isNotEmpty(type)) {
			criteria.andTypeEqualTo(type);
		}
		example.setOrderByClause(" create_date desc");
		List<Banner> list = bannerMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Banner> pageInfo = new PageInfo<Banner>(list);
		}
		return HttpResult.success( list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveBanner(BannerVo vo) {
		Banner record=new Banner();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(bannerMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(bannerMapper.insertSelective(record));
		}
	}

	

	@Override
	public HttpResult<Object> getSubjectList(String subJect, BaseRequestDto dto) {
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		SubjectCriteria example = new SubjectCriteria();
		com.inuol.entity.SubjectCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(dto.getDeviceType())) {
			if(DeviceType.PC.equals(dto.getDeviceType())) {
				criteria.andDeviceTypeEqualTo(dto.getDeviceType());
			}else {
				criteria.andDeviceTypeEqualTo(DeviceType.APP.getType());
			}
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectEqualTo(subJect);
		}
		example.setOrderByClause(" create_date desc");
		List<Subject> list = subjectMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Subject> pageInfo = new PageInfo<Subject>(list);
		}
		return HttpResult.success(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveSubject(BannerVo vo) {
		Subject record=new Subject();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(subjectMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(subjectMapper.insertSelective(record));
		}
	}

	

	@Override
	public HttpResult<Object> getInformationList(String subJect, BaseRequestDto dto) {
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		InformationCriteria example = new InformationCriteria();
		com.inuol.entity.InformationCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(dto.getDeviceType())) {
			if(DeviceType.PC.equals(dto.getDeviceType())) {
				criteria.andDeviceTypeEqualTo(dto.getDeviceType());
			}else {
				criteria.andDeviceTypeEqualTo(DeviceType.APP.getType());
			}
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectEqualTo(subJect);
		}
		example.setOrderByClause(" create_date desc");
		List<InformationWithBLOBs> list = informationMapper.selectByExampleWithBLOBs(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<InformationWithBLOBs> pageInfo = new PageInfo<InformationWithBLOBs>(list);
		}
		return HttpResult.success(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveInformation(InformationVo vo) {
		InformationWithBLOBs record=new InformationWithBLOBs();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(informationMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(informationMapper.insertSelective(record));
		}
	}

	

	@Override
	public HttpResult<Object> getFunctionList(String name, BaseRequestDto dto) {

		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		FunctionCriteria example = new FunctionCriteria();
		com.inuol.entity.FunctionCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(name)) {
			criteria.andNameLike("%"+name+"%");
		}
		example.setOrderByClause(" sort asc");
		List<Function> list = functionMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Function> pageInfo = new PageInfo<Function>(list);
		}
		return HttpResult.success(list);
	
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateOrSaveFunction(FunctionVo vo) {
		Function record=new Function();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(functionMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(functionMapper.insertSelective(record));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> updateFunctionState(Long id, int state) {
		Function record=new Function();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(functionMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateInformationState(Long id, int state) {
		InformationWithBLOBs record = new InformationWithBLOBs();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(informationMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateSubjectState(Long id, int state) {
		Subject record = new Subject();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(subjectMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateBannerState(Long id, int state) {
		Banner record = new Banner();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(bannerMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateNoticeState(Long id, int state) {
		Notice record = new Notice();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(noticeMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateMessageState(Long id, int state) {
		Message record = new Message();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(messageMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updatePopState(Long id, int state) {
		Pop record = new Pop();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(popMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	public HttpResult<Object> getVideoList(String type,String classify,String subJect, BaseRequestDto dto) {

		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		VideoCriteria example = new VideoCriteria();
		com.inuol.entity.VideoCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(type)) {
			criteria.andTypeEqualTo(type);
		}
		if (DataUtils.isNotEmpty(classify)) {
			criteria.andClassifyEqualTo(classify);
		}
		if (DataUtils.isNotEmpty(subJect)) {
			criteria.andSubJectLike("%"+subJect+"%");
		}
		example.setOrderByClause(" create_date asc");
		List<Video> list = videoMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<Video> pageInfo = new PageInfo<Video>(list);
		}
		return HttpResult.success(list);
	
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateOrSaveVideo(VideoVo vo) {
		Video record=new Video();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(videoMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(videoMapper.insertSelective(record));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateVideoState(Long id, int state) {
		Video record = new Video();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(videoMapper.updateByPrimaryKeySelective(record));
	}

	@Override
	public HttpResult<Object> getCulturalCreationList(String backGround, String backGroundType, BaseRequestDto dto) {

		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		}
		CulturalCreationCriteria example = new CulturalCreationCriteria();
		com.inuol.entity.CulturalCreationCriteria.Criteria criteria = example.createCriteria();

		if(dto.isBeManage()) {
			criteria.andStateNotEqualTo(3);
		}else {
			criteria.andStateEqualTo(1);
		}
		
		if (DataUtils.isNotEmpty(backGround)) {
			criteria.andBackGroundLike("%"+backGround+"%");
		}
		if (DataUtils.isNotEmpty(backGroundType)) {
			criteria.andBackGroundTypeEqualTo(backGroundType);
		}
		example.setOrderByClause(" create_date asc");
		List<CulturalCreation> list = culturalCreationMapper.selectByExample(example);
		if (DataUtils.isNotEmpty(dto.getPageNum()) && DataUtils.isNotEmpty(dto.getPageSize())
				&& dto.getPageNum().intValue() > 0 && dto.getPageSize() > 0) {
			PageInfo<CulturalCreation> pageInfo = new PageInfo<CulturalCreation>(list);
		}
		return HttpResult.success(list);
	
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateOrSaveCulturalCreation(CulturalCreationVo vo) {
		CulturalCreation record=new CulturalCreation();
		BeanCopierUtils.copy(vo, record);
		if(DataUtils.isNotEmpty(record.getId())) {
			return HttpResult.retCol(culturalCreationMapper.updateByPrimaryKeySelective(record));
		}else {
			return HttpResult.retCol(culturalCreationMapper.insertSelective(record));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public HttpResult<Object> updateCulturalCreationState(Long id, int state) {
		CulturalCreation record = new CulturalCreation();
		record.setId(id);
		record.setState(state);
		return HttpResult.retCol(culturalCreationMapper.updateByPrimaryKeySelective(record));
		}
}
