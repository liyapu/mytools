package com.lyp.learn.dict;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lyp.learn.dict.bean.PoiReturnTypeBO;
import com.lyp.learn.dict.bean.PoiReturnTypeDictBO;
import com.lyp.learn.dict.bean.QueryDictBO;
import com.lyp.learn.utils.JsonUtil;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommonDictService {


    public List<PoiReturnTypeDictBO> getDictList(QueryDictBO queryDictBO) {
        String dictStr = "[{\"poiType\":1,\"poiBizBranchMark\":0,\"poiTypeName\":\"河南省\",\"hiddenClientChannelList\":[3],\"hiddenOperationTypeList\":[2],\"hiddenCreateBillTypeList\":[1],\"poiReturnType\":[{\"returnType\":1,\"returnTypeName\":\"商丘市\"},{\"returnType\":2,\"returnTypeName\":\"郑州市\"},{\"returnType\":3,\"returnTypeName\":\"开封市\"}],\"poiReturnSource\":[{\"billSource\":1,\"billSourceName\":\"大学生\"},{\"billSource\":2,\"billSourceName\":\"高中生\"},{\"billSource\":3,\"billSourceName\":\"中学生\"}]},{\"poiType\":2,\"poiBizBranchMark\":0,\"poiTypeName\":\"北京市\",\"poiReturnType\":[{\"returnType\":1,\"returnTypeName\":\"朝阳区\"},{\"returnType\":2,\"returnTypeName\":\"海定区\"},{\"returnType\":3,\"returnTypeName\":\"东城区\",\"hiddenOperationTypeList\":[2]}]}]";
        List<PoiReturnTypeDictBO> poiReturnTypeDictList = JsonUtil.parse(dictStr,
            new TypeReference<List<PoiReturnTypeDictBO>>() {
            });

        poiReturnTypeDictList = poiReturnTypeDictList.stream()
            .filter(poiDict -> !hiddenPoiType(poiDict, queryDictBO))
            .map(poiDict -> hiddenReturnType(poiDict, queryDictBO))
            .collect(Collectors.toList());

        return poiReturnTypeDictList;
    }


    private boolean hiddenPoiType(PoiReturnTypeDictBO poiDict, QueryDictBO queryDictBO) {
        return allHidden(hidden(poiDict.getHiddenClientChannelList(), queryDictBO.getClientChannel()),
            hidden(poiDict.getHiddenCreateBillTypeList(), queryDictBO.getCreateBillType()),
            hidden(poiDict.getHiddenOperationTypeList(), queryDictBO.getOperationType()));
    }

    /**
     * 先把不配置的隐藏字段的特殊情况过滤掉，然后剩下的都要隐藏才隐藏
     */
    @SafeVarargs
    private final boolean allHidden(Optional<Boolean>... values) {
        final List<Optional<Boolean>> optList = Stream
            .of(values)
            .filter(Optional::isPresent)
            .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(optList)) {
            return false;
        }
        final List<Optional<Boolean>> andList = optList.stream()
            .filter(Optional::get)
            .collect(Collectors.toList());
        return Objects.equals(optList.size(), andList.size());
    }

    /**
     * 隐藏列表字段不配置和配置但是没有匹配上，需要区分对待，不然上层做 &&，|| 运算会有影响
     */
    private Optional<Boolean> hidden(List<Integer> hiddenIdList, Integer hiddenId) {
        if (CollectionUtils.isEmpty(hiddenIdList)) {
            return Optional.empty();
        }
        return Optional.of(hiddenIdList.contains(hiddenId));
    }

    private PoiReturnTypeDictBO hiddenReturnType(PoiReturnTypeDictBO poiDict, QueryDictBO queryDictBO) {
        List<PoiReturnTypeBO> poiReturnTypeList = poiDict.getPoiReturnType().stream()
            .filter(returnType -> !hiddenReturnTypePredicate(returnType, queryDictBO))
            .collect(Collectors.toList());
        poiDict.setPoiReturnType(poiReturnTypeList);
        return poiDict;
    }

    private boolean hiddenReturnTypePredicate(PoiReturnTypeBO returnType, QueryDictBO queryDictBO) {
        return allHidden(hidden(returnType.getHiddenClientChannelList(), queryDictBO.getClientChannel()),
            hidden(returnType.getHiddenCreateBillTypeList(), queryDictBO.getCreateBillType()),
            hidden(returnType.getHiddenOperationTypeList(), queryDictBO.getOperationType()));
    }


}
