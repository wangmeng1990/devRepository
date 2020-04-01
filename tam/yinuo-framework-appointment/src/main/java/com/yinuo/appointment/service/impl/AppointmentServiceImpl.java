package com.yinuo.appointment.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inuol.dto.AppointmentDto;
import com.inuol.dto.CompanionDto;
import com.inuol.dto.CompanionHisDto;
import com.inuol.entity.AppointmentDate;
import com.inuol.entity.AppointmentDateCriteria;
import com.inuol.entity.AppointmentDateItem;
import com.inuol.entity.AppointmentDateItemCriteria;
import com.inuol.entity.TouristAppointment;
import com.inuol.entity.TouristAppointmentCriteria;
import com.inuol.entity.TouristCompanion;
import com.inuol.entity.TouristCompanionCriteria;
import com.inuol.entity.TouristCompanionHis;
import com.inuol.entity.TouristCompanionHisCriteria;
import com.inuol.entity.TouristCompanionHisCriteria.Criteria;
import com.inuol.entity.Weather;
import com.inuol.entity.WeatherCriteria;
import com.inuol.user.User;
import com.inuol.vo.AppointmentDateItemVo;
import com.inuol.vo.AppointmentDateVo;
import com.inuol.vo.AppointmentScenicVo;
import com.inuol.vo.CompanionHisVo;
import com.yinuo.api.user.UserApi;
import com.yinuo.appointment.mapper.AppointmentDateItemMapper;
import com.yinuo.appointment.mapper.AppointmentDateMapper;
import com.yinuo.appointment.mapper.TouristAppointmentMapper;
import com.yinuo.appointment.mapper.TouristCompanionHisMapper;
import com.yinuo.appointment.mapper.TouristCompanionMapper;
import com.yinuo.appointment.mapper.WeatherMapper;
import com.yinuo.appointment.service.AppointmentService;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.common.ListResult;
import com.yinuo.common.utils.BeanCopierUtils;
import com.yinuo.common.utils.DataUtils;
import com.yinuo.common.utils.DateUtils;
@Service
public class AppointmentServiceImpl implements AppointmentService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static Integer remain=0;
	@Autowired
	private TouristAppointmentMapper touristAppointmentMapper;
	@Autowired
	private TouristCompanionMapper touristCompanionMapper;
	@Autowired
	private TouristCompanionHisMapper touristCompanionHisMapper;
	@Autowired
	private AppointmentDateMapper appointmentDateMapper;
	@Autowired
	private AppointmentDateItemMapper appointmentDateItemMapper;
	@Autowired
	WeatherMapper weatherMapper;
	@Autowired
	private UserApi userApi;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	private Lock  lock=new ReentrantLock(); 
	@SuppressWarnings("unchecked")
	@Override
	public HttpResult<ListResult<TouristAppointment>> getAppointmentList(AppointmentDto dto) {

		TouristAppointmentCriteria example = new TouristAppointmentCriteria();
		com.inuol.entity.TouristAppointmentCriteria.Criteria criteria = example.createCriteria();

		if (null != dto.getUserId() && dto.getUserId() > 0) {
			criteria.andUidEqualTo(dto.getUserId());
		}
		if (DataUtils.isNotEmpty(dto.getMobile())) {
			criteria.andMobileLike("%" + dto.getMobile() + "%");
		}
		if (DataUtils.isNotEmpty(dto.getName())) {
			criteria.andNameLike("%" + dto.getName() + "%");
		}
		if (DataUtils.isNotEmpty(dto.getSerialNo())) {
			criteria.andSerialNoLike("%" + dto.getSerialNo() + "%");
		}
		if (DataUtils.isNotEmpty(dto.getCreateDateStart())) {
			criteria.andCreateDateGreaterThanOrEqualTo(DateUtils.string2Date(dto.getCreateDateStart()));
		}
		if (DataUtils.isNotEmpty(dto.getCreateDateEnd())) {
			criteria.andCreateDateLessThanOrEqualTo(DateUtils.string2Date(dto.getCreateDateEnd()));
		}
		if (DataUtils.isNotEmpty(dto.getPlayDate())) {
			criteria.andPlayDateEqualTo(dto.getPlayDate());
		}
		if (DataUtils.isNotEmpty(dto.getStatus())) {
			criteria.andStatusEqualTo(Integer.valueOf(dto.getStatus()));
		}
		if(DataUtils.isNotEmpty(dto.getDeviceType())) {
			criteria.andTypeEqualTo(dto.getDeviceType());
		}
		example.setOrderByClause(" create_date desc");
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		List<TouristAppointment> list = touristAppointmentMapper.selectByExample(example);
		PageInfo<TouristAppointment> pageInfo = new PageInfo<TouristAppointment>(list);
		return HttpResult.success(pageInfo.getTotal(), list);
	}
	@Override
	public HttpResult<List<TouristCompanion>> getCompanionList(CompanionDto dto) {
		TouristCompanionCriteria example=new TouristCompanionCriteria();
		example.createCriteria().andUidEqualTo(dto.getUid()).andAppointmentIdEqualTo(dto.getId());
		example.setOrderByClause("create_date asc");
		HttpResult<List<TouristCompanion>> rest=new HttpResult<List<TouristCompanion>>();
		rest.setData(touristCompanionMapper.selectByExample(example));
		return rest;
	}
	@Override
	public HttpResult<List<TouristCompanionHis>> getCompanionHisList(CompanionHisDto dto) {
		TouristCompanionHisCriteria example=new TouristCompanionHisCriteria();
		example.createCriteria().andUidEqualTo(dto.getUid());
		example.setOrderByClause("create_date asc");
		touristCompanionHisMapper.selectByExample(example);
		HttpResult<List<TouristCompanionHis>> rest=new HttpResult<List<TouristCompanionHis>>();
		rest.setData(touristCompanionHisMapper.selectByExample(example));
		return rest;
	}
	@Override
	public HttpResult<Object> addCompanion(@Valid CompanionHisVo vo) {
		// 新增或修改校验重复数据
		TouristCompanionHisCriteria example = new TouristCompanionHisCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andIdTypeEqualTo(vo.getIdType()).andIdCardEqualTo(vo.getIdCard());
		if (null != vo.getId() && vo.getId() > 0) {
			criteria.andIdNotEqualTo(vo.getId());
		}
		if (touristCompanionHisMapper.countByExample(example) > 0) {
			return HttpResult.failure(null, "已存在相同证件类型，证件号码的信息");
		}
		int ret=0;
		TouristCompanionHis record = new TouristCompanionHis();
		BeanCopierUtils.copy(vo, record);
		if (null != vo.getId() && vo.getId() > 0) {
			record.setUpdateDate(new Date());
			touristCompanionHisMapper.updateByPrimaryKeySelective(record);
		}else {
			 ret = touristCompanionHisMapper.insertSelective(record);
		}
		return HttpResult.retCol(ret);
	}
	@SuppressWarnings("unused")
	@Override
	public HttpResult<List<AppointmentDateVo>> getAppointment(Integer scenicId, String playDate) {
		
		AppointmentDateCriteria example=new AppointmentDateCriteria();
		example.createCriteria().andScenicIdEqualTo(scenicId).andStatusEqualTo(1);
		example.setOrderByClause(" effective_date asc");
		List<AppointmentDate> dateList=appointmentDateMapper.selectByExample(example);
		
		if(DataUtils.isEmpty(dateList)) {
			return HttpResult.failure(null, "请刷新或稍后再操作");
		}
		
		LocalDateTime  localDateTime=LocalDateTime.now();
		LocalDate localDate=localDateTime.toLocalDate();
		
		AppointmentDate preDate=findDate(dateList,localDate.toString(),1);//前一规则生效时间
		AppointmentDate nextDate=findDate(dateList,localDate.toString(),2);//后一规则生效时间
		
		Map<Long,Object> dateMap=new HashMap<Long, Object>();
		dateMap.put(preDate.getId(), preDate);
		dateMap.put(nextDate.getId(), nextDate);
		
		if(null==preDate) {
			return HttpResult.failure("请刷新或稍后再操作");//还没有可以约的日期
		}
		
		List<AppointmentDateVo> dateVoList=new ArrayList<AppointmentDateVo>();
		
			dateVoList=getPlayDateList(preDate,nextDate, localDate);
			
			List<String> playDateList=new ArrayList<String>();
			for(AppointmentDateVo v:dateVoList) {
				playDateList.add(v.getPlayDate());
			}
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("dateList", playDateList);
			paramMap.put("scenicId", scenicId);
			List<Long> dateidList=new ArrayList<Long>();
			dateidList.add(preDate.getId());
			dateidList.add(nextDate.getId());
			paramMap.put("dateIdList", dateidList);
			
			List<Map<String, Object>> scenicAppointmentList=appointmentDateMapper.queryScenicAppointment(paramMap);
			if(DataUtils.isEmpty(scenicAppointmentList)) {
				return HttpResult.failure(null, "请刷新或稍后再操作");
			}
			
			for(int i=0;i<dateVoList.size();i++) {
				dateVoList.get(i).setRemain(getReMain(dateVoList.get(i),scenicAppointmentList));
				if(DataUtils.isNotEmpty(playDate)) {
					if(dateVoList.get(i).getPlayDate().equals(playDate)) {
						setAppointmentDateItemVo(scenicId,dateVoList.get(i),localDateTime);
					}
				}else if(i==0){
					setAppointmentDateItemVo(scenicId,dateVoList.get(i),localDateTime);
				}
			}
		
		return HttpResult.success(dateVoList);
	}
	
	/***
	 * 
	 * @param dateList 已按时间升序排列
	 * @param localDate
	 * @param type
	 * @return
	 */
	private AppointmentDate findDate(List<AppointmentDate> dateList, String localDate, int type) {
		AppointmentDate date=null;
		if(type==1) {//从 dateList 找小于等于 localDate的最大值
			for(AppointmentDate d:dateList) {
				if(localDate.compareTo(d.getEffectiveDate())<0) {
					break;
				}else {
					date=d;
				}
			}
		}else {//从 dateList 找大于等于 localDate的最小值
			for(AppointmentDate d:dateList) {
				if(localDate.compareTo(d.getEffectiveDate())<0) {
					date=d;
					break;
				}
			}
		}
		return date;
	}
	
	private Integer getReMain(AppointmentDateVo vo,List<Map<String, Object>> list) {
		
		BigDecimal num=new BigDecimal(0);//景点当前时间可预约量总量
		
		for(Map<String,Object> m:list) {
			if(vo.getDateId()==(Integer)m.get("dateId")&&num.intValue()==0) {
				num=(BigDecimal)m.get("num");
			}
			if(vo.getPlayDate().equals((String)m.get("playDate"))&&vo.getDateId()==(Integer)m.get("dateId")) {
				BigDecimal companion = ((BigDecimal)m.get("companion"));
				return num.intValue()-companion.intValue();//景点当前可预约量=景点当前时间可预约量总量-已预约量
			}
		}
		return num.intValue();
	}
	private List<AppointmentDateVo> getPlayDateList(AppointmentDate preDate,AppointmentDate nextDate, LocalDate localDate) {
		List<AppointmentDateVo> playDateList=new ArrayList<AppointmentDateVo>();
		
		for(int i=0;i<preDate.getPeriod();i++) {
			AppointmentDateVo vo=new AppointmentDateVo();
			String date = localDate.plusDays(i).toString();
			if(null!=nextDate) {
				if(date.compareTo(nextDate.getEffectiveDate())<0) {
					vo.setDateId(preDate.getId());
					vo.setPlayDate(date);
					playDateList.add(vo);
				}else {
					break;
				}
			}else {
				vo.setDateId(preDate.getId());
				vo.setPlayDate(date);
				playDateList.add(vo);
			}
			
		}
		//计算下一规则天数
		//新规则可预约到的时间
		if(null!=nextDate) {
         for(int i=0;i<nextDate.getPeriod();i++) {
        	 AppointmentDateVo vo=new AppointmentDateVo();
        	 String date = localDate.plusDays(i).toString();
        	 if(date.compareTo(nextDate.getEffectiveDate())>=0) {
        		 vo.setDateId(nextDate.getId());
 				 vo.setPlayDate(date);
 				 playDateList.add(vo);
        	 }
         }
		}
		return playDateList;
	}
	private void setAppointmentDateItemVo(Integer scenicId,AppointmentDateVo vo,LocalDateTime localDateTime) {
		
		AppointmentDateItemCriteria example=new AppointmentDateItemCriteria();
		example.createCriteria().andScenicIdEqualTo(scenicId).andDateIdEqualTo((int)vo.getDateId());
		example.setOrderByClause(" sort asc");
		List<AppointmentDateItem> dateitemList=appointmentDateItemMapper.selectByExample(example);
		
		
		for(AppointmentDateItem item:dateitemList) {
			AppointmentDateItemVo dateItemVo=new AppointmentDateItemVo();
			dateItemVo.setItemId(item.getId());
			dateItemVo.setTimeQuantum(item.getTimeQuantum());
			dateItemVo.setExpired(beExpired(localDateTime,item.getTimeQuantum(),vo.getPlayDate()));
			vo.getDateItemVo().add(dateItemVo);
		}
	}
	private String beExpired( LocalDateTime localDateTime, String timeQuantum, String playDate) {
		
		String[] quantum = timeQuantum.split("-");
		String start = quantum[0];
		String end = quantum[1];
		
		String localTime=localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
		
		if(playDate.equals(localDateTime.toLocalDate().toString())) {//预订今天的需要校验时间段是否过期
			if(localTime.compareTo(end)>=0&&localTime.compareTo(start)>0) {//未起售
				return "1";
			}
		}
		return "";
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<String> doAppointment(Integer scenicId, long userId, String playDate,
			String companion, Integer itemId,String deviceType) {
		
		AppointmentDateItem dateItem = appointmentDateItemMapper.selectByPrimaryKey((long)itemId);
		String beExpired=beExpired(LocalDateTime.now(), dateItem.getTimeQuantum(), playDate);
		if("1".equals(beExpired)) {//所选时间段已过期
			return HttpResult.failure("请刷新或稍后再操作");
		}
		User user = userApi.getUserById(userId);
		if(null==user) {
			return HttpResult.failure("当前登陆用户无效");
		}
		checkAppointment(user,playDate,dateItem);//相同的游玩时段，一个用户只能预约一次
		checkCompanion(companion);//参观人重复校验
		lock.lock();
		try {
			List<TouristCompanion> companionList = JSONArray.parseArray(companion, TouristCompanion.class);
			HttpResult<AppointmentDateItemVo> item = getAppointmentItem(itemId, playDate);
			AppointmentDateItemVo itemVo = item.getData();
			if (null != itemVo && itemVo.getItemId() > 0) {
				 remain = itemVo.getNum();
					if (remain<=0||remain - companionList.size() <= 0) {
						return HttpResult.failure("当前时段余票不足，请选择其他时段预约");
					}
			}
			createAppointment(scenicId, companion, playDate, dateItem.getTimeQuantum(), deviceType,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("预约失败,请稍后重试");
		} finally {
			lock.unlock();
		}
		//更新常用人信息
	    updateCompanionHis(userId,companion,user);
		return HttpResult.success();
	}
	private void checkAppointment(User user, String playDate, AppointmentDateItem dateItem) {
		TouristAppointmentCriteria example=new TouristAppointmentCriteria();
		example.createCriteria().andUidEqualTo(user.getId()).andPlayDateEqualTo(playDate).andTimeQuantumEqualTo(dateItem.getTimeQuantum()).andStatusEqualTo(0);
		if(touristAppointmentMapper.countByExample(example)>0) {
			throw new BusinessException("以预约该游玩时段,请勿重复预约");
		}
	}
	private void checkCompanion(String companion) {
		List<TouristCompanion> tempList=new ArrayList<TouristCompanion>();
		List<TouristCompanion> companionList = JSONArray.parseArray(companion, TouristCompanion.class);
		for(TouristCompanion c:companionList) {
			 String idType=c.getIdType();
			 String idCard= c.getIdCard();
			if(DataUtils.isNotEmpty(tempList)) {
				 for(TouristCompanion h:tempList) {//通过证件类型和证件号码确定是否新的常用人
					 if(idType.equals(h.getIdType())&&idCard.equals(h.getIdCard())) {
						throw new BusinessException("存在相同的参观者,请确认后再尝试");
					 }
				 } 
				 tempList.add(c);
			}else {
				tempList.add(c);
			}
		}
	}
	private void  createAppointment(Integer scenicId, String companion, String playDate, String timeQuantum,
			String deviceType,User user) {
        List<TouristCompanion> companionList=JSONArray.parseArray(companion, TouristCompanion.class);
		TouristAppointment record=new TouristAppointment();
		record.setName(user.getRealityName());
		record.setSerialNo(serialNoGenerator());
		record.setCompanion(companionList.size());
		record.setMobile(user.getPhone());
		record.setPlayDate(playDate);
		record.setScenicId(Long.valueOf(scenicId));
		record.setStatus(0);
		record.setTimeQuantum(timeQuantum);
		record.setUid(user.getId());
		record.setType(deviceType);
		int reta=touristAppointmentMapper.insertSelective(record);
		if(reta<=0) {
			throw new BusinessException("请刷新或稍后再操作");
		}
		
		for(TouristCompanion t:companionList) {
			t.setUid(user.getId().intValue());
			t.setAppointmentId(record.getId());
			int retc=touristCompanionMapper.insertSelective(t);
			if(retc<=0) {
				throw new BusinessException("请刷新或稍后再操作");
			}
		}
	}
	
	private synchronized String serialNoGenerator() {
		String localDate=LocalDate.now().toString().replaceAll("-", "");
		String key="serialNo:"+localDate;
		RedisAtomicInteger entityIdCounter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
		String num=getSerialNo(entityIdCounter.incrementAndGet());
		String serialNo="CHNM"+localDate+num;
		return serialNo;
	}
	private String getSerialNo(int cacheNo) {
		String cacheNoStr=String.valueOf(cacheNo);
		if(cacheNoStr.length()<6) {
			int count=6-cacheNoStr.length();
			for(int i=0;i<count;i++) {
				cacheNoStr="0"+cacheNoStr;
			}
		}
		return cacheNoStr;
	}
	private void updateCompanionHis(long userId, String companion,User user) {
		 List<TouristCompanion> companionList=JSONArray.parseArray(companion, TouristCompanion.class);
		 TouristCompanionHisCriteria example=new TouristCompanionHisCriteria();
		 List<TouristCompanionHis> hisList=touristCompanionHisMapper.selectByExample(example);
		 
		 for(TouristCompanion c:companionList) {
			 String idType=c.getIdType();
			 String idCard= c.getIdCard();
			 boolean repeat=false;//是否存在重复的常用人
			 if(DataUtils.isNotEmpty(hisList)) {
				 for(TouristCompanionHis h:hisList) {//通过证件类型和证件号码确定是否新的常用人
					 if(idType.equals(h.getIdType())&&idCard.equals(h.getIdCard())) {
						 repeat=true;
						 break;
					 }
				 } 
				 if(!repeat) {
					 //新增新的常用人
					 TouristCompanionHis newHis=new TouristCompanionHis();
					 BeanCopierUtils.copy(c, newHis);
					 newHis.setUid(user.getId().intValue());
					 //当前登录用户已实名认证的，证件类型和证件号与当前实名用户相同的标记为本人
					 if(null!=user.getBeAuth()&&user.getBeAuth().intValue()==1&&user.getIdType().equals(idType)&&user.getIdCard().equals(idCard)) {
						 newHis.setBeSelf(1);
					 }
					 touristCompanionHisMapper.insertSelective(newHis);
				 }
			 }else {
				 //新增新的常用人
				 TouristCompanionHis newHis=new TouristCompanionHis();
				 BeanCopierUtils.copy(c, newHis);
				 newHis.setUid(user.getId().intValue());
				 //当前登录用户已实名认证的，证件类型和证件号与当前实名用户相同的标记为本人
				 if(null!=user.getBeAuth()&&user.getBeAuth().intValue()==1&&user.getIdType().equals(idType)&&user.getIdCard().equals(idCard)) {
					 newHis.setBeSelf(1);
				 }
				 touristCompanionHisMapper.insertSelective(newHis);
			 }
		 }
	}
	@Override
	public HttpResult<AppointmentDateItemVo> getAppointmentItem(long itemId,String playDate) {
		
		AppointmentDateItem dateItem = appointmentDateItemMapper.selectByPrimaryKey(itemId);
		if(null==dateItem||null==dateItem.getId()) {
			return HttpResult.failure("请刷新或稍后再试");
		}
		
		Integer num = dateItem.getNum();//该时段可预约量
		
		TouristAppointmentCriteria example = new TouristAppointmentCriteria();
		example.createCriteria().andScenicIdEqualTo((long)dateItem.getScenicId()).andPlayDateEqualTo(playDate).
		andTimeQuantumEqualTo(dateItem.getTimeQuantum()).andStatusEqualTo(0);
		List<TouristAppointment> list=touristAppointmentMapper.selectByExample(example);
		
		Integer bookNum=0;
		for(TouristAppointment m:list) {
			bookNum=bookNum+m.getCompanion();
		}
		AppointmentDateItemVo dateItemVo=new AppointmentDateItemVo();
		dateItemVo.setItemId(dateItem.getId());
		dateItemVo.setBookNum(bookNum);
		dateItemVo.setNum(num-bookNum);
		dateItemVo.setTimeQuantum(dateItem.getTimeQuantum());
		
		return HttpResult.success(dateItemVo);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly = false)
	public HttpResult<Object> cancelAppointment(TouristAppointment ta) {
		ta.setUpdateDate(new Date());
		int ret=touristAppointmentMapper.updateByPrimaryKeySelective(ta);
		if(ret<=0) {
			return HttpResult.success("操作失败");
		}
		return HttpResult.success("已取消");
	}
	@Override
	public HttpResult<List<AppointmentScenicVo>> getAppointmentScenic() {
		List<AppointmentScenicVo> list=touristAppointmentMapper.getAppointmentScenic();
		return HttpResult.success(list);
	}
	@Override
	public HttpResult<Weather> getWeather() {
		WeatherCriteria example=new WeatherCriteria();
		List<Weather> list=weatherMapper.selectByExample(example);
		if(DataUtils.isNotEmpty(list)) {
			return HttpResult.success(list.get(0));
		}
		return HttpResult.success();
	}
}
