package id.ratware.pojo.general;

import lombok.Getter;
import lombok.Setter;

/***********************************************************************
 * Module:  id.ratware.pojo.general.GeneralResponse
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@Getter
@Setter
public class GeneralResponse {
    private String responseMessage;

    public void success(){
        this.responseMessage = "SUCCESS";
    }
}
