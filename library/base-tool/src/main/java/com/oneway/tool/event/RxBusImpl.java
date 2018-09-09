package com.oneway.tool.event;


import org.simple.eventbus.EventBus;

/**
 * @Description: RxBus事件管理
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2016-12-19 15:07
 */
public class RxBusImpl implements IBus {

    @Override
    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void post(IEvent event) {

    }

    @Override
    public void post(String tag, Object event) {
        EventBus.getDefault().post(event, tag);
    }

    @Override
    public void postSticky(IEvent event) {

    }
}
