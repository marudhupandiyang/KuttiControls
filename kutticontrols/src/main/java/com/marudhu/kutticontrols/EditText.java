package com.marudhu.kutticontrols;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by marudhu on 8/11/14.
 */
public class EditText extends android.widget.EditText {

    private static final int left    = 0 ;
    private static final int right   = 2 ;
//
//    static final int top     = 1 ;
//    static final int bottom  = 3 ;

    OnClickListener drawableLeftListener;
    OnClickListener drawableRightListener;

    Drawable leftDrawable;
    Drawable rightDrawable;

    public EditText(Context context) {
        super(context);
        setIconsClickable();
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomAttributes(context, attrs);
        setIconsClickable();
    }

    private void setCustomAttributes(Context context, AttributeSet attrs) {
        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        if (attributes == null) {
            return;
        }

        setDrawablesFromAttributes(attributes);

        setCallbackListenersFromAttributes(attributes);

    }

    private void setDrawablesFromAttributes(TypedArray a) {
        leftDrawable = a.getDrawable(R.styleable.EditText_iconLeft);
        rightDrawable = a.getDrawable(R.styleable.EditText_iconRight);
        setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, rightDrawable, null);
    }

    private void setCallbackListenersFromAttributes(TypedArray a) {
        String leftListenerMethodName = a.getString(R.styleable.EditText_iconLeftClickAction);
        String rightListenerMethodName = a.getString(R.styleable.EditText_iconRightClickAction);

        if (leftListenerMethodName != null) {
            setLeftIconListener(getNewListenerForMethodInXml(leftListenerMethodName));
        }

        if (rightListenerMethodName != null) {
            setRightIconListener(getNewListenerForMethodInXml(rightListenerMethodName));
        }
    }

    private OnClickListener getNewListenerForMethodInXml(final String leftListenerMethodName) {
        return new OnClickListener() {
            private Method mHandler;

            public void onClick(View v) {
                if (mHandler == null) {
                    try {
                        mHandler = getContext().getClass().getMethod(leftListenerMethodName);
                    } catch (NoSuchMethodException e) {
                        throw new IllegalStateException("KuttiControls: EditText No such method \"" + leftListenerMethodName  + "\" exist for LeftListener!");
                    }
                }

                try {
                    mHandler.invoke(getContext());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException();
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException();
                }
            }
        };
    }


    public EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomAttributes(context, attrs);
        setIconsClickable();
    }

//    public EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        setIconsClickable();
//    }

    public void setLeftIconListener(OnClickListener listener){
        if(leftDrawable ==  null) throw new RuntimeException("A listener cannot be set without something to listen on. Set the Left Icon first before setting up the listener");
        drawableLeftListener = listener;
    }

    public void setRightIconListener(OnClickListener listener){
        if(rightDrawable ==  null) throw new RuntimeException("A listener cannot be set without something to listen on. Set the Left Icon first before setting up the listener");
        drawableRightListener = listener;
    }

    public void setLeftIcon(Drawable drawable){
        leftDrawable = drawable;
        invalidate();
    }

    public void setRightIcon(Drawable drawable){
        rightDrawable = drawable;
        invalidate();
    }

    public void setRightIcon(Drawable drawable, OnClickListener listener){
        setRightIconListener(listener);
        setRightIcon(drawable);
    }

    public void setLeftIcon(Drawable drawable, OnClickListener listener){
        setLeftIconListener(listener);
        setLeftIcon(drawable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        throw  new UnsupportedOperationException();
//        super.setOnTouchListener(l);
    }

    private void setIconsClickable() {

        final Drawable leftDrawable = getCompoundDrawables()[left];
        final Drawable rightDrawable = getCompoundDrawables()[right];

        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && (leftDrawable!= null || rightDrawable != null)) {
                    int fuzzValue = 10;

                    //for event x & y
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();

                    int topBound    = view.getPaddingTop() + fuzzValue;
                    int bottomBound = view.getHeight() - view.getPaddingBottom() + fuzzValue;

                    if (handleLeftIconCLick(view, fuzzValue, x, y, topBound, bottomBound, leftDrawable))
                        return true;

                    if (handleRightIconClick(view, fuzzValue, x, y, topBound, bottomBound, rightDrawable))
                        return true;
                }
                return false;
            }


        });
    }

    private boolean handleRightIconClick(View view, int fuzzValue, int x, int y, int topBound, int bottomBound, Drawable rightDrawable) {
        if(rightDrawable != null) {
            final Rect bounds = rightDrawable.getBounds();
            int rightLeftBound  = view.getRight() - bounds.width() - fuzzValue;
            int rightRightBound = view.getRight() - view.getPaddingRight() + fuzzValue;

            if (isBetween(x,rightLeftBound,rightRightBound) && isBetween(y,topBound,bottomBound)) {
                if(drawableRightListener != null){
                    drawableRightListener.onClick(view);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean handleLeftIconCLick(View view, int fuzzValue, int x, int y, int topBound, int bottomBound, Drawable leftDrawable) {
        if (leftDrawable != null) {
            final Rect bounds = leftDrawable.getBounds();

            int leftLeftBound  = view.getLeft() - view.getPaddingLeft() - bounds.width() - fuzzValue;
            int rightRightBound = view.getLeft() - view.getPaddingLeft() + bounds.width() + fuzzValue;

            if (isBetween(x, leftLeftBound, rightRightBound) && isBetween(y,topBound,bottomBound))  {
                if(drawableLeftListener != null){
                    drawableLeftListener.onClick(view);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBetween(int x, int leftBound, int rightBound) {
        return x >= leftBound && x <= rightBound;
    }

}