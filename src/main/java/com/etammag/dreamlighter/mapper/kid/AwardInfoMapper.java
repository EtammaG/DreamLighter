package com.etammag.dreamlighter.mapper.kid;

import com.etammag.dreamlighter.entity.kid.AwardDto;
import com.etammag.dreamlighter.entity.kid.AwardExchangeDto;
import com.etammag.dreamlighter.entity.kid.db.Award;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@Page
public interface AwardInfoMapper {
    List<AwardDto> search(@Param("id") Long id, @Param("name") String name, @Param("typeId") Long typeId);

    List<Award> selectLike(Long id);

    List<AwardExchangeDto> selectExchanges(Long id);

}
