package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler implements BaseHandler {
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[A-ZА-Я]((т.д.|т.п.|т.е.)[^.]{3}|[^?!.])*([.]{3}|[.?!])");

    private SentenceHandler sentenceHandler = new SentenceHandler();

    @Override
    public LiteTextComponent parse(String text) {
        LiteTextComponent sentenceComponent = new LiteTextComposite();
        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            sentenceComponent.add(sentenceHandler.parse(matcher.group()));
        }
        return sentenceComponent;
    }
}
