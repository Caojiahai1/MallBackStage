package com.mailbackstage.controller;

import com.mailbackstage.common.CommonResult;
import com.mailbackstage.nosql.mongodb.document.MemberReadHistory;
import com.mailbackstage.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return new CommonResult<Integer>().success(true, "创建成功", count);
        } else {
            return new CommonResult<>().success(false, "创建失败");
        }
    }

    @ApiOperation("删除浏览记录")
    @DeleteMapping("/delete")
    public CommonResult<Integer> delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.deleteList(ids);
        if (count > 0) {
            return new CommonResult<Integer>().success(true, "删除成功", count);
        } else {
            return new CommonResult<>().success(false, "删除失败");
        }
    }

    @ApiOperation("展示浏览记录")
    @GetMapping("/list/{memberId}")
    public CommonResult<List<MemberReadHistory>> list(@PathVariable("memberId") Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return new CommonResult<List<MemberReadHistory>>().success(true, "查询成功", memberReadHistoryList);
    }
}