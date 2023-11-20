package com.etammag.dreamlighter.common.mbpg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class PlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://192.168.200.100:3306/dream_lighter?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                        "root", "Root_123")
                .globalConfig(builder -> {
                    builder
                            .disableOpenDir()
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\CodeProject\\IntelliJ_IDEA\\DreamLighter\\src\\main\\java") // 指定输出目录
                    ;
                })
                .packageConfig(builder -> {
                    builder.parent("com.neu") // 设置父包名
                            .moduleName("dreamlighter") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\CodeProject\\IntelliJ_IDEA\\DreamLighter\\src\\main\\resources\\mapper")) // 设置mapperXml生成路径
                    ;
                })
                .strategyConfig(builder -> {
                    builder// 设置需要生成的表名
//                            .addInclude("chat")
//                            .addInclude("donor")
//                            .addInclude("donor_kid_donation")
//                            .addInclude("donor_kid_thing")
//                            .addInclude("donor_project")
//                            .addInclude("donor_project_donation")
//                            .addInclude("donor_project_to_type")
//                            .addInclude("donor_project_type")
//                            .addTablePrefix("donor_")
//                            .addInclude("kid")
//                            .addInclude("kid_award")
//                            .addInclude("kid_award_exchange")
//                            .addInclude("kid_award_like")
//                            .addInclude("kid_award_to_type")
//                            .addInclude("kid_award_type")
//                            .addInclude("kid_mission")
//                            .addInclude("kid_reply")
//                            .addInclude("kid_reply_hot")
//                            .addInclude("kid_reply_hot_comment")
//                            .addInclude("kid_reply_hot_like")
//                            .addInclude("kid_school")
//                            .addInclude("kid_to_mission")
//                            .addInclude("kid_to_type")
//                            .addInclude("kid_type")
//                            .addTablePrefix("kid_")
//                            .addInclude("volun_article")
//                            .addInclude("volun_article_comment")
//                            .addInclude("volun_article_like")
//                            .addInclude("volunteer")
//                            .addTablePrefix("volun_")
//                            .addInclude("volun_to_kid")
//                            .addInclude("user_donor")
//                            .addInclude("user_kid")
//                            .addInclude("user_volunteer")
//                            .addInclude("volun_to_mission")
                            .addInclude("volun_article_love")
                            .addTablePrefix("volun_")
                            .entityBuilder()
                            .enableLombok() //使用lombok
                            .disableSerialVersionUID()
                            //.enableTableFieldAnnotation()
//                                    .addTableFills(Arrays.asList(
//                                            new Column("create_time", FieldFill.INSERT),
//                                            new Column("create_user", FieldFill.INSERT),
//                                            new Column("update_time", FieldFill.INSERT_UPDATE),
//                                            new Column("update_user", FieldFill.INSERT_UPDATE)
//                                    ))
//                                .logicDeleteColumnName("is_deleted")
//                            .fileOverride()
                            .controllerBuilder()
                            .enableRestStyle()
                            //.fileOverride()
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            //.enableBaseColumnList()
                            .fileOverride()
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                    //.fileOverride()
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}