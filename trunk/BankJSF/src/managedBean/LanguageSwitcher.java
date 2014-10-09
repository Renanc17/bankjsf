package managedBean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "switcher")
@SessionScoped
public class LanguageSwitcher {
    
	Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    
    public String switchLocale(String lang) {
        locale = new Locale(lang);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() +
            "?faces-redirect=true";
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}