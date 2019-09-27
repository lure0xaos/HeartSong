package gargoyle.heartsong.services;

import org.markdownj.MarkdownProcessor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MD {
    private static final Pattern PATTERN_LINES = Pattern.compile("\n", Pattern.LITERAL);
    private static final String BR = "<br/>";
    private final MarkdownProcessor processor;

    public MD() {
        processor = new MarkdownProcessor();
    }

    public String toHtml(String source) {
        return PATTERN_LINES.matcher(processor.markdown(source)).replaceAll(Matcher.quoteReplacement(BR));
    }
}
