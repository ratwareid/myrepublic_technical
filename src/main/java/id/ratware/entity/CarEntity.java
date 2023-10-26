package id.ratware.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***********************************************************************
 * Module:  id.ratware.entity.CarEntity
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@Entity
@Table(name = "m_cars")
@Getter
@Setter
@NoArgsConstructor
public class CarEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nama,warna,merk;
    public Integer kecepatan,harga;

    public CarEntity(String nama, String warna, String merk, Integer kecepatan, Integer harga) {
        this.nama = nama;
        this.warna = warna;
        this.merk = merk;
        this.kecepatan = kecepatan;
        this.harga = harga;
    }

    public String getInfo() {
        return "Mobil " + this.nonNull(nama) + "," + this.nonNull(warna) + "," + this.nonNull(kecepatan)+ "km/jam," + this.nonNull(harga) + "jt,"+this.nonNull(merk);
    }

    public Object nonNull(Object data){
        return data != null ? data : "";
    }
}
