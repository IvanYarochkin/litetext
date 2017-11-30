package com.yarachkin.litetext.action;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;
import com.yarachkin.litetext.exception.ActionLiteTextException;

public class ReshuffleLexemeAction {

    public static LiteTextComponent reshuffleLexeme(LiteTextComponent component) throws ActionLiteTextException {
        if ( component.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite paragraphs = (LiteTextComposite) component;
            paragraphs.getComponents().forEach(paragraph -> findSentences(paragraph));
        }
        return component;
    }

    private static void findSentences(LiteTextComponent paragraph) {
        if ( paragraph.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite paragraphComposite = (LiteTextComposite) paragraph;
            paragraphComposite.getComponents().forEach(sentence -> findLexemes(sentence));
        }
    }

    private static void findLexemes(LiteTextComponent sentence) {
        if ( sentence.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite sentenceComposite = (LiteTextComposite) sentence;
            LiteTextComposite firstLexeme = new LiteTextComposite();
            LiteTextComposite lastLexeme = new LiteTextComposite();
            int firstElementIndex = 0;
            int lastElementIndex = 0;

            for (int i = 0; i < sentenceComposite.getComponents().size(); i++) {
                if ( sentenceComposite.getComponents().get(i).getClass().getSimpleName().equals("LiteTextComposite")
                        && firstLexeme.getComponents().isEmpty() ) {
                    firstLexeme = (LiteTextComposite) sentenceComposite.getChild(i);
                    firstElementIndex = i;
                }
                if ( sentenceComposite.getComponents().get(i).getClass().getSimpleName().equals("LiteTextComposite") ) {
                    lastLexeme = (LiteTextComposite) sentenceComposite.getChild(i);
                    lastElementIndex = i;
                }
            }
            sentenceComposite.getComponents().set(firstElementIndex, lastLexeme);
            sentenceComposite.getComponents().set(lastElementIndex, firstLexeme);
        }
    }
}
