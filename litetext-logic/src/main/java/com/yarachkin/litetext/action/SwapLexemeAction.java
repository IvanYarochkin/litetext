package com.yarachkin.litetext.action;

import com.yarachkin.litetext.chain.impl.TextHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;
import com.yarachkin.litetext.exception.ActionLiteTextException;
import com.yarachkin.litetext.exception.ChainLiteTextException;
import com.yarachkin.litetext.exception.CompositeLiteTextException;

public class SwapLexemeAction {

    public static LiteTextComponent swapLexeme(String text) throws ActionLiteTextException {
        try {
            TextHandler textHandler = new TextHandler();
            LiteTextComponent component = textHandler.parse(text);
            for (LiteTextComponent paragraph : component.getComponents()) {
                handleSentences(paragraph);
            }

            return component;
        } catch (ChainLiteTextException | CompositeLiteTextException e) {
            throw new ActionLiteTextException(e);
        }
    }

    private static void handleSentences(LiteTextComponent paragraph) throws CompositeLiteTextException {
        for (LiteTextComponent sentence : paragraph.getComponents()) {
            if ( sentence.getClass().getSimpleName().equals("LiteTextComposite") ) {
                LiteTextComposite firstLexeme = new LiteTextComposite();
                LiteTextComposite lastLexeme = new LiteTextComposite();
                int firstElementIndex = 0;
                int lastElementIndex = 0;

                for (int i = 0; i < sentence.getComponents().size(); i++) {
                    if ( sentence.getComponents().get(i).getClass().getSimpleName().equals("LiteTextComposite")
                            && firstLexeme.getComponents().isEmpty() ) {
                        firstLexeme = (LiteTextComposite) sentence.getChild(i);
                        firstElementIndex = i;
                    }
                    if ( sentence.getComponents().get(i).getClass().getSimpleName().equals("LiteTextComposite") ) {
                        lastLexeme = (LiteTextComposite) sentence.getChild(i);
                        lastElementIndex = i;
                    }
                }
                sentence.getComponents().set(firstElementIndex, lastLexeme);
                sentence.getComponents().set(lastElementIndex, firstLexeme);
            }
        }
    }
}
