package com.mailbackstage.service;

import com.mailbackstage.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
public interface MemberReadHistoryService {

    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int deleteList(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}