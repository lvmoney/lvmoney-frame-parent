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

package com.lvmoney.frame.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @describe：主键状态
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/7/20 10:38
 */
@ApiModel("主键状态VO")
public class IdStatusParam implements Serializable {
    private static final long serialVersionUID = -7581307955242965701L;

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("状态,1:启用 0:禁用")
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IdStatusVo{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
