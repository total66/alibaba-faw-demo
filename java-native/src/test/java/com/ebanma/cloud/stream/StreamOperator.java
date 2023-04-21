package com.ebanma.cloud.stream;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.Sku;
import com.ebanma.cloud.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperator {
  
    List<Sku> list;
  
    @Before
    public void init() {  
        list = BaseCartService.getCartSkuList();
    }  

    //filter 过滤掉不符合断言判断的数据
    @Test
    public void filterTest() {  
        list.stream()  
                .filter(sku ->  
                        SkuCategoryEnum.BOOKS
                                .equals(sku.getSkuCategory()))  
                .forEach(item ->  
                        System.out.println(  
                                JSON.toJSONString(
                                        item, true)));  
    }

    //map使用：将一个元素转换成另一个元素
    @Test
    public void mapTest() {
        list.stream()
                .map(sku -> sku.getSkuName())
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    //flatMap使用：将一个对象转换成流
    @Test
    public void flatMapTest() {
        list.stream()
                .flatMap(sku -> Arrays.stream(
                        sku.getSkuName().split("")))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    //peek使用：对流中元素进行遍历操作，与forEach类似，但不会销毁流元素
    @Test
    public void peek() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    //sort使用：对流中元素进行排序，可选则自然排序或指定排序规则。有状态操作
    @Test
    public void sortTest() {
        list = list.stream()
//                .peek(sku -> System.out.println(sku.getSkuName()))
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .collect(Collectors.toList());
//                .forEach(item ->
//                        System.out.println(
//                                JSON.toJSONString(
//                                        item, true)));
        System.out.println(list.get(0).getTotalPrice());
    }

    //distinct使用：对流元素进行去重。有状态操作
    @Test
    public void distinctTest() {
        System.out.println(list.stream()
                .map(sku -> sku.getSkuCategory())
                .distinct().toArray().toString());
//                .forEach(item ->
//                        System.out.println(
//                                JSON.toJSONString(
//                                        item, true)));
    }

    //skip使用：跳过前N条记录。有状态操作
    @Test
    public void skipTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    //limit使用：截断前N条记录。有状态操作
    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(2 * 3)
                .limit(3)
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(
                                        item, true)));
    }

    //allMatch使用：终端操作，短路操作。所有元素匹配，返回true
    @Test
    public void allMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    //anyMatch使用：任何元素匹配，返回true
    @Test
    public void anyMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    //noneMatch使用：任何元素都不匹配，返回true
    @Test
    public void noneMatchTest() {
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .noneMatch(sku -> sku.getTotalPrice() > 10_000);
        System.out.println(match);
    }

    //findFirst使用：找到第一个
    @Test
    public void findFirstTest() {
        Optional<Sku> optional = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .findFirst();
        System.out.println(
                JSON.toJSONString(optional.get(), true));
    }

    //findAny使用：找任意一个
    @Test
    public void findAnyTest() {
        Optional<Sku> optional = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .findAny();
        System.out.println(
                JSON.toJSONString(optional.get(), true));
    }

    //max使用：求最大值
    @Test
    public void maxTest() {
        OptionalDouble optionalDouble = list.stream()
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    //min使用：求最小值
    @Test
    public void minTest() {
        OptionalDouble optionalDouble = list.stream()
                // 获取总价
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    //count使用：求个数
    @Test
    public void countTest() {
        long count = list.stream()
                .count();
        System.out.println(count);
    }

    //由数值直接构建流
    @Test
    public void streamFromValue() {
        Stream stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.out::println);
    }

    //通过数组构建流
    @Test
    public void streamFromArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    //通过文件生成流
    @Test
    public void streamFromFile() throws IOException {
        // TODO 此处替换为本地文件的地址全路径
        String filePath = "E:\\Note\\阿里培训\\课程\\2023.1.30\\2023.1.30 时间复杂度和空间复杂度.md";
        Stream<String> stream = Files.lines(
                Paths.get(filePath));
        stream.forEach(System.out::println);
    }

    //通过函数生成流（无线流）
    @Test
    public void streamFromFunction() {
        // Stream stream = Stream.iterate(0, n -> n + 2);
        Stream stream = Stream.generate(Math::random);
        stream.limit(100)
                .forEach(System.out::println);
    }

    @Test
    public void BigDec(){
        BigDecimal bigDecimal = new BigDecimal(12.6);
        BigDecimal bigDecimal1 = new BigDecimal("12.6");
        System.out.println(bigDecimal.equals(bigDecimal1));
        System.out.println("----");
    }

}