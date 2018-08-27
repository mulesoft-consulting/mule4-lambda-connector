package com.pacificlife.mule.lambda.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "lambda")
@Extension(name = "Lambda")
@Configurations(LambdaConfiguration.class)
@ErrorTypes(LambdaError.class)
public class LambdaExtension {

}
