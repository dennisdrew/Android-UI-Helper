/* I did not write this on my own; found it somewhere on the Internet,
 * and I forget where it came from so I cannot properly acknowledge
 * the original author.
 */
package com.dtdennis.uihelper;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NonFocusingScrollView extends ScrollView {

	public NonFocusingScrollView(Context context) {
		super(context);
	}

	public NonFocusingScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NonFocusingScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected boolean onRequestFocusInDescendants(int direction,
			Rect previouslyFocusedRect) {
		return true;
	}

}