package com.etammag.dreamlighter.mapper.common.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.common.db.Chat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

    List<String> selectAllMsgByBoth(
            @PathVariable("toId") Long toId,
            @PathVariable("fromId") Long fromId,
            @PathVariable("type") int type);

}
