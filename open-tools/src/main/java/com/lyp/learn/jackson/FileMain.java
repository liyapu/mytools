package com.lyp.learn.jackson;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FileMain {

    public static void main(String[] args) {
        try {
            String filePath = FileMain.class.getResource("/atemp.txt").getPath();
//            log.info("filePath :{}", filePath);
//            System.out.println("filePath:" + filePath);
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
//            log.info("line size:{}", lines.size());
//            System.out.println("line size:" + lines.size());

            for (String line : lines) {
                if (!line.contains("message")) {
                    continue;
                }
//                System.out.println("line:" + line);
                String oldResult = line.substring(line.indexOf("oldResultStr") + "oldResultStr".length() + 1, line.indexOf("newResultStr") - ", \\n".length() - 1);
                String newResult = line.substring(line.indexOf("newResultStr") + "newResultStr".length() + 1, line.length() - "\\n\",".length() + 1);

//                System.out.println("oldResult === " + oldResult);
//                System.out.println("newResult === " + newResult);
//                System.out.println();
                Integer result = JsonDiffUtils.diffJson(oldResult, newResult, Collections.emptyList(), false);
                if (result != 1) {
                    System.out.println(result);
                }
            }
            System.out.println("end");
        } catch (IOException e) {
            log.error("read file error", e);
        }
    }
}