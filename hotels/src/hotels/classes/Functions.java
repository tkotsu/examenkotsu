package hotels.classes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Functions {

	public static boolean existeixelclient(String dni, ArrayList<Clients> clients) {
		for (Clients c : clients) {
			if (c.getDni().equals(dni)) {

				return true;
			}
		}
		return false;
	}

	public static Clients retornaclient(String dni, ArrayList<Clients> client) {
		for (Clients c : client) {
			if (dni.equals(c.getDni())) {
				return c;
			}
		}
		return null;
	}

	public static LocalDate dataentrada(JCalendar jcdata) {
		Long dataNano = jcdata.getDate().getTime();
		LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
		return data;

	}

	public static boolean existeixlhabitacio(Hotel hotel, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi)) {
				return true;
			}
		}
		return false;
	}

	public static int retornarnumhabitacio(Hotel hotel, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi)) {
				return h.getCapacitat();
			}
		}
		return 0;
	}

	public static void actualitzahabitacio(Hotel hotel, String capacitat, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi))
				;
			h.setCapacitat(Integer.parseInt(capacitat));
		}
	}

	public static boolean estaocupat(Hotel hotel, LocalDate entrada, LocalDate sortida, String numeropersones) {
		int limit = 2;
		for (Habitacions h : hotel.getHabitacions()) {
			for (int i = 0; i < limit; i++) {
				int capacitat = Integer.parseInt(numeropersones) + i;
				for (Reserves r : hotel.pendents) {

					if (r.getHabitacio().getCapacitat() == capacitat
							&& ((entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida()))
									|| (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida()))
									|| (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())))) {
						return true;
					}
				}
				for (Reserves r : hotel.confirmades) {

					if (r.getHabitacio().getCapacitat() == capacitat
							&& ((entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida()))
									|| (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida()))
									|| (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())))) {
						return true;
					}
				}
				if (h.getCapacitat() == capacitat) {
					return true;
				}
			}
		}

		return false;
	}

	public static Habitacions afegirhabi(Hotel hotel, LocalDate entrada, LocalDate sortida, String numeropersones) {
		int limit = 2;
		for (Habitacions h : hotel.getHabitacions()) {
			for (int i = 0; i < limit; i++) {
				int capacitat = Integer.parseInt(numeropersones) + i;
				for (Reserves r : hotel.pendents) {
					if (h.getNumhabitacio() == r.getHabitacio().getNumhabitacio()) {

						if (r.getHabitacio().getCapacitat() == capacitat
								&& ((entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida()))
										|| (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida()))
										|| (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())))) {
							return h;
						}
					}
				}

				for (Reserves r : hotel.confirmades) {
					if (h.getNumhabitacio() == r.getHabitacio().getNumhabitacio()) {
						if (r.getHabitacio().getCapacitat() == capacitat
								&& ((entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida()))
										|| (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida()))
										|| (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())))) {
							return h;
						}
					}

				}
				if (h.getCapacitat() == capacitat) {
					return h;
				}
			}
		}
		return null;

	}

	public static void pasarloaconfirmades(ArrayList<Reserves> arraypendents, ArrayList<Reserves> arrayconfirmades,
			int index, DefaultTableModel reserves, DefaultTableModel confirmades, JDateChooser data,
			String entradaosortida) {
		arrayconfirmades.add(arraypendents.get(index));
		arraypendents.remove(index);
		reserves.removeRow(index);
		Reserves.afegirtaulaconfirmades(arrayconfirmades, confirmades, data, entradaosortida);
		String[] ok = new String[] { "ok" };
		JOptionPane finestraok = new JOptionPane();
		finestraok.showOptionDialog(null, "Reserva Confirmada", "Atenció", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, ok, ok[0]);
	}

	public static void eliminarpendent(ArrayList<Reserves> arraypendents, int index, DefaultTableModel reserves) {
		arraypendents.remove(index);
		reserves.removeRow(index);
	}

	public static void llistaclients(ArrayList<Clients> client, String cerca, DefaultListModel<Clients> model,
			DefaultListModel<Reserves> model2) {
		model.clear();
		model2.clear();
		for (Clients c : client) {
			if (c.getNom().toLowerCase().contains(cerca.toLowerCase())
					|| c.getCognom().toLowerCase().contains(cerca.toLowerCase())
					|| c.getDni().toLowerCase().contains(cerca.toLowerCase())) {
				model.addElement(c);

			}
		}
	}

	public static void retornareservesclient(DefaultListModel<Reserves> model2, ArrayList<Reserves> arrayList,
			ArrayList<Reserves> arrayList2, Clients clients2) {
		model2.clear();
		for (Reserves reserves : arrayList) {
			if (reserves.getClient().equals(clients2)) {
				model2.addElement(reserves);
			}
		}
		for (Reserves reserves : arrayList2) {
			if (reserves.getClient().equals(clients2)) {
				model2.addElement(reserves);
			}
		}
	}

	public static void eliminarreserva(DefaultListModel<Reserves> model2, ArrayList<Reserves> pendents,
			ArrayList<Reserves> confirmades, Reserves selectedValue, DefaultTableModel reserves2,
			DefaultTableModel confirmades2) {

		for (Reserves r2 : confirmades) {
			if (selectedValue.getClient().getDni().equalsIgnoreCase(r2.getClient().getDni()) && selectedValue.getEntrada().isEqual(r2.getEntrada())
					&& selectedValue.getHabitacio().equals(r2.getHabitacio())
					&& selectedValue.getNumpersones() == r2.getNumpersones()
					&& selectedValue.getSortida().isEqual(r2.getSortida())) {
				confirmades.remove(r2);
				model2.removeElement(r2);
				break;
			}
		}
		for (Reserves r2 : pendents) {
			if (selectedValue.getClient().getDni().equalsIgnoreCase(r2.getClient().getDni()) && selectedValue.getEntrada().isEqual(r2.getEntrada())
					&& selectedValue.getHabitacio().equals(r2.getHabitacio())
					&& selectedValue.getNumpersones() == r2.getNumpersones()
					&& selectedValue.getSortida().isEqual(r2.getSortida())) {
				pendents.remove(r2);
				model2.removeElement(r2);
				break;
			}
		}
	}

	public static void actualitzapendents(ArrayList<Reserves> pendents, DefaultTableModel reserves) {
		reserves.setRowCount(0);
		for (Reserves reserves2 : pendents) {
			reserves.addRow(reserves2.posanttaula());
		}
	}

	public static void actualitzaconfirmades(ArrayList<Reserves> confirmades, DefaultTableModel reserves, JDateChooser jcdata, String string) {
		Reserves.afegirtaulaconfirmades(confirmades, reserves, jcdata, string);

	}
	
	public static Habitacions tornahabitacio(ArrayList<Habitacions> habitacions, String numhabi) {
		for (Habitacions habitacions2 : habitacions) {
			if(habitacions2.getNumhabitacio()==Integer.parseInt(numhabi));
			return habitacions2;
		}
		return null;
	}
	
	public static void pendentstaula(DefaultTableModel reserves, ArrayList<Reserves> arrayList) {
		for (Reserves reserves2 : arrayList) {
			reserves.addRow(reserves2.posanttaula());
		}
	}

	public static void confirmadestaula(DefaultTableModel confirmades, Hotel hotel, JButton entradasortida,
			JDateChooser mostrardatareserves) {
		Reserves.afegirtaulaconfirmades(hotel.getConfirmades(), confirmades, mostrardatareserves, entradasortida.getText());
	}

}