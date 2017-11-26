package com.yarachkin.litetext.chain.impl;

import com.yarachkin.litetext.chain.BaseHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceHandler implements BaseHandler {
    private static final Pattern LEXEME_AND_EXPRESSION_PATTERN = Pattern.compile("((\\d|\\+|-\\d|--|i|j|\\*|/|\\()(\\d|\\+|-|i|j|\\*|/|\\(|\\)|\\s)+)|" +
            "(\\w|\\()(\\w|\\.{3}|\\s-|-|\\?!|!\\?|[!?.,:;)])*");
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(\\d|\\+|-\\d|--|i|j|\\*|/|\\()(\\d|\\+|-|i|j|\\*|/|\\(|\\)|\\s)+");

    private LexemeHandler lexemeHandler = new LexemeHandler();
    private ExpressionHandler expressionHandler = new ExpressionHandler();

    @Override
    public LiteTextComponent parse(String text) {
        LiteTextComponent lexemeAndExpressionComponent = new LiteTextComposite();
        Matcher matcher = LEXEME_AND_EXPRESSION_PATTERN.matcher(text);
        while (matcher.find()) {
            Matcher expressionMatcher = EXPRESSION_PATTERN.matcher(matcher.group());
            if ( expressionMatcher.matches() ) {
                lexemeAndExpressionComponent.add(expressionHandler.parse(matcher.group()));
            } else {
                lexemeAndExpressionComponent.add(lexemeHandler.parse(matcher.group()));
            }
        }
        return lexemeAndExpressionComponent;
    }
}
