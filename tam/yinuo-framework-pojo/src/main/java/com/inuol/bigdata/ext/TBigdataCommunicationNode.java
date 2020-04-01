package com.inuol.bigdata.ext;

import com.inuol.bigdata.TBigdataCommunication;
import lombok.Data;

import java.util.List;

/**
 * @author ：jias
 * @date ：2020/1/19 9:59
 */
@Data
public class TBigdataCommunicationNode extends TBigdataCommunication {

    private String keyWord; //or条件查询关键字

    private List<TBigdataCommunication> children;

    private int cut;
}
