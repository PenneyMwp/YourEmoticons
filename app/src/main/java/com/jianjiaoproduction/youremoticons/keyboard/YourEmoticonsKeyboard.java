package com.jianjiaoproduction.youremoticons.keyboard;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.EditorInfo;

import com.jianjiaoproduction.youremoticons.R;

/**
 * Created by zhibzhang on 5/11/2016.
 */
public class YourEmoticonsKeyboard extends Keyboard{
    private Key mEnterKey;
    public YourEmoticonsKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
        Key key = new Key(res, parent, x, y, parser);
        if(key.codes[0] == -4){
            mEnterKey = key;
        }

        return key;
    }

    /**
     * This looks at the ime options given by the current editor, to set the
     * appropriate label on the keyboard's enter key (if it has one).
     */
    void setImeOptions(Resources res, int options) {
        if (mEnterKey == null) {
            return;
        }

        switch (options&(EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
            case EditorInfo.IME_ACTION_GO:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_go_key);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_next_key);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                mEnterKey.icon = res.getDrawable(R.drawable.ic_search_black_24dp, null);
                mEnterKey.label = null;
                break;
            case EditorInfo.IME_ACTION_SEND:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = res.getDrawable(R.drawable.ic_send_black_24dp, null);
                mEnterKey.label = null;
                break;
            default:
                mEnterKey.icon = res.getDrawable(R.drawable.ic_keyboard_return, null);
                mEnterKey.label = null;
                break;
        }
    }
}
