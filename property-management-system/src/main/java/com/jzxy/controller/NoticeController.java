package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Notice;
import com.jzxy.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 龙殇
 * @version 1.0
 * @description 通知公告Controller
 * @date 2023/3/6 16:00
 */

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
    * @description 查看所有通知公告(分页 )
    * @param noticeTitle  公告标题
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 14:01
    */
    @GetMapping("/list")
    public Result getNoticeListByPage(String noticeTitle, PageParameter pageParameter){
        return noticeService.getNoticeListByPage(noticeTitle, pageParameter);
    }

    /**
    * @description 删除通知公告
    * @param noticeIds 通知公告的Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/8 8:56
    */
    @DeleteMapping("/delete")
    public Result deleteNotice(int[] noticeIds){
        return noticeService.deleteNotice(noticeIds);
    }

    /**
    * @description 发布/更新 通知公告
    * @param notice 通知公告的内容
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 11:27
    */
    @PutMapping("/add")
    public Result addNotice(@RequestBody Notice notice){
        return noticeService.addNotice(notice);
    }

    /**
    * @description 根据Id查询通知公告
    * @param id 通知公告Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/9 12:18
    */
    @GetMapping("/one")
    public Result getNoticeById(Integer id){
        return noticeService.getNoticeById(id);
    }
}
