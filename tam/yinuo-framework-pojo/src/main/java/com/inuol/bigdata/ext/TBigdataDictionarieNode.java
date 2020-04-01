package com.inuol.bigdata.ext;


import com.inuol.bigdata.TBigdataDictionarie;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TBigdataDictionarieNode extends TBigdataDictionarie {

    List<TBigdataDictionarieNode> children;



}