package com.jianjiaoproduction.youremoticons;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.util.Stack;

/**
 *
 * Created by Zhibin on 4/14/2016.
 */
public class YourEmoticons extends InputMethodService implements KeyboardView.OnKeyboardActionListener{
    //TODO: refactor to have an array to hold the predefined emoticons, and another array to hold current emoticons.
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
    public void onInitializeInterface(){
        mEmoticonKeyboard = new YourEmoticonsKeyboard(this, R.xml.emoticon_keyboard);
    }

    @Override
    public void onStartInput(EditorInfo info, boolean restarting) {
        super.onStartInput(info, restarting);
        mEmoticonKeyboard.setImeOptions(getResources(), info.imeOptions);
    }

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        kv.setKeyboard(mEmoticonKeyboard);
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
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                return;
            case -5://setting
                Intent intent = new Intent(this, SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
    private YourEmoticonsKeyboard mEmoticonKeyboard;
    Stack<Integer> inputHist;
    private InputMethodManager mInputMethodManager;
}
