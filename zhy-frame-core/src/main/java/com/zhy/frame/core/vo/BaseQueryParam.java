/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhy.frame.core.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
@ApiModel("查询参数对象")
public abstract class BaseQueryParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @ApiModelProperty(value = "页码,默认为1")
	private Integer current = 1;
	@ApiModelProperty(value = "页大小,默认为10")
	private Integer size = 10;
    @ApiModelProperty(value = "搜索字符串")
    private String keyword;

    public void setCurrent(Integer current) {
	    if (current == null || current <= 0){
	        this.current = 1;
        }else{
            this.current = current;
        }
    }

    public void setSize(Integer size) {
	    if (size == null || size <= 0){
	        this.size = 10;
        }else{
            this.size = size;
        }
    }

}
