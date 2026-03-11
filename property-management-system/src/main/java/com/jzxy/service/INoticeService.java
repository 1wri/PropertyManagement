package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Notice;

/**
 * @author 龙殇
 * @version 1.0
 * @description 通知公告服务接口
 * @date 2023/3/6 16:03
 */

public interface INoticeService extends IService<Notice> {

    /**
     * @description 查看所有通知公告(分页 )
     * @param noticeTitle  公告标题
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 14:01
     */
    Result getNoticeListByPage(String noticeTitle, PageParameter pageParameter);


    /**
     * @description 删除通知公告
     * @param noticeIds 通知公告的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 8:56
     */
     Result deleteNotice(int[] noticeIds);

    /**
     * @description 发布/更新 通知公告
     * @param notice 通知公告的内容
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 11:27
     */
    Result addNotice(Notice notice);

    /**
     * @description 根据Id查询通知公告
     * @param id 通知公告Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 12:18
     */
    Result getNoticeById(Integer id);
}
