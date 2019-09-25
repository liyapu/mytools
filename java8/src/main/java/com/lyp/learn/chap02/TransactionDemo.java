package com.lyp.learn.chap02;


import java.util.*;
import java.util.stream.Collectors;

public class TransactionDemo {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）
        System.out.println("------------(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）--------");
        List<Transaction> transactionList1 = transactions.stream()
                                                            .filter(t -> t.getYear() ==  2011)
                                                            .sorted(new Comparator<Transaction>() {
                                                                @Override
                                                                public int compare(Transaction o1, Transaction o2) {
                                                                   return  o1.getValue() - o2.getValue();
                                                                }
                                                            }).collect(Collectors.toList());
        System.out.println(transactionList1);

        List<Transaction> transactionList11 = transactions.stream()
                                                            .filter(t -> t.getYear() == 2011)
                                                            .sorted(Comparator.comparing(Transaction::getValue))
                                                            .collect(Collectors.toList());
        System.out.println(transactionList11);
        System.out.println();

        //(2) 交易员都在哪些不同的城市工作过
        System.out.println("-----------(2) 交易员都在哪些不同的城市工作过-----------");
        List<String> cityList = transactions.stream()
                                                .map(t -> t.getTrader().getCity())
                                                .distinct()
                                                .collect(Collectors.toList());
        System.out.println(cityList);

        Set<String> citySet = transactions.stream()
                                            .map(t -> t.getTrader().getCity())
                                            .collect(Collectors.toSet());
        System.out.println(citySet);
        System.out.println();

        //(3) 查找所有来自于剑桥的交易员，并按姓名排序
        System.out.println("---------(3) 查找所有来自于剑桥的交易员，并按姓名排序--------");
        List<String> nameList = transactions.stream()
                                                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                                                .map(t -> t.getTrader().getName())
                                                .distinct()
                                                .sorted()
                                                .collect(Collectors.toList());
        System.out.println(nameList);

        List<Trader> traderList3 = transactions.stream()
                                                    .map(t -> t.getTrader())
                                                    .filter(t -> t.getCity().equals("Cambridge"))
                                                    .distinct()
                                                    .sorted(Comparator.comparing(Trader::getName))
                                                    .collect(Collectors.toList());
        System.out.println(traderList3);
        System.out.println();



        //(4) 返回所有交易员的姓名字符串，按字母顺序排序
        System.out.println("----------(4) 返回所有交易员的姓名字符串，按字母顺序排序-----------");
        List<String> nameList4 = transactions.stream()
                                                .map(t -> t.getTrader().getName())
                                                .distinct()
                                                .sorted(new Comparator<String>() {
                                                    @Override
                                                    public int compare(String o1, String o2) {
                                                        return o1.compareTo(o2);
                                                    }
                                                })
                                                .collect(Collectors.toList());
        System.out.println(nameList4);

        String nameStr = transactions.stream()
                                        .map(t -> t.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .reduce("",(x,y) -> x + y);
        System.out.println(nameStr);

        String nameStr4 = transactions.stream()
                                        .map(t -> t.getTrader().getName())
                                        .distinct()
                                        .sorted()
                                        .collect(Collectors.joining());
        System.out.println(nameStr4);

        String nameStr5 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining("-"));
        System.out.println(nameStr5);

        String nameStr6 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining("-","$","|"));
        System.out.println(nameStr6);

        System.out.println();

        //(5) 有没有交易员是在米兰工作的
        System.out.println("----------(5) 有没有交易员是在米兰工作的-------------");
        boolean resultBoolean5 = transactions.stream()
                                                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(resultBoolean5);
        System.out.println();

        //(6) 打印生活在剑桥的交易员的所有交易额
        System.out.println("-----------(6) 打印生活在剑桥的交易员的所有交易额-----------");
        List<Integer> transactionList6 = transactions.stream()
                                                            .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                                                            .map(t -> t.getValue())
                                                            .collect(Collectors.toList());
        System.out.println(transactionList6);

        transactions.stream()
                    .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                    .map(t -> t.getValue())
                    .forEach(System.out::println);

        System.out.println();

        //(7) 所有交易中，最高的交易额是多少
        System.out.println("------------(7) 打印生活在剑桥的交易员的所有交易额------------------");
        OptionalInt valueMax = transactions.stream()
                                        .mapToInt(t -> t.getValue())
                                        .max();
        System.out.println(valueMax.isPresent() ? valueMax.getAsInt() : valueMax.orElse(0));

        Optional<Integer> valueMax7 = transactions.stream()
                                    .map(Transaction::getValue)
                                    .reduce(Integer::max);
        System.out.println(valueMax7.isPresent() ? valueMax7.get() : valueMax7.orElse(0));
        System.out.println();

        //(8) 找到交易额最小的交易
        System.out.println("------------(8) 找到交易额最小的交易-------------");
        OptionalInt transactionMinValue = transactions.stream()
                                                .mapToInt(t -> t.getValue())
                                                .min();

        List<Transaction> transactionList8 = transactions.stream()
                                                .filter(t -> t.getValue() == (transactionMinValue.isPresent() ? transactionMinValue.getAsInt() : Integer.MIN_VALUE))
                                                .collect(Collectors.toList());
        System.out.println(transactionList8);

        Optional<Transaction> transactionMin = transactions.stream()
                                                    .reduce((x,y) -> x.getValue() > y.getValue() ? y : x);
        System.out.println(transactionMin.isPresent() ? transactionMin.get() :"not find");

        Optional<Transaction> transactionMin3 = transactions.stream()
                                                                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(transactionMin3.isPresent() ? transactionMin3.get() :"not find");

        Optional<Transaction> transactionMin4 = transactions.stream()
                                        .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(transactionMin4.isPresent() ? transactionMin4.get() : "not find");


    }
}
