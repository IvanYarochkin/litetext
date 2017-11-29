package com.yarachkin.litetext.action;

import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;

import java.util.TreeMap;

public class SortingAction {
    private static TreeMap<Integer, LiteTextComponent> sortedSentences;

    public static TreeMap<Integer, LiteTextComponent> printSentencesInAscendingOrder(LiteTextComponent component) {
        sortedSentences = new TreeMap<>();
        if ( component.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite textComposite = (LiteTextComposite) component;
            textComposite.getComponents().forEach(paragraph -> findSentences(paragraph));
        }
        sortedSentences.entrySet().forEach(element -> System.out.println(element.getKey() + " lexemes :" + element.getValue()));
        return sortedSentences;
    }

    private static void findSentences(LiteTextComponent paragraph) {
        if ( paragraph.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite paragraphComposite = (LiteTextComposite) paragraph;
            paragraphComposite.getComponents().forEach(sentence -> sortedSentences.put(findLexemes(sentence), sentence));
        }
    }

    private static int findLexemes(LiteTextComponent sentence) {
        int lexemeCounts = 0;

        if ( sentence.getClass().getSimpleName().equals("LiteTextComposite") ) {
            LiteTextComposite sentenceComposite = (LiteTextComposite) sentence;
            for (LiteTextComponent sentenceElement : sentenceComposite.getComponents()) {
                if ( sentenceElement.getClass().getSimpleName().equals("LiteTextComposite") ) {
                    lexemeCounts++;
                }
            }
        }
        return lexemeCounts;
    }
}
