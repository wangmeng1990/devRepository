package com.yinuo.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.ErrCodeAndMsg;

import net.sf.cglib.beans.BeanCopier;
/**
 * 对象属性名和类型需一致，否则会忽略不一致的属性
 * @author wangmeng
 *
 */
public class BeanCopierUtils {
	
	/**
	 * 单例, 避免重复创建BeanCopier消耗资源.
	 * BeanCopier.create主要耗时方法
	 */
	public static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();
	
	public static void copy(Object source, Object target){
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if(!beanCopierMap.containsKey(beanKey)){
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		}else{
			copier = beanCopierMap.get(beanKey);
		}
			copier.copy(source, target, null);
	}
	
	public static <T> List<T> copyList(Collection slist, Class<T> target){
		if(DataUtils.isNotEmpty(slist)) {
			List<T> tlist=new ArrayList<T>(slist.size());
			for(Object s:slist) {
				T t;
				try {
					t = target.newInstance();
					copy(s, t);
					tlist.add(t);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new BusinessException(ErrCodeAndMsg.FAIL);
				}
			}
			return tlist;
		}else {
			return new ArrayList<T>();
		}
	}
	private static String generateKey(Class<?> class1,Class<?>class2){
		return class1.toString() +":"+ class2.toString();
	}
}