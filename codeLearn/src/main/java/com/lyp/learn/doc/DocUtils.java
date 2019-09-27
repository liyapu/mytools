package com.lyp.learn.doc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("companyName", "北京启迪区块链");
            dataMap.put("companyAddress", "五道口搜狐大厦");
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
