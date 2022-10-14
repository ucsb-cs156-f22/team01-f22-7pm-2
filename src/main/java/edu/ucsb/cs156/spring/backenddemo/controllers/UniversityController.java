package edu.ucsb.cs156.spring.backenddemo.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="University info from http://universities.hipolabs.com/")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    @Autowired
    UniversityQueryService universityQueryService;

    @ApiOperation(value="Enter part of a university name, get back all matching universities", notes="More information about the data available here: https://github.com/Hipo/university-domains-list")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversity(
        @ApiParam("part of a university name, e.g. Santa Barbara") @RequestParam String name
    ) throws JsonProcessingException {
        log.info("getUniversity: name={}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }
}
