package com.jianjiaoproduction.youremoticons;

import android.app.Dialog;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.util.Stack;

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
    public void onCreate() {
        super.onCreate();
        mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.emoticon_keyboard);
        kv.setKeyboard(keyboard);
        kv.setPreviewEnabled(false);
        kv.setOnKeyboardActionListener(this);

        kv.invalidateAllKeys();
        inputHist = new Stack<>();
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
        String emoString = "";
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
            case -1: //space
                emoString = " ";
                break;
            case -2: //delete
                ic.deleteSurroundingText(1, 0);
                break;
            case -3: //language switch
                mInputMethodManager.switchToNextInputMethod(getToken(), false /* onlyCurrentIme */);
                return;
            case -4://return
                //TODO: Need to handle search event
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                return;
            default:
                emoString = "";
        }

        if(emoString.length() > 0)
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

    private IBinder getToken() {
        final Dialog dialog = getWindow();
        if (dialog == null) {
            Log.e("GET_TOKEN", "Not able to get dialog");
            return null;
        }
        final Window window = dialog.getWindow();
        if (window == null) {
            Log.e("GET_TOKEN", "Not able to get window");
            return null;
        }
        Log.e("GET_TOKEN", window.getAttributes().token.toString());
        return window.getAttributes().token;
    }

    private KeyboardView kv;
    private Keyboard keyboard;
    Stack<Integer> inputHist;
    private InputMethodManager mInputMethodManager;
}
