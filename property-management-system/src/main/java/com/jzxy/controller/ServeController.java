package com.jzxy.controller;

import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Serve;
import com.jzxy.service.IServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author 龙殇
 * @version 1.0
 * @description 上门服务Controller
 * @date 2023/3/15 9:32
 */

@RestController
@RequestMapping("/serve")
public class ServeController {

    @Autowired
    private IServeService serveService;

    /**
    * @description 查看所有上门服务信息(分页 )
    * @param serve 条件查询参数
     * @param pageParameter 分页参数
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/15 9:38
    */
    @GetMapping("/list")
    public Result getServeListByPage(Serve serve, PageParameter pageParameter){
        return serveService.getServeListByPage(serve, pageParameter);
    }

    /**
    * @description 新增上门服务
    * @param serve 服务信息
    * @author 龙殇
    * @date 2023/3/16 10:17
    */
    @PostMapping("/add")
    public Result addServerInfo(@RequestBody Serve serve){
        serve.setCommitTime(LocalDate.now());

        if (serveService.save(serve)) {
            return Result.success();
        }

        return Result.fail();
    }

    /**
    * @description 派遣员工进行上门服务
    * @param id 上门服务Id
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/16 12:41
    */
    @PostMapping("/dispatch")
    public Result dispatchEmployee(Integer id){
        boolean flag = serveService.update()
                .eq("id", id)
                .set("ishandle", 2)
                .update();
        return flag ? Result.success() : Result.fail("派遣失败，请稍后再试！！！");
    }

    /**
   * @description 根据业主信息查询对应的上门服务信息
   * @param serve 业主信息
   * @return com.jzxy.common.Result
   * @author 龙殇
   * @date 2023/4/7 17:18
   */
    @GetMapping("/selectByOwner")
    public Result selectServerByOwner(Serve serve){
        return serveService.selectServerByOwner(serve);
    }

    /**
    * @description 根据工单Id修改上门服务状态
    * @param serveId 工单id
    * @date 2023/4/7 22:23
    */
    @PostMapping("/update")
    public Result updateIsHandle(Integer serveId){

        serveService.update()
                .eq("id", serveId)
                .set("ishandle", 1)
                .update();

        return Result.success();
    }

    /**
    * @description 根据业主信息获得上门服务的描述信息和提交时间（用于工单评价功能）
    * @param serve 业主基本信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/4/8 10:14
    */
    @GetMapping("/info")
    public Result getBasicInfo(Serve serve){
        return serveService.getBasicInfo(serve);
    }

    /**
    * @description 根据业主信息查询历史评价信息
    * @param serve 业主基本信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/4/8 12:57
    */
    @GetMapping("/historyInfo")
    public Result getHistoryInfo(Serve serve){
        return serveService.getHistoryInfo(serve);
    }

}
