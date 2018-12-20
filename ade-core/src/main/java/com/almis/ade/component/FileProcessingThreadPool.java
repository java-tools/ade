package com.almis.ade.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
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
   *
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
   *
   * @param callable
   * @return
   */
  @SuppressWarnings("unchecked")
  public Future addJob(Callable callable) {
    return executorService.submit(callable);
  }

  /**
   *
   * @param future
   * @return
   */
  public boolean isJobDone(Future future) {
    return future.isDone();
  }

  /**
   *
   * @return
   */
  public boolean getIsAlive() {
    return !executorService.isShutdown();
  }

  /**
   *
   */
  @PreDestroy
  public void shutdownExecutorService(){
    executorService.shutdown();
  }
}

