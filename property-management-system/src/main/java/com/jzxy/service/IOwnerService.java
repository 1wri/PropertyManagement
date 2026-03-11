package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Owner;

/**
 * @author 龙殇
 * @version 1.0
 * @description 业主管理服务接口
 * @date 2023/3/11 8:09
 */
public interface IOwnerService extends IService<Owner> {

    /**
     * @description 查看所有业主信息(分页 )
     * @param owner 分页参数
     * @param pageParameter 条件查询参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 8:36
     */
    Result getOwnerListByPage(Owner owner, PageParameter pageParameter);


    /**
     * @description 删除业主信息
     * @param ownerIds 业主信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 9:00
     */
    Result deleteOwner(Integer[] ownerIds);

    /**
     * @description 新增/更新 房产信息
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 15:36
     */
    Result addOwner(Owner owner);

    /**
     * @description 根据Id查找业主信息
     * @param id 房产信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/11 16:49
     */
    Result getOwnerById(Integer id);

    /**
     * @description 业主登录
     * @param owner 业主信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/15 22:22
     */
    Result ownerLogin(Owner owner);
}
