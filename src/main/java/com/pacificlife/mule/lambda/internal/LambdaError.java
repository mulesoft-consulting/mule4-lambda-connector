package com.pacificlife.mule.lambda.internal;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum LambdaError implements ErrorTypeDefinition<LambdaError> {
    INVOCATION_FAILED, AWS_ERROR
}
