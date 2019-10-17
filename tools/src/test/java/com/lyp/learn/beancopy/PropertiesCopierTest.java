package com.lyp.learn.beancopy;

import com.lyp.learn.bean.User;
import com.lyp.learn.bean.UserVo;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 09:53
 *
 * 单元测试
 * 参数化的单元测试
 *
 * 结论:
 * Apache BeanUtils的性能最差,不建议使用。
 * Apache PropertyUtils100000次以内性能还能接受,到百万级别性能就比较差了,可酌情考虑。
 * spring BeanUtils和BeanCopier性能较好,如果对性能有特别要求,可使用BeanCopier,不然spring BeanUtils也是可取的。
 *
 */
@RunWith(Parameterized.class)
public class PropertiesCopierTest {
    @Parameterized.Parameter(0)
    public PropertiesCopier propertiesCopier;

    //测试次数
    private static List<Integer> testTimes = Arrays.asList(100,1000,10_000,100_000,1_000_000);
    //测试结果 以 markdown 表格的形式输出
    private static StringBuilder resultBuilder = new StringBuilder("|实现|100|1,000|10,000|100,000|1,000,000|\n").append("|-----|-----|-----|-----|-----|-----|\n");

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new StaticCglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new CglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new SpringBeanUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsPropertyUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsBeanUtilsPropertiesCopier()});
        return params;
    }

    @Before
    public void setUp(){
        String name = propertiesCopier.getClass().getSimpleName().replace("PropertiesCopier","");
        resultBuilder.append("|").append(name).append("|");
    }

    @Test
    public void copyProperties() throws Exception{
        User userSource = new User();
        userSource.setId(100);
        userSource.setName("张三");
        userSource.setAge(18);
        userSource.setAddress("河南省 商丘市");
        userSource.setTelephone("13566668888");
        userSource.setHeight(170);
        userSource.setWeight(62);

        UserVo userVoTarget = new UserVo();

        //预热一次
        propertiesCopier.copyProperties(userSource,userVoTarget);

        for(Integer time : testTimes){
            long start = System.nanoTime();
            for(int i = 0; i < time; i++){
                propertiesCopier.copyProperties(userSource,userVoTarget);
            }
            resultBuilder.append((System.nanoTime()-start)/1_000_000D).append("|");
        }
        resultBuilder.append("\n");
    }

    @AfterClass
    public static void tearDown() throws Exception{
        System.out.println("测试结果：");
        System.out.println(resultBuilder);
    }
}
