package com.etammag.dreamlighter.entity.mission;

import com.etammag.dreamlighter.entity.kid.db.Reply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "KidVieDto", description = "志愿者端中每一个任务的未被批改的孩子信息")
public class ReplyVieDto extends Reply {

    @ApiModelProperty("孩子的照片")
    private String kidPic;
    @ApiModelProperty("孩子的名称")
    private String kidName;

}
