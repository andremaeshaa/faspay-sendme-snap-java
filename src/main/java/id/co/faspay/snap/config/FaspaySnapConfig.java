package id.co.faspay.snap.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
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
 * The FaspaySnapConfig class is responsible for managing the configuration
 * for interacting with the Faspay API. This includes managing environment-specific
 * base URLs, partner authentication details, SSL configurations, and utility
 * methods for generating unique identifiers and timestamps.
 * This class extends the Constants class to utilize predefined URLs and constants.
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
     * Initializes the SSL context using the provided SSL certificate file path. This method:
     * - Loads an X.509 certificate from the file specified by the sslCertPath field.
     * - Creates a key store and adds the loaded certificate to it.
     * - Configures a TrustManagerFactory with the key store.
     * - Initializes an SSLContext using the created trust managers.
     * -
     * If the initialization fails due to any exceptions, an error is logged and a runtime exception is thrown.
     * -
     * This method is intended to ensure secure communication by properly setting up
     * the SSL context and trust managers before making API calls.
     *
     * @throws RuntimeException If the SSL context initialization fails
     */
    private void initSslContext() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            // Load the certificate
            try (InputStream is = new FileInputStream(sslCertPath)) {
                System.out.println("Loading certificate from: " + is);

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
     * Loads an X.509 certificate from the provided input stream.
     *
     * @param is The input stream containing the certificate data.
     * @return An X509Certificate object representing the loaded certificate.
     * @throws CertificateException If the certificate cannot be parsed or is invalid.
     */
    private X509Certificate loadCertificate(InputStream is) throws CertificateException {
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

    /**
     * Generates a unique external ID which combines the partner ID, current timestamp,
     * and a random number.
     *
     * @return A unique external identifier in the format of partner ID concatenated
     *         with the current timestamp in milliseconds and a random integer.
     */
    public String getExternalId() {
        long miliseconds = Math.round((double) System.nanoTime() / 1_000_000);
        Random random = new Random();
        int randomNum = random.nextInt(1000);

        return String.format("%s%d%d", partnerId, miliseconds, randomNum);
    }

    /**
     * Generates a timestamp in the ISO 8601 format with time zone (e.g., yyyy-MM-dd'T'HH:mm:ssXXX).
     *
     * @return The current timestamp as a string.
     */
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
