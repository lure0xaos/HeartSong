package gargoyle.heartsong.controllers.admin;

import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.user.MutableUser;
import gargoyle.heartsong.services.admin.AdminService;
import gargoyle.heartsong.util.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(AdminUserController.PATH_ROOT)
@RequiredArgsConstructor
public class AdminUserController {

    public static final String PATH_ROOT = "/admin/user";
    private static final String PATH_INDEX = "/";
    private static final String PATH_CREATE = "/create";
    private static final String PATH_EDIT = "/edit/";
    private static final String PATH_EDIT_W = "/edit/{username}";
    private static final String PATH_DELETE_W = "/delete/{username}";
    private static final String PATH_KICK_W = "/kick/{username}";

    private static final String VIEW_ADMIN_USER_INDEX = "admin/user/index";
    private static final String VIEW_ADMIN_USER_EDIT = "admin/user/edit";

    private static final String MODEL_PASSWORD_ENCODER = "passwordEncoder";
    private static final String MODEL_USER = "user";
    private static final String MODEL_USERS = "users";
    private static final String MODEL_SESSIONS = "sessions";

    private static final String PARAM_USERNAME = "username";

    private static final String MAPPING_INDEX = "admin.user.index";
    private static final String MAPPING_CREATE = "admin.user.create";
    private static final String MAPPING_EDIT = "admin.user.edit";

    private final PasswordEncoder passwordEncoder;
    private final AdminService service;

    @ModelAttribute(MODEL_USER)
    public UserDetails createUser() {
        return new MutableUser(null, passwordEncoder);
    }

    @ModelAttribute(MODEL_PASSWORD_ENCODER)
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    @GetMapping(name = MAPPING_INDEX, value = PATH_INDEX)
    public ModelAndView getIndex() {
        List<UserDetails> users = service.getUsers();
        return new ModelAndView(VIEW_ADMIN_USER_INDEX, Maps.of(
                MODEL_USERS, users
        ));
    }

    @GetMapping(name = MAPPING_CREATE, value = PATH_CREATE)
    public ModelAndView getCreate() {
        UserDetails user = service.createUser();
        List<Object> sessions = Collections.emptyList();
        return new ModelAndView(VIEW_ADMIN_USER_EDIT, Maps.of(
                MODEL_USER, user,
                MODEL_SESSIONS, sessions
        ));
    }

    @GetMapping(name = MAPPING_EDIT, value = PATH_EDIT_W)
    public ModelAndView getEdit(@PathVariable(PARAM_USERNAME) String username) {
        UserDetails user = service.findUserByUsername(username).orElseThrow(() -> new ItemNotFoundException("user", username));
        List<Session> sessions = service.findSessionsByUsername(username);
        return new ModelAndView(VIEW_ADMIN_USER_EDIT, Maps.of(
                MODEL_USER, user,
                MODEL_SESSIONS, sessions
        ));
    }

    @PostMapping(PATH_EDIT)
    public String onCreate(MutableUser user) {
        service.saveUser(user);
        String username = user.getUsername();
//        return "redirect:/admin/user/edit/" + username;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, username)
                .arg(1, user)
                .build();
    }

    @PostMapping(PATH_EDIT_W)
    public String onEdit(@PathVariable(PARAM_USERNAME) String username, MutableUser user) {
        service.saveUser(user);
//        return "redirect:/admin/user/edit/" + username;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, username)
                .arg(1, user)
                .build();
    }

    @PostMapping(PATH_DELETE_W)
    public String onDelete(@PathVariable(PARAM_USERNAME) String username) {
        service.deleteUser(username);
//        return "redirect:/admin/user/";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_INDEX)
                .build();
    }

    @PostMapping(PATH_KICK_W)
    public String onKick(@PathVariable(PARAM_USERNAME) String username) {
        service.kickUser(username);
//        return "redirect:/admin/user/";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_INDEX)
                .build();
    }

}
