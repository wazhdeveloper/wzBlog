package com.wz.blogcommon.bean.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-25-19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private List<MenuDto> children;

    private Long id;

    private String label;

    private Long parentId;

    public MenuDto setChildren(List<MenuDto> children) {
        this.children = children;
        return this;
    }

    public MenuDto setLabel(String label) {
        this.label = label;
        return this;
    }
}
