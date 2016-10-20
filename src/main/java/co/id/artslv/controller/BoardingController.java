package co.id.artslv.controller;

import co.id.artslv.lib.boarding.Boarding;
import co.id.artslv.lib.responses.MessageWrapper;
import co.id.artslv.lib.utility.CustomErrorResponse;
import co.id.artslv.lib.utility.CustomException;
import co.id.artslv.service.BoardingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequestMapping(value = "/arts_gatein",method = RequestMethod.POST)
    public ResponseEntity<?> gateIn(@RequestBody Boarding boarding) throws JsonProcessingException {
        MessageWrapper gatein;
        try {
            gatein = boardingService.insertGateIn(boarding);
        } catch (CustomException e) {
            CustomErrorResponse customErrorResponse = (CustomErrorResponse) e.getCause();
            MessageWrapper<Object> error = new MessageWrapper<>(customErrorResponse);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        return new ResponseEntity<>(gatein,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/arts_gateout",method = RequestMethod.POST)
    public ResponseEntity<?> gateOut(@RequestBody Boarding boarding) throws JsonProcessingException {
        MessageWrapper gateout;
        try {
            gateout = boardingService.insertGateOut(boarding);
        } catch (CustomException e) {
            CustomErrorResponse customErrorResponse = (CustomErrorResponse) e.getCause();
            MessageWrapper<Object> error = new MessageWrapper<>(customErrorResponse);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        return new ResponseEntity<>(gateout,HttpStatus.OK);
    }

}
