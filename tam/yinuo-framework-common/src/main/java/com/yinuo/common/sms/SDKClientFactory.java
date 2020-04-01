/**
 * 
 */
package com.yinuo.common.sms;

import org.apache.commons.pool.BasePoolableObjectFactory;


/**
 *
 */
public class SDKClientFactory  extends BasePoolableObjectFactory<SDKClient> {

	@Override
    public SDKClient makeObject() throws Exception {  
		return new SDKServiceLocator().getSDKService();
    }
}
