package hotels.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Reserves {
	Clients client;
	int numpersones;
	Habitacions habitacio;
	LocalDate entrada, sortida;

	public Reserves(Clients client) {
		super();
		this.client = client;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	public int getNumpersones() {
		return numpersones;
	}

	public void setNumpersones(int numpersones) {
		this.numpersones = numpersones;
	}

	public Habitacions getHabitacio() {
		return habitacio;
	}

	public void setHabitacio(Habitacions habitacio) {
		this.habitacio = habitacio;
	}

	public LocalDate getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}

	public LocalDate getSortida() {
		return sortida;
	}

	public void setSortida(LocalDate sortida) {
		this.sortida = sortida;
	}

	public String[] posanttaula() {
		String[] array = new String[4];
		array[0] = this.entrada.getDayOfMonth() + "/" + this.entrada.getMonthValue() + "/" + this.entrada.getYear();
		array[1] = client.getDni();
		array[2] = this.numpersones + "";
		array[3] = this.getHabitacio().getNumhabitacio() + "";
		return array;
	}

	public static void afegirtaulaconfirmades(ArrayList<Reserves> confirmades, DefaultTableModel confirmades2,
			JDateChooser data, String entradaosortida) {
		confirmades2.fireTableRowsDeleted(0, confirmades2.getRowCount());
		confirmades2.setRowCount(0);
		for (Reserves reserves : confirmades) {
			if (data.getDate().equals(Date.valueOf(reserves.getEntrada())) && entradaosortida.equals("Entrades")) {
				String[] textconfirmades = new String[4];
				textconfirmades[0] = reserves.getClient().getDni();
				textconfirmades[1] = reserves.getClient().getNom();
				textconfirmades[2] = reserves.getClient().getCognom();
				textconfirmades[3] = String.valueOf(reserves.getHabitacio().getNumhabitacio());
				confirmades2.addRow(textconfirmades);
			} else if (data.getDate().equals(Date.valueOf(reserves.getSortida()))
					&& entradaosortida.equals("Sortides")) {
				String[] textconfirmades = new String[4];
				textconfirmades[0] = reserves.getClient().getDni();
				textconfirmades[1] = reserves.getClient().getNom();
				textconfirmades[2] = reserves.getClient().getCognom();
				textconfirmades[3] = String.valueOf(reserves.getHabitacio().getNumhabitacio());
				confirmades2.addRow(textconfirmades);
			}
			else {
				
			}
		}
	}
	
	@Override
	public String toString() {
		return this.getEntrada()+" No. ha:"+this.getHabitacio().getNumhabitacio()+" Capa: "+this.getHabitacio().getCapacitat();
	}

}