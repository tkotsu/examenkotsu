package hotels.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Fitxer{
	File fclients, carpeta, fpendents, fconfirmades, fhabitacions;
	FileWriter fwhabitacions, fwclients, fwpendents, fwconfirmades;
	BufferedWriter bwhabitacions, bwclients, bwpendents, bwconfirmades;
	FileReader frhabitacions, frclients, frpendents, frconfirmades;
	BufferedReader brhabitacions, brclients, brpendents, brconfirmades;
	
	public Fitxer() {
		creaDirectori();
		creaFclients();
		creaFpendents();
		creaFconfirmades();
		creaFhabitacions();
	}
	
	private void creaDirectori() {
		carpeta = new File("dades");
		if(carpeta.mkdir()) {
			System.out.println("Carpeta creada correctament");	
		}
		else {
			System.err.println("Error al crear la carpeta!");
		}
	}
	
	private void creaFclients() {
		fclients = new File("dades"+ File.separator +"clients");
		if(fclients.exists()) {
			System.out.println("El fitxer ja existeix!");
		}
		else {
			try {
				if(fclients.createNewFile()) {
					System.out.println("Fitxer creat!");
				}
				else {
					System.err.println("Error al crear el fitxer");	
				}
			} catch (IOException e) {
				System.err.println("Error al crear el fitxer!!");
			}
		}
	}
	
	private void creaFpendents() {
		fpendents = new File("dades"+ File.separator +"pendents");
		if(fpendents.exists()) {
			System.out.println("El fitxer ja existeix!");
		}
		else {
			try {
				if(fpendents.createNewFile()) {
					System.out.println("Fitxer creat!");
				}
				else {
					System.err.println("Error al crear el fitxer");	
				}
			} catch (IOException e) {
				System.err.println("Error al crear el fitxer!!");
			}
		}
	}
	
	private void creaFconfirmades() {
		fconfirmades = new File("dades"+ File.separator +"confirmades");
		if(fconfirmades.exists()) {
			System.out.println("El fitxer ja existeix!");
		}
		else {
			try {
				if(fconfirmades.createNewFile()) {
					System.out.println("Fitxer creat!");
				}
				else {
					System.err.println("Error al crear el fitxer");	
				}
			} catch (IOException e) {
				System.err.println("Error al crear el fitxer!!");
			}
		}
	}
	
	private void creaFhabitacions() {
		fhabitacions = new File("dades"+ File.separator +"habitacions");
		if(fhabitacions.exists()) {
			System.out.println("El fitxer ja existeix!");
		}
		else {
			try {
				if(fhabitacions.createNewFile()) {
					System.out.println("Fitxer creat!");
				}
				else {
					System.err.println("Error al crear el fitxer");	
				}
			} catch (IOException e) {
				System.err.println("Error al crear el fitxer!!");
			}
		}
	}
	
	public void writerfclients(ArrayList<Clients> client) {
		try {
			fwclients = new FileWriter(fclients);
			bwclients = new BufferedWriter(fwclients);
			for (Clients clients : client) {
				bwclients.write(clients.getDni()+"-"+clients.getNom()+"-"+clients.getCognom());
				bwclients.write(System.lineSeparator());
			}
			bwclients.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readerfclients(ArrayList<Clients> client) {
		try {
			frclients = new FileReader(fclients);
			brclients = new BufferedReader(frclients);
			String currentline;
			try {
				while((currentline = brclients.readLine()) != null) {
					String[] arrayclient = currentline.split("-");
					Clients clients = new Clients(arrayclient[0]);
					clients.setNom(arrayclient[1]);
					clients.setCognom(arrayclient[2]);
					client.add(clients);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writerfhabitacions(ArrayList<Habitacions> habi) {
		try {
			fwhabitacions = new FileWriter(fhabitacions, false);
			bwhabitacions = new BufferedWriter(fwhabitacions);
			for (Habitacions habitacions : habi) {
				bwhabitacions.write(habitacions.getNumhabitacio()+"-"+habitacions.getCapacitat());
				bwhabitacions.write(System.lineSeparator());
			}
			bwhabitacions.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readerfhabitacions(ArrayList<Habitacions> habi, Hotel hotel) {
		try {
			frhabitacions = new FileReader(fhabitacions);
			brhabitacions = new BufferedReader(frhabitacions);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentline;
		try {
			while ((currentline = brhabitacions.readLine()) != null) {
				String[] habitacio = currentline.split("-");
				Habitacions rehabi = new Habitacions();
				rehabi.setNumhabitacio(Integer.parseInt(habitacio[0]));
				rehabi.setCapacitat(Integer.parseInt(habitacio[1]));
				habi.add(rehabi);
			}
			hotel.setHabitacions(habi);
			brhabitacions.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writerfpendents(ArrayList<Reserves> pendents) {
		try {
			fwpendents = new FileWriter(fpendents);
			bwpendents = new BufferedWriter(fwpendents);
			for (Reserves reserves : pendents) {
				String dataentrada = reserves.getEntrada().getDayOfMonth()+"/"+reserves.getEntrada().getMonthValue()+"/"+reserves.getEntrada().getYear();
				String datasortida = reserves.getSortida().getDayOfMonth()+"/"+reserves.getSortida().getMonthValue()+"/"+reserves.getSortida().getYear();
				bwpendents.write(reserves.getClient().getDni()+"-"+dataentrada+"-"+datasortida+"-"+String.valueOf(reserves.getNumpersones())+"-"+reserves.getHabitacio().getNumhabitacio());
				bwpendents.write(System.lineSeparator());
			}
			bwpendents.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readerfpendents(ArrayList<Reserves> pendents, Hotel hotel) {
		try {
			frpendents = new FileReader(fpendents);
			brpendents = new BufferedReader(frpendents);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentline;
		try {
			while((currentline=brpendents.readLine())!=null) {
				String[] dadespendent = currentline.split("-");
				String[] dataentrada = dadespendent[1].split("/");
				String[] datasortida = dadespendent[2].split("/");
				LocalDate entrada = LocalDate.of(Integer.parseInt(dataentrada[2]), Integer.parseInt(dataentrada[1]), Integer.parseInt(dataentrada[0]));
				LocalDate sortida = LocalDate.of(Integer.parseInt(datasortida[2]), Integer.parseInt(datasortida[1]), Integer.parseInt(datasortida[0]));
				Reserves pendent = new Reserves(Functions.retornaclient(dadespendent[0], hotel.getClients()));
				pendent.setEntrada(entrada);
				pendent.setSortida(sortida);
				pendent.setNumpersones(Integer.parseInt(dadespendent[3]));
				pendent.setHabitacio(Functions.tornahabitacio(hotel.getHabitacions(), dadespendent[4]));
				pendents.add(pendent);
			}
			hotel.setPendents(pendents);

			brpendents.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writerfconfirmats(ArrayList<Reserves> confirmats) {
		try {
			fwconfirmades = new FileWriter(fconfirmades);
			bwconfirmades = new BufferedWriter(fwconfirmades);
			for (Reserves reserves : confirmats) {
				String dataentrada = reserves.getEntrada().getDayOfMonth()+"/"+reserves.getEntrada().getMonthValue()+"/"+reserves.getEntrada().getYear();
				String datasortida = reserves.getSortida().getDayOfMonth()+"/"+reserves.getSortida().getMonthValue()+"/"+reserves.getSortida().getYear();
				bwconfirmades.write(reserves.getClient().getDni()+"-"+dataentrada+"-"+datasortida+"-"+String.valueOf(reserves.getNumpersones())+"-"+reserves.getHabitacio().getNumhabitacio());
				bwconfirmades.write(System.lineSeparator());
			}
			bwconfirmades.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readerfconfirmades(ArrayList<Reserves> confirmades, Hotel hotel) {
		try {
			frconfirmades = new FileReader(fconfirmades);
			brconfirmades = new BufferedReader(frconfirmades);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentline;
		try {
			while((currentline=brconfirmades.readLine())!=null) {
				String[] dadespendent = currentline.split("-");
				String[] dataentrada = dadespendent[1].split("/");
				String[] datasortida = dadespendent[2].split("/");
				LocalDate entrada = LocalDate.of(Integer.parseInt(dataentrada[2]), Integer.parseInt(dataentrada[1]), Integer.parseInt(dataentrada[0]));
				LocalDate sortida = LocalDate.of(Integer.parseInt(datasortida[2]), Integer.parseInt(datasortida[1]), Integer.parseInt(datasortida[0]));
				Reserves confirmada = new Reserves(Functions.retornaclient(dadespendent[0], hotel.getClients()));
				confirmada.setEntrada(entrada);
				confirmada.setSortida(sortida);
				confirmada.setNumpersones(Integer.parseInt(dadespendent[3]));
				confirmada.setHabitacio(Functions.tornahabitacio(hotel.getHabitacions(), dadespendent[4]));
				confirmades.add(confirmada);
			}
			hotel.setConfirmades(confirmades);

			brpendents.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}