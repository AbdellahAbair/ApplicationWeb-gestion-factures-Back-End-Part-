package com.firsttest.autho.service;

import com.firsttest.autho.dao.FacturesDupliquerRepository;
import com.firsttest.autho.entities.Facture;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
@Data
@NoArgsConstructor
public class FacturesDuplicata {
   @Autowired
    private FacturesDupliquerRepository f;

   public List<Facture> search_factures_exp(String code_exp)
   {
       List<Facture>liste_facture=new ArrayList<Facture>();
       f.findByCodeExp(code_exp).forEach(facture->{
liste_facture.add(facture);

       });

      return liste_facture;
   }
    public List<Facture> search_factures_clt(String code_clt)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeClt(code_clt).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_date(String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByDateFac(date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_num(int num_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByNumFac(num_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_exp_clt(String code_exp,String code_clt)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndCodeClt(code_exp,code_clt).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_exp_clt_date(String code_exp,String code_clt,String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndCodeCltAndDateFac(code_exp,code_clt,date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_exp_clt_numfac_date(String code_exp,String code_clt,int num_fac,String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndCodeCltAndNumFacAndDateFac(code_exp,code_clt,num_fac,date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }

    public List<Facture> search_factures_exp_clt_numfac(String code_exp,String code_clt,int num_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndCodeCltAndNumFac(code_exp,code_clt,num_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_exp_clt_datefac(String code_exp,String code_clt,String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndCodeCltAndDateFac(code_exp,code_clt,date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_exp_numfac_date(String code_exp,int num_fac,String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByCodeExpAndNumFacAndDateFac(code_exp,num_fac,date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public List<Facture> search_factures_numfac_date_clt(int num_fac,String code_clt,String date_fac)
    {
        List<Facture>liste_facture=new ArrayList<Facture>();
        f.findByNumFacAndCodeCltAndDateFac(num_fac,code_clt,date_fac).forEach(facture->{
            liste_facture.add(facture);

        });

        return liste_facture;
    }
    public void saveFactures(Facture facture)
    {
        this.f.save(facture);
    }
    public void readfactures(String path)
    {
        Facture dupliquer=new Facture();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            List<List<String>> values = lines.map(line -> Arrays.asList(line.split(";"))).collect(Collectors.toList());
            values.forEach(value -> {
                if(!value.get(0).equals("code_exp")) {
                   // System.out.println(value.get(0) + "\t" + value.get(1) + "\t" + value.get(2)+"\t"+ value.get(3)+"\t"+value.get(4)+"\t"+value.get(5));
                   dupliquer.setId(null);
                    dupliquer.setCodeExp(value.get(0));
                  dupliquer.setCodeClt(value.get(1));
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date = formatter.parse(value.get(2));
                        SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date1=simpleFormat.format(date);
                       dupliquer.setDateFac(date1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dupliquer.setNumFac(Integer.parseInt(value.get(3).toString()));
                    dupliquer.setMntTtc(Float.parseFloat(value.get(4).toString()+"f"));
                    dupliquer.setMntHt(Float.parseFloat(value.get(5).toString()+"f"));
                    System.out.println(dupliquer);
                    this.saveFactures(dupliquer);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteFactures()
    {
        this.f.deleteAll();
    }
}
