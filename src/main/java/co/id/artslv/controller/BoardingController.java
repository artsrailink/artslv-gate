package co.id.artslv.controller;

import co.id.artslv.lib.boarding.Boarding;
import co.id.artslv.lib.responses.MessageWrapper;
import co.id.artslv.lib.utility.CustomErrorResponse;
import co.id.artslv.lib.utility.CustomException;
import co.id.artslv.service.BoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gemuruhgeo on 06/09/16.
 */
@RestController
@RequestMapping(value = "/boarding")
public class BoardingController {

    @Autowired
    private BoardingService boardingService;

    @RequestMapping(value = "/gate_in",method = RequestMethod.POST)
    public ResponseEntity<?> getPayment(@RequestBody Boarding boarding){
        MessageWrapper payments;
        try {
            payments = boardingService.insertGateIn(boarding);
        } catch (CustomException e) {
            CustomErrorResponse customErrorResponse = (CustomErrorResponse) e.getCause();
            MessageWrapper<Object> error = new MessageWrapper<>(customErrorResponse);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        return new ResponseEntity<>(payments,HttpStatus.OK);
    }

}
