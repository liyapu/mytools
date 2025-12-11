//package com.lyp.learn.mock;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.when;
//
//
///**
// * 使用反射去设置对象ReflectionTestUtils
// */
//@RunWith(MockitoJUnitRunner.class)
//public class PromotionManagementGatewayTest {
//
//    @InjectMocks
//    private PromotionManagementGateway promotionManagementGateway;
//
//    @Spy
//    private PromotionManagementGateway spyPromotionManagementGateway;
//
//    @Mock
//    private TPromotionQueryService tPromotionQueryService;
//
//    @Mock
//    private CommonConfig commonConfig;
//    @Mock
//    private TAllPromotionQueryService tAllPromotionQueryService;
//    @Mock
//    private TNPromotionQueryService tnPromotionQueryService;
//
//
//    @Before
//    public void setup() {
//        // mock lion 配置类 commonConfig
//        // 手动注入Mock对象到Spy中
//        //然后具体的单测方法，mock返回值
//        ReflectionTestUtils.setField(spyPromotionManagementGateway, "commonConfig", commonConfig);
//    }
//
//    /**
//     * 测试查询结果为空时，返回空Map
//     */
//    @Test
//    public void test_queryMultiPoiSkuPromotionList1() throws TException {
//        List<PoiSkuPair> poiSkuPairList = new ArrayList<>();
//        PoiSkuPair poiSkuPair1 = new PoiSkuPair();
//        poiSkuPair1.setPoiId(1L);
//        poiSkuPair1.setSkuId(1L);
//        poiSkuPairList.add(poiSkuPair1);
//        QueryPoiSkuPromotionTResponse response = new QueryPoiSkuPromotionTResponse();
//        response.setCode(0);
//        response.setData(new ArrayList<>());
//
//        //mock lion 具体的值
//        when(commonConfig.getPromotionYtMigrationSwitch()).thenReturn(0);
//        when(commonConfig.getPromotionYtMigrationPercent()).thenReturn(0D);
//
//        when(tAllPromotionQueryService.queryMultiPoiSkuPromotionList(any())).thenReturn(response);
//        Map<PoiSkuPair, Set<Integer>> poiSkuPairSetMap =
//                promotionManagementGateway.queryPoiSku2PromotionTypeSetMapGray(poiSkuPairList, Maps.newHashMap());
//        Assert.assertTrue(poiSkuPairSetMap.isEmpty());
//    }