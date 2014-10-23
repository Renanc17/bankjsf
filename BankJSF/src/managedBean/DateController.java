package managedBean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Deprecated
@SessionScoped
@ManagedBean (name = "date")
public class DateController {

	private Date dataAtual = new Date();
	private Date dataAtualMenos30 = new Date();
	
	public Date getDataAtual(){
        return dataAtual;
    }

	public Date getDataAtualMenos30(){
        dataAtualMenos30 = new Date();
        dataAtualMenos30.setDate(dataAtualMenos30.getDate() - 30);
        return dataAtualMenos30;
    }
	
}

