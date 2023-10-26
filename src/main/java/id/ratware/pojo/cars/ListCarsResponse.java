package id.ratware.pojo.cars;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/***********************************************************************
 * Module:  id.ratware.pojo.cars.ListCarsResponse
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@Getter
@Setter
public class ListCarsResponse{

    private ArrayList<CarDataResponse> data;

    {
        data = new ArrayList<>();
    }
}
