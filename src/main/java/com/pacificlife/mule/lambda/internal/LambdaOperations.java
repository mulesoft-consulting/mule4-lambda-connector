package com.pacificlife.mule.lambda.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.util.IOUtils;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.ByteBuffer;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class LambdaOperations {
    private static final Logger logger = LoggerFactory.getLogger(LambdaOperations.class);

    /**
     * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @Throws(LambdaErrorProvider.class)
    public String invoke(@Config LambdaConfiguration config, String functionName, InputStream content) {
        try {
            logger.info("Invoking lambda function" +functionName);
            InvokeRequest req = new InvokeRequest().withFunctionName(functionName)
                    .withPayload(ByteBuffer.wrap(IOUtils.toByteArray(content)));
            logger.info("request ready, invoking");
            InvokeResult invoke = config.getClient().invoke(req);
            logger.info("request completed, got status code "+invoke.getStatusCode());
            if (invoke.getStatusCode() != 200) {
                throw new IllegalStateException("Lambda method invocation returned " + invoke.getStatusCode() + " : " +
                        invoke.getFunctionError());
            }
            return new String(invoke.getPayload().array());
        } catch (Throwable e) {
            logger.error("Failed to invoke lambda function " +functionName);
            throw new ModuleException(LambdaError.AWS_ERROR, e);
        }
    }
}
