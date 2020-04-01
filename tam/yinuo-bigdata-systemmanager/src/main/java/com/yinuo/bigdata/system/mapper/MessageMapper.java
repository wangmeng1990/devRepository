package com.yinuo.bigdata.system.mapper;

import com.inuol.bigdata.TBigdataMessage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：jias
 * @date ：2020/1/15 16:23
 */
public interface MessageMapper extends Mapper<TBigdataMessage> {

    public int updateMessageType(@Param("ids") Long[] ids);

    public int deleteBatchMessage(@Param("ids") Long[] ids);

}
