package com.almis.ade.autoconfigure;

import com.almis.ade.component.FileProcessingThreadPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Class used to launch initial load treads
 */
@Configuration
@EnableAsync
public class AsyncConfig {

  /**
   * File processing thread pool
   * @return FileProcessingThreadPool
   */
  @Bean
  @ConditionalOnMissingBean
  FileProcessingThreadPool fileProcessingThreadPool() {
    return new FileProcessingThreadPool();
  }
}