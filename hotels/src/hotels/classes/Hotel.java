package hotels.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

public class Hotel {
	ArrayList<Habitacions> habitacions = new ArrayList<Habitacions>();
	ArrayList<Clients> clients = new ArrayList<Clients>();
	ArrayList<Reserves> pendents = new ArrayList<Reserves>();
	ArrayList<Reserves> confirmades = new ArrayList<Reserves>();
	String nomhotel;

	public Hotel(String nomhotel) {
		super();
		this.nomhotel = nomhotel;
	}

	public ArrayList<Habitacions> getHabitacions() {
		return habitacions;
	}

	public void setHabitacions(ArrayList<Habitacions> habitacions) {
		this.habitacions = habitacions;
	}

	public ArrayList<Clients> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Clients> clients) {
		this.clients = clients;
	}

	public ArrayList<Reserves> getPendents() {
		return pendents;
	}

	public void setPendents(ArrayList<Reserves> pendents) {
		this.pendents = pendents;
	}

	public ArrayList<Reserves> getConfirmades() {
		return confirmades;
	}

	public void setConfirmades(ArrayList<Reserves> confirmades) {
		this.confirmades = confirmades;
	}

	public String getNomhotel() {
		return nomhotel;
	}

	public void setNomhotel(String nomhotel) {
		this.nomhotel = nomhotel;
	}

	public void afegirpendents(String cdni, Hotel hotel, JCalendar jcdata, String numeropersones,
			ArrayList<Reserves> reservahotel, DefaultTableModel reserves, String nom, String cognom,
			ArrayList<Clients> clientshotel, String numnits) {
		if (Functions.existeixelclient(cdni, hotel.getClients())) {

			Clients client = new Clients(cdni);
			client = Functions.retornaclient(cdni, hotel.getClients());
			Reserves reserva = new Reserves(client);
			LocalDate datasortida = Functions.dataentrada(jcdata).plusDays(Integer.parseInt(numnits));

			if (Functions.estaocupat(hotel, Functions.dataentrada(jcdata), datasortida, numeropersones)) {
				reserva.setSortida(datasortida);
				reserva.setEntrada(Functions.dataentrada(jcdata));
				reserva.setNumpersones(Integer.valueOf(numeropersones));
				reserva.setHabitacio(
						Functions.afegirhabi(hotel, Functions.dataentrada(jcdata), datasortida, numeropersones));
				reservahotel.add(reserva);
				hotel.setPendents(reservahotel);
				reserves.addRow(reserva.posanttaula());
			} else {
				String[] ok = new String[] { "ok" };
				JOptionPane avis = new JOptionPane();
				avis.showOptionDialog(null, "No hi han habitacions lliures en els dies escollits!!", "Atenció",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ok, ok[0]);
			}

		} else {
			Clients client = new Clients(cdni);
			client.setNom(nom);
			client.setCognom(cognom);
			clientshotel.add(client);
			hotel.setClients(clientshotel);
			Reserves reserva = new Reserves(client);
			LocalDate datasortida = Functions.dataentrada(jcdata).plusDays(Integer.parseInt(numnits));
			if (Functions.estaocupat(hotel, Functions.dataentrada(jcdata), datasortida, numeropersones)) {

				reserva.setSortida(datasortida);
				reserva.setEntrada(Functions.dataentrada(jcdata));
				reserva.setNumpersones(Integer.valueOf(numeropersones));
				reserva.setHabitacio(
						Functions.afegirhabi(hotel, Functions.dataentrada(jcdata), datasortida, numeropersones));
				reservahotel.add(reserva);
				hotel.setPendents(reservahotel);
				reserves.addRow(reserva.posanttaula());

			} else {
				String[] ok = new String[] { "ok" };
				JOptionPane avis = new JOptionPane();
				avis.showOptionDialog(null, "No hi han habitacions lliures en els dies escollits!!", "Atenció",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ok, ok[0]);
			}
		}

	}
	
	
	
	

}