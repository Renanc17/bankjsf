package managedBean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Deprecated
@SessionScoped
@ManagedBean (name = "date")
public class DateController {

	private Date dataAtual = new Date();
	private Date dataAtualMenos7 = new Date();
	private Date dataAtualMenos30 = new Date();
	private Date dataAtualMenos90 = new Date();
	private Date dataAtualMais1 = new Date();
	private Date dataAtualMais45 = new Date();
	
	public Date getDataAtual(){
        return dataAtual;
    }
	
	public Date getDataAtualMenos7(){
		dataAtualMenos7.setDate(dataAtualMenos7.getDate() - 7);
        return dataAtualMenos30;
    }
	
	public Date getDataAtualMenos30(){
        dataAtualMenos30.setDate(dataAtualMenos30.getDate() - 30);
        return dataAtualMenos30;
    }

	public Date getDataAtualMenos90(){
        dataAtualMenos90.setDate(dataAtualMenos90.getDate() - 90);
        return dataAtualMenos90;
    }
	
	public Date getDataAtualMais1(){
        dataAtualMais1.setDate(dataAtualMais1.getDate() + 1);	
		return dataAtualMais1;
    }
	
	public Date getDataAtualMais45(){
		dataAtualMais45.setDate(dataAtualMais45.getDate() + 10);
        return dataAtual;
    }
	
}

