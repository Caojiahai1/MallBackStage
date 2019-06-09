package com.mailbackstage.nosql.mongodb.repository;

import com.mailbackstage.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/9
 * @since 1.0.0
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String>{
    /**
     * 根据会员id按时间倒序获取浏览记录
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

}