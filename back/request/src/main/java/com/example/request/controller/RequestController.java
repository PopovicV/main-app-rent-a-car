package com.example.request.controller;

import com.example.request.DTO.BundleDTO;
import com.example.request.DTO.RequestDTO;
import com.example.request.DTO.RequestForFrontDTO;
import com.example.request.DTO.VehicleMainViewDTO;
import com.example.request.DTO.user.UserDTO;
import com.example.request.model.Bundle;
import com.example.request.model.Request;
import com.example.request.service.RequestService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Request>> getAll() {
        return new ResponseEntity<>(this.requestService.findAll(), HttpStatus.OK);
    }

    /**
     * PUT /server/request/{id}
     *
     * @return returns status of request update
     */
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Request> updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Request newRequest = this.requestService.update(id, request);
        if (newRequest != null)
            return new ResponseEntity<>(newRequest, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * POST /server/request/
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> newRequest(@RequestBody RequestDTO requests) {
        System.out.println(requests);
        boolean status = this.requestService.addRequest(requests);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * DELETE /server/request/{id}
     *
     * @return returns status of request deletion
     */
    @DeleteMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long id) {
        boolean status = this.requestService.delete(id);
        if (status)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("Request not found", HttpStatus.NOT_FOUND);
    }

    /**
     * POST /server/request/physicalRent
     *
     * @return returns status of new physical request creation
     */
    @PostMapping(value = "/physicalRent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Boolean> physicalRenting(@RequestBody Request request) {
        System.out.println(request);
        boolean status = this.requestService.addPhysicalRenting(request);
        if (status){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/ownerRequestHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BundleDTO>> ownerRequestHistory(@RequestParam(value = "ownerId") Long ownerId) throws Exception {
        List<UserDTO> users = (this.getUsernames()).getBody();
        List<VehicleMainViewDTO> vehicles = (this.getVehicleMainViewDTO()).getBody();

        List<Request> requestList = requestService.getAllRequestsForOwner(ownerId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForOwner(requestList, users, vehicles);
        List<BundleDTO> bundleList = requestService.getBundles(requestDTOList);

        return new ResponseEntity<List<BundleDTO>>(bundleList, HttpStatus.OK);
   }

    @GetMapping(value = "/buyerRequestHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BundleDTO>> buyerRequestHistory(@RequestParam(value = "userId") Long userId) throws Exception {
        List<UserDTO> users = (this.getUsernames()).getBody();
        List<VehicleMainViewDTO> vehicles = (this.getVehicleMainViewDTO()).getBody();

        List<Request> requestList = requestService.getAllRequestsForUser(userId);
        List<RequestForFrontDTO> requestDTOList = requestService.getDTOListForUser(requestList, users, vehicles);
        List<BundleDTO> bundleList = requestService.getBundles(requestDTOList);

        return new ResponseEntity<List<BundleDTO>>(bundleList, HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("Getting all usernames");
        List<UserDTO> response = restTemplate.exchange("http://user/user/usernames/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {}).getBody();

        return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleMainViewDTO>> getVehicleMainViewDTO() throws Exception {
        System.out.println("Getting all vehicles");
        List<VehicleMainViewDTO> response = restTemplate.exchange("http://vehicle/search/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<VehicleMainViewDTO>>() {}).getBody();

        return new ResponseEntity<List<VehicleMainViewDTO>>(response, HttpStatus.OK);
    }

    /**
     * GET /server/request/canUserPostReview
     *
     * @return return true if user can post review
     */
    @GetMapping(value = "canUserPostReview/{vehicleId}+{userId}")
    public ResponseEntity<Boolean> canUserPostReview(@PathVariable Long userId, @PathVariable Long vehicleId) {
        return new ResponseEntity<Boolean>(this.requestService.canUserPostReview(vehicleId, userId), HttpStatus.OK);
    }
}
