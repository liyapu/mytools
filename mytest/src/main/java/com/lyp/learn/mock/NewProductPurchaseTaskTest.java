//package com.lyp.learn.mock;
//
//import com.lyp.test.learn.config.MccConfig;
//import com.lyp.test.learn.crane.NewProductPurchaseTask;
//import com.lyp.test.learn.enums.newproduct.NewProductCitySkuStatusEnum;
//import com.lyp.test.learn.gateway.PoiGateway;
//import com.lyp.test.learn.service.newproduct.approval.NewProductApprovalService;
//import com.lyp.test.learn.service.newproduct.rdcorder.NewProductLoadService;
//import com.lyp.mall.poi.client.thrift.cityaggre.ManagementCityTInfo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * 反射调用私有方法
// */
//@RunWith(MockitoJUnitRunner.class)
//public class NewProductPurchaseTaskTest {
//
//    @InjectMocks
//    private NewProductPurchaseTask newProductPurchaseTask;
//
//    @Mock
//    private ExecutorService executorService;
//
//    @Mock
//    private PoiGateway poiGateway;
//
//    @Mock
//    private NewProductLoadService newProductLoadService;
//
//    @Mock
//    private NewProductApprovalService newProductApprovalService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        // 设置超时时间 - 修改为设置MccConfig类中的静态字段
//        // 将Long类型的60L改为Integer类型的60
//        ReflectionTestUtils.setField(MccConfig.class, "NEW_PRODUCT_CITY_LOAD_TASK_TIMEOUT_SECOND", 60);
//
//        // 由于不能使用PowerMockito，我们需要修改测试策略
//        // 我们将在测试方法中使用ReflectionTestUtils来替换CraneTool.shouldExecute的调用结果
//    }
//
//
//
//    /**
//     * 测试模拟审批单状态流转 - 审批拒绝
//     */
//    @Test
//    public void testApproveFinish_Rejected() {
//        // 准备测试数据
//        String param = "{\"bpmBillCode\":\"BPM123456\",\"toStatus\":" + NewProductCitySkuStatusEnum.REJECTED.getCode() + "}";
//        doNothing().when(newProductApprovalService).approvalReject(anyString());
//
//        // 执行测试
//        newProductPurchaseTask.approveFinish(param);
//
//        // 验证结果
//        verify(newProductApprovalService, times(0)).approvalPass(anyString());
//        verify(newProductApprovalService, times(1)).approvalReject("BPM123456");
//    }
//
//
//    /**
//     * 测试查询所有经营城市ID列表 - 正常场景
//     */
//    @Test
//    public void testQueryAllManagementCityIdList_Success() {
//        // 准备测试数据
//        List<ManagementCityTInfo> cityInfos = createMockCityInfos();
//        when(poiGateway.getAllManagementCityInfo()).thenReturn(cityInfos);
//
//        // 使用反射调用私有方法进行测试
//        List<Long> result = ReflectionTestUtils.invokeMethod(newProductPurchaseTask, "queryAllManagementCityIdList");
//
//        // 验证结果
//        verify(poiGateway, times(1)).getAllManagementCityInfo();
//        assert result != null;
//        assert result.size() == 3;
//        assert result.contains(110100L);
//        assert result.contains(120100L);
//        assert result.contains(130100L);
//    }
//
//    /**
//     * 测试查询所有经营城市ID列表 - 空结果
//     */
//    @Test
//    public void testQueryAllManagementCityIdList_Empty() {
//        // 准备测试数据
//        when(poiGateway.getAllManagementCityInfo()).thenReturn(Collections.emptyList());
//
//        // 使用反射调用私有方法进行测试
//        List<Long> result = ReflectionTestUtils.invokeMethod(newProductPurchaseTask, "queryAllManagementCityIdList");
//
//        // 验证结果
//        verify(poiGateway, times(1)).getAllManagementCityInfo();
//        assert result != null;
//        assert result.isEmpty();
//    }
//
//    /**
//     * 测试查询所有经营城市ID列表 - 返回null
//     */
//    @Test
//    public void testQueryAllManagementCityIdList_Null() {
//        // 准备测试数据
//        when(poiGateway.getAllManagementCityInfo()).thenReturn(null);
//
//        // 使用反射调用私有方法进行测试
//        List<Long> result = ReflectionTestUtils.invokeMethod(newProductPurchaseTask, "queryAllManagementCityIdList");
//
//        // 验证结果
//        verify(poiGateway, times(1)).getAllManagementCityInfo();
//        assert result != null;
//        assert result.isEmpty();
//    }
//
//    /**
//     * 创建模拟的城市信息列表
//     */
//    private List<ManagementCityTInfo> createMockCityInfos() {
//        List<ManagementCityTInfo> cityInfos = new ArrayList<>();
//
//        ManagementCityTInfo cityInfo1 = new ManagementCityTInfo();
//        cityInfo1.setManagementCityId(110100L);
//        cityInfo1.setManagementCityName("北京市");
//        cityInfos.add(cityInfo1);
//
//        ManagementCityTInfo cityInfo2 = new ManagementCityTInfo();
//        cityInfo2.setManagementCityId(120100L);
//        cityInfo2.setManagementCityName("天津市");
//        cityInfos.add(cityInfo2);
//
//        ManagementCityTInfo cityInfo3 = new ManagementCityTInfo();
//        cityInfo3.setManagementCityId(130100L);
//        cityInfo3.setManagementCityName("石家庄市");
//        cityInfos.add(cityInfo3);
//
//        return cityInfos;
//    }
//
//    /**
//     * 模拟ExecutorService.submit方法
//     */
//    private void mockExecutorServiceSubmit() {
//        doAnswer(invocation -> {
//            Runnable runnable = invocation.getArgument(0);
//            // 不实际执行任务，避免调用真实的loadForSingleManageCity方法
//            return CompletableFuture.completedFuture(null);
//        }).when(executorService).submit(any(Runnable.class));
//    }
//}
