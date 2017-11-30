package com.yarachkin.litetext.action;

import com.yarachkin.litetext.chain.impl.TextHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.LiteTextComposite;
import com.yarachkin.litetext.exception.ActionLiteTextException;
import com.yarachkin.litetext.exception.ChainLiteTextException;
import com.yarachkin.litetext.exception.CompositeLiteTextException;

import java.util.TreeMap;

public class SortingSentenceAction {
    private static TreeMap<Integer, LiteTextComponent> sortedSentences;

    public static TreeMap<Integer, LiteTextComponent> printSentencesInAscendingOrder(String text) throws ActionLiteTextException {
        try {
            sortedSentences = new TreeMap<>();
            TextHandler textHandler = new TextHandler();
            LiteTextComponent textComponent = textHandler.parse(text);
            for (LiteTextComponent paragraph : textComponent.getComponents()) {
                calculateSizeSentences(paragraph);
            }
            sortedSentences.entrySet().forEach(element -> System.out.println(element.getKey() + " lexemes :" + element.getValue()));
            return sortedSentences;
        } catch (ChainLiteTextException | CompositeLiteTextException e) {
            throw new ActionLiteTextException(e);
        }
    }

    private static void calculateSizeSentences(LiteTextComponent paragraph) throws CompositeLiteTextException {
        for (LiteTextComponent sentence : paragraph.getComponents()) {
            int lexemeCounts = 0;

            for (LiteTextComponent sentenceElement : sentence.getComponents()) {
                if ( sentenceElement instanceof LiteTextComposite ) {
                    lexemeCounts++;
                }
            }
            sortedSentences.put(lexemeCounts, sentence);
        }
    }
}
