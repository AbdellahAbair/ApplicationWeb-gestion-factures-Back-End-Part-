package com.firsttest.autho;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import com.firsttest.autho.dao.FacturesDupliquerRepository;
import com.firsttest.autho.dao.UtilisateurRepositories;
import com.firsttest.autho.entities.*;
import com.firsttest.autho.service.AccountService;
import com.firsttest.autho.service.FacturesDuplicata;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class AuthoApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthoApplication.class, args);
	}
	@Autowired
	FacturesDupliquerRepository f;
	@Bean
	CommandLineRunner start(AccountService accountService,FacturesDuplicata fact){
		return args->{
			fact.deleteFactures();
            accountService.deleteUser();
			accountService.save(new AppRole(null,"USER"));
			accountService.save(new AppRole(null,"ADMIN"));
			Stream.of("user1","user2","user3","Abdellah").forEach(un->{
				accountService.saveUser(un,"1234","1234");
			});
			accountService.addRoleToUser("Abdellah","ADMIN");
            accountService.updateuser(false,"user2");
            accountService.loadusers();
            System.out.println("");
			String fileName = "aba.csv";
			CentreList centre=new CentreList();
			try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
				List<List<String>> values = lines.map(line -> Arrays.asList(line.split(";"))).collect(Collectors.toList());
				values.forEach(value -> {
					if(!value.get(0).equals("Name Site")) {
						//System.out.println(value.get(0) + "\t" + value.get(1) + "\t" + value.get(2));
						centre.setNom(value.get(0));
						centre.setIp(value.get(1));
						centre.setServeur(value.get(2));
						centre.setId(null);
						accountService.savecentre(centre);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

			fact.readfactures("out_1101.csv");
			fact.readfactures("out_1201.csv");
             System.out.println(accountService.loadUserByUsername("Abdellah"));
             System.out.println(accountService.listesroles(false));
			//FacturesDupliquer factures=new FacturesDupliquer("3501", "2", "01/05/1998", 2,88.2f,11.0f);
            //f.deleteAll();
			//f.save(factures);
			//fact.deleteFactures();
			//fact.saveFactures(factures);
			/*fact.search_factures("5601").forEach(data->{

System.out.println(data);
			});
			/*Date date1 = new Date();
			SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date;
			date=simpleFormat.format(date1);
			System.out.println(simpleFormat.format(date1));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate currentdate = LocalDate.now();
			String end=dtf.format(currentdate);

			SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
			Date EndConfinement = sdformat.parse("20/04/2020"); //End Confinement Inchallah is : 20/04/2020
			Date virusdateactive= sdformat.parse(end);
			System.out.println("The date CoronaVirus still active is: " + sdformat.format(virusdateactive));
			System.out.println("The date End Confinement is: " + sdformat.format(EndConfinement));

		
*/
//read file .txt
			try {
				File myObj = new File("fileTest.txt");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					System.out.println(data);
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

	//read file .pdf
			try (PDDocument document = PDDocument.load(new File("CV.pdf"))) {

				document.getClass();

				if (!document.isEncrypted()) {

					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition(true);

					PDFTextStripper tStripper = new PDFTextStripper();

					String pdfFileInText = tStripper.getText(document);
					//System.out.println("Text:" + st);

					// split by whitespace
					String lines[] = pdfFileInText.split("\\r?\\n");
					for (String line : lines) {
						System.out.println(line);
					}

				}

			}



		};

	}

}
