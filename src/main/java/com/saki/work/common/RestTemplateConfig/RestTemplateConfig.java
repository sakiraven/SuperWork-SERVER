package com.saki.work.common.RestTemplateConfig;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;

@Configuration
public class RestTemplateConfig {

    private int readTimeout = 5000;
    private int connectionTimeout = 5000;

    @Bean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //设置过期时间
        factory.setConnectionRequestTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        final SSLContextBuilder builder = new SSLContextBuilder();
        try {
            //全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, (X509Certificate[] x509Certificate, String s) -> true);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        } catch (KeyStoreException e) {
            throw e;
        }
        SSLConnectionSocketFactory socketFactory = null;
        try {
            socketFactory = new SSLConnectionSocketFactory(builder.build(), new String[]{"TLSv1", "TLSv1.2","SSLv3","SSLv2Hello"}, null, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        //为自定义连接器注册http与https
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager(registry);
        phccm.setMaxTotal(500);
        final CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                .setConnectionManager(phccm)
                .setConnectionManagerShared(true)
                .setRetryHandler(new CustomRequestRetryHandler(3))
                .build();

        factory.setHttpClient(httpClient);
        final RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
    public static class CustomRequestRetryHandler extends DefaultHttpRequestRetryHandler {
        /*protected static final Collection<Class<? extends IOException>> ignoredExceptions =
                Arrays.asList(UnknownHostException.class, SSLException.class);*/

        protected static final Collection<Class<? extends IOException>> ignoredExceptions =
                Arrays.asList(ConnectTimeoutException.class, UnknownHostException.class);

        public CustomRequestRetryHandler(final int retryCount) {
            super(retryCount, true, ignoredExceptions);
        }
    }
}
