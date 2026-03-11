package com.jzxy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.common.ReturnPageData;
import com.jzxy.mapper.NoticeMapper;
import com.jzxy.pojo.Notice;
import com.jzxy.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 龙殇
 * @version 1.0
 * @description 通知公告服务类
 * @date 2023/3/6 16:03
 */

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @description 查看所有通知公告(分页 )
     * @param noticeTitle  公告标题
     * @param pageParameter 分页参数
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 14:01
     */
    @Override
    public Result getNoticeListByPage(String noticeTitle, PageParameter pageParameter) {
        //设置分页参数
        IPage<Notice> page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());

        //设置条件查询的条件
        LambdaQueryWrapper<Notice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Notice::getTitle, noticeTitle).orderByDesc(Notice::getCreateDate);


        //根据分页参数查询
        page = noticeMapper.selectPage(page, queryWrapper);

        //查询的总条数
        double total = page.getTotal();
        //总的页面数
        double pageCount = Math.ceil(total / pageParameter.getPageSize());
        //判断当前查询的页面数是否大于总页面数
        if (pageParameter.getCurrentPage() > pageCount){
            //大于页面总数
            //把当前页数置为 1
            pageParameter.setCurrentPage(1);
            page = new Page<>(pageParameter.getCurrentPage(), pageParameter.getPageSize());
            //重新查询第一页数据
            page = noticeMapper.selectPage(page, queryWrapper);
            //把当前页数置为 1
            page.setCurrent(1);
        }

        ReturnPageData<Notice> data = new ReturnPageData<>();
        data.setData(page.getRecords());
        data.setTotal(page.getTotal());
        data.setCurrentPage(page.getCurrent());

        return CollectionUtil.isEmpty(page.getRecords())?
                Result.fail(data, "查找的数据不存在，请重新输入") : Result.success(data);
    }

    /**
     * @description 删除通知公告
     * @param noticeIds 通知公告的Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/8 8:56
     */
    @Override
    public Result deleteNotice(int[] noticeIds ) {

        ArrayList<Integer> noticeList = new ArrayList<>(noticeIds.length);

        for (int noticeId : noticeIds) {
            noticeList.add(noticeId);
        }

        int flag = noticeMapper.deleteBatchIds(noticeList);

        return flag > 0? Result.success():Result.fail("");
    }

    /**
     * @description 发布/更新 通知公告
     * @param notice 通知公告的内容
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 11:27
     */
    public Result addNotice(Notice notice){
        return saveOrUpdate(notice) ? Result.success() : Result.fail();
    }

    /**
     * @description 根据Id查询通知公告
     * @param id 通知公告Id
     * @return com.jzxy.common.Result
     * @author 龙殇
     * @date 2023/3/9 12:18
     */
    public Result getNoticeById(Integer id){
        Notice notice = noticeMapper.selectById(id);

        if (BeanUtil.isEmpty(notice)) {
            return Result.fail();
        }

        return Result.success(notice);
    }
}
