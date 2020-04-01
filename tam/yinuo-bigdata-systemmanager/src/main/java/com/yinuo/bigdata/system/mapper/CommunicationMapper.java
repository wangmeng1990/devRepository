package com.yinuo.bigdata.system.mapper;

import com.inuol.bigdata.TBigdataCommunication;
import com.inuol.bigdata.ext.TBigdataCommunicationNode;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author ：jias
 * @date ：2020/1/16 14:31
 */
public interface CommunicationMapper extends Mapper<TBigdataCommunication> {

    public int addCommunicateBatch(@Param("communicationList") List<TBigdataCommunication> communicationList);

    /*public List<Map<String,String>> appSelectCommunicate();*/

    public List<TBigdataCommunicationNode> appSelectCommunicate();


}
