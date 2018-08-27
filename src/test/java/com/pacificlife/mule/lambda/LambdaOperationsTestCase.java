package com.pacificlife.mule.lambda;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.amazonaws.services.lambda.AWSLambda;
import com.pacificlife.mule.lambda.internal.LambdaConfiguration;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mule.functional.api.flow.FlowRunner;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;
import org.mule.runtime.api.component.Component;
import org.mule.runtime.api.component.location.Location;

import java.util.Optional;

public class LambdaOperationsTestCase extends MuleArtifactFunctionalTestCase {

    /**
     * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
     */
    @Override
    protected String getConfigFile() {
        return "test-mule-config.xml";
    }

    @Test
    public void invokeFunction() throws Exception {
//        AWSLambda lambdaClient = Mockito.mock(AWSLambda.class);
//        String payloadValue = ((String) flowRunner("invokeFlow")
//                .withVariable("client",lambdaClient)
//                .run()
//                .getMessage()
//                .getPayload()
//                .getValue());
//        assertThat(payloadValue, is("Hello Mariano Gonzalez!!!"));
    }
}
