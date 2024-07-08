package com.reply.tddSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping(path = "/lb")
public class LBController {

    @Autowired
    LoadBalancerService loadBalancerService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<URI> getAllServers() {
        return this.loadBalancerService.getRegistry();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getLB() {
        return this.loadBalancerService.getServer().toString();
    }

}
