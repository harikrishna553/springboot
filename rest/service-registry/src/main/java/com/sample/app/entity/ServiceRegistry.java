package com.sample.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "service_registry", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"serviceName", "version", "environment"})
})
public class ServiceRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    private String serviceName;
    private String environment;
    private String version;
    private Boolean active;
    private LocalDateTime lastHeartbeat;

    @OneToMany(mappedBy = "serviceRegistry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceAddresses> serviceAddresses;

    // Getters and setters

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(LocalDateTime lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public List<ServiceAddresses> getServiceAddresses() {
        return serviceAddresses;
    }

    public void setServiceAddresses(List<ServiceAddresses> serviceAddresses) {
        this.serviceAddresses = serviceAddresses;
    }
}