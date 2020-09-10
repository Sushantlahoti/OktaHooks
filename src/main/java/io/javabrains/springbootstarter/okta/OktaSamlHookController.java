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
public class OktaSamlHookController {
	
	private String i = "Welcome@123";
/*	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Authorization", "");
	}  

*/
	
	@RequestMapping("/saml")
	public String getAllTopics(@RequestHeader MultiValueMap<String, String> headers, @RequestBody String body) {
		
		
		System.out.println(body);
		
		headers.forEach((key, value) -> {
	        System.out.println(String.format(
	          "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
	    });
		System.out.println(headers.getFirst("authorization"));
		
		
	//	return Arrays.asList(new OktaPasswordHook("com.okta.action.update", "verified"));	
		
		// Value
		
		
		/*

		JSONArray commandsObj1 = new JSONArray();

		JSONObject e1 = new JSONObject();
		e1.put("op", "replace");
		e1.put("path", "/claims/array/attributeValues/1/value");
		e1.put("value", "replacementValue");
		commandsObj1.put(e1);

		JSONObject e2 = new JSONObject();
		e2.put("op", "replace");
		e2.put("path", "/authentication/authnContext");
		JSONObject e3 = new JSONObject();
		e3.put("authnContextClassRef", "Something:different?");
		e2.put("value", e3);

		JSONObject e4 = new JSONObject();
		e4.put("op", "add");
		e4.put("path", "/claims/foo");

		JSONObject e9 = new JSONObject();

		JSONObject e5 = new JSONObject();
		e5.put("NameFormat", "urn:oasis:names:tc:SAML:2.0:attrname-format:basic");
		e9.put("attributes", e5);

		JSONObject e7 = new JSONObject();
		e7.put("xsi:type", "xs:string");
		JSONObject e8 = new JSONObject();
		e8.put("attributes", e7);
		
		JSONArray commandsObj2 = new JSONArray();
		
		commandsObj2.put(e8);
		e9.put("attributeValues", commandsObj2);
		
		
		

		e4.put("value", e9);

		commandsObj1.put(e2);

		commandsObj1.put(e4);

		// Commands Object
		JSONArray commandsObj = new JSONArray();

		JSONObject e = new JSONObject();
		e.put("type", "com.okta.assertion.patch");
		e.put("value", commandsObj1);
		commandsObj.put(e);

		JSONObject f = new JSONObject();
		f.put("commands", commandsObj);

		
		*/
		
		
		
		/***** Commands Array object 1 values ********/
		JSONArray commandsObj1Values = new JSONArray();

		// Commands Array object 1 value object 1 content
	//	String o1v1Obj = "replacementValue";

		// Commands Array object 1 value object 2 content
	//	JSONObject o1v2Obj = new JSONObject();
	//	o1v2Obj.put("authnContextClassRef", "Something:different?");

		// Commands Array object 1 value object 3 content
		JSONObject o1v3Obj = new JSONObject();

		// Commands Array object 1 value object 3 attributes content
		JSONObject o1v3_attObj = new JSONObject();
		o1v3_attObj.put("NameFormat", "urn:oasis:names:tc:SAML:2.0:attrname-format:basic");

		// Commands Array object 1 value object 3 attributesValues content
		JSONArray o1v3_attValueObj = new JSONArray();

		JSONObject e4 = new JSONObject();
		e4.put("xsi:type", "xs:string");
		JSONObject e5 = new JSONObject();
		e5.put("attributes", e4);
		e5.put("value", "1247362878461398");

		o1v3_attValueObj.put(e5);

		o1v3Obj.put("attributes", o1v3_attObj);
		o1v3Obj.put("attributeValues", o1v3_attValueObj);

		//commandsObj1Values.put(getCommandsValueObject("replace", "/claims/array/attributeValues/1/value", o1v1Obj));
		//commandsObj1Values.put(getCommandsValueObject("replace", "/authentication/authnContext", o1v2Obj));
		commandsObj1Values.put(getCommandsValueObject("add", "/claims/LoanCareNumber", o1v3Obj));

		/***** Commands Array object 2 values ********/
		JSONArray commandsObj2Values = new JSONArray();

		// Commands Array object 2 value object 1 content
		String o2v1Obj = "definitelyARealSession";
		
	//	commandsObj2Values.put(getCommandsValueObject("replace", "/authentication/sessionIndex", o2v1Obj));

		/***** Commands Array ********/
		JSONArray commandsJsonArr = new JSONArray();
		commandsJsonArr.put(getCommandsObject("com.okta.assertion.patch", commandsObj1Values));
		//commandsJsonArr.put(getCommandsObject("com.okta.assertion.patch", commandsObj2Values));

		/***** Final Data ********/
		JSONObject finalObject = new JSONObject();
		finalObject.put("commands", commandsJsonArr);

		System.out.println(finalObject);
		return finalObject.toString();
		
	}

	/**
	 * 
	 * To return commands object
	 * 
	 * @param typeContent
	 * @param valueContent
	 * @return
	 */
	private static JSONObject getCommandsObject(String typeContent, JSONArray valueContent) {
		JSONObject commandObject = new JSONObject();

		commandObject.put("type", "com.okta.assertion.patch");
		commandObject.put("value", valueContent);

		return commandObject;
	}

	/**
	 * 
	 * To return commands object Value content
	 * 
	 * @param opContent
	 * @param pathContent
	 * @param valueContent
	 * @return
	 */
	private static JSONObject getCommandsValueObject(String opContent, String pathContent, Object valueContent) {
		JSONObject valueObject = new JSONObject();
		valueObject.put("op", opContent);
		valueObject.put("path", pathContent);
		valueObject.put("value", valueContent);
		return valueObject;
		
		
	//	System.out.println(f);
	//	return f.toString();
		// return d.getString(key)
	}

}