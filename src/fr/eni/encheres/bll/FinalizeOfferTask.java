package fr.eni.encheres.bll;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FinalizeOfferTask implements ServletContextListener {
	private ScheduledExecutorService executorService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
	    executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(this::checkExpiredOffers, 1, 1, TimeUnit.MINUTES);
	}

	private void checkExpiredOffers() {
	    System.out.println("Checking for expired offers, Not implemented yet");
	    // select where NOW() > ENCHERES.ARTICLES_VENDUS.
	    // DELETE RETRAIT, ENCHERES, ARTICLES_VENDUS where no_article
	    // ShipArticle();
	    // add to SOLD table or add column to ARTICLES_VENDUS: boolean "sold"?
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		executorService.shutdownNow();
	 }
}
