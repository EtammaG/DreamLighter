package com.etammag.dreamlighter.mapper.donor;

import com.etammag.dreamlighter.entity.donor.DonationStaDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Page
public interface DonationStatisticMapper {

    DonationStaDto selectSta(@Param("donorId") Long donorId);

    DonationStaDto selectSta2(@Param("donorId") Long donorId);

    DonationStaDto selectSta2ByDate(@Param("donorId") Long donorId,
                                    @Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    List<DonationStaDto> selectRank(@Param("num") int num);

    List<DonationStaDto> selectRankByDate(@Param("num") int num,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

    Integer selectNumOfKid();
}
