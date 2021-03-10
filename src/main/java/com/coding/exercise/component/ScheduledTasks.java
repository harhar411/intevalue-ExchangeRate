package com.coding.exercise.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coding.exercise.entity.AutoCompleteDataJSON;
import com.coding.exercise.entity.ChartDataJSON;
import com.coding.exercise.entity.FinancialDataJSON;
import com.coding.exercise.repository.ChartDataJSONRepository;
import com.coding.exercise.repository.DefaultDataJSONRepository;
import com.coding.exercise.repository.FinancialDataJSONRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	DefaultDataJSONRepository defaulDataRepo;
	
	@Autowired
	FinancialDataJSONRepository financialDataRepo;
	
	@Autowired
	ChartDataJSONRepository chartDataRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Scheduled(fixedRate = 30000)
	public void getChartData() throws JsonProcessingException, URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("x-rapidapi-key", "7357c43c6cmsh035c13eb97cebe2p1775f5jsn4bf8063d56f1");
		headers.add("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		URI uri = new URI("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval=5m&symbol=AMRN&range=1d&region=US");
		
		ScheduledTasks.log.info("Current time : " + dateFormat.format(new Date()));
		ScheduledTasks.log.info("Begin sending request to Yahoo finance API for \"get-chart\"");

		String stringData =
				restTemplate.exchange(
					uri, 
					HttpMethod.GET, 
					requestEntity, 
					String.class)
				.getBody();
		
		ScheduledTasks.log.info("This is the data : ");
		ScheduledTasks.log.info(stringData);
		
		ChartDataJSON chartDataJSON = new ChartDataJSON();
		
		chartDataJSON.setJsonData(stringData);
		
		chartDataRepo.save(chartDataJSON);		
	}
	
	@Scheduled(fixedRate = 30000)
	public void getFinancialData() throws JsonProcessingException, URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("x-rapidapi-key", "7357c43c6cmsh035c13eb97cebe2p1775f5jsn4bf8063d56f1");
		headers.add("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		URI uri = new URI("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-financials?symbol=AMRN&region=US");
		
		ScheduledTasks.log.info("Current time : " + dateFormat.format(new Date()));
		ScheduledTasks.log.info("Begin sending request to Yahoo finance API for \"get-financials\"");

		String stringData =
				restTemplate.exchange(
					uri, 
					HttpMethod.GET, 
					requestEntity, 
					String.class)
				.getBody();
		
		ScheduledTasks.log.info("This is the data : ");
		ScheduledTasks.log.info(stringData);
		
		FinancialDataJSON financialDataJSON = new FinancialDataJSON();
		
		financialDataJSON.setJsonData(stringData);
		
		financialDataRepo.save(financialDataJSON);
	}
	
	@Scheduled(fixedRate = 30000)
	public void getDefaultData() throws JsonProcessingException, URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("x-rapidapi-key", "7357c43c6cmsh035c13eb97cebe2p1775f5jsn4bf8063d56f1");
		headers.add("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		URI uri = new URI("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=tesla&region=US");
		
		ScheduledTasks.log.info("Current time : " + dateFormat.format(new Date()));
		ScheduledTasks.log.info("Begin sending request to Yahoo finance API for \"auto-complete\"");
		
		String stringData =
				restTemplate.exchange(
					uri, 
					HttpMethod.GET, 
					requestEntity, 
					String.class)
				.getBody();
		
		ScheduledTasks.log.info("This is the data : ");
		ScheduledTasks.log.info(stringData);
		
		AutoCompleteDataJSON defaultDataJson = new AutoCompleteDataJSON();
		
		defaultDataJson.setJsonData(stringData);
		
		defaulDataRepo.save(defaultDataJson);		
	}
	
}