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
	private Date dataAtualMenos90 = new Date();
	
	public Date getDataAtual(){
        return dataAtual;
    }

	public Date getDataAtualMenos30(){
        dataAtualMenos30 = new Date();
        dataAtualMenos30.setDate(dataAtualMenos30.getDate() - 30);
        return dataAtualMenos30;
    }

	public Date getDataAtualMenos90(){
        dataAtualMenos90 = new Date();
        dataAtualMenos90.setDate(dataAtualMenos90.getDate() - 90);
        return dataAtualMenos90;
    }
	
}

