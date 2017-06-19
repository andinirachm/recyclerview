package id.recyclerview.controller;

import java.util.List;

/**
 * Created by Andini Rachmah on 11/9/2016.
 */

public class GeneralController {
    public interface ConsumeApiListener<T> {
        boolean onReceive(boolean status, String message, T data);
    }

    public interface ConsumeApiListenerStr {
        String onReceive(boolean status, String message, String token);
    }

    public interface ConsumeApiListenerArr<T> {
        void onReceive(boolean status, String message, List data);
    }

    public interface ConsumeApiListenerArr2<T> {
        void onReceive(boolean status, String message, List data, List data2);
    }

}
