package co.id.artslv.controller;

import co.id.artslv.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gemuruhgeo on 06/09/16.
 */
@RestController
@RequestMapping(value = "/gate")
public class GateController {

    @Autowired
    private GateService gateService;


}
