package com.example.filckrbrowser;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerItemClickListner extends RecyclerView.SimpleOnItemTouchListener {

    private final OnRecyclerClickListner mListner;
    private final GestureDetectorCompat mgestureDetectorCompat;

    interface OnRecyclerClickListner {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListner(Context mcontext, final RecyclerView recyclerView, OnRecyclerClickListner listner) {

        mListner = listner;
        mgestureDetectorCompat = new GestureDetectorCompat(mcontext, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childview = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childview != null && mListner != null){
                    mListner.onItemClick(childview, recyclerView.getChildAdapterPosition(childview));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childview = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childview != null && mListner != null){
                    mListner.onItemLongClick(childview, recyclerView.getChildAdapterPosition(childview));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        if (mgestureDetectorCompat != null){
            boolean result = mgestureDetectorCompat.onTouchEvent(e);
            return result;
        }else {
            return false;
        }
    }
}
