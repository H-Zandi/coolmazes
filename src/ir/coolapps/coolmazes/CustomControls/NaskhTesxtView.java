package ir.coolapps.coolmazes.CustomControls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NaskhTesxtView extends TextView {

	public NaskhTesxtView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			this.setTypeface(Typeface.createFromAsset(context.getAssets(),
					"Fonts/DroidNaskh.ttf"));
		}
	}

}
