package com.ark.hngxt.product.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import net.sf.cglib.core.Converter;

public class BeanConverter implements Converter{

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object value, Class target, Object context) {
		 if (value instanceof Integer) {  
	            return (Integer) value;  
	        } else if (value instanceof Timestamp) {  
	            Timestamp date = (Timestamp) value;  
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return sdf.format(date);  
	        } else if (value instanceof BigDecimal) {  
	            BigDecimal bd = (BigDecimal) value;  
	            return bd.toPlainString();  
	        }  
	        return null;
	}
}
