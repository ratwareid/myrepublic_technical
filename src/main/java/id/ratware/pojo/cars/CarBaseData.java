package id.ratware.pojo.cars;

import lombok.Getter;
import lombok.Setter;

/***********************************************************************
 * Module:  id.ratware.pojo.cars.CarBaseResponse
 * Author:  Ratwareid
 * Created: 26/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@Getter
@Setter
public class CarBaseData {
    public String nama,warna,merk;
    public Integer kecepatan,harga;
}
