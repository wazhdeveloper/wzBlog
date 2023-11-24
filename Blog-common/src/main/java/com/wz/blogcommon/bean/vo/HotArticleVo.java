package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wazh
 * @since 2023-10-09-21:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo implements Serializable {

    private static final long serialVersionUID = -6849794412314667710L;
    private Long id;

    private String title;

    private Long viewCount;


}
