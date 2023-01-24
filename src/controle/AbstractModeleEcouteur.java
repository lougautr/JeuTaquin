package controle;

import java.util.*;

public abstract class AbstractModeleEcouteur{

	protected List<EcouteurModele> list;

	public AbstractModeleEcouteur(){
		this.list = new ArrayList<EcouteurModele>();
	}

	public void ajoutEcouteur(EcouteurModele e){
		this.list.add(e);
	}

	public void retraitEcouteur(EcouteurModele e){
		this.list.remove(e);
	}

	protected void puzzleChange(){
		for (EcouteurModele e : this.list){
			e.modeleMisAJour(this);
		}
	}

}
