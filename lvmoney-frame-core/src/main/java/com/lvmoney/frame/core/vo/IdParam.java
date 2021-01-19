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
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @describe：ID参数
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/7/20 10:38
 */
@Data
@ApiModel("ID参数")
public class IdParam implements Serializable {
    private static final long serialVersionUID = -5353973980674510450L;

    @NotBlank(message = "ID不能为空")
    private String id;
}
