package com.dougwag.action.completableFuture;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: MikeWang
 * @Date: 2020/5/26 4:29 PM
 * @Description:
 */
public class Fizz {
    private static final Integer MAX_NUMBER = 10; //优化hbase 测试 ，默认给10个 一批

    private ThreadPoolExecutor exector = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

//    public List<GetResponse> queryIndicatrixBulkV2(IndicatrixHbaseBulkBO indicatrixHbaseBulkBO) {
//        List<GetResponse> responses = null;
//        List<List<GetResponse>> parts = new ArrayList<>();
//        final HBaseClient ugHBaseClient = clientRouter.getHBaseClient("ugHBaseClient");
//        List<String> indicatrixNameList = indicatrixHbaseBulkBO.getIndicatrixNameList();
//        int limit = countStep(indicatrixNameList.size());
//        List<List<String>> mgIndexlist = new ArrayList<>();//分批次请求hbase
//        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
//            mgIndexlist.add(indicatrixNameList.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
//        });
//        logger.info("queryIndicatrixBulkV2 请求分 {} 批次 ", mgIndexlist.size());
//        List<CompletableFuture<List<GetResponse>>> futures = mgIndexlist.stream().map(
//                part->CompletableFuture.supplyAsync(()->getHbaseResultV2(ugHBaseClient,indicatrixHbaseBulkBO,part),exector))
//                .collect(Collectors.toList());
//        parts = futures.stream().map(CompletableFuture::join)
//                .collect(Collectors.toList());
//
//        responses = parts.stream().filter(item->item!=null)
//                .reduce(new ArrayList<>(),(all,item)->{
//                    all.addAll(item);
//                    return all;
//                });
//
//        return responses;
//    }
//    private static List<GetResponse> getHbaseResultV2(final HBaseClient ugHBaseClient,IndicatrixHbaseBulkBO indicatrixHbaseBulkBO,List<String> indexList){
//        List<GetResponse> responses = null;
//
//        try {
//            responses = ugHBaseClient.getBulk(indicatrixHbaseBulkBO.getTableName(), indicatrixHbaseBulkBO.getValue(), indexList);
//        } catch (RejectedExecutionException e) {
//            try {
//                Thread.sleep(200);
//                responses = ugHBaseClient.getBulk(indicatrixHbaseBulkBO.getTableName(), indicatrixHbaseBulkBO.getValue(), indexList);
//            } catch (HbaseClientException | InterruptedException e1) {
//                logger.error("hbase query indicatrix data error ", e);
//            }
//        } catch (HbaseClientException e) {
//            logger.error("hbase query indicatrix data error ", e);
//            return Collections.emptyList();
//        }
//        if (CollectionUtils.isEmpty(responses)) {
//            responses = Collections.emptyList();
//        }
//        return responses;
//    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

}
