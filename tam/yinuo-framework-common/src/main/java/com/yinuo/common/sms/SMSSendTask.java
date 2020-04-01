/**
 * 
 */
package com.yinuo.common.sms;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 */

public class SMSSendTask {
	private static SMSSendTask smsSendTask = new SMSSendTask();

	private AsyncTaskExecutor taskExecutor;

	private int smsPriority = 3;
	private long smsID = 0;

	private SMSSendTask(){}

	public static  SMSSendTask getInstance(){
		return smsSendTask;
	}

	public SMSSendTask setTaskExecutor(AsyncTaskExecutor taskExecutor){
		if(this.taskExecutor == null){
			this.taskExecutor = taskExecutor;
		}
		return this;
	}

	/**
	 * 文本短信
	 * @param mobiles
	 * @param smsContent
     * @return
     */
	public int doMsgJob(final String[] mobiles, final String smsContent) {
		return doMsgJob(mobiles,smsContent,"",5);
	}

	/**
	 * 文本短信
	 * @param mobiles
	 * @param smsContent
	 * @param addSerial
	 * @param smsPriority
     * @return
     */
	public int doMsgJob(final String[] mobiles, final String smsContent, final String addSerial, final int smsPriority) {
		Future<Integer> result = taskExecutor.submit(new Callable<Integer>(){
			public Integer call() throws Exception {
//				return SMSClient.sendSMS(mobiles, smsContent, addSerial, smsPriority);
				return SMSUtils.sendSMS(mobiles, smsContent);
			}
		});
		try {
			return result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 语音信息
	 * @param mobiles
	 * @param smsContent
     * @return
     */
	public int doVoiceJob(final String[] mobiles,final String smsContent){
		return doVoiceJob(mobiles,smsContent,"",smsPriority,smsID);
	}

	/**
	 * 语音信息
	 * @param mobiles
	 * @param smsContent
	 * @return
	 */
	public int doVoiceJob(final String[] mobiles,final String smsContent,
						  final String addSerial, final int smsPriority, final long smsID){
		Future<Integer> result = taskExecutor.submit(new Callable<Integer>(){
			public Integer call() throws Exception {
				return SMSClient.sendVoice(mobiles,smsContent,addSerial,"GBK",smsPriority,smsID);
			}
		});

		try {
			return result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return -1;
	}
}