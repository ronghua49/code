package com.haohua.controllor;    /*
 * @author  Administrator
 * @date 2018/7/23
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haohua.entity.Parts;
import com.haohua.entity.Type;
import com.haohua.exception.NotFoundException;
import com.haohua.service.CarServiceImpl;
import com.haohua.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/car/parts")
public class CarControllor {

    @Autowired
    private CarServiceImpl carServiceImpl;

    /**
     * 根据零件id查询parts对象的详情
     * @param id  零件的id
     * @param model 向前端发送值的对象
     * @return parts的详情页
     * @throws Exception
     */
    @GetMapping("/{id:\\d+}")
    public String findPartsById(@PathVariable Integer id, Model model){
        try{
           Parts parts = carServiceImpl.findById(id);
           model.addAttribute("parts",parts);
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
            model.addAttribute("message",e.getMessage());
        }
        return "car/partDetail";
    }

    /**
     * 根据页码 查询parts的详情页
     * @param pageNo 要查看的parts对象的页码
     * @param model 向页面发送 对应的详情页面
     * @return List<parts> 对象集合
     */
    @GetMapping
    public String findPartPage(@RequestParam (name = "p",defaultValue = "1",required = false)Integer pageNo,
                                @RequestParam(required = false) String partsName,
                               @RequestParam(required = false) Integer partsType,
                               @RequestParam(required = false) String message,
                               Model model){
        Map<String ,Object> paramMap= new HashMap<>();
        paramMap.put("partsName",partsName);
        paramMap.put("partsType",partsType);

        PageInfo<Parts> page = carServiceImpl.findWithTypeByPageNoAndMap(pageNo,paramMap);
        List<Type> typeList = carServiceImpl.findTypeList();

        model.addAttribute("typeList",typeList);
        model.addAttribute("page",page);
        model.addAttribute("message",message);
        return "car/partlist";
    }
    @GetMapping("/new")
    public String addPart(Model model){
        List<Type> typeList = carServiceImpl.findTypeList();
        model.addAttribute("typeList",typeList);
        return "car/addParts";
    }
    @PostMapping("/new")
    @ResponseBody
    public Integer addParts(Parts parts){
            //增加新零部件
        Integer res= carServiceImpl.addNewParts(parts);
        return res ;
    }
    @GetMapping("/{partsId:\\d+}/del")
    public String delParts(@PathVariable Integer partsId,  RedirectAttributes redirectAttributes){
            Integer res =carServiceImpl.delPartsById(partsId);
            if (res!=0){
                redirectAttributes.addFlashAttribute("message","删除成功");
            }
            return "redirect:/car/parts";
    }
    @GetMapping("/{partsId:\\d+}/edit")
    public String editPartsById(@PathVariable Integer partsId ,Model model){
        Parts parts = carServiceImpl.findById(partsId);
        List<Type> typeList = carServiceImpl.findTypeList();
        if (parts!=null){
            model.addAttribute("parts",parts);
            model.addAttribute("typeList",typeList);
        }else{
           model.addAttribute("message","参数异常");
        }
        return  "car/edit";
    }
    @PostMapping("/edit")
    @ResponseBody
    public Integer editPartsByParts(Parts parts){
            Integer res =carServiceImpl.editPartsByParts(parts);
            return res;
    }


}
