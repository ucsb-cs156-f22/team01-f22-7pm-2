package edu.ucsb.cs156.spring.backenddemo.services;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class TidesQueryService {


    private final RestTemplate restTemplate;

    public TidesQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    // Documentation for endpoint is at: https://api.tidesandcurrents.noaa.gov/api/prod/
    public final String ENDPOINT = 	"https://api.tidesandcurrents.noaa.gov/api/prod/datagetter?application=ucsb-cs156&begin_date={beginDate}&end_date={endDate}&station={station}&product=predictions&datum=mllw&units=english&time_zone=lst_ldt&interval=hilo&format=json";

    public String getJSON(String beginDate, String endDate, String station) throws HttpClientErrorException {
        log.info("beginDate={}, endDate={}, station={}",  beginDate, endDate, station);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> uriVariables = Map.of("beginDate", beginDate, "endDate", endDate, "station", station);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }
}