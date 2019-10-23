package com.java.employeeconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping("/getEmployee")
	public String getEmployee() {

		ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(serviceInstance.getUri().toString()+"/getEmployee", HttpMethod.GET, getHeaders(),
					String.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return response.getBody();

	}

	private HttpEntity<?> getHeaders() {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
