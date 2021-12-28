package com.jb.sharkreccorder.Utils.Observer;

import android.media.MediaRecorder;

public interface ISujet {
    void register(IObserver o);
    void unregister(IObserver o);
    void update(MediaRecorder recorder);
}
