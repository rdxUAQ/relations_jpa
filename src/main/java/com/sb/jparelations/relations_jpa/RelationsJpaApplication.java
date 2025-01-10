package com.sb.jparelations.relations_jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sb.jparelations.relations_jpa.entities.Address;
import com.sb.jparelations.relations_jpa.entities.Client;
import com.sb.jparelations.relations_jpa.entities.Invoice;
import com.sb.jparelations.relations_jpa.repositories.ClientRepository;
import com.sb.jparelations.relations_jpa.repositories.InvoiceRepository;

@SpringBootApplication
public class RelationsJpaApplication implements CommandLineRunner{ 

	@Autowired
	private ClientRepository _clientRepository;

	@Autowired
	private InvoiceRepository _invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(RelationsJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//var result = manyToOne(5);

		//System.out.println(result == true ? "Db initialited correctly":"DB INIT error");
		
		createInvoicesToClient(1L);

		oneToMany(2L);

	}

	@Transactional
	public void oneToMany(Long id){

		Optional<Client> oClient = _clientRepository.findById(id);

		if(oClient.isPresent()){

			System.out.println("=============One To many client addresses=============");

			Client client = oClient.orElseThrow(null);

			

			try{
				Address address = new Address("street"+id, "colony"+id, id.intValue()+1);
				System.out.println("addres created");
				client.getAddresses().add(address);
				_clientRepository.save(client);
				address = new Address("street"+id+1, "colony"+id+1, id.intValue()+2);
				System.out.println("addres created");
				System.out.println("=======================addres saved=======================");
			}catch(Exception ex){
				System.out.println("=======================Exception saving address=======================");
				System.out.println(ex);
			}

		}

	}


	@Transactional
	public Boolean manyToOne(Integer clients){

		for(int i = 0; i < clients+1; i++){

			Client client = new Client("name"+i,"lastname"+i);

			
			var result = _clientRepository.save(client);

			if(result != null){

				System.out.println("=============Saved Client making Invoices=============");

				try{
				Invoice invoice = new Invoice("description"+(i+1), i+1*100L);
				invoice.setClient(client);
				_invoiceRepository.save(invoice);

				invoice = new Invoice("description"+(i+2), i+2*100L);
				invoice.setClient(client);
				_invoiceRepository.save(invoice);

			}catch(Exception e){
				System.out.println("(=============Error saving Invoice=============\n");
				return false;

			}
				

			}else{

				System.out.println("(=============Error saving client=============\n"+client.toString());
				return false;
			}	
		}
		return true;
	}


	@Transactional
	public void createInvoicesToClient(Long id){

		Optional<Client> oClient = _clientRepository.findById(id);

		if(oClient.isPresent()){

			System.out.println("=============Creating Invoices to an Existen Entity=============");
			Client client = oClient.orElseThrow(null);

			Invoice invoice = new Invoice("description"+(id+1), 1+1*100L);
			invoice.setClient(client);
			_invoiceRepository.save(invoice);

			invoice = new Invoice("description"+(id+2), 1+2*100L);
			invoice.setClient(client);
			_invoiceRepository.save(invoice);
		}


	}

}
