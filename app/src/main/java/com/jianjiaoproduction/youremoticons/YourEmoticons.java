package com.jianjiaoproduction.youremoticons;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;

/**
 *
 * Created by Zhibin on 4/14/2016.
 */
public class YourEmoticons extends InputMethodService implements KeyboardView.OnKeyboardActionListener{
    final String EMOTICON_1 = "¯\\_(ツ)_/¯";
    final String EMOTICON_2 = "╮(╯▽╰)╭";
    final String EMOTICON_3 = "(╯‵□′)╯︵┴─┴";
    final String EMOTICON_4 = "┬─┬ ノ( ' - 'ノ)";
    final String EMOTICON_5 = "∠( ᐛ 」∠)＿";
    final String EMOTICON_6 = "_(:3 」∠ )_";
    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.emoticon_keyboard);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        kv.invalidateAllKeys();
        return kv;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {
        InputConnection ic = getCurrentInputConnection();
        String emoString;
        switch(i){
            case 1:
                emoString = EMOTICON_1;
                break;
            case 2:
                emoString = EMOTICON_2;
                break;
            case 3:
                emoString = EMOTICON_3;
                break;
            case 4:
                emoString = EMOTICON_4;
                break;
            case 5:
                emoString = EMOTICON_5;
                break;
            case 6:
                emoString = EMOTICON_6;
                break;
            default:
                emoString = "";
        }

        ic.commitText(emoString, 1);
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private KeyboardView kv;
    private Keyboard keyboard;
}
