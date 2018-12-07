package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/12/6
 */

import com.haohua.service.serviceImpl.WiexinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class WinxinController {
    private static final String TOKEN = "abc";

    /**
     * signature	微信加密签名
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     *
     * @return 加密后字符串
     */
    @GetMapping("/identify")
    @ResponseBody
    public String checkSignature(@RequestParam String signature, @RequestParam String timestamp,
                                 @RequestParam String nonce, @RequestParam String echostr) throws IOException {
        String checkSignatuer = WiexinService.check(TOKEN, timestamp, nonce);
        if (checkSignatuer.equalsIgnoreCase(signature)) {
            return echostr;
        } else {
            return "error";
        }
    }
}
