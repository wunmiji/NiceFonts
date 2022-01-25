package com.nicefonts.other;

import com.nicefonts.interphase.SetOnFinish;
import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.util.Duration;

public class TransitionClass {

    public void translateAlert (WritableValue<Number> wv, Double object, double duration, SetOnFinish sof) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(wv, object, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.getKeyFrames().add(kf);

        SequentialTransition st = new SequentialTransition(timeline, new PauseTransition(Duration.seconds(duration)));
        st.setOnFinished((event) -> sof.finish());
        st.play();
    }

}
