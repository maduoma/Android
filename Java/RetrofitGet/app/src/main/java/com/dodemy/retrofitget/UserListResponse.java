
package com.dodemy.retrofitget;

import java.util.HashMap;
import java.util.Map;

public class UserListResponse {

    // POJO class to get the data from web api
private String id;
private String name;
private String email;
private String password;
private String com_code;
private String status;
private String forgot;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getCom_code() {
return com_code;
}

public void setCom_code(String com_code) {
this.com_code = com_code;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getForgot() {
return forgot;
}

public void setForgot(String forgot) {
this.forgot = forgot;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}