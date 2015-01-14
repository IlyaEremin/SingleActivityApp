package com.flatstack.singleactivityapp.utils;

import de.greenrobot.event.EventBus;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class Bus {

    private static EventBus bus = EventBus.getDefault();

    private Bus() {
    }

    public static void subscribe(Object object) {
        bus.register(object);
    }

    public static void unsubscribe(Object object) {
        bus.unregister(object);
    }

    public static void event(Object object) {
        bus.post(object);
    }

}
