package com.pacificlife.mule.lambda.internal;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(LambdaOperations.class)
public class LambdaConfiguration {
    static AWSLambda mockClient;
    @Parameter
    private String accessKey;
    @Parameter
    private String secretKey;
    @Parameter
    @Optional(defaultValue = "us-east-1")
    private String region;
    private AWSLambda client;

    public LambdaConfiguration() {
    }

    public LambdaConfiguration(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public synchronized AWSLambda getClient() {
        if( client == null ) {
            AWSLambdaClientBuilder clientBuilder = AWSLambdaClientBuilder.standard().withRegion(region);
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
            client = clientBuilder.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        }
        return client;
    }

    public synchronized void setClient(AWSLambda client) {
        this.client = client;
    }
}
