package com.yinuo.common.sms;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.pool.*;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yinuo.common.common.CommonConstants;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SMSClient {
    private static Logger log = LoggerFactory.getLogger(SMSClient.class);
    private static Logger smslog = LoggerFactory.getLogger("SMSEMAY");

    // private static SMSClient instance;

    /**
      *软件序列号,请通过亿美销售人员获取
      */
    public static final String softwareSerialNo = CommonConfiguration.getValue("Emay.softwareSerialNo");
    /**
     * 序列号首次激活时自己设定
     */
    public static final String SMSkey = CommonConfiguration.getValue("Emay.key");
    /**
     * 密码,请通过亿美销售人员获取
     */
    public static final String SMSpassword = CommonConfiguration.getValue("Emay.password");
    /**
     * uri
     */
    public static final String SMSurl = CommonConfiguration.getValue("Emay.uri");
    /**
     * 开关
     */
    public static final String SMSswitch = CommonConfiguration.getValue("Emay.switch");
    public static final String SMSregistered = CommonConfiguration.getValue("Emay.registered", "true");
    /**
     * 生成一个ObjectPoolFactory实例
     * // =new SDKClientFactory();
     */
    public static SDKClientFactory objFactory;
    public static ObjectPoolFactory<SDKClient> poolFactory;
    // SDKClientFactory();
    public static ObjectPool<SDKClient> pool;
    /**
     * 开关
     */
    public static boolean EmaySwitch = false;
    static String SMS_Log_TemplateStr = "短信通道在程序启动后发送短信数:{%s},result:{%s};smsPriority:{%s};mobiles:{%s};content:{%s}";
    static String ScheduleSMS_Log_TemplateStr = "短信通道在程序启动后发送短信数:{%s},result:{%s};sendtime:{%s};mobiles:{%s};content:{%s}";
    static String Voice_Log_TemplateStr = "短信通道在程序启动后发送语音数:{%s},result:{%s};smsPriority:{%s};mobiles:{%s};content:{%s}";

    static long emay_smscount = 0;
    static long emay_voicecount = 0;
    static Object lock_smscount = new Object();
    static Object lock_voicecount = new Object();
    static Object lock_regist = new Object();

    static double emay_balance = 0;
    static Date startDate;
    static boolean registered = true;

    static {
        try {
            objFactory = new SDKClientFactory();
            poolFactory = new GenericObjectPoolFactory<SDKClient>(objFactory);
            pool = poolFactory.createPool();
            EmaySwitch = "on".equalsIgnoreCase(SMSswitch);
            if (registered) {
                try {
                    registEx(SMSpassword);
                } catch (Exception ex) {
                    log.error("短信通道注册失败:", ex);
                }
            }
            emay_balance = getBalance();
            startDate = new Date();
            smslog.info(String.format("短信通道启动。查询当前余额:%s,启动时间:%s", emay_balance
                    , DateFormatUtils.format(startDate, CommonConstants.PARTTERN_YYYY_MM_DD_HH_MM_SS)));
        } catch (Exception e) {
            log.error("短信连接池初始化失败:", e);
        }
    }

    private SMSClient() {}

    public static int chargeUp(String cardNo, String cardPass) {
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.chargeUp(softwareSerialNo, SMSkey, cardNo,
                    cardPass);

            pool.returnObject(sdkclient);
        } catch (Exception e) {
            log.error(String.format("getBalance returned:%s", value), e);
            value = -1;
        }
        return value;
    }

    public static double getBalance() {
        double value = 0.0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.getBalance(softwareSerialNo, SMSkey);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            log.error(String.format("getBalance returned:%s", value), e);
            value = -1;
        }
        return value;
    }

    public static double getEachFee() {
        double value = 0.0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.getEachFee(softwareSerialNo, SMSkey);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            log.error(String.format("getEachFee returned:%s", value), e);
            value = -1;
        }
        return value;
    }

    public static List<Mo> getMO() {
        Mo[] mo = null;
        try {
            if (EmaySwitch) {
                SDKClient sdkclient = pool.borrowObject();
                mo = sdkclient.getMO(softwareSerialNo, SMSkey);
                pool.returnObject(sdkclient);
            }
        } catch (Exception e) {
            log.error("", e);
        }

        if (null == mo) {
            return null;
        } else {
            List<Mo> molist = Arrays.asList(mo);
            return molist;
        }
    }

    public static List<StatusReport> getReport() {
        StatusReport[] sr = null;
        try {
            SDKClient sdkclient = pool.borrowObject();
            sr = sdkclient.getReport(softwareSerialNo, SMSkey);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            log.error("", e);
        }
        if (null != sr) {
            return Arrays.asList(sr);
        } else {
            return null;
        }
    }

    public static int logout() {
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();

            value = sdkclient.logout(softwareSerialNo, SMSkey);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            value = -1;
            log.error("", e);
        } finally {

        }
        return value;
    }

    public static int registDetailInfo(String eName, String linkMan,
                                       String phoneNum, String mobile, String email, String fax,
                                       String address, String postcode) {
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.registDetailInfo(softwareSerialNo, SMSkey, eName,
                    linkMan, phoneNum, mobile, email, fax, address, postcode);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            log.error(String.format("registDetailInfo returned:%s", value), e);
            value = -1;
        }
        return value;
    }

    public synchronized static int registEx(String password) {
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.registEx(softwareSerialNo, SMSkey, password);
            pool.returnObject(sdkclient);
            registered = (value == 0);
            log.info(String.format("亿美短信注册结果:{%s},Emay.switch:{%s},Emay.uri:{%s},Emay.softwareSerialNo:{%s}"
                    , value, EmaySwitch, SMSurl, softwareSerialNo));
        } catch (Exception e) {
            registered = false;
            value = -1;
            log.error(String.format("registEx returned:%s", value), e);
        }
        return value;
    }

    public static int sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority) {
        if (!EmaySwitch) {
            return 0;
        }
        long smscount = 0;
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.sendSMS(softwareSerialNo, SMSkey, "",
                    mobiles, smsContent, addSerial, "gbk", smsPriority, 0);

            if (value == 0) {
                smslog.debug(String.format(SMS_Log_TemplateStr, smscount, value, smsPriority, Arrays.toString(mobiles), smsContent));
            } else {
                smslog.warn(String.format(SMS_Log_TemplateStr, smscount, value, smsPriority, Arrays.toString(mobiles), smsContent));
            }
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            smslog.warn(String.format(SMS_Log_TemplateStr, smscount, -1, smsPriority, Arrays.toString(mobiles), smsContent));
            log.warn(String.format(SMS_Log_TemplateStr, smscount, -1, smsPriority, Arrays.toString(mobiles), smsContent), e);
            Date sendtimeDate = new Date();
            //1分钟后重发
            sendtimeDate.setMinutes(sendtimeDate.getMinutes() + 1);
            String sendTime = DateFormatUtils.format(sendtimeDate, "yyyyMMddHHmmss");
            sendScheduledSMSEx(mobiles, smsContent, sendTime, "GBK");
        }
        return 0;
    }

    public static int sendScheduledSMSEx(String[] mobiles, String smsContent,
                                         String sendTime, String srcCharset) {
        if (EmaySwitch) { return 0 ;}
        long smscount = 0;
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.sendSMS(softwareSerialNo, SMSkey, sendTime,
                    mobiles, smsContent, "", srcCharset, 3, 0);
            if (value == 0) {
                smslog.debug(String.format(ScheduleSMS_Log_TemplateStr, smscount, value, sendTime, Arrays.toString(mobiles), smsContent));
            } else {
                smslog.warn(String.format(ScheduleSMS_Log_TemplateStr, smscount, value, sendTime, Arrays.toString(mobiles), smsContent));
            }
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            smslog.warn(String.format(ScheduleSMS_Log_TemplateStr, smscount, -1, sendTime, Arrays.toString(mobiles), smsContent));
            log.warn(String.format(ScheduleSMS_Log_TemplateStr, smscount, -1, sendTime, Arrays.toString(mobiles), smsContent), e);
        }
        return 0;
    }

    public static int sendSMSEx(String[] mobiles, String smsContent,
                                String addSerial, String srcCharset, int smsPriority, long smsID) {
        if (!EmaySwitch) {return 0;}
        long smscount = 0;
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.sendSMS(softwareSerialNo, SMSkey, "", mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
            if (value == 0) {
                smslog.debug(String.format(SMS_Log_TemplateStr, smscount, value, smsPriority, Arrays.toString(mobiles), smsContent));//sms id未加入其中log

            } else {
                smslog.warn(String.format(SMS_Log_TemplateStr, smscount, value, smsPriority, Arrays.toString(mobiles), smsContent));//sms id未加入其中log
            }
            smslog.debug(String.format(SMS_Log_TemplateStr, smscount, value, smsPriority, Arrays.toString(mobiles), smsContent));//sms id未加入其中log
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            smslog.warn(String.format(SMS_Log_TemplateStr, smscount, -1, smsPriority, Arrays.toString(mobiles), smsContent));
            log.warn(String.format(SMS_Log_TemplateStr, smscount, -1, smsPriority, Arrays.toString(mobiles), smsContent), e);
            Date sendtimeDate = new Date();
            sendtimeDate.setMinutes(sendtimeDate.getMinutes() + 1);//1分钟后重发
            String sendTime = DateFormatUtils.format(sendtimeDate, "yyyyMMddHHmmss");
            sendScheduledSMSEx(mobiles, smsContent, sendTime, "GBK");
        }
        return 0;
    }

    public static int sendVoice(String[] mobiles, String smsContent,
                                String addSerial, String srcCharset, int smsPriority, long smsID) {
        if (!EmaySwitch) {return 0;}
        long voicecount = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            String value = sdkclient.sendVoice(softwareSerialNo, SMSkey, "", mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
            smslog.debug(String.format(Voice_Log_TemplateStr, emay_voicecount, value, smsPriority, Arrays.toString(mobiles), smsContent));//sms id未加入其中log
            if (value.startsWith("0")) {
                smslog.debug(String.format(Voice_Log_TemplateStr, voicecount, -1, smsPriority, Arrays.toString(mobiles), smsContent));
            } else {
                smslog.warn(String.format(Voice_Log_TemplateStr, voicecount, -1, smsPriority, Arrays.toString(mobiles), smsContent));
            }
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            smslog.warn(String.format(Voice_Log_TemplateStr, voicecount, -1, smsPriority, Arrays.toString(mobiles), smsContent), e);
            log.warn(String.format(Voice_Log_TemplateStr, voicecount, -1, smsPriority, Arrays.toString(mobiles), smsContent), e);

        }
        return 0;
    }

    public static int serialPwdUpd(String serialPwd, String serialPwdNew) {
        int value = 0;
        try {
            SDKClient sdkclient = pool.borrowObject();
            value = sdkclient.serialPwdUpd(softwareSerialNo, SMSkey, serialPwd,
                    serialPwdNew);
            pool.returnObject(sdkclient);
        } catch (Exception e) {
            value = -1;
            log.error("", e);
        }
        return value;
    }


    public static void close() {
        double endBalance = getBalance();
        Date stopDate = new Date();
        try {
            pool.close();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            synchronized (lock_regist) {
                registered = false;
            }
            smslog.info(String.format("sms通道关闭。短信数目:%s,语音短信数目:%s,启动时间: %s,启动时余额:%s，关闭时间:%s,关闭时余额:%s",
                    emay_smscount, emay_voicecount, DateFormatUtils.format(startDate, CommonConstants.PARTTERN_YYYY_MM_DD_HH_MM_SS)
                    , emay_balance, DateFormatUtils.format(stopDate, CommonConstants.PARTTERN_YYYY_MM_DD_HH_MM_SS), endBalance));
        }
    }
}