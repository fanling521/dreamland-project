package com.fanling.dreamland.controller.api;

import com.fanling.common.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "附件对外API")
@RestController
@RequestMapping("/file/attachment")
public class AttachmentApiController extends BaseController {

}
