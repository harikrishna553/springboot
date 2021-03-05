package com.sample.app.generators;

import java.util.UUID;

import org.quartz.SchedulerException;
import org.quartz.spi.InstanceIdGenerator;

public class CustomQuartzInstanceIdGenerator implements InstanceIdGenerator {

  @Override
  public String generateInstanceId() throws SchedulerException {
    try {
      return UUID.randomUUID().toString();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

}