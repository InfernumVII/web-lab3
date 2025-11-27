package com.infernumvii.view;
/*https://showcase.primefaces.org/ui/ajax/poll.xhtml */
// https://codepen.io/ahmadbassamemran/pen/WdKQyx
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.primefaces.PrimeFaces;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;

@ViewScoped
@Named("clockView")
@Getter
public class ClockView implements Serializable {

    public void getServerTime(){
        long utcMillis = Instant.now().toEpochMilli();
        PrimeFaces.current().ajax().addCallbackParam("serverTime", utcMillis);
    }
}
