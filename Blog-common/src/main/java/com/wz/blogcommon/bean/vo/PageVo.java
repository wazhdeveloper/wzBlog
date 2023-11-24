package com.wz.blogcommon.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    private List<?> rows;
    private Long total; //原是Long 可能会出错
}
