package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.Reply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {

    void insertScore(Reply reply);
}
