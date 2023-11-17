package com.lyp.learn.other.rules;

import static com.lyp.learn.other.rules.RuleConstant.MATCH_ADDRESS_START;

/**
 * @author liyapu
 * @date 2023-11-17 22:19
 * @description
 */
// 具体规则- 例子1
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        if (dto.getAddress().startsWith(MATCH_ADDRESS_START)) {
            return true;
        }
        return false;
    }
}