package com.yinuo.bigdata.system.mapper;


import com.inuol.bigdata.TBigdataDictionarie;
import com.inuol.bigdata.ext.TBigdataDictionarieNode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：jias
 * @date ：2020/1/13 14:27
 */
public interface DictionarieMapper extends Mapper<TBigdataDictionarie> {

   public List<TBigdataDictionarieNode> dictionarieList();


}
