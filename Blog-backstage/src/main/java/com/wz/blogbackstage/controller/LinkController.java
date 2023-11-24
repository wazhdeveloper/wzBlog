package com.wz.blogbackstage.controller;

import com.wz.blogbackstage.service.LinkService;
import com.wz.blogcommon.bean.Link;
import com.wz.blogcommon.bean.vo.PageVo;
import com.wz.blogcommon.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import java.util.List;

/**
 * @author wazh
 * @since 2023-10-26-19:47
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult<?> listLink(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("status") String status
    ) {
        PageVo pageVo = linkService.listLink(pageNum, pageSize, name, status);
        return ResponseResult.okResult(pageVo);
    }

    @PostMapping
    public ResponseResult<?> postLink(@RequestBody Link link) {
        linkService.save(link);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<?> getLink(@PathVariable("id") Long id) {
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }

    @PutMapping
    public ResponseResult<?> putLink(@RequestBody Link link) {
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteLink(@PathVariable("id") Long id) {
        linkService.removeById(id);
        return ResponseResult.okResult();
    }

}
