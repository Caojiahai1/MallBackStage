package com.mailbackstage.service.impl;

import com.mailbackstage.nosql.mongodb.document.MemberReadHistory;
import com.mailbackstage.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.mailbackstage.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService{

    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int deleteList(List<String> ids) {
        if (ids != null && ids.size() > 0) {
            List<MemberReadHistory> deleteList = new ArrayList<>();
            for (String id : ids) {
                MemberReadHistory memberReadHistory = new MemberReadHistory();
                memberReadHistory.setId(id);
                deleteList.add(memberReadHistory);
            }
            memberReadHistoryRepository.deleteAll(deleteList);
            return ids.size();
        }
        return 0;
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}