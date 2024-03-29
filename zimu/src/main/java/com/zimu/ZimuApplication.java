package com.zimu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@SpringBootApplication(exclude = FreeMarkerAutoConfiguration.class)
@MapperScan("com.zimu.mapper")
@EnableCaching
public class ZimuApplication {

    public static void main(String[] args) {
        disableSslVerification();// 禁用
        SpringApplication.run(ZimuApplication.class, args);
    }

    private static void disableSslVerification() {
        try {

            SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        } catch (Exception e) {

        }
    }
}
