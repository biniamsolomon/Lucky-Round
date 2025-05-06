package com.bini.Lucky_Round.utils;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public final class SSLUtil {

	private SSLUtil() {

	}

	private static final HostnameVerifier trivialHostnameVerifier = (hostname, sslSession) -> true;

	private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
			// Internal Usage
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
			// Internal Usage
		}
	} };

	public static void turnOffSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
		HttpsURLConnection.setDefaultHostnameVerifier(trivialHostnameVerifier);
		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

}

