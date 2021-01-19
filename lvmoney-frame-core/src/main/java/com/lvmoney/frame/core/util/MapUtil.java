/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lvmoney.frame.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */
public class MapUtil {

    private Map<String, Object> map;

    private MapUtil() {
        map = new HashMap<>();
    }

    public static MapUtil builder() {
        return new MapUtil();
    }

    public MapUtil put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return this.map;
    }

    /**
     * 初始化map的大小
     *
     * @param sum:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/1/21 10:25
     */
    public static int initMapSize(int sum) {
        return (int) (sum / 0.75 + 1);
    }

}
