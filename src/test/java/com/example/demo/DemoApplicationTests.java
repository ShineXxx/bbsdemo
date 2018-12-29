package com.example.demo;

import com.example.demo.bean.Tz;
import com.example.demo.mapper.Tzmapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.service.Tzservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	DataSource dataSource;
	@Autowired
	Usermapper usermapper;
	@Autowired
	Tzmapper tzmapper;
	@Autowired
    Tzservice tzservice;
	@Test
	public void contextLoads() throws SQLException {
//		System.out.println("adfasdfasdfasdf"+dataSource.getClass());
//		System.out.println(usermapper.getUserByName("shine"));
//        Tz tz=new Tz();
//        tz=tzmapper.getTzByid(1);
//        System.out.println(tz.getUser().getName());
//        System.out.println(new Random().nextInt(Integer.MAX_VALUE));
		/*List<Tz> list=tzmapper.searchByTitle("%的%");
		for (Iterator<Tz> tzIterator=list.iterator();tzIterator.hasNext();){
            System.out.println(tzIterator.next().getTitle());
        }*/
/*		Tz tz=new Tz();
		tz.setTzid(31);
		tz.setTitle("changetitle");
		tz.setText("changetext");
		tz.setPhoto("/imges/1.png");
        System.out.println(tzmapper.updateTz(tz));*/
        tzmapper.delTzByid(20);
	}

}

