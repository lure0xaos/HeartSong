package gargoyle.heartsong.advices;

import gargoyle.heartsong.dao.app.BandDao;
import gargoyle.heartsong.model.app.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class LayoutAdvice {
    private final BandDao bandDao;

    @ModelAttribute("countByLetter")
    public Map<Letter, Integer> countByLetter() {
        return bandDao.countByLetter();
    }

    @ModelAttribute("applicationLanguage")
    public String getApplicationLanguage() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

}
