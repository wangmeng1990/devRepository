package com.yinuo.common.utils;

import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;
/**
 * dozer是一种JavaBean的映射工具，类似于apache的BeanUtils。但是dozer更强大，它可以灵活的处理复杂类型之间的映射。
 * 不但可以进行简单的属性映射、复杂的类型映射、双向映射、递归映射等，并且可以通过XML配置文件进行灵活的配置
 * @author wangmeng
 * 效率不如BeanCopier @see com.yinuo.framework.utils.BeanCopierUtils
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}
