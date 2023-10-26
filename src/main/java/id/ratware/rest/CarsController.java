package id.ratware.rest;

import id.ratware.entity.CarEntity;
import id.ratware.pojo.cars.CarBaseData;
import id.ratware.pojo.cars.CarDataResponse;
import id.ratware.pojo.cars.CarEditRequest;
import id.ratware.pojo.cars.ListCarsResponse;
import id.ratware.pojo.general.GeneralResponse;
import id.ratware.service.CarsService;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

/***********************************************************************
 * Module:  id.ratware.rest.CarsController
 * Author:  Ratwareid
 * Created: 25/10/2023
 * Info:  If You dont know me ? Just type ratwareid in google.
 ***********************************************************************/

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarsController {

    @Inject
    CarsService carsService;

    //Pembuatan mobil
    //POST with request body to http://localhost:8080/cars
    @POST
    public Response create(CarBaseData request){

        try {
            CarEntity carEntity = new CarEntity();
            carEntity.setNama(request.getNama());
            carEntity.setWarna(request.getWarna());
            carEntity.setMerk(request.getMerk());
            carEntity.setKecepatan(request.getKecepatan());
            carEntity.setHarga(request.getHarga());
            carsService.save(carEntity);

            GeneralResponse response = new GeneralResponse();
            response.success();

            return Response.ok(response).build();
        }catch (Exception e){
            System.err.println("Terjadi kesalahan karena : "+ e.getMessage());
            return Response.status(HttpResponseStatus.NOT_FOUND.code()).build();
        }
    }

    //Tampilkan semua mobil
    //GET http://localhost:8080/cars
    @GET
    public Response get(){
        ListCarsResponse response = new ListCarsResponse();
        List<CarEntity> dataCars = carsService.getAll();
        for (CarEntity car : dataCars) {
            CarDataResponse dataResponse = new CarDataResponse();
            dataResponse.setId(car.getId());
            dataResponse.setNama(car.getNama());
            dataResponse.setWarna(car.getWarna());
            dataResponse.setMerk(car.getMerk());
            dataResponse.setKecepatan(car.getKecepatan());
            dataResponse.setHarga(car.getHarga());

            response.getData().add(dataResponse);
        }

        return Response.ok(response).build();
    }

    //Tampilkan spesifik mobil
    //GET http://localhost:8080/cars/{id}
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long carID) {

        CarDataResponse dataResponse = new CarDataResponse();
        Optional<CarEntity> dataCars = carsService.getById(carID);
        if (dataCars.isPresent()) {
            CarEntity car = dataCars.get();
            dataResponse.setId(car.getId());
            dataResponse.setNama(car.getNama());
            dataResponse.setWarna(car.getWarna());
            dataResponse.setMerk(car.getMerk());
            dataResponse.setKecepatan(car.getKecepatan());
            dataResponse.setHarga(car.getHarga());

            return Response.ok(dataResponse).build();
        }
        return Response.status(HttpResponseStatus.NOT_FOUND.code()).build();
    }


    //Perubahan warna dan/atau harga mobil
    //PUT http://localhost:8080/cars/{id}
    @PUT
    @Path("/{id}")
    public Response updateById(@PathParam("id") Long carID, CarEditRequest request) {
        try {
            GeneralResponse response = new GeneralResponse();
            carsService.update(carID,request.getWarna(),request.getHarga());
            response.success();
            return Response.ok(response).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).build();
        }
    }

    //Penghapusan mobil
    //DELETE http://localhost:8080/cars/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long carID) {
        try {
            GeneralResponse response = new GeneralResponse();
            carsService.delete(carID);
            response.success();
            return Response.ok(response).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).build();
        }
    }
}
