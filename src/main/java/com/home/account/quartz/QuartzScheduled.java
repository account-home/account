package  com.home.account.quartz;

import com.home.account.page.MyCrawler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling //标注为定时任务类
@EnableAsync //开启多线程
public class QuartzScheduled {

    //电视爬取任务
    @Async
    @Scheduled(cron = "0 0 2 1 * ?") //每个月的早上2点执行
    public void configureTasks() throws InterruptedException {
        MyCrawler crawler = new MyCrawler();
        crawler.crawling(new String[]{"http://www.baidu.com"});
    }

//    @Async   //锁
//    @Scheduled(fixedDelay = 2000)
//    public void second() {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//    }
}

