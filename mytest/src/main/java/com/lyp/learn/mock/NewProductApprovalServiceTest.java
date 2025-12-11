//package com.lyp.learn.mock;
//
//
//import com.dianping.squirrel.client.StoreKey;
//import com.google.common.collect.HashBasedTable;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Table;
//import com.lyp.test.learn.config.MdpMccConfig;
//import com.lyp.test.learn.dao.mapper.NewProductApprovalMapper;
//import com.lyp.test.learn.dao.param.QueryNewProductApprovalParam;
//import com.lyp.test.learn.dao.po.NewProductApprovalPO;
//import com.lyp.test.learn.dao.po.PageResultPO;
//import com.lyp.test.learn.dao.repository.NewProductApprovalRepository;
//import com.lyp.test.learn.dao.repository.NewProductCitySkuInfoRepository;
//import com.lyp.test.learn.enums.CloudProductTypeEnum;
//import com.lyp.test.learn.enums.newproduct.NewProductCitySkuStatusEnum;
//import com.lyp.test.learn.exception.BizException;
//import com.lyp.test.learn.exception.EmptyListException;
//import com.lyp.test.learn.gateway.*;
//import com.lyp.test.learn.gateway.dto.ManageCityAndCategoryDTO;
//import com.lyp.test.learn.gateway.res.SkuBaseDTO;
//import com.lyp.test.learn.model.rdcpurchase.PlacePurchaseOrderResponseModel;
//import com.lyp.test.learn.rdcpurchase.dto.NewProductRdcSkuDTO;
//import com.lyp.test.learn.rdcpurchase.dto.NewProductSynergyApprovalDTO;
//import com.lyp.test.learn.rdcpurchase.enums.RdcGroupRoleEnum;
//import com.lyp.test.learn.rdcpurchase.req.NewProductSynergyApprovalQueryRequest;
//import com.lyp.test.learn.rdcpurchase.req.NewProductSynergyApprovalSaveRequest;
//import com.lyp.test.learn.rdcpurchase.resp.NewProductSynergyApprovalQueryResDTO;
//import com.lyp.test.learn.service.newproduct.NewProductAuthService;
//import com.lyp.test.learn.service.newproduct.NewProductQueryService;
//import com.lyp.test.learn.service.newproduct.approval.NewProductApprovalService;
//import com.lyp.test.learn.service.newproduct.model.RdcAndSaleQty;
//import com.lyp.test.learn.service.newproduct.model.RdcInfoStringModel;
//import com.lyp.test.learn.service.newproduct.rdcorder.NewProductLoadService;
//import com.lyp.test.learn.service.query.PmsQueryService;
//import org.apache.calcite.common.time.DateUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.util.*;
//import org.springframework.util.Assert;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
///**
// * mock static 枚举类
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(RdcGroupRoleEnum.class)
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
//public class NewProductApprovalServiceTest {
//
//    @InjectMocks
//    private NewProductApprovalService newProductApprovalService;
//
//    @Mock
//    private NewProductApprovalMapper newProductApprovalMapper;
//
//    @InjectMocks
//    private NewProductApprovalRepository repository;
//
//    @Mock
//    private NewProductApprovalRepository newProductApprovalRepository;
//
//    @Mock
//    private NewProductCitySkuInfoRepository newProductCitySkuInfoRepository;
//
//    @Mock
//    private NewProductAuthService newProductAuthService;
//
//    @Mock
//    private SquirrelGateway squirrelGateway;
//    @Mock
//    private NewProductLoadService newProductLoadService;
//    @Mock
//    private MdpMccConfig mdpMccConfig;
//
//
//    private QueryNewProductApprovalParam param;
//    private List<NewProductApprovalPO> expectedResult;
//
//    private List<Long> rdcIds;
//    private QueryNewProductApprovalParam validParam;
//
//    private NewProductSynergyApprovalQueryRequest request;
//
//    private final String OPERATOR = "test_operator";
//
//    private final String USER_MIS_STR = "doBatchExport_test_operator";
//
//    private String operator = "test_operator";
//
//    @Before
//    public void setUp() {
//        // 初始化配置static开关，确保测试时能正确执行商品信息查询逻辑
//        MdpMccConfig.PURCHASE_NUM_FRONT_TO_BACK_QUANTITY_SWITCH = true;
//
//        //反射设置对象字段值
//        ReflectionTestUtils.setField(newProductApprovalService,"mdpMccConfig", mdpMccConfig);
//        ReflectionTestUtils.setField(newProductApprovalService, "NEW_PRODUCT_ORDER_KEY", "test_new_product_order_key");
//    }
//
//
//    @Test
//    public void testApprovalPass_Success() {
//        // 准备测试数据
//        String bpmCode = "BPM123456";
//        String approvalBillCode = "BILL123456";
//
//        List<NewProductApprovalPO> approvalPOList = new ArrayList<>();
//        NewProductApprovalPO approvalPO = new NewProductApprovalPO();
//        approvalPO.setId(1L);
//        approvalPO.setSkuId(1001L);
//        approvalPO.setManageCityId(110100L);
//        approvalPO.setBillStatus(NewProductCitySkuStatusEnum.APPROVED.getCode());
//        approvalPO.setRdcInfo("[{\"rdcId\":10001,\"rdcName\":\"测试仓库\",\"rdcSkuPurchaseStatus\":1}]");
//        approvalPO.setBoxQuantity(5);
//        approvalPO.setManualAdjustedQty(10);
//        approvalPO.setPreArrivalDay("20250711");
//        approvalPOList.add(approvalPO);
//
//        // 设置mock行为
//        when(newProductApprovalRepository.queryApprovalBillCodeBpmCode(bpmCode)).thenReturn(approvalBillCode);
//        when(squirrelGateway.tryLock(any(StoreKey.class), anyString(), anyInt())).thenReturn(true);
//        doNothing().when(newProductApprovalRepository).updateBillStatusByBillCode(anyString(), anyInt(), anyInt());
//        doNothing().when(newProductCitySkuInfoRepository).updateStatusByBillCode(anyString(), anyInt(), anyInt());
//        when(newProductApprovalRepository.queryNewProductApprovalByBillCode(approvalBillCode)).thenReturn(approvalPOList);
//
//        // 模拟城市品类销售占比
//        Map<ManageCityAndCategoryDTO, List<RdcAndSaleQty>> salesRatioMap = new HashMap<>();
//        List<RdcAndSaleQty> rdcAndSaleQtyList = new ArrayList<>();
//        rdcAndSaleQtyList.add(new RdcAndSaleQty(10001L, 100L));
//        salesRatioMap.put(new ManageCityAndCategoryDTO(110100L, 1L), rdcAndSaleQtyList);
//        when(footstoneGateway.queryCityAndCategorySalesRatio(anyList())).thenReturn(salesRatioMap);
//
//        // 模拟RDC部门映射
//        Map<Long, Long> rdcToDeptMap = new HashMap<>();
//        rdcToDeptMap.put(10001L, 2001L);
//        when(authGateway.getDepartmentId(anyList())).thenReturn(rdcToDeptMap);
//
//        // 模拟商品基础信息查询
//        List<SkuBaseDTO> skuBaseDTOList = new ArrayList<>();
//        SkuBaseDTO skuBaseDTO = new SkuBaseDTO();
//        skuBaseDTO.setSkuId(1001L);
//        skuBaseDTO.setUnitId(1L);
//        skuBaseDTOList.add(skuBaseDTO);
//        when(productGateway.querySkuBaseInfoByPage(anyList())).thenReturn(skuBaseDTOList);
//
//        // 模拟创建采购单
//        List<PlacePurchaseOrderResponseModel> responseModels = new ArrayList<>();
//        PlacePurchaseOrderResponseModel responseModel = new PlacePurchaseOrderResponseModel();
//        responseModel.setPoiId(10001L);
//        responseModel.setSupplierId(3001L);
//        responseModel.setPoNo("PO123456");
//        responseModel.setSuccessfulSkuIdList(Collections.singletonList(1001L));
//        responseModels.add(responseModel);
//        when(pmsQueryService.submitPurchaseOrder(anyList())).thenReturn(responseModels);
//
//        // mock lion配置方法， 静态枚举类
//        when(mdpMccConfig.getCombinationRdcIdConfigList()).thenReturn(new ArrayList<>());
//        PowerMockito.mockStatic(RdcGroupRoleEnum.class);
//        when(RdcGroupRoleEnum.isEffectiveReceiver(anyInt())).thenReturn(false);
//
//
//        // 执行测试
//        newProductApprovalService.approvalPass(bpmCode);
//
//        // 验证结果
//        verify(newProductApprovalRepository, times(1)).updateBillStatusByBillCode(
//                eq(approvalBillCode),
//                eq(NewProductCitySkuStatusEnum.APPROVING.getCode()),
//                eq(NewProductCitySkuStatusEnum.APPROVED.getCode()));
//        verify(newProductCitySkuInfoRepository, times(1)).updateStatusByBillCode(
//                eq(approvalBillCode),
//                eq(NewProductCitySkuStatusEnum.APPROVING.getCode()),
//                eq(NewProductCitySkuStatusEnum.APPROVED.getCode()));
//        verify(newProductCitySkuInfoRepository, times(1)).updateStatusByBillCode(
//                eq(approvalBillCode),
//                eq(NewProductCitySkuStatusEnum.APPROVED.getCode()),
//                eq(NewProductCitySkuStatusEnum.CREATED.getCode()));
//    }
//
//}
