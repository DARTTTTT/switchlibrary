package com.pro.switchlibrary;

import android.os.Environment;

public class AppConfig {

  /*  public static boolean DEBUGABLE = BuildConfig.DEBUG;

    public static boolean IS_SALE_PKG = BuildConfig.SALES_PACKAGE;
    public static boolean IS_MINOR_PKG = BuildConfig.MINOR_PACKAGE;  //马甲包*/

    /*最少充值金额*/
    public static final int RECHARGE_AMOUNT_LEAST = 50;
    /*账户头像保存路径*/
    public static final String ADDRESS_OF_USERHEADPIC = Environment.getExternalStorageDirectory() + "/cainiu";

    /*账户头像保存名称*/
    public static final String NAME_OF_USERHEADPIC = "/userheadpic.jpg";


    /*签名保存路径*/
    public static final String ADDRESS_OF_HANDWRITE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lt7";
    /*签名保存名称*/
    public static final String NAME_OF_HANDWRITE = "0handwrite.jpg";

    public static final String EVENT_BUS_RELOAD_WEB = "reload";
    public static final String EVENT_BUS_REFRESH_PRODUCTLIST = "refresh";
    public static final String ID = "ID";
    public static final String BANK = "BANK";

    public static final String key_touch_id = "key_touch_id:";
    public static final String key_bank = "key_bank:";
    public static final String key_identify = "key_identify:";


    /*有盾人脸识别*/
    public static final String PUB_KEY = "7daf26e9-f100-412f-9bf6-c292b3949291";
    public static final String AUTHKEY = PUB_KEY;
    public static final String URLNOTIFY = null;
    public static final String SECURITY_KEY = "6054ab74-850e-4197-90d6-5569cfbf3d41";

    /*智齿人工客服*/
    //public static final String APPKEY = "4b637c04fa3e43ba9fd40ff12e0b8cad";

    public static final String APPKEY = "9ec98afea51647059292fa126a0e4c01";


    public static String STAY_X_LC_ID = "XIw7RaosXRo180ReKidGgR3h-gzGzoHsz";
    public static String STAY_X_LC_KEY = "kJNfsUwzguPMRBAr4J9wGi8R";
    public static String STAY_X_BMOB_APPLICATION_ID = "2bde724b56bc36566f94797f80c2853d";
    public static String STAY_X_BMOB_REST_API_KEY = "cc8bf97a0abe8afeaff6f77af48c35b7";
    public static String STAY_UM_KEY = "5bfe5375f1f55663090001d8";


}
