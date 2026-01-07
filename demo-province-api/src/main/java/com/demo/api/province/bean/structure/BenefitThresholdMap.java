package com.sama.api.ledger.bean.structure;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 效益类间阈值组
 * @author: huxh
 * @description:
 * @datetime: 2025/10/22 16:23
 */
public class BenefitThresholdMap extends LinkedHashMap<String, CategorizedThresholdPair> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8618996234067320764L;

}
