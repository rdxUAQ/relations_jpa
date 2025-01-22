package com.sb.jparelations.relations_jpa;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

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
		
		//createInvoicesToClient(1L);

		//oneToManyAddreses(2L);

		//removeAddress(2L);

		//removeAddressFindById();

		//OneToManyBidirectional();

		OneToManyBidirectionalById(2L);

	}

	@Transactional
	public void OneToManyBidirectionalById(Long id){

		System.out.println("=======================OneToManyBidirectionalById=======================");

		Optional<Client>  Oclient = _clientRepository.findById(id);

		Oclient.ifPresent(client -> {

			Invoice inv1 = new Invoice("compra bidirectional By id", 98132L);
			Invoice inv2 = new Invoice("compra bidirectional By id2", 914632L);

			client.addInvoice(inv1).addInvoice(inv2);

			_clientRepository.save(client);

			Optional<Client> result = _clientRepository.findOneWithInvoices(client.getId());


			try{
			result.ifPresent(show -> {
				System.out.println("=======================INFO=======================");
				System.out.println(show.toString());
			});

			}catch(Exception ex){
				System.out.println("=======================INFO=======================");
				System.out.println(ex);
			}

		});

	}

	@Transactional
	public void OneToManyBidirectional(){
		System.out.println("=======================OneToManyBidirectional=======================");

		Client client = new Client("cliente ", "bidirectional");

		Invoice inv1 = new Invoice("compra bidirectional", 5625L);
		Invoice inv2 = new Invoice("compra bidirectional 2", 987987L);


		client.addInvoice(inv1).addInvoice(inv2);

		_clientRepository.save(client);

		Optional<Client> result = _clientRepository.findById(client.getId());


		try{
		result.ifPresent(show -> {
			System.out.println("=======================INFO=======================");
			System.out.println(show.toString());
		});

		}catch(Exception ex){
			System.out.println("=======================INFO=======================");
			System.out.println(ex);
		}

	}


	@Transactional
	public void removeAddressFindById(){
		Optional<Client> optionalC = _clientRepository.findById(2L);

		optionalC.ifPresent(client -> 
		{

			Address add1= new Address("calle 2", "colonia 2", 33);
			Address add2 = new Address("calle 3", "colonia 3", 35);

			client.setAddresses(Arrays.asList(add1,add2));

			_clientRepository.save(client);

			System.out.println(client);


			Optional<Client> optionalByOneClient = _clientRepository.findOne(2L); 
			optionalByOneClient.ifPresent(c ->{

				Address remAddress = c.getAddresses().stream().filter(add ->
				add.getNumber().equals(add2.getNumber()) &&
				add.getColony().equals(add2.getColony()) &&
				add.getStreet_avenue().equals(add2.getStreet_avenue())).findFirst().orElse(null);

				try{
					c.getAddresses().remove(remAddress);
					_clientRepository.save(c);
					System.out.println(c);
				}
				catch(Exception ex){
					System.out.println(ex);
				}
			});

		});
	}

	
	@Transactional
	public void removeAddress(Long id){

		Scanner scanner = new Scanner(System.in);

		Optional<Client> oClient = _clientRepository.findById(id);

		if(oClient.isPresent()){
			Client client = oClient.orElseThrow();

			System.out.println("=======================addres Saved=======================");

			Address address1 = new Address("street "+id+3,"colony"+id+3,id.intValue()+10);
			client.getAddresses().add(address1);
			
			_clientRepository.save(client);

			

			Optional<Client> oFindClient = _clientRepository.findById(id);

			oFindClient.ifPresent(c->{
				Address removeAddress = c.getAddresses().stream().
				filter(a -> 
				a.getStreet_avenue().equals(address1.getStreet_avenue()) && 
				a.getColony().equals(address1.getColony()) &&
				a.getNumber().equals(address1.getNumber()))
				.findFirst()
				.orElse(null);
				
				System.out.println("=======================address Deleted=======================");
				c.getAddresses().remove(removeAddress);
				System.out.println(address1.toString());
				_clientRepository.save(c);
				System.out.println(c);
			});

			scanner.close();

		}
	}

	@Transactional
	public void oneToManyAddreses(Long id){

		Optional<Client> oClient = _clientRepository.findById(id);

		if(oClient.isPresent()){

			System.out.println("=============One To many client addresses=============");

			Client client = oClient.orElseThrow(null);

			

			try{
				Address address = new Address("street"+id, "colony"+id, id.intValue()+1);
				System.out.println("addres created");
				client.getAddresses().add(address);
				_clientRepository.save(client);

				Address address1 = new Address("street"+(id+1), "colony"+(id+1), id.intValue()+2);

				client.setAddresses(Arrays.asList(address, address1));
				_clientRepository.save(client);

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
