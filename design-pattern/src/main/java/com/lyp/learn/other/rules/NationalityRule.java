package com.lyp.learn.other.rules;

import static com.lyp.learn.other.rules.RuleConstant.MATCH_ADDRESS_START;
import static com.lyp.learn.other.rules.RuleConstant.MATCH_NATIONALITY_START;

/**
 * @author liyapu
 * @date 2023-11-17 22:19
 * @description
 */
// 具体规则- 例子2
public class NationalityRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleDto dto) {
        NationalityRuleDto nationalityRuleDto = new NationalityRuleDto();
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            nationalityRuleDto.setNationality(MATCH_NATIONALITY_START);
        }
        return (T) nationalityRuleDto;
    }


    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke!");
        NationalityRuleDto nationalityRuleDto = (NationalityRuleDto) t;
        if (nationalityRuleDto.getNationality().startsWith(MATCH_NATIONALITY_START)) {
            return true;
        }
        return false;
    }
}
