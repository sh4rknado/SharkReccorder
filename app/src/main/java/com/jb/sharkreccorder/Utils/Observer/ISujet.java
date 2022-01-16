package com.jb.sharkreccorder.Utils.Observer;

public interface ISujet {
    void register(IObserver o);
    void unregister(IObserver o);
    void update(String key, Object value);
}
