package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program2 {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		JOptionPane.showMessageDialog(null, "Enter rental data");
		String carModel=JOptionPane.showInputDialog("Car model");
		String start= JOptionPane.showInputDialog("\"Pickup (dd/MM/yyy hh:ss): \"");
		Date d = new SimpleDateFormat("dd/MM/yyy HH:ss").parse(start);
		String finish=JOptionPane.showInputDialog("Return (dd/MM/yyy hh:yy):");
		Date d1 = new SimpleDateFormat("dd/MM/yyy HH:ss").parse(finish);
		
		CarRental cr = new CarRental(d,d1, new Vehicle(carModel));
		
		String aux = JOptionPane.showInputDialog("Enter price per hour: ");
		double pricePerHour = Double.parseDouble(aux);
		String aux2 = JOptionPane.showInputDialog("Enter price per day: ");
		double pricePerDay = Double.parseDouble(aux2);
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		JOptionPane.showMessageDialog(null, "INVOICE:");
		JOptionPane.showMessageDialog(null, "Basic payment: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
		JOptionPane.showMessageDialog(null, "Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		JOptionPane.showMessageDialog(null, "Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		

	}

}
