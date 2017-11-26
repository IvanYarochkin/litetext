package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.chain.exception.ChainLiteTextException;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler implements BaseHandler {
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("(?<=\t).*");

    private ParagraphHandler paragraphHandler = new ParagraphHandler();

    @Override
    public LiteTextComponent parse(String text) throws ChainLiteTextException {
        LiteTextComponent paragraphComponent = new LiteTextComposite();
        paragraphComponent.setFirstAdditionalText("\t");
        paragraphComponent.setLastAdditionalText("\n\n");
        Matcher matcher = PARAGRAPH_PATTERN.matcher(text);
        while (matcher.find()) {
            paragraphComponent.add(paragraphHandler.parse(matcher.group()));
        }
        return paragraphComponent;
    }
}
