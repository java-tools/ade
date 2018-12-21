package com.almis.ade.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * FileProcessingThreadPool class
 * @author dfuentes
 */
public class FileProcessingThreadPool {

  @Value("${ade.file.generation.thread.keepalive:30}")
  private int keepAlive;

  @Value("${ade.file.generation.thread.max:10}")
  private int maxThreads;

  @Value("${ade.file.generation.queue.max:0}")
  private int maxQueue;

  private ExecutorService executorService;

  /**
   * Init executor service
   */
  @PostConstruct
  public void initializeExecutorService(){
    ThreadPoolExecutorFactoryBean executorServiceFactory = new ThreadPoolExecutorFactoryBean();
    executorServiceFactory.setCorePoolSize(0);
    executorServiceFactory.setMaxPoolSize(maxThreads);
    if(maxQueue > 0) {
      executorServiceFactory.setQueueCapacity(maxQueue);
    }
    executorServiceFactory.setKeepAliveSeconds(keepAlive);
    executorServiceFactory.setAllowCoreThreadTimeOut(true);
    executorServiceFactory.setDaemon(true);
    executorServiceFactory.afterPropertiesSet();
    executorService = executorServiceFactory.getObject();
  }

  /**
   * Add file process job
   * @param callable Callable job
   * @return job as Future
   */
  @SuppressWarnings("unchecked")
  public Future addJob(Callable callable) {
    return executorService.submit(callable);
  }

  /**
   * Check if job is done
   * @param future future job
   * @return flag isJobDone
   */
  public boolean isJobDone(Future future) {
    return future.isDone();
  }

  /**
   * Get if job is alibe
   * @return isAlive flag
   */
  public boolean getIsAlive() {
    return !executorService.isShutdown();
  }

  /**
   * Shutdown executor
   */
  @PreDestroy
  public void shutdownExecutorService(){
    executorService.shutdown();
  }
}

