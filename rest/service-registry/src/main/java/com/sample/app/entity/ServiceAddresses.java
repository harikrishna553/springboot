package com.sample.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceAddresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceInstanceId;

    private String serviceURL;
    private String serviceHealthURL;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceRegistry serviceRegistry;

    // Getters and setters

    public Integer getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(Integer serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getServiceHealthURL() {
        return serviceHealthURL;
    }

    public void setServiceHealthURL(String serviceHealthURL) {
        this.serviceHealthURL = serviceHealthURL;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
}
