package com.xnhb.et.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.oneway.ui.adapter.vp.AbstractViewPagerAdapter;

/**
 * 作者 oneway on 2018/10/31
 * 描述: 弃用了
 * 参考链接:
 */
public class C2CAdapter extends AbstractViewPagerAdapter {

    public C2CAdapter(String[] titles) {
        super(null, titles);
    }

    @Override
    public View newView(ViewGroup container, int position) {
        C2CSubPage subView = new C2CSubPage(container.getContext(), position, getTitle(position));
        return subView.getView();
    }

    public void update() {

    }

//    @Override
//    public int getItemPosition(Object object) {
//        View view = (View)object;
////        int currentPage = ((DispImgActivity)mContext).getCurrentPagerIdx(); // Get current page index
//        if(currentPage == (Integer)view.getTag()){
//            return POSITION_NONE;
//        }else{
//            return POSITION_UNCHANGED;
//        }
////      return POSITION_NONE;
//    }


}
