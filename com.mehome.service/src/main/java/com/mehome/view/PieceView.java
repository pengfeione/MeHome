package com.mehome.view;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IProductService;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/html")
public class PieceView {
    @GetMapping(path = "/piece")
    public String list() {
        return "HousePiece";
    }

    @GetMapping(path = "/piece2")
    public String piece2() {
        return "HousePiece2";
    }
}
