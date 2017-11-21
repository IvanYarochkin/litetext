package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler implements BaseHandler {
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("(?<=\t).*");

    private SentenceHandler sentenceHandler = new SentenceHandler();

    @Override
    public LiteTextComponent parse(String text) {
        LiteTextComponent paragraphs = new LiteTextComposite();
        Matcher matcher = PARAGRAPH_PATTERN.matcher(text);
        while (matcher.find()) {
            paragraphs.add(sentenceHandler.parse(matcher.group()));
        }
        return paragraphs;
    }
}
