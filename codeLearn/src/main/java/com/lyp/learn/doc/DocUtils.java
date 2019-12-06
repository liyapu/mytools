package com.lyp.learn.doc;

import com.lyp.learn.base.threads.pk01.Object;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-10 20:26
 */
public class DocUtils {

    /**
     * 简单输出
     */
    @Test
    public void test00(){
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("companyName", "北京启迪区块链");
            dataMap.put("companyAddress", "");
            dataMap.put("companyCapital","100w");

            List<Person> persons = new ArrayList<>();
            persons.add(new Person("1","张三",28,"天津市"));
            persons.add(new Person("2","李四",18,"洛阳市"));
            persons.add(new Person("3","王五",16,"商丘市"));
            persons.add(new Person("4","赵六",20,"郑州市"));
            persons.add(new Person("5","张三",10,"西安市"));
            dataMap.put("persons",persons);

            Map<String,String> userMap = new HashMap<>();
            userMap.put("1","李世民");
            userMap.put("2","唐国强");
            userMap.put("3","毛泽东");
            dataMap.put("userMap",userMap);

            Map<String,Object>  sonChanges = new HashMap<>();
            List<Change> changes = new ArrayList<>();
            changes.add(new Change("放假了，子子变更信息显示看看",new Date()));
            changes.add(new Change("收购企业，子子产权重组",new Date()));
            changes.add(new Change("人员调整，子子大变动",new Date()));
            sonChanges.put("changes",changes);
            sonChanges.put("sonName","王子");
            sonChanges.put("sonAge","28");
            dataMap.put("sonChanges",sonChanges);

            List<Branch> branches = new ArrayList<>();
            branches.add(new Branch("5d5500fc1623dd0006518883","北京分支"));
            branches.add(new Branch("5d380b3714718e6c0382ba44","湖南分支"));
            branches.add(new Branch("5d5a922ac59e8b000799f598","海南分支"));
            branches.add(new Branch("5d39440214718e71ce1a80b9","商丘分支"));
            dataMap.put("branches",branches);

            Map<String,Object> branchSonMap = new HashMap<>();
            Map<String,Object> map5d5500fc1623dd0006518883 = new HashMap<>();
            map5d5500fc1623dd0006518883.put("branchUnit","万元");
            map5d5500fc1623dd0006518883.put("branchTotalCapital","12334.433");
            map5d5500fc1623dd0006518883.put("branchPerson",new Person("100","张三分支",28,"天津市分支"));
            List<Change> changes5d5500fc1623dd0006518883 = new ArrayList<>();
            changes5d5500fc1623dd0006518883.add(new Change("放假了三，张三分支变更信息显示看看",new Date()));
            changes5d5500fc1623dd0006518883.add(new Change("收购企业，张三分支产权重组",new Date()));
            changes5d5500fc1623dd0006518883.add(new Change("人员调整，张三分支大变动",new Date()));
            map5d5500fc1623dd0006518883.put("changes",changes5d5500fc1623dd0006518883);
            branchSonMap.put("5d5500fc1623dd0006518883",map5d5500fc1623dd0006518883);
//            dataMap.put("map5d5500fc1623dd0006518883",map5d5500fc1623dd0006518883);

            Map<String,Object> map5d380b3714718e6c0382ba44 = new HashMap<>();
//            map5d380b3714718e6c0382ba44.put("branchUnit","万元");
//            map5d380b3714718e6c0382ba44.put("branchTotalCapital","200000.200");
//            map5d380b3714718e6c0382ba44.put("branchPerson",new Person("200","利斯市分支",28,"陕西市分支"));
//            List<Change> changes5d380b3714718e6c0382ba44 = new ArrayList<>();
//            changes5d380b3714718e6c0382ba44.add(new Change("放假了三，利斯市分支变更信息显示看看",new Date()));
//            changes5d380b3714718e6c0382ba44.add(new Change("收购企业，利斯市分支产权重组",new Date()));
//            changes5d380b3714718e6c0382ba44.add(new Change("人员调整，利斯市分支大变动",new Date()));
//            map5d380b3714718e6c0382ba44.put("changes",changes5d380b3714718e6c0382ba44);
            branchSonMap.put("5d380b3714718e6c0382ba44",map5d380b3714718e6c0382ba44);
//            dataMap.put("map5d380b3714718e6c0382ba44",map5d380b3714718e6c0382ba44);


            Map<String,Object> map5d5a922ac59e8b000799f598 = new HashMap<>();
            map5d5a922ac59e8b000799f598.put("branchUnit","万元");
            map5d5a922ac59e8b000799f598.put("branchTotalCapital","200000.200");
            map5d5a922ac59e8b000799f598.put("branchPerson",new Person("300","市分支",28,"陕西市分支"));
            List<Change> changes5d5a922ac59e8b000799f598 = new ArrayList<>();
            changes5d5a922ac59e8b000799f598.add(new Change("放假了三，陕西分支变更信息显示看看",new Date()));
            changes5d5a922ac59e8b000799f598.add(new Change("收购企业，陕西市分支产权重组",new Date()));
            changes5d5a922ac59e8b000799f598.add(new Change("人员调整，陕西市分支大变动",new Date()));
            map5d5a922ac59e8b000799f598.put("changes",changes5d5a922ac59e8b000799f598);
            branchSonMap.put("5d5a922ac59e8b000799f598",map5d5a922ac59e8b000799f598);
//            dataMap.put("map5d5a922ac59e8b000799f598",map5d5a922ac59e8b000799f598);


            Map<String,Object> map5d39440214718e71ce1a80b9 = new HashMap<>();
            map5d39440214718e71ce1a80b9.put("branchUnit","万亿元");
            map5d39440214718e71ce1a80b9.put("branchTotalCapital","300000.200");
            map5d39440214718e71ce1a80b9.put("branchPerson",new Person("400","商海分支",28,"商丘市啊分支"));
            List<Change> changes5d39440214718e71ce1a80b9 = new ArrayList<>();
            changes5d39440214718e71ce1a80b9.add(new Change("放假了三，商海分支变更信息显示看看",new Date()));
            changes5d39440214718e71ce1a80b9.add(new Change("收购企业，商海市分支产权重组",new Date()));
            changes5d39440214718e71ce1a80b9.add(new Change("人员调整，商海市分支大变动",new Date()));
            map5d39440214718e71ce1a80b9.put("changes",changes5d39440214718e71ce1a80b9);
            branchSonMap.put("5d39440214718e71ce1a80b9",map5d39440214718e71ce1a80b9);
//            dataMap.put("map5d39440214718e71ce1a80b9",map5d39440214718e71ce1a80b9);

            dataMap.put("branchSonMap",branchSonMap);



            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setDefaultEncoding("utf-8");
            String targetTemplate = DocUtils.class.getClassLoader().getResource("").getPath().toString();
            String targetOutput = DocUtils.class.getClassLoader().getResource("").getPath().toString() + "平凡世界.doc";
            System.out.println(targetTemplate);
            System.out.println(targetOutput);
            configuration.setDirectoryForTemplateLoading(new File(targetTemplate));

            // 输出文档路径及名称
            File outFile = new File(targetOutput);
//            outFile.deleteOnExit();
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("平凡的世界.xml","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 简单输出
     */
    @Test
    public void test01(){
        try {
            Map<String,String> dataMap = new HashMap<String,String>();
            dataMap.put("name", "张三");
            dataMap.put("idNumber", "123123");
//            dataMap.put("address", "上海市啊");
            dataMap.put("other","什么都可以");
            Configuration configuration = new Configuration(Configuration.getVersion() );
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/ 还有其他方式
            String targetTemplate = DocUtils.class.getClassLoader().getResource("").getPath().toString();
            String targetOutput = DocUtils.class.getClassLoader().getResource("").getPath().toString() + "firstt.doc";
            System.out.println(targetTemplate);
            System.out.println(targetOutput);
            configuration.setDirectoryForTemplateLoading(new File("/Users/liyapu/myGitRepository/mytools/codeLearn/src/main/resources/"));

            // 输出文档路径及名称
            File outFile = new File(targetOutput);
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("第1版.ftl","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * list 循环输出
     */
    @Test
    public void test02(){
        try {
            Map<String,Object> dataMap = new HashMap<>();
            List<Person> persons = new ArrayList<>();
//            persons.add(new Person("张三",20,"北京"));
//            persons.add(new Person("李四",18,"上海"));
//            persons.add(new Person("王五",25,"长安"));
//            persons.add(new Person("赵柳",19,"南京"));
            dataMap.put("persons",persons);

            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/ 还有其他方式
            configuration.setDirectoryForTemplateLoading(new File("/Users/liyapu/myGitRepository/mytools/codeLearn/src/main/resources/"));

            // 输出文档路径及名称
            File outFile = new File("/Users/liyapu/test/doc/second.doc");
            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("第2版.ftl","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testClassPath() throws IOException {
        //获取当前类class所在的路径
        String classPath = DocUtils.class.getResource("").getPath();
        System.out.println("classPath = " + classPath);

        //获取当前类class所在的resource路径
        String classPath1 = DocUtils.class.getResource("/").getPath();
        System.out.println("classPath1 = " + classPath1);

        String classPath2 = DocUtils.class.getResource("/enterprise_doc.xml").getPath();
        System.out.println("classPath2 = " + classPath2);

        String classPath3 = DocUtils.class.getResource("/txt/we.txt").getPath();
        System.out.println("classPath3 = " + classPath3);

        String classPath4 = DocUtils.class.getClassLoader().getResource("").getPath();
        System.out.println("classPath4 = " + classPath4);

        //String classPath5 = DocUtils.class.getClassLoader().getResource("/").getPath();
        //System.out.println("classPath5 = " + classPath5);


        System.out.println();
        File f = new File("");
        System.out.println("空字符file的Path : " + f.getPath());
        System.out.println("空字符file的标准路径CanonicalPath : " + f.getCanonicalPath());
        System.out.println("空字符file的绝对路径AbsolutePath : " + f.getAbsolutePath());

        System.out.println();

    }


    /**
     * 读取文档
     */
    @Test
    public void test05() throws IOException {
        String classPath2 = DocUtils.class.getResource("/enterprise_doc.xml").getPath();
        List<String> lines = Files.readAllLines(Paths.get(classPath2), StandardCharsets.UTF_8);
        System.out.println(lines.size());

    }

    /**
     * 输出文档
     */
    @Test
    public void test06() throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fos = new FileOutputStream("测试输出.xml");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        PrintWriter pw = new PrintWriter(osw);

        pw.println("啦啦啦");
        pw.println("哈哈哈");
        System.out.println("写出完毕!");
        pw.close();
    }

    /**
     * 读取文件 ，然后输出
     *  <#assign reportTime  = .now>
     *  <#list shareholders as shareholder>
     *  </#list>
     */
    @Test
    public void test07() throws Exception{
        FileOutputStream fos = new FileOutputStream("测试输出.xml");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        PrintWriter pw = new PrintWriter(osw);


        String classPath2 = DocUtils.class.getResource("/enterprise_doc.xml").getPath();
        List<String> lines = Files.readAllLines(Paths.get(classPath2), StandardCharsets.UTF_8);
        System.out.println(lines.size());
        Pattern p = Pattern.compile("<\\/*#{1}");
        for(String str : lines){
            if(p.matcher(str).find()){
//                System.out.println(str);
                continue;
            }
            pw.write(str);
        }
//        System.out.println(p.matcher("    <#assign reportTime  = .now>").find());
//        System.out.println(p.matcher("    <#list shareholders as shareholder>").find());
//        System.out.println(p.matcher("    </#list>").find());


        System.out.println("写出完毕!");
        pw.close();
    }
}
