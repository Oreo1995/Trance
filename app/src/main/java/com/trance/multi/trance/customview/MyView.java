package com.trance.multi.trance.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class MyView extends View{

    private String mtext;
    private int msrc;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attr){
        super(context,attr);
        int resourceId = 0;
        int textId = attr.getAttributeResourceValue(null, "Text", 0);
        int srcId = attr.getAttributeResourceValue(null, "Src", 0);
        mtext = context.getResources().getText(textId).toString();
        msrc = srcId;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        int bh = 0,bw = 0;
        InputStream is = getResources().openRawResource(msrc);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        if (bitmap != null) {
            bh = bitmap.getHeight();
            bw = bitmap.getWidth();
        }

        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawText(mtext,bw/2,30,paint);
    }
}
