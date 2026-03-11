package com.jzxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.House;
import com.jzxy.pojo.Owner;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 龙殇
 * @version 1.0
 * @description 房产管理接口类
 * @date 2023/3/9 14:32
 */

public interface IHouseService extends IService<House> {

    /**
     * @description 查看所有房产信息(分页 )
     * @param pageParameter 分页参数
     * @param house 条件查询参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 15:12
     */
    Result getHouseListByPage(House house, PageParameter pageParameter);

    /**
     * @description 删除房产信息
     * @param houseIds  房产信息的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 16:46
     */
    Result deleteHouse(Integer[] houseIds);

    /**
     * @description 新增/更新 房产信息
     * @param house 房产信息
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 17:20
     */
    Result addHouse(@RequestBody House house);

    /**
     * @description 根据Id查找房产信息
     * @param id 房产信息Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 17:23
     */
    Result getHouseById(Integer id);

    /**
    * @description 根据业主姓名和电话号码查询房产信息
    * @param owner 业主信息
    * @author 龙殇
    * @date 2023/4/7 13:53
    */
    House getHouseByOwnerInfo(Owner owner);
}
