package com.mangjakseon.controller;

import com.mangjakseon.dto.HeartDTO;
import com.mangjakseon.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@RequestMapping(value="/heart",method =  {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping(value = "/up")
    @ResponseBody
    public ResponseEntity<HeartDTO> heart(@RequestBody HeartDTO heartDto) {
        heartService.heart(heartDto);

       return new ResponseEntity<>(heartDto, HttpStatus.CREATED);
    }

//    @DeleteMapping(value = "/down")
//    public ResponseEntity<HeartDTO> unHeart(@RequestBody HeartDTO heartDto) {
//        heartService.unHeart(heartDto);
//        return new ResponseEntity<>(heartDto, HttpStatus.OK);
//    }
}
