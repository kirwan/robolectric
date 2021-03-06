package org.robolectric.shadows;

import android.widget.RadioGroup;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import static android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(RadioGroup.class)
public class ShadowRadioGroup extends ShadowLinearLayout {
    @RealObject
    protected RadioGroup realGroup;

    private int checkedRadioButtonId = -1;
    private OnCheckedChangeListener onCheckedChangeListener;

    @Implementation
    public int getCheckedRadioButtonId() {
        return checkedRadioButtonId;
    }

    @Implementation
    public void check(int id) {
        checkedRadioButtonId = id;
        notifyListener();
    }

    @Implementation
    public void clearCheck() {
        notifyListener();
        checkedRadioButtonId = -1;
        notifyListener();
    }

    private void notifyListener() {
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(realGroup, checkedRadioButtonId);
        }
    }

    @Implementation
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onCheckedChangeListener = listener;
    }
    
    public OnCheckedChangeListener getOnCheckedChangeListener() {
    	return onCheckedChangeListener;
    }
}
