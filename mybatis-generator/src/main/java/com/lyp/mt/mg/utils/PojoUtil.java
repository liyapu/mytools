package com.lyp.mt.mg.utils;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: liyapu
 * @description: 处理 my-generator 生成的 bean
 * @date 2019-10-10 19:51
 */
public class PojoUtil {

    /**
     * 从当前系统中获取换行符，默认是"\n"
     *
     * 不要随便用 \n\r    \n    \r，因为他们在不同操作系统有不同的表示。
     * 如果在java代码中把它们写死了，那么java跨平台运行的特性就没有了
     *
     * 或者使用
     * bufferedWriter.newLine();
     * 由于BufferedReader的rendLIne()是不读入换行符的，所以写入换行时须用 bufferedWriter.newLine() 方法
     */
    public static final String NEW_LINE_SEPARATOR = System.getProperty("line.separator", "\n");


    public static void createDir() throws IOException {
        String currentRoot = PojoUtil.class.getResource("/").getPath();
        System.out.println("currentRoot = " + currentRoot );
        String currentRootDir = new File("").getAbsolutePath().toString();
        System.out.println("currentRootDir = " + currentRootDir);

//        Path pathBak = Paths.get(currentRoot,"com","lyp","mt","mg","generator","bak");
        Path pathBak = Paths.get(currentRootDir,"/src/main/java/com/lyp/mt/mg/generator/bak/");
        if(!Files.exists(pathBak)){
            Files.createDirectories(pathBak);
        }

        Path pathSource = Paths.get(currentRootDir,"/src/main/java/com/lyp/mt/mg/generator/pojo/");
        Stream<Path> listFiles = Files.list(pathSource);
        listFiles.forEach(p ->{
            System.out.println(p.getFileName());
            try {
                List<String> allLines = Files.readAllLines(p);
                String desFile = pathBak.toString() + File.separator + p.getFileName();
                System.out.println("desFile = " + desFile);
                BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(desFile)), "UTF-8"));
                for(String line : allLines){
                    if(line.startsWith("package")){
                        line = line.substring(0,8) + "com.lyp.mt.mg.generator.bak;";
                    }
                    if(line.startsWith("public class")){
                        line = line.replace("{","extends BaseBean {");
                    }
                    bw.write(line + NEW_LINE_SEPARATOR);
                }
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @Test
    public void test1() throws IOException {
        createDir();
    }
}
