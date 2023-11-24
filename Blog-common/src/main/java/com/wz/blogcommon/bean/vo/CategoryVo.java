package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wazh
 * @since 2023-10-10-19:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = -684979413121667710L;

    private Long id;

    private String name;

    private String description;

}
