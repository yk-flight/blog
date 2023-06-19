package com.zrkizzy.web.controller.system;

import com.zrkizzy.data.domain.Link;
import com.zrkizzy.data.dto.LinkDTO;
import com.zrkizzy.data.query.LinkQuery;
import com.zrkizzy.data.vo.LinkVO;
import com.zrkizzy.server.service.system.ILinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 友情链接数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/6/19
 */
@Api(tags = "友情链接控制器")
@RestController
@RequestMapping("/admin/link")
public class LinkController {

     @Autowired
     private ILinkService linkService;


}
