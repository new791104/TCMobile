package Objects;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by charlie on 2017/11/28.
 */

public class disScrollingPager extends ViewPager {

    private boolean isScrollingEnabled = true;

    public disScrollingPager(Context context) {
        super(context);
    }

    public disScrollingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isScrollingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isScrollingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setScrollingEnabled(boolean b) {
        this.isScrollingEnabled = b;
    }
}
