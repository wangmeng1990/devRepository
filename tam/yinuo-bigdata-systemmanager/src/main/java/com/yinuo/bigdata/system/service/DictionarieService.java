package com.yinuo.bigdata.system.service;

import com.inuol.bigdata.TBigdataDictionarie;
import com.inuol.bigdata.ext.TBigdataDictionarieNode;
import com.yinuo.bigdata.system.mapper.DictionarieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典管理service
 * @author ：jias
 * @date ：2020/1/13 14:27
 */
@Service
public class DictionarieService {

  @Autowired
  private DictionarieMapper dictionarieMapper;

  public List<TBigdataDictionarieNode> dictionarieList(){
      return dictionarieMapper.dictionarieList();
  }

  public int addDictionarie(TBigdataDictionarie dictionarie){

     return dictionarieMapper.insert(dictionarie);
  }

  public int deleteDictionarie(Long id){
      return dictionarieMapper.deleteByPrimaryKey(id);
  }

    public int updateDictionarie(TBigdataDictionarie dictionarie){
        return dictionarieMapper.updateByPrimaryKeySelective(dictionarie);
    }

}
