package clepcea.jwttest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	
	@GetMapping
	public String index(){
		return "Hello";
	}
	
	@GetMapping("/elements")
	public Map<String, String> elements(){
		HashMap<String, String> map = new HashMap<>();
		map.put("unu", "one");
		map.put("doi", "two");
		
		return map;
	}
	
	@GetMapping("/admin")
	public Map<String, String> admin(){
		HashMap<String, String> map = new HashMap<>();
		map.put("unua", "ones");
		map.put("dois", "twos");
		
		return map;
	}
}
