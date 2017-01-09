package org.vialle.tradingcc.ethereum.api.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Verifie la santé de l'application
 */
@RestController
public class MonitoringApiRestController {

    private static final Logger LOG = LoggerFactory.getLogger(MonitoringApiRestController.class);


    /**
     * Ce service est destiné à être uniquement appelé par l'Elastic Load Balancer afin de connaitre l'état
     * de santé du serveur.
     */
    @RequestMapping(method = RequestMethod.GET, path = "monitoring/aws")
    //TODO sécuriser l'accés à ce service avec le DevOps
    public ResponseEntity healthcheck() {

        //TODO Effectuer les opérations nécessaires pour verifier la bonne santé de l'application, tel que l'accés aux repositories

        //Return 200 OK si tout va bien
        LOG.debug("[RESULT] AWS Healthcheck OK");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Ce service est destiné à être uniquement appelé par le devops, afin de tester l'installation des systemes applicatifs
     */
    @RequestMapping(method = RequestMethod.GET, path = "/monitoring/ghost")
    //TODO sécuriser l'accés à ce service avec le DevOps
    public ResponseEntity ghostCheck() {

        //TODO Effectuer les opérations nécessaires pour verifier la bonne santé de l'application, tel que l'accés aux repositories

        //Return 200 OK si tout va bien
        LOG.debug("[RESULT] Ghost Healthcheck OK");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}