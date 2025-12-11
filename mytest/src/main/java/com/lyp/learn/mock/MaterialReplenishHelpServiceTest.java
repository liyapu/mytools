//package com.lyp.learn.mock;
//
//import com.lyp.test.learn.config.MccConfig;
//import com.lyp.test.learn.enums.SubModuleEnum;
//import com.lyp.test.learn.enums.material.MaterialOperationTypeEnum;
//import com.lyp.test.learn.gateway.LogCenterGateway;
//import com.lyp.test.learn.gateway.MetricOpLogGateWay;
//import com.lyp.test.learn.model.dto.material.MaterialOperationLogDTO;
//import com.lyp.test.learn.model.dto.material.MaterialReplenishLogDTO;
//import com.lyp.test.learn.service.material.MaterialDraftService;
//import com.lyp.test.learn.service.material.MaterialReplenishHelpService;
//import com.lyp.test.learn.tool.JsonTool;
//import com.lyp..mall.log.center.tservice.common.TSourceType;
//import com.lyp..mall.log.center.tservice.operate.OpLog;
//import com.lyp..mall.log.center.tservice.operate.OpLogInfo;
//import com.lyp..mall.metrics.oplog.tservice.ConfigOpLogDTO;
//import com.lyp..mall.metrics.oplog.tservice.ConfigOpLogRes;
//import com.lyp..mall.metrics.oplog.tservice.QueryConfigOpLogResponse;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.stubbing.Answer;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import java.lang.reflect.Field;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.*;
//
//import static com.lyp.test.learn.common.ResponseVo.fail;
//import static com.lyp.test.learn.constants.MaterialConstrants.MATERIAL_OPT_TYPE;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * mock static 方法
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({MccConfig.class})
//@PowerMockIgnore({
//        "javax.net.ssl.*",
//        "javax.security.*",
//        "javax.crypto.*",
//        "javax.management.*",
//        "javax.net.*",
//        "javax.naming.*",
//        "javax.xml.*",
//        "org.xml.*",
//        "org.w3c.*",
//        "com.sun.org.apache.xerces.*",
//        "sun.security.ssl.*"
//})
//public class MaterialReplenishHelpServiceTest {
//
//    @InjectMocks
//    private MaterialReplenishHelpService replenishHelpService;
//
//    @Mock
//    private MetricOpLogGateWay metricOpLogGateWay;
//
//    @Mock
//    private LogCenterGateway logCenterGateway;
//
//    @Before
//    public void setUp() throws NoSuchFieldException, IllegalAccessException {
//        // 创建一个部分模拟的对象，只模拟我们需要的方法
//        replenishHelpService = Mockito.spy(new MaterialReplenishHelpService());
//
//        Field metricOpLogGateWayField = MaterialReplenishHelpService.class.getDeclaredField("metricOpLogGateWay");
//        metricOpLogGateWayField.setAccessible(true);
//        metricOpLogGateWayField.set(replenishHelpService, metricOpLogGateWay);
//
//        Field logCenterGatewayField = MaterialReplenishHelpService.class.getDeclaredField("logCenterGateway");
//        logCenterGatewayField.setAccessible(true);
//        logCenterGatewayField.set(replenishHelpService, logCenterGateway);
//
//    }
//
//    @Test
//    public void testAddOptLog() {
//        // 准备测试数据
//        String bizId = "bizId123";
//        String operator = "operator";
//        String remark = "remark";
//        MaterialOperationTypeEnum operationType = MaterialOperationTypeEnum.DRAFT_CONFIRMED;
//
//        // 设置MCC配置 静态方法
//        PowerMockito.mockStatic(MccConfig.class);
//        when(MccConfig.getMaterialOpLogMigrationSwitch()).thenReturn(1); // 同时写入新旧系统
//
//        // 执行测试方法
//        replenishHelpService.addOptLog(bizId, operator, remark, operationType);
//
//        // 验证旧系统日志调用
//        ArgumentCaptor<OpLog> opLogCaptor = ArgumentCaptor.forClass(OpLog.class);
//        verify(logCenterGateway).addOpLog(opLogCaptor.capture());
//
//        // 验证旧系统日志内容
//        OpLog capturedOpLog = opLogCaptor.getValue();
//        assertEquals(MATERIAL_OPT_TYPE,(Long)capturedOpLog.getOpType());
//        assertEquals(TSourceType.FMP, capturedOpLog.getFromSys());
//        assertEquals(bizId, capturedOpLog.getBizId());
//        assertEquals(operator, capturedOpLog.getOperator());
//
//        // 验证序列化的日志内容
//        MaterialOperationLogDTO logDTO = JsonTool.parse(capturedOpLog.getChangeCon(), MaterialOperationLogDTO.class);
//        assertNotNull(logDTO);
//        assertEquals(operationType.getDesc(), logDTO.getChangeCon());
//        assertEquals(remark, logDTO.getRemark());
//
//        // 验证新系统日志调用
//        ArgumentCaptor<List<ConfigOpLogDTO>> configOpLogCaptor = ArgumentCaptor.forClass(List.class);
//        verify(metricOpLogGateWay).batchInsertOpLog(configOpLogCaptor.capture());
//
//        // 验证新系统日志内容
//        List<ConfigOpLogDTO> capturedConfigOpLogs = configOpLogCaptor.getValue();
//        assertEquals(1, capturedConfigOpLogs.size());
//        ConfigOpLogDTO configOpLog = capturedConfigOpLogs.get(0);
//        assertEquals(MetricOpLogGateWay.APPKEY, configOpLog.getAppkey());
//        assertEquals(MetricOpLogGateWay.MODULE, configOpLog.getModule());
//        assertEquals(MetricOpLogGateWay.SUB_MODULE, configOpLog.getSubModule());
//        assertEquals(bizId, configOpLog.getObjectKey());
//        assertEquals(MATERIAL_OPT_TYPE + "", configOpLog.getOpType());
//        assertEquals(operator, configOpLog.getOperator());
//        assertEquals("none", configOpLog.getOldContent());
//
//        // 验证序列化的新系统日志内容
//        MaterialOperationLogDTO newLogDTO = JsonTool.parse(configOpLog.getNewContent(), MaterialOperationLogDTO.class);
//        assertNotNull(newLogDTO);
//        assertEquals(operationType.getDesc(), newLogDTO.getChangeCon());
//        assertEquals(remark, newLogDTO.getRemark());
//    }
//
//}
