package com.wz.blogforeground.controller;

import com.wz.blogcommon.annotation.SystemLog;
import com.wz.blogcommon.bean.Link;
import com.wz.blogcommon.bean.vo.LinkVo;
import com.wz.blogcommon.result.ResponseResult;
import com.wz.blogcommon.utils.BeanCopyUtil;
import com.wz.blogforeground.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-12-9:33
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @SystemLog(businessName = "获取所有友链")
    public ResponseResult<?> getAllLink() {
        List<Link> links = linkService.getAllLink();
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
