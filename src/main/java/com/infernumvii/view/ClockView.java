package com.infernumvii.view;
/*https://showcase.primefaces.org/ui/ajax/poll.xhtml */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;

@ViewScoped
@Named("clockView")
@Getter
public class ClockView implements Serializable {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String currentDate = formatter.format(new Date());

    public void updateDate(){
        currentDate = formatter.format(new Date());
    }
}
