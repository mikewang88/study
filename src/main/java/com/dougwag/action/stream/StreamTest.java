package com.dougwag.action.stream;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: MikeWang
 * @Date: 2020/6/14 2:43 PM
 * @Description:
 */
public class StreamTest {
    /**
     *  单次批量请求仅能支持irisRequests size为10
     *
     * @param bus
     * @param splitSize
     * @return
     */
//    private List<IrisQuery> splitList(final InterestRadarBatchBus bus, final int splitSize) {
//        List<RadarEntity> entities = bus.getRadarEntitys();
//
//        if (CollectionUtils.isEmpty(entities)) {
//            return Collections.emptyList();
//        }
//        final int maxRound = maxRound(bus.getRadarEntitys().size());
//        return Stream.iterate(0, n -> n + 1)
//                .limit(maxRound)
//                .map(round -> entities.parallelStream()
//                        .skip(round * splitSize)
//                        .limit(splitSize)
//                        .map(item -> buildIrisRequestParam(item, bus.getCodeType(), bus.getEncryptType()))
//                        .collect(Collectors.toList())
//                )
//                .filter(b -> !b.isEmpty())
//                .map(item -> buildIrisQueryParam(bus, item))
//                .collect(Collectors.toList());
//    }
}
