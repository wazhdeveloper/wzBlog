package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wazh
 * @since 2023-10-21-20:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageTagVo {
    private Long id;
    private String name;
    private String remark;
}
