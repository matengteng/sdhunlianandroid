package newair.com.marriagenetwork.widget.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import newair.com.marriagenetwork.R;

/**
 * Created by mateng on 15/9/30.
 */
public class DotsForViewPagerLayout extends LinearLayout {


    private Context context;
    private ImageView[] dots;

    private int count;

    public DotsForViewPagerLayout(Context context) {

        super(context);
        this.context = context;

    }

    public DotsForViewPagerLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public void initDots(int count) {
        this.count = count;
        //创建一个LinearLayout用来摆放Dot
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
                RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, 0, 0, 10);
        this.setLayoutParams(layoutParams);
        this.setOrientation(LinearLayout.HORIZONTAL);
        dots = new ImageView[count];
        for (int i = 0; i < count; i++) {
            ImageView iv = new ImageView(context);
            LayoutParams ivParams = new LayoutParams(
                    10, 10);
            ivParams.setMargins(5, 0, 5, 0);
            iv.setLayoutParams(ivParams);
            iv.setImageResource(R.drawable.dot);
            dots[i] = iv;
            iv.setEnabled(true);
            this.addView(iv);
        }
        dots[0].setEnabled(false);
    }

    /**
     * 通过当前的图片位置改变底部小点选中状态
     *
     * @param position viewpager的位置
     */
    public void setCurrentDot(int position, int currentIndex) {
        if (position < 0 || position > count - 1
                || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
    }


}
