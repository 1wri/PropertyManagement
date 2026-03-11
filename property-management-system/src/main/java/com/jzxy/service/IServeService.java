package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Serve;

/**
 * @author 龙殇
 * @version 1.0
 * @description 上门服务接口
 * @date 2023/3/15 9:30
 */
public interface IServeService extends IService<Serve> {

    /**
     * @description 查看所有上门服务信息(分页 )
     * @param serve 条件查询参数
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 9:38
     */
    Result getServeListByPage(Serve serve, PageParameter pageParameter);

    /**
     * @description 根据业主信息查询对应的上门服务信息
     * @param serve 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/7 17:18
     */
    Result selectServerByOwner(Serve serve);

    /**
     * @description 根据业主信息获得上门服务的描述信息和提交时间（用于工单评价功能）
     * @param serve 业主基本信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 10:14
     */
    Result getBasicInfo(Serve serve);

    /**
     * @description 根据业主信息查询历史评价信息
     * @param serve 业主基本信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/4/8 12:57
     */
    Result getHistoryInfo(Serve serve);
}
