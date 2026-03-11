package com.jzxy;

import cn.hutool.crypto.digest.MD5;
import com.jzxy.common.PageParameter;
import com.jzxy.common.Result;
import com.jzxy.pojo.Notice;
import com.jzxy.service.INoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙殇
 * @version 1.0
 * @description 通知公告测试类
 * @date 2023/3/9 14:29
 */

@SpringBootTest
class NoticeTests {

    @Autowired
    private INoticeService noticeService;

    //测试通知公告的新增
    @Test
    void testNoticeSave() {
        List<Notice> noticeList = new ArrayList<>();
        for (int i = 2; i < 51; i++) {

            Notice notice = new Notice();

            notice.setTitle("天气公告" + i);
            notice.setContent("今晚有大风,请关好门窗");
            notice.setCreateUser("阿狸");
            notice.setUpdateUser("阿狸");

            noticeList.add(notice);
        }
        noticeService.saveBatch(noticeList);
    }

    //测试分页查询
    @Test
    void testPage() {
        Result list = noticeService.getNoticeListByPage("",new PageParameter());

        System.out.println(list.getData());
    }

    //测试MD5加密
    @Test
    void testMD5() {
        System.out.println(MD5.create().digestHex16("123456".getBytes(StandardCharsets.UTF_8)));
    }

}
