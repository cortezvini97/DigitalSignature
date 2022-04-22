package com.vcinsidedigital.digitalsignature;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class DigitalSignature extends View
{
    private Paint paint;
    private Path path;
    private OnToSignListener onToSignListener;

    public DigitalSignature(Context context) {
        super(context);
        config();
    }

    public DigitalSignature(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        config();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                if(onToSignListener != null) onToSignListener.onStartSignature();
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                setIsEmpty(false);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default: return false;
        }
        invalidate();
        return true;
    }

    public void setOnToSignListener(OnToSignListener listener)
    {
        this.onToSignListener = listener;
    }

    public void clear()
    {
        invalidate();
        path.reset();
        setIsEmpty(true);
    }

    public void save(Context context)
    {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bitmap = this.getDrawingCache();
        bitmap.setHasAlpha(true);

        String tempFile = tempFileImage(context, bitmap);

        onToSignListener.onSave(tempFile);

    }



    public interface OnToSignListener
    {
        void onStartSignature();
        void onSigned();
        void onClear();
        void onSave(String tmpFile);
    }

    private void config()
    {
        paint = new Paint();
        path = new Path();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);
    }

    private void setIsEmpty(boolean isEmpty)
    {
        if (onToSignListener != null)
        {
            if(isEmpty)
            {
                onToSignListener.onClear();
            }else
            {
                onToSignListener.onSigned();
            }
        }
    }

    private String tempFileImage(Context context, Bitmap bitmap)
    {
        File outputDir = context.getCacheDir();
        File imageFile = new File(outputDir, UUID.randomUUID().toString() + ".png");

        OutputStream os;

        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return imageFile.getAbsolutePath();
    }
}
