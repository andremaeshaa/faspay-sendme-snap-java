package id.co.faspay.snap.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import id.co.faspay.snap.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration for the Faspay SendMe Snap API SDK.
 * This class holds all the necessary configuration parameters for connecting to the Faspay API.
 */
public class FaspaySnapConfig extends Constants {
    private static final Logger logger = LoggerFactory.getLogger(FaspaySnapConfig.class);

    protected String baseUrl;
    private final String partnerId;
    private final String privateKey;
    private final String sslCertPath;
    private SSLContext sslContext;
    private X509TrustManager trustManager;

    /**
     * Creates a new configuration with the specified parameters.
     * By default, the environment is set to "sandbox".
     *
     * @param partnerId The partner ID for authentication
     * @param privateKey The private key for signing requests
     * @param sslCertPath The path to the SSL certificate file
     */
    public FaspaySnapConfig(String partnerId, String privateKey, String sslCertPath) {
        this.partnerId = Objects.requireNonNull(partnerId, "partnerId must not be null");
        this.privateKey = Objects.requireNonNull(privateKey, "privateKey must not be null");
        this.sslCertPath = Objects.requireNonNull(sslCertPath, "sslCertPath must not be null");

        // Set default environment to sandbox
        setEnv(null);

        initSslContext();
    }

    /**
     * Sets the environment for the API calls.
     * If env is null or not recognized, defaults to "sandbox".
     *
     * @param env The environment to use ("sandbox" or "production")
     */
    public void setEnv(String env) {
        if (env == null) {
            this.baseUrl = Constants.baseUrlSandbox;
            return;
        }

        switch (env) {
            case "production":
                this.baseUrl = Constants.baseUrlProd;
                break;
            case "sandbox":
            default:
                this.baseUrl = Constants.baseUrlSandbox;
                break;
        }
    }

    /**
     * Initializes the SSL context with the provided certificate.
     */
    private void initSslContext() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            // Load the certificate
            try (InputStream is = new FileInputStream(new File(sslCertPath))) {
                System.out.println("Loading certificate from: " + is.toString());

                X509Certificate cert = loadCertificate(is);
                keyStore.setCertificateEntry("faspay-cert", cert);
            }

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);

            TrustManager[] trustManagers = tmf.getTrustManagers();
            this.trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, null);
            this.sslContext = sslContext;
        } catch (Exception e) {
            logger.error("Failed to initialize SSL context", e);
            throw new RuntimeException("Failed to initialize SSL context", e);
        }
    }

    /**
     * Loads an X.509 certificate from the given input stream.
     * This method supports PEM-encoded X.509 certificates.
     *
     * @param is The input stream containing the certificate
     * @return The loaded X.509 certificate
     * @throws CertificateException If the certificate cannot be loaded
     * @throws IOException If an I/O error occurs
     */
    private X509Certificate loadCertificate(InputStream is) throws CertificateException, IOException {
        try {
            java.security.cert.CertificateFactory cf = java.security.cert.CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(is);
        } catch (CertificateException e) {
            logger.error("Failed to load certificate: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the base URL of the Faspay API.
     *
     * @return The base URL
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Gets the partner ID for authentication.
     *
     * @return The partner ID
     */
    public String getPartnerId() {
        return partnerId;
    }

    public String getExternalId() {
        long miliseconds = Math.round((double) System.nanoTime() / 1_000_000);
        Random random = new Random();
        int randomNum = random.nextInt(1000);

        return String.format("%s%d%d", partnerId, miliseconds, randomNum);
    }

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        return ZonedDateTime.now().format(formatter);
    }

    /**
     * Gets the private key for signing requests.
     *
     * @return The private key
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Gets the path to the SSL certificate file.
     *
     * @return The SSL certificate path
     */
    public String getSslCertPath() {
        return sslCertPath;
    }

    /**
     * Gets the SSL context initialized with the provided certificate.
     *
     * @return The SSL context
     */
    public SSLContext getSslContext() {
        return sslContext;
    }

    /**
     * Gets the trust manager initialized with the provided certificate.
     *
     * @return The trust manager
     */
    public X509TrustManager getTrustManager() {
        return trustManager;
    }
}
