package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler implements BaseHandler {
    private static final Pattern WORD_AND_PUNCTUATION_MARK_PATTERN = Pattern.compile("((\\w)+)|(\\.{3}|\\?!|!\\?|-|[.,!:;?])");
    private static final Pattern WORD_PATTERN = Pattern.compile("\\w+");

    private WordHandler wordHandler = new WordHandler();
    private PunctuationMarkHandler signHandler = new PunctuationMarkHandler();

    @Override
    public LiteTextComponent parse(String text) {
        LiteTextComponent wordAndPunctuationMarkComponent = new LiteTextComposite();
        Matcher matcher = WORD_AND_PUNCTUATION_MARK_PATTERN.matcher(text);
        while (matcher.find()) {
            Matcher wordMatcher = WORD_PATTERN.matcher(matcher.group());
            if ( wordMatcher.matches() ) {
                wordAndPunctuationMarkComponent.add(wordHandler.parse(matcher.group()));
            } else {
                wordAndPunctuationMarkComponent.add(signHandler.parse(matcher.group()));
            }
        }
        return wordAndPunctuationMarkComponent;
    }
}
