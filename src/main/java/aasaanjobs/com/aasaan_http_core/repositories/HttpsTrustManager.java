package aasaanjobs.com.aasaan_http_core.repositories;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

// TODO: Auto-generated Javadoc
/**
 * The Class HttpsTrustManager.
 */
public class HttpsTrustManager implements X509TrustManager {

    /** The Constant _AcceptedIssuers. */
    private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};
    
    /** The trust managers. */
    private static TrustManager[] trustManagers;

    /**
     * Allow all ssl.
     */
    public static void allowAllSSL() {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String hostName, SSLSession arg1) {
                return (hostName.equalsIgnoreCase("api.aasaanjobs.com")
                        || hostName.equalsIgnoreCase("maps.googleapis.com")
                        || hostName.equalsIgnoreCase("wzrkt.com")
                        || hostName.equalsIgnoreCase("static.aasaanjobs.com")
                        || hostName.equalsIgnoreCase("api.branch.io")
                        || hostName.equalsIgnoreCase("s3-ap-southeast-1.amazonaws.com")
                        || hostName.equalsIgnoreCase("graph.facebook.com"))
                        && arg1.isValid();

            }

        });

        SSLContext context = null;
        if (trustManagers == null) {
            trustManagers = new TrustManager[]{new HttpsTrustManager()};
        }

        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, trustManagers, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(context
                .getSocketFactory());
    }

    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkClientTrusted(
            java.security.cert.X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkServerTrusted(
            java.security.cert.X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {

    }

    /**
     * Checks if is client trusted.
     *
     * @param chain the chain
     * @return true, if is client trusted
     */
    public boolean isClientTrusted(X509Certificate[] chain) {
        return true;
    }

    /**
     * Checks if is server trusted.
     *
     * @param chain the chain
     * @return true, if is server trusted
     */
    public boolean isServerTrusted(X509Certificate[] chain) {
        return true;
    }

    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return _AcceptedIssuers;
    }

}