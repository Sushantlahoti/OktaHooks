package io.javabrains.springbootstarter.okta;


import java.util.stream.Collectors;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OktaPasswordHookController {
	
	private String i = "Welcome@123";
/*	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Authorization", "");
	}  

*/
	
	@RequestMapping("/okta")
	public String getAllTopics(@RequestHeader MultiValueMap<String, String> headers, @RequestBody String body) {
		
		
		System.out.println(body);
		
		headers.forEach((key, value) -> {
	        System.out.println(String.format(
	          "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
	    });
		System.out.println(headers.getFirst("authorization"));
		
		
	//	return Arrays.asList(new OktaPasswordHook("com.okta.action.update", "verified"));	
		
		
		JSONObject d = new JSONObject();
		JSONObject vaule = new JSONObject();
	//	vaule.put("credential", "UNVERIFIED");
		vaule.put("credential", "VERIFIED");
		JSONObject e = new JSONObject();
		e.put("type", "com.okta.action.update");
		e.put("value", vaule);
		JSONArray commandsObj = new JSONArray();
		commandsObj.put(e);
		JSONObject f = new JSONObject();
		f.put("commands", commandsObj);
		System.out.println(f);
		return f.toString();
		//return d.getString(key)
	}
}
