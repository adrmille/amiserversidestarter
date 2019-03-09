package fr.amisss.core.common.system;

import fr.amisss.CoreTestApplication;
import fr.amisss.core.camunda.CamundaProcessHelper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CoreTestApplication.class})
@SpringBootTest
@Transactional
@ActiveProfiles("disableRealTrading")
public class CamundaProcessHelperTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private CamundaProcessHelper camundaProcessHelper;

    @Test
    public void should_detect_the_flow_status_correctly() throws InterruptedException {

        final String processId = "Blocked-flow";
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId);
        Thread.sleep(500);

        Assert.assertNotNull(processInstance);
        Assert.assertTrue(camundaProcessHelper.checkProcessActiveByName("Blocked-flow"));

        runtimeService.suspendProcessInstanceByProcessDefinitionId(processInstance.getProcessDefinitionId());
        Thread.sleep(500);
        Assert.assertFalse(camundaProcessHelper.checkProcessActiveByName("Blocked-flow"));
    }

}
