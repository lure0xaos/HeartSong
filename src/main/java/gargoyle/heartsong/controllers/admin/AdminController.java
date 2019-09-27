package gargoyle.heartsong.controllers.admin;

import gargoyle.heartsong.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final String PATH_ROOT = "/";
    private static final String VIEW_ADMIN_INDEX = "admin/index";
    private final AppService service;

    @GetMapping(PATH_ROOT)
    public ModelAndView getIndex() {
        return new ModelAndView(VIEW_ADMIN_INDEX);
    }

}
