package com.lyp.learn.jackson;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class FileSkuIdMain {

    public static void main(String[] args) {
        try {
            String filePath = FileSkuIdMain.class.getResource("/skuId1.txt").getPath();
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            System.out.println("line size:" + lines.size());

            Set<Long> skuIdSet = new HashSet<>();
            for (String line : lines) {
                if (!line.contains("skuId")) {
                    continue;
                }
                String skuIdStr = line.substring(line.indexOf("skuId") + 7, line.length() - 1);

                try {
                    skuIdSet.add(Long.parseLong(skuIdStr.trim().replace("\"", "")));
                } catch (NumberFormatException e) {
                }
                System.out.println(skuIdStr);
            }
            System.out.println("skuIdSet" + skuIdSet.size());
            System.out.println("skuIdSet" + skuIdSet);
            System.out.println("end");
        } catch (IOException e) {
            log.error("read file error", e);
        }
    }
}