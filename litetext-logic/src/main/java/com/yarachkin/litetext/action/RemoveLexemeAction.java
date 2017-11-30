package com.yarachkin.litetext.action;

import com.yarachkin.litetext.chain.impl.TextHandler;
import com.yarachkin.litetext.composite.LiteTextComponent;
import com.yarachkin.litetext.composite.impl.PunctuationMark;
import com.yarachkin.litetext.composite.impl.Word;
import com.yarachkin.litetext.exception.ActionLiteTextException;
import com.yarachkin.litetext.exception.ChainLiteTextException;
import com.yarachkin.litetext.exception.CompositeLiteTextException;

import java.util.ArrayList;

public class RemoveLexemeAction {

    public static LiteTextComponent removeLexeme(String text, int size, char firstSymbol) throws ActionLiteTextException {
        try {
            TextHandler handler = new TextHandler();
            LiteTextComponent component = handler.parse(text);
            for (LiteTextComponent paragraph : component.getComponents()) {
                handleSentences(paragraph, size, firstSymbol);
            }
            return component;
        } catch (ChainLiteTextException | CompositeLiteTextException e) {
            throw new ActionLiteTextException(e);
        }
    }

    private static void handleSentences(LiteTextComponent paragraph, int size, char firstSymbol) throws CompositeLiteTextException {
        for (LiteTextComponent sentence : paragraph.getComponents()) {
            handleLexeme(sentence, size, firstSymbol);
        }
    }

    private static void handleLexeme(LiteTextComponent sentence, int size, char firstSymbol) throws CompositeLiteTextException {
        ArrayList<LiteTextComponent> waitingRemovingElements = new ArrayList<>();

        for (LiteTextComponent lexeme : sentence.getComponents()) {
            if ( lexeme.getClass().getSimpleName().equals("LiteTextComposite") && calculateLexemeSize(lexeme) == size
                    && firstSymbol == calculateFirstSymbol(lexeme) ) {
                waitingRemovingElements.add(lexeme);
            }
        }

        for (LiteTextComponent element : waitingRemovingElements) {
            sentence.remove(element);
        }
    }

    private static int calculateLexemeSize(LiteTextComponent lexeme) throws CompositeLiteTextException {
        int lexemeSize = 0;
        for (LiteTextComponent element : lexeme.getComponents()) {
            if ( element.getClass().getSimpleName().equals("Word") ) {
                Word word = (Word) element;
                lexemeSize += word.getWord().length();
            } else if ( element.getClass().getSimpleName().equals("PunctuationMark") ) {
                PunctuationMark punctuationMark = (PunctuationMark) element;
                lexemeSize += punctuationMark.getPunctuationMark().length();
            }
        }
        return lexemeSize;
    }

    private static char calculateFirstSymbol(LiteTextComponent lexeme) throws CompositeLiteTextException {
        String firstSymbol = "";

        for (LiteTextComponent element : lexeme.getComponents()) {
            if ( firstSymbol.isEmpty() && element.getClass().getSimpleName().equals("Word") ) {
                Word word = (Word) element;

                firstSymbol = Character.toString(word.getWord().charAt(0));

            } else if ( firstSymbol.isEmpty() && element.getClass().getSimpleName().equals("PunctuationMark") ) {
                PunctuationMark punctuationMark = (PunctuationMark) element;
                firstSymbol = Character.toString(punctuationMark.getPunctuationMark().charAt(0));
            }
        }
        return firstSymbol.charAt(0);
    }
}
