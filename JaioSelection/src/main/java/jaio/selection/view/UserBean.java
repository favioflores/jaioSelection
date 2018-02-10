/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaio.selection.view;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Flores Sanchez
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean{

	public String name;
	public String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

        
	public String outcome(){

		FacesContext fc = FacesContext.getCurrentInstance();
		this.country = getCountryParam(fc);

		return "result";
	}

	//get value from "f:param"
	public String getCountryParam(FacesContext fc){

		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("country");

	}

	//getter and setter methods

}